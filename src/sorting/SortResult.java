package sorting;

import song.songcontainer.SongContainer;

import java.util.Collection;

/**
 * Class representing the result of a sorting operation.
 * @param <T> the type of songs in the collection
 */
public class SortResult<T extends Comparable<T>> extends SongContainer<T> {
    public SortResult(Collection<T> songs) {
        super(songs);
    }
}
