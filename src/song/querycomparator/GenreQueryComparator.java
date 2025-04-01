package song.querycomparator;

import song.Song;

public class GenreQueryComparator extends QueryComparator<Song> {
    @Override
    public String getComparable(Song from) {
        return from.getGenre().name().toLowerCase();
    }
}