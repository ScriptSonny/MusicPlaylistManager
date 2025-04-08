package gui;

import collection.binarysearchtree.BinarySearchTree;
import collection.doublylinkedlist.DoublyLinkedList;
import enums.DataStructureType;
import enums.PlayerState;
import search.*;
import song.*;
import song.querycomparator.ArtistQueryComparator;
import song.querycomparator.GenreQueryComparator;
import song.querycomparator.QueryComparator;
import song.querycomparator.TitleQueryComparator;
import song.songcontainer.Playlist;
import song.songcontainer.SongContainer;
import sorting.*;
import utils.DataImporter;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class MusicPlaylistGUI extends JFrame {
    private final JButton playButton;
    private final JTextField searchField;
    private final JList<String> playlistDisplay;
    private final DefaultListModel<String> playlistModel;
    private final JLabel nowPlayingLabel;
    private final AtomicInteger index = new AtomicInteger(0); // Track song index
    private final AtomicInteger remainingDuration = new AtomicInteger(0); // Track remaining duration
    private Timer playTimer;
    private PlayerState playerState = PlayerState.STOPPED;

    public MusicPlaylistGUI() {
        setTitle("Music Playlist Manager");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Display playlist
        playlistModel = new DefaultListModel<>();
        playlistDisplay = new JList<>(playlistModel);
        JScrollPane scrollPane = new JScrollPane(playlistDisplay);
        add(scrollPane, BorderLayout.CENTER);

        // Status label to display current number
        nowPlayingLabel = new JLabel("Now Playing: None");
        nowPlayingLabel.setFont(new Font("Arial", Font.BOLD, 14));
        add(nowPlayingLabel, BorderLayout.NORTH);

        // Control panel
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(2, 3, 10, 10));

        JButton loadButton = new JButton("📂 Load Playlist");
        JButton sortButton = new JButton("🔀 Sort");
        playButton = new JButton("▶ Play");
        searchField = new JTextField(10);
        JButton searchButton = new JButton("🔍 Search");

        controlPanel.add(loadButton);
        controlPanel.add(sortButton);
        controlPanel.add(playButton);
        controlPanel.add(searchField);
        controlPanel.add(searchButton);

        add(controlPanel, BorderLayout.SOUTH);

        loadButton.addActionListener(_e -> selectDataStructureAndLoadFile());
        searchButton.addActionListener(_e -> searchFromData(searchField.getText()));
        playButton.addActionListener(_e -> playSongs());
        sortButton.addActionListener(_e -> sortPlaylist());

        playlistDisplay.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) { // double click
                    int index = playlistDisplay.locationToIndex(e.getPoint());
                    if (index != -1) {
                        String selectedSongText = playlistModel.get(index);
                        Song selectedSong = findSongByText(selectedSongText);
                        if (selectedSong != null) {
                            playSpecificSong(selectedSong, index);
                        }
                    }
                }
            }
        });
    }

    /**
     * First opens a dropdown to select a data structure.
     * Then opens file chooser to select a dataset
     */
    private void selectDataStructureAndLoadFile() {
        DataStructureType choice = (DataStructureType) JOptionPane.showInputDialog(
                this,
                "Select a data structure",
                "Choose data structure",
                JOptionPane.QUESTION_MESSAGE,
                null,
                DataStructureType.values(),
                DataStructureType.DOUBLY_LINKED_LIST // default
        );

        if (choice == null) {
            return; // No choice made, stop
        }

        SongContainer<Song> container = switch (choice) {
            case DOUBLY_LINKED_LIST -> new Playlist<Song>(new DoublyLinkedList<>());
            case BINARY_SEARCH_TREE -> new Playlist<Song>(new BinarySearchTree<>());
            case HASH_SET -> new Playlist<>(new HashSet<Song>());
        };

        SongDispenser.getInstance().setSongContainer(container);

        // Choose file now
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select dataset (CSV");
        fileChooser.addChoosableFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                return f.isFile() && f.getName().toLowerCase().endsWith("csv");
            }

            @Override
            public String getDescription() {
                return "CSV files";
            }
        });
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            loadDataFromFile(selectedFile);
        }
    }

    /**
     * Loads the songs from the dataset and puts them in the SongDispenser.
     * Also updates the GUI to reflect the loaded songs.
     *
     * @param file - the selected dataset file.
     */
    private void loadDataFromFile(File file) {
        List<Song> songs = DataImporter.loadSongsFromCSV(file);

        if (songs.isEmpty()) {
            JOptionPane.showMessageDialog(this, "⚠ No valid data found in file!");
            return;
        }

        // Set the loaded songs into SongDispenser via a new Playlist
        Playlist<Song> container = new Playlist<>(songs);
        SongDispenser.getInstance().setSongContainer(container);

        updateGUI(songs);

        JOptionPane.showMessageDialog(this, "🎧 Dataset loaded from: " + file.getName());
    }

    /**
     * Opens a dialogue to choose a search method.
     * Then renders the found songs using the given query.
     *
     * @param query - search query.
     */
    private void searchFromData(String query) {
        SongContainer<Song> container = SongDispenser.getInstance().getSongContainer();
        if (container == null || container.getSongs().isEmpty()) {
            JOptionPane.showMessageDialog(this, "No songs available for search!");
            return;
        }

        if (query == null || query.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a search query.");
            return;
        }

        SearchMethod<Song> method;
        String timeComplexity;
        String[] methodOptions = {"Linear Search", "Binary Search", "HashMap Search"};
        String methodChoice = (String) JOptionPane.showInputDialog(
                this,
                "Select a search method:",
                "Choose Search Method",
                JOptionPane.QUESTION_MESSAGE,
                null,
                methodOptions,
                methodOptions[0]);

        if (methodChoice == null) return;

        switch (methodChoice) {
            case "Linear Search" -> {
                method = new LinearSearch<>();
                timeComplexity = "O(n) - Worst case";
            }
            case "Binary Search" -> {
                method = new BinarySearch<>();
                timeComplexity = "O(log n)";
            }
            case "HashMap Search" -> {
                method = new HashMapSearch<>();
                timeComplexity = "O(1) average, O(n) worst";
            }
            default -> {
                JOptionPane.showMessageDialog(this, "Invalid search method!");
                return;
            }
        }

        String[] fieldOptions = {"Title", "Artist", "Genre"};
        String fieldChoice = (String) JOptionPane.showInputDialog(
                this,
                "Select a field to search in:",
                "Choose Field",
                JOptionPane.QUESTION_MESSAGE,
                null,
                fieldOptions,
                fieldOptions[0]);

        if (fieldChoice == null) return;

        QueryComparator<Song> queryComparator;
        switch (fieldChoice) {
            case "Artist" -> queryComparator = new ArtistQueryComparator();
            case "Genre" -> queryComparator = new GenreQueryComparator();
            default -> queryComparator = new TitleQueryComparator();
        }

        AtomicReference<SearchResult<Song>> resultRef = new AtomicReference<>();

        long timeTaken = measureExecutionTime(() -> resultRef.set(SongDispenser.getInstance().search(query, method, queryComparator)));

        SearchResult<Song> result = resultRef.get();

        int foundSongs = result.getSongs().size();
        updateGUI(result.getSongs());

        JOptionPane.showMessageDialog(this,
                "🔍 Search Method: " + methodChoice + "\n" +
                        "🔍 Search Field: " + fieldChoice + "\n" +
                        "🔍 Found " + foundSongs + " out of " + container.getSongs().size() + " songs.\n" +
                        "⏳ Search took: " + timeTaken + " ms.\n" +
                        "🕒 Time Complexity: " + timeComplexity + ".");
    }

    /**
     * Opens a dialog to let the user select a sorting algorithm and comparator field,
     * then applies the selected sorting algorithm via the SongDispenser and updates the GUI.
     */
    private void sortPlaylist() {
        String[] sortingMethods = {"QuickSort", "MergeSort", "BubbleSort"};
        String methodChoice = (String) JOptionPane.showInputDialog(
                this,
                "Select a sorting method:",
                "Choose Sorting Method",
                JOptionPane.QUESTION_MESSAGE,
                null,
                sortingMethods,
                sortingMethods[0]);

        if (methodChoice == null) {
            return;
        }

        SortingMethod<Song> sortingMethod;
        String timeComplexity;
        
        switch (methodChoice) {
            case "QuickSort" -> {
                sortingMethod = new QuickSort<>();
                timeComplexity = "Average: O(n log n), Worst: O(n²)";
            }
            case "MergeSort" -> {
                sortingMethod = new MergeSort<>();
                timeComplexity = "O(n log n)";
            }
            case "BubbleSort" -> {
                sortingMethod = new BubbleSort<>();
                timeComplexity = "O(n²) (Worst case)";
            }
            default -> {
                JOptionPane.showMessageDialog(this, "⚠ Invalid choice!");
                return;
            }
        }

        String[] comparatorOptions = {"Title", "Artist", "Duration", "Popularity"};
        String comparatorChoice = (String) JOptionPane.showInputDialog(
                this,
                "Select a sort criterion:",
                "Choose Comparator",
                JOptionPane.QUESTION_MESSAGE,
                null,
                comparatorOptions,
                comparatorOptions[0]);

        if (comparatorChoice == null) {
            return;
        }

        Comparator<Song> comparator = switch (comparatorChoice) {
            case "Artist" -> SongComparators.BY_ARTIST;
            case "Duration" -> SongComparators.BY_DURATION;
            case "Popularity" -> SongComparators.BY_POPULARITY;
            default -> SongComparators.BY_TITLE;
        };

        // Measure sorting execution time
        AtomicReference<SortResult<Song>> sortedResult = new AtomicReference<>();
        long timeTaken = measureExecutionTime(() -> sortedResult.set(SongDispenser.getInstance().sort(sortingMethod, comparator)));

        if (!sortedResult.get().getSongs().isEmpty()) {
            updateGUI(sortedResult.get().getSongs());
            JOptionPane.showMessageDialog(this,
                    "✅ Playlist sorted using " + methodChoice + " by " + comparatorChoice + "!\n" +
                            "⏳ Sorting took: " + timeTaken + " ms\n" +
                            "🕒 Time Complexity: " + timeComplexity);
        } else {
            JOptionPane.showMessageDialog(this, "⚠ Error while sorting: No songs found!");
        }
    }

    /**
     * Clears the current view, then renders the given songs.
     *
     * @param songs - Collection of songs to render.
     */
    private void updateGUI(Collection<Song> songs) {
        playlistModel.clear();

        if (songs.isEmpty()) {
            JOptionPane.showMessageDialog(this, "⚠ No songs to render!");
            return;
        }

        for (Song song : songs) {
            playlistModel.addElement(song.toString());
        }
    }

    /**
     * Starts or resumes playback of the playlist.
     * If already playing, toggle pause.
     * Uses a Swing Timer to simulate playback in real-time
     */
    private void playSongs() {
        if (SongDispenser.getInstance().getSongContainer() == null) return;

        if (playlistModel.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No songs to play!");
            return;
        }

        // If already playing, toggle pause
        if (playerState == PlayerState.PLAYING) {
            playTimer.stop();
            playerState = PlayerState.PAUSED;
            playButton.setText("⏸ Paused");
            nowPlayingLabel.setFont(new Font("Segoe UI Emoji", Font.BOLD, 14));
            nowPlayingLabel.setText("Paused: " + playlistModel.get(index.get()) + " (" + remainingDuration.get() + "s left)");
            return;
        }

        playerState = PlayerState.PLAYING;
        playButton.setText("▶ Play");

        playTimer = new Timer(1000, _e -> {
            if (playerState != PlayerState.PLAYING) return;

            if (index.get() >= playlistModel.getSize()) {
                playTimer.stop();
                playerState = PlayerState.STOPPED;
                nowPlayingLabel.setText("Playlist finished!");
                return;
            }

            String songText = playlistModel.get(index.get());
            Song currentSong = findSongByText(songText);

            if (currentSong != null) {
                if (remainingDuration.get() == 0) {
                    remainingDuration.set(currentSong.getDuration());
                }

                nowPlayingLabel.setFont(new Font("Segoe UI Emoji", Font.BOLD, 14));
                nowPlayingLabel.setText("Now Playing: " + currentSong.getTitle() + " (" + remainingDuration.get() + "s left)");
                remainingDuration.getAndDecrement();

                if (remainingDuration.get() < 0) {
                    index.incrementAndGet();
                    remainingDuration.set(0);
                }
            }
        });

        playTimer.setInitialDelay(0);
        playTimer.start();
    }

    /**
     * Searches Song-object that equals with the text in the list
     *
     * @param songText text to search for
     * @return found Song object
     */
    private Song findSongByText(String songText) {
        if (SongDispenser.getInstance().getSongContainer() == null) return null;

        SongContainer<Song> container = SongDispenser.getInstance().getSongContainer();
        if (container == null || container.getSongs().isEmpty()) return null;

        for (Song song : container.getSongs()) {
            if (song.toString().equals(songText)) {
                return song;
            }
        }
        return null;
    }

    /**
     * Play a specific song at the given index.
     *
     * @param song      song to play
     * @param songIndex index of the song
     */
    private void playSpecificSong(Song song, int songIndex) {
        if (song == null) {
            JOptionPane.showMessageDialog(this, "Could not find selected song!");
            return;
        }

        // Stop current playing song (if necessary)
        if (playTimer != null && playTimer.isRunning()) {
            playTimer.stop();
        }

        index.set(songIndex);
        remainingDuration.set(song.getDuration());
        playerState = PlayerState.PLAYING;
        playButton.setText("⏸ Pause");

        nowPlayingLabel.setFont(new Font("Segoe UI Emoji", Font.BOLD, 14));
        nowPlayingLabel.setText("Now Playing: 🎵 " + song.getTitle() + " (" + remainingDuration.get() + "s left)");

        playTimer = new Timer(1000, _e -> {
            if (playerState != PlayerState.PLAYING) return;

            remainingDuration.getAndDecrement();
            if (remainingDuration.get() <= 0) {
                playTimer.stop();
                playerState = PlayerState.STOPPED;
                index.incrementAndGet();

                if (index.get() < playlistModel.size()) {
                    String nextSongText = playlistModel.get(index.get());
                    Song nextSong = findSongByText(nextSongText);
                    if (nextSong != null) {
                        playSpecificSong(nextSong, index.get());
                    }
                } else {
                    nowPlayingLabel.setText("Playlist finished!");
                }
            } else {
                nowPlayingLabel.setText("Now Playing: 🎵 " + song.getTitle() + " (" + remainingDuration.get() + "s left)");
            }
        });

        playTimer.setInitialDelay(0);
        playTimer.start();
    }

    /**
     * measure execution time of algorithms
     *
     * @param action what to measure
     * @return time in milliseconds
     */
    private long measureExecutionTime(Runnable action) {
        long startTime = System.nanoTime();
        action.run();
        return (System.nanoTime() - startTime) / 1_000_000;
    }
}
