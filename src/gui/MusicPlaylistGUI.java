package gui;

import javax.swing.*;
import java.awt.*;
import java.io.File;

import collection.BinarySearchTree;
import collection.DoublyLinkedList;
import song.Song;
import utils.DataImporter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
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
        playlistModel.clear();
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

        // Update GUI
        for (Song song : songs)
        {
            playlistModel.addElement(song.toString());
        }

        JOptionPane.showMessageDialog(this, "🎧 Dataset loaded from: " + file.getName());
    }

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(() -> {
            MusicPlaylistGUI gui = new MusicPlaylistGUI();
            gui.setVisible(true);
        });
    }
}
