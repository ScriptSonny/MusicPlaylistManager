package utils;

import artist.Artist;
import song.Genre;
import song.Song;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataImporter
{
    // CSV Importfunction
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
                                values[0].trim(),                  // Titel
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


    // JSON importfunction
    public static List<Song> loadSongsFromJSON(File file)
    {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Song> songs = new ArrayList<>();
        try
        {
            // turn JSON into a list of song objects
            songs = objectMapper.readValue(file, new TypeReference<List<Song>>() {});
            for (Song song : songs)
            {
                // Check if artist and genre are correct
                song.setArtist(new Artist(song.getArtist().getName()));
                song.setGenre(Genre.valueOf(song.getGenre().name()));
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        } catch (IllegalArgumentException e)
        {
            System.out.println("Invalid Genre in JSON-file");
        }
        return songs;
    }
}
