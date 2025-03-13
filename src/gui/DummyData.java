package gui;

import artist.Artist;
import collection.doublylinkedlist.DoublyLinkedList;
import song.Genre;
import song.Song;

public class DummyData {
    public static DoublyLinkedList<Song> getDummySongs() {
        DoublyLinkedList<Song> songs = new DoublyLinkedList<>();

        Artist artist1 = new Artist("Coldplay");
        Artist artist2 = new Artist("Adele");
        Artist artist3 = new Artist("Ed Sheeran");

        songs.add(new Song("Yellow", artist1, Genre.ROCK, 267, 85));
        songs.add(new Song("Rolling in the Deep", artist2, Genre.POP, 228, 92));
        songs.add(new Song("Shape of You", artist3, Genre.POP, 263, 98));

        return songs;
    }

    public static DoublyLinkedList<String> getDummyPlaylist() {
        DoublyLinkedList<String> playlist = new DoublyLinkedList<>();
        for (Song song : getDummySongs()) {
            playlist.add(song.toString());
        }
        return playlist;
    }
}