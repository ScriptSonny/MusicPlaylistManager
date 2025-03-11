package gui;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import model.Song;
import java.util.ArrayList;
import java.util.List;

public class MusicPlaylistGUI extends JFrame
{
    private JButton loadButton, sortButton, shuffleButton, playButton, searchButton;
    private JTextField searchField;
    private JList<String> playlistDisplay;
    private DefaultListModel<String> playlistModel;

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

        // Add dummy data with play button
        loadButton.addActionListener(e -> selectAndLoadFile());
    }

    private void selectAndLoadFile()
    {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select dataset (CSV or JSON");
        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION)
        {
            File selectedFile = fileChooser.getSelectedFile();
            JOptionPane.showMessageDialog(this, "Selected file: " + selectedFile.getName());

            //TODO add import function
            loadDataFromFile(selectedFile);
        }
    }

    private void loadDataFromFile(File file)
    {
        // Simulation of importing the data
        playlistModel.clear();
        List<Song> songs = new ArrayList<>();

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
