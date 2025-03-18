package gui;

import collection.BinarySearchTree;
import collection.doublylinkedlist.DoublyLinkedList;
import search.BinarySearch;
import search.LinearSearch;
import search.SearchMethod;
import song.*;
import sorting.BubbleSort;
import sorting.MergeSort;
import sorting.QuickSort;
import sorting.SortingMethod;
import utils.DataImporter;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.io.File;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class MusicPlaylistGUI extends JFrame
{
    private JButton loadButton, sortButton, shuffleButton, playButton, searchButton;
    private JTextField searchField;
    private JList<String> playlistDisplay;
    private DefaultListModel<String> playlistModel;
    private Collection<Song> selectedDataStructure;

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

        // Control panel
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(2, 3, 10, 10));

        loadButton = new JButton("📂 Load Playlist");
        sortButton = new JButton("🔀 Sort");
        shuffleButton = new JButton("🎲 Shuffle");
        playButton = new JButton("▶ Play");
        searchField = new JTextField(10);
        searchButton = new JButton("🔍 Search");

        controlPanel.add(loadButton);
        controlPanel.add(sortButton);
        controlPanel.add(shuffleButton);
        controlPanel.add(playButton);
        controlPanel.add(searchField);
        controlPanel.add(searchButton);

        add(controlPanel, BorderLayout.SOUTH);

        loadButton.addActionListener(e -> selectDataStructureAndLoadFile());
        searchButton.addActionListener(e -> searchFromData(searchField.getText()));
        sortButton.addActionListener(e -> sortPlaylist());
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
            JOptionPane.showMessageDialog(this, "Selected file: " + selectedFile.getName());

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
        SearchMethod method = null;
        String[] dataStructures = {"Linear Search", "Binary Search"};
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
                break;
            case "Binary Search":
                method = new BinarySearch();
                break;
            default:
                JOptionPane.showMessageDialog(this, "Invalid Choice!");
                return;
        }
        
        SearchResult results = SongDispenser.getInstance().search(query, method);
        updateGUI(results.getSongs());
    }

    /**
     * Method to sort all songs in a Playlist based on user choice.
     */
    private void sortPlaylist() {
        String[] sortingMethods = {"QuickSort", "MergeSort", "BubbleSort"};
        String choice = (String) JOptionPane.showInputDialog(
                this,
                "Select a sorting method:",
                "Choose Sorting Method",
                JOptionPane.QUESTION_MESSAGE,
                null,
                sortingMethods,
                sortingMethods[0]);

        if (choice == null) {
            return;
        }

        SortingMethod sortingMethod;

        switch (choice) {
            case "QuickSort":
                sortingMethod = new QuickSort();
                break;
            case "MergeSort":
                sortingMethod = new MergeSort();
                break;
            case "BubbleSort":
                sortingMethod = new BubbleSort();
                break;
            default:
                JOptionPane.showMessageDialog(this, "⚠ Invalid choice!");
                return;
        }

        SortResult sortedResult = SongDispenser.getInstance().sort(sortingMethod);

        if (!sortedResult.getSongs().isEmpty()) {
            updateGUI(sortedResult.getSongs());
            JOptionPane.showMessageDialog(this, "Playlist sorted using " + choice + "!");
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

        Iterator<Song> iterator = songs.iterator();

        while (iterator.hasNext())
        {
            playlistModel.addElement(iterator.next().toString());
        }
    }

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(() -> {
            MusicPlaylistGUI gui = new MusicPlaylistGUI();
            gui.setVisible(true);
        });
    }
}
