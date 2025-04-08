package song.songcontainer;

import song.songcontainer.SongContainer;

import java.util.Collection;

/**
 * Playlist class that extends SongContainer.
 * @param <T> the type of songs in the playlist, which must be comparable
 */
public class Playlist<T extends Comparable<T>> extends SongContainer<T> {
    public Playlist(Collection<T> songs) {
        super(songs);
    }
}
