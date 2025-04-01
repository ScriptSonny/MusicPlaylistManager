package song;

import java.util.Collection;

public class Playlist<T extends Comparable<T>> extends SongContainer<T> {
    public Playlist(Collection<T> songs) {
        super(songs);
    }
}
