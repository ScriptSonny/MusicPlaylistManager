package song;

import java.util.Collection;

/**
 * SearchResult class that extends SongContainer.
 * @param <T> the type of songs in the search result, which must be comparable
 */
public class SearchResult<T extends Comparable<T>> extends SongContainer<T> {
    public SearchResult(Collection<T> songs) {
        super(songs);
    }
}
