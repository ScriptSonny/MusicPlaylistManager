package song.querycomparator;

import song.Song;

public class ArtistQueryComparator extends QueryComparator<Song>
{
    @Override
    public String getComparable(Song from)
    {
        return from.getArtist().getName();
    }
}
