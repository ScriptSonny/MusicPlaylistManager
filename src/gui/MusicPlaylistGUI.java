package gui;

import collection.binarysearchtree.BinarySearchTree;
import collection.doublylinkedlist.DoublyLinkedList;
import search.BinarySearch;
import search.HashMapSearch;
import search.LinearSearch;
import search.SearchMethod;
import song.*;
import sorting.BubbleSort;
import sorting.MergeSort;
import sorting.QuickSort;
import sorting.SortingMethod;
import utils.DataImporter;

import javax.swing.*;
import javax.swing.Timer;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class MusicPlaylistGUI extends JFrame
{
    private JButton loadButton, sortButton, playButton, searchButton;
    private JTextField searchField;
    private JList<String> playlistDisplay;
    private DefaultListModel<String> playlistModel;
    private Collection<Song> selectedDataStructure;
    private JLabel nowPlayingLabel;
    private Timer playTimer;
    private boolean isPaused = false;
    private AtomicInteger index = new AtomicInteger(0); // Track song index
    private AtomicInteger remainingDuration = new AtomicInteger(0); // Track remaining duration
    private boolean playingSong = false;

    public MusicPlaylistGUI()
    {
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

        // Statuslabel to display current number
        nowPlayingLabel = new JLabel("Now Playing: None");
        nowPlayingLabel.setFont(new Font("Arial", Font.BOLD, 14));
        add(nowPlayingLabel, BorderLayout.NORTH);

        // Control panel
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(2, 3, 10, 10));

        loadButton = new JButton("📂 Load Playlist");
        sortButton = new JButton("🔀 Sort");
        playButton = new JButton("▶ Play");
        searchField = new JTextField(10);
        searchButton = new JButton("🔍 Search");

        controlPanel.add(loadButton);
        controlPanel.add(sortButton);
        controlPanel.add(playButton);
        controlPanel.add(searchField);
        controlPanel.add(searchButton);

        add(controlPanel, BorderLayout.SOUTH);

        loadButton.addActionListener(e -> selectDataStructureAndLoadFile());
        searchButton.addActionListener(e -> searchFromData(searchField.getText()));
        playButton.addActionListener(e -> playSongs());
        sortButton.addActionListener(e -> sortPlaylist());

        playlistDisplay.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                if (e.getClickCount() == 2) // double click
                {
                    int index = playlistDisplay.locationToIndex(e.getPoint());
                    if (index != -1)
                    {
                        String selectedSongText = playlistModel.get(index);
                        Song selectedSong = findSongByText(selectedSongText);
                        if (selectedSong != null)
                        {
                            playSpecificSong(selectedSong, index);
                        }
                    }
                }
            }
        });
    }

    /**
     * First opens a dropdown to select a datastructure.
     * Then opens filechooser to select a dataset
     */
    private void selectDataStructureAndLoadFile()
    {
        String[] dataStructures = {"Doubly Linked List", "Binary Search Tree (BST)", "HashSet"};
        String choice = (String) JOptionPane.showInputDialog(
                this,
                "Select a datastructure:",
                "Choose Datastructure",
                JOptionPane.QUESTION_MESSAGE,
                null,
                dataStructures,
                dataStructures[0]);

        if (choice == null)
        {
            return; // No choice made, stop
        }

        // Initialise chose datastructure
        switch (choice)
        {
            case "Doubly Linked List":
                selectedDataStructure = new DoublyLinkedList<>();
                break;
            case "Binary Search Tree (BST)":
                selectedDataStructure = new BinarySearchTree<>();
                break;
            case "HashSet":
                selectedDataStructure = new HashSet<>();
                break;
            default:
                JOptionPane.showMessageDialog(this, "Invalid Choice!");
                return;
        }

        // Choose file now
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select dataset (CSV");
        fileChooser.addChoosableFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f)
            {
                return f.isFile() && f.getName().toLowerCase().endsWith("csv");
            }
            
            @Override
            public String getDescription()
            {
                return "CSV files";
            }
        });
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION)
        {
            File selectedFile = fileChooser.getSelectedFile();
            loadDataFromFile(selectedFile);
        }
    }

    /**
     * Loads the songs from the dataset and puts them in songs list.
     * @param file - the selected dataset file.
     */
    private void loadDataFromFile(File file)
    {
        List<Song> songs = DataImporter.loadSongsFromCSV(file);

        if (songs.isEmpty())
        {
            JOptionPane.showMessageDialog(this, "⚠ No valid data found in file!");
            return;
        }

        if (selectedDataStructure != null)
        {
            selectedDataStructure.addAll(songs);
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Invalid data structure selected!");
            return;
        }
        
        updateGUI(songs);
        SongDispenser.getInstance().setSongContainer(new Playlist(songs));

        JOptionPane.showMessageDialog(this, "🎧 Dataset loaded from: " + file.getName());
    }
    
    /**
     * Opens a dialogue to choose a search method.
     * Then renders the found songs using the given query.
     * @param query - search query.
     */
    private void searchFromData(String query)
    {
        if (selectedDataStructure == null || selectedDataStructure.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No songs available for search!");
            return;
        }

        SearchMethod method;
        String timeComplexity = "";
        String[] dataStructures = {"Linear Search", "Binary Search", "HashMap Search"};
        String choice = (String) JOptionPane.showInputDialog(
                this,
                "Select a search method:",
                "Choose Search Method",
                JOptionPane.QUESTION_MESSAGE,
                null,
                dataStructures,
                dataStructures[0]);
        
        if (choice == null)
        {
            return; // No choice made, stop
        }
        
        // Initialise chose search method
        switch (choice)
        {
            case "Linear Search":
                method = new LinearSearch();
                timeComplexity = "O(n) - Worst case";
                break;
            case "Binary Search":
                method = new BinarySearch();
                timeComplexity = "O(log n)";
                break;
            case "HashMap Search":
                method = new HashMapSearch();
                break;
            default:
                JOptionPane.showMessageDialog(this, "Invalid Choice!");
                return;
        }

        int totalSongs = selectedDataStructure.size();

        // Measure execution time for search
        SearchResult[] results = new SearchResult[1];
        long timeTaken = measureExecutionTime(() -> results[0] = SongDispenser.getInstance().search(query, method));

        int foundSongs = results[0].getSongs().size();
        updateGUI(results[0].getSongs());

        JOptionPane.showMessageDialog(this,
                "🔍 Found " + foundSongs + " out of " + totalSongs + " songs.\n" +
                        "⏳ Search took: " + timeTaken + " ms.\n" +
                        "🕒 Time Complexity: " + timeComplexity + ".");
    }

    /**
     * Method to sort all songs in a Playlist based on user choice.
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
            case "QuickSort":
                sortingMethod = new QuickSort<>();
                timeComplexity = "Average: O(n log n), Worst: O(n²)";
                break;
            case "MergeSort":
                sortingMethod = new MergeSort<>();
                timeComplexity = "O(n log n)";
                break;
            case "BubbleSort":
                sortingMethod = new BubbleSort<>();
                timeComplexity = "O(n²) (Worst case)";
                break;
            default:
                JOptionPane.showMessageDialog(this, "⚠ Invalid choice!");
                return;
        }

        String[] comparatorOptions = {"Title", "Artist", "Year", "Popularity"};
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
            case "Year" -> SongComparators.BY_DURATION;
            case "Popularity" -> SongComparators.BY_POPULARITY;
            default -> SongComparators.BY_TITLE;
        };

        // Measure sorting execution time
        AtomicReference<SortResult<Song>> sortedResult = new AtomicReference<>();
        long timeTaken = measureExecutionTime(() -> {
            sortedResult.set(SongDispenser.getInstance().sort(sortingMethod, comparator));
        });

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
     * @param songs - Collection of songs to render.
     */
    private void updateGUI(Collection<Song> songs)
    {
        playlistModel.clear();

        if (songs.isEmpty())
        {
            JOptionPane.showMessageDialog(this, "⚠ No songs to render!");
            return;
        }

        for (Song song : songs) {
            playlistModel.addElement(song.toString());
        }
    }

    /**
     * Simulates the playing of songs in displayed order
     */
    private void playSongs() {
        if (playlistModel.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No songs to play!");
            return;
        }

        // If already playing, toggle pause
        if (playTimer != null && playTimer.isRunning()) {
            playTimer.stop();
            isPaused = true;
            playButton.setText("⏸ Paused");
            nowPlayingLabel.setFont(new Font("Segoe UI Emoji", Font.BOLD, 14));
            nowPlayingLabel.setText("Paused: " + playlistModel.get(index.get()) + " (" + remainingDuration.get() + "s left)");
            return;
        }

        isPaused = false;
        playButton.setText("▶ Play");

        playTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!playingSong) {
                    // Start next song if no song is currently playing
                    if (index.get() >= playlistModel.getSize()) {
                        playTimer.stop();
                        nowPlayingLabel.setText("Playlist finished!");
                        return;
                    }

                    String songText = playlistModel.get(index.get());
                    Song currentSong = findSongByText(songText);

                    if (currentSong != null) {
                        remainingDuration.set(remainingDuration.get() == 0 ? currentSong.getDuration() : remainingDuration.get());
                        nowPlayingLabel.setFont(new Font("Segoe UI Emoji", Font.BOLD, 14));
                        nowPlayingLabel.setText("Now Playing: " + currentSong.getTitle() + " (" + remainingDuration.get() + "s left)");
                        playingSong = true;
                    }
                } else {
                    // Countdown and update display
                    remainingDuration.getAndDecrement();
                    if (remainingDuration.get() <= 0) {
                        playingSong = false;
                        remainingDuration.set(0);
                        index.incrementAndGet(); // Move to next song
                    } else {
                        nowPlayingLabel.setFont(new Font("Segoe UI Emoji", Font.BOLD, 14));
                        nowPlayingLabel.setText("Now Playing: " + playlistModel.get(index.get()) + " (" + remainingDuration.get() + "s left)");
                    }
                }
            }
        });

        playTimer.setInitialDelay(0);
        playTimer.start();
    }

    /**
     * Searches Song-object that equals with the text in the list
     * @param songText text to search for
     * @return found Song object
     */
    private Song findSongByText(String songText)
    {
        if (selectedDataStructure == null) return null;

        for (Song song : selectedDataStructure)
        {
            if (song.toString().equals(songText))
            {
                return song;
            }
        }
        return null;
    }

    /**
     * Play a specific song at the given index.
     * @param song song to play
     * @param songIndex index of the song
     */
    private void playSpecificSong(Song song, int songIndex)
    {
        if (song == null)
        {
            JOptionPane.showMessageDialog(this, "Could not find selected song!");
            return;
        }

        // Stop current playing song (if necessary)
        if (playTimer != null && playTimer.isRunning())
        {
            playTimer.stop();
        }

        index.set(songIndex);
        remainingDuration.set(song.getDuration());
        playingSong = true;
        isPaused = false;
        playButton.setText("⏸ Pause");

        nowPlayingLabel.setFont(new Font("Segoe UI Emoji", Font.BOLD, 14));
        nowPlayingLabel.setText("Now Playing: 🎵 " + song.getTitle() + " (" + remainingDuration.get() + "s left)");

        playTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remainingDuration.getAndDecrement();
                if (remainingDuration.get() <= 0) {
                    playTimer.stop();
                    playingSong = false;
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
            }
        });

        playTimer.setInitialDelay(0);
        playTimer.start();
    }

    /**
     * measure execution time of algorithms
     * @param action what to measure
     * @return time in milliseconds
     */
    private long measureExecutionTime(Runnable action) {
        long startTime = System.nanoTime();
        action.run();
        return (System.nanoTime() - startTime) / 1_000_000;
    }

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(() -> {
            MusicPlaylistGUI gui = new MusicPlaylistGUI();
            gui.setVisible(true);
        });
    }
}
