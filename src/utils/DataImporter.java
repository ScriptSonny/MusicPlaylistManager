package utils;

import artist.Artist;
import song.Genre;
import song.Song;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataImporter {
    /**
     * Load songs from a CSV file.
     * @param file - The CSV file containing song data.
     * @return - A list of Song objects.
     */
    public static List<Song> loadSongsFromCSV(File file) {
        List<Song> songs = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            br.readLine(); // skip header
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length == 5) {
                    try {
                        // Trim whitespace
                        String genreText = values[2].trim();
                        Genre genre = Genre.fromString(genreText);

                        // Make song object
                        Song song = new Song(
                                values[0].trim(),                  // Title
                                new Artist(values[1].trim()),      // Artist object
                                genre,                             // Genre enum
                                Integer.parseInt(values[3].trim()),// Duration
                                Integer.parseInt(values[4].trim()) // Popularity
                        );
                        songs.add(song);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid genre in CSV: " + values[2]);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return songs;
    }
}
