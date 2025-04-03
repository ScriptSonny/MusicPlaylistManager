package song.querycomparator;

import song.Song;

/**
 * This class implements the QueryComparator interface and provides a method to compare songs by their title.
 */
public class TitleQueryComparator extends QueryComparator<Song> {
    @Override
    public String getComparable(Song from) {
        return from.getTitle();
    }
}
