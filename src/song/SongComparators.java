package song;

import java.util.Comparator;

public class SongComparators {
    public static final Comparator<Song> BY_TITLE = Comparator.comparing(Song::getTitle);
    public static final Comparator<Song> BY_POPULARITY = (s1, s2) -> s2.getPopularity() - s1.getPopularity();
    public static final Comparator<Song> BY_ARTIST = Comparator.comparing(song -> song.getArtist().getName(), String.CASE_INSENSITIVE_ORDER);
    public static final Comparator<Song> BY_DURATION = Comparator.comparingInt(Song::getDuration);
}