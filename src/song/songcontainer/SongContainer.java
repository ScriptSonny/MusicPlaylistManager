package song.songcontainer;

import java.util.Collection;

public abstract class SongContainer<T extends Comparable<T>> {
    private Collection<T> songs;

    public SongContainer(Collection<T> songs) {
        this.songs = songs;
    }

    // Getters and setters
    public Collection<T> getSongs() {
        return this.songs;
    }

    public void setSongs(Collection<T> songs) {
        this.songs = songs;
    }

    /**
     * Adds a new song to the collection.
     * @param newSong the new song to add
     */
    public void appendSong(T newSong) {
        if (newSong == null) {
            return;
        }
        this.songs.add(newSong);
    }

    /**
     * Adds a collection of songs to the existing collection.
     * @param songs the collection of songs to add
     */
    public void appendSongs(Collection<T> songs) {
        this.songs.addAll(songs);
    }

    @Override
    public String toString() {
        return "SongContainer{" +
                "songs=" + this.songs.toString() +
                '}';
    }
}
