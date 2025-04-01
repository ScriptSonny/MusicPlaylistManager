package song;

import java.util.Collection;

public class SortResult<T extends Comparable<T>> extends SongContainer<T>
{
    public SortResult(Collection<T> songs)
    {
        super(songs);
    }
}
