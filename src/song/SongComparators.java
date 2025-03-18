package song;

import java.util.Comparator;

public class SongComparators {
    public static final Comparator<Song> BY_TITLE = Comparator.comparing(Song::getTitle);

    public static final Comparator<Song> BY_POPULARITY = (s1, s2) -> s2.getPopularity() - s1.getPopularity();
}