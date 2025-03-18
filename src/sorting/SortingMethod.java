package sorting;

import song.Song;
import song.SortResult;

import java.util.Collection;

public interface SortingMethod {
    public abstract SortResult sort(Collection<Song> songs);
}