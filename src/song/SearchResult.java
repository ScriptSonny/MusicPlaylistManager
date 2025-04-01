package song;

import java.util.Collection;

public class SearchResult<T extends Comparable<T>> extends SongContainer<T> {
    public SearchResult(Collection<T> songs) {
        super(songs);
    }
}
