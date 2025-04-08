package song.querycomparator;

import song.Song;

/**
 * This class implements the QueryComparator interface and provides a method to compare songs by their artist.
 */
public class ArtistQueryComparator extends QueryComparator<Song> {
    @Override
    public String getComparable(Song from) {
        return from.getArtist().getName();
    }
}
