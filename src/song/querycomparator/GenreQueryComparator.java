package song.querycomparator;

import song.Song;

/**
 * This class implements the QueryComparator interface and provides a method to compare songs by their genre.
 */
public class GenreQueryComparator extends QueryComparator<Song> {
    @Override
    public String getComparable(Song from) {
        return from.getGenre().name().toLowerCase();
    }
}