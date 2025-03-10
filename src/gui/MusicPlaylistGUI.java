package gui;

import javax.swing.*;
import java.awt.*;
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

        // Playlist weergave
        playlistModel = new DefaultListModel<>();
        playlistDisplay = new JList<>(playlistModel);
        JScrollPane scrollPane = new JScrollPane(playlistDisplay);
        add(scrollPane, BorderLayout.CENTER);

        // Control paneel
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(2, 3, 10, 10));

        loadButton = new JButton("📂 Laad Playlist");
        sortButton = new JButton("🔀 Sorteer");
        shuffleButton = new JButton("🎲 Shuffle");
        playButton = new JButton("▶ Afspelen");
        searchField = new JTextField(10);
        searchButton = new JButton("🔍 Zoek");

        controlPanel.add(loadButton);
        controlPanel.add(sortButton);
        controlPanel.add(shuffleButton);
        controlPanel.add(playButton);
        controlPanel.add(searchField);
        controlPanel.add(searchButton);

        add(controlPanel, BorderLayout.SOUTH);

        // Voeg dummydata toe met playlist knop
        loadButton.addActionListener(e ->
        {
            playlistModel.clear();
            List<String> songs = DummyData.getDummyPlaylist();
            for (String song : songs)
            {
                playlistModel.addElement(song);
            }
            JOptionPane.showMessageDialog(this, "🎧 Dummy Playlist geladen!");
        });
    }

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(() -> {
            MusicPlaylistGUI gui = new MusicPlaylistGUI();
            gui.setVisible(true);
        });
    }
}
