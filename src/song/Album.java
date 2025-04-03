package song;

import java.util.Collection;

/**
 * This class represents an album of songs. It extends the SongContainer class and provides
 * @param <T> the type of the song
 */
public class Album<T extends Comparable<T>> extends SongContainer<T> {
    public Album(Collection<T> songs) {
        super(songs);
    }
}
