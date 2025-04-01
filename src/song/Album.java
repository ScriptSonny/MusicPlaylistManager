package song;

import java.util.Collection;

public class Album<T extends Comparable<T>> extends SongContainer<T> {
    public Album(Collection<T> songs) {
        super(songs);
    }
}
