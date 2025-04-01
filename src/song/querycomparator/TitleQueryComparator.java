package song.querycomparator;

import song.Song;

public class TitleQueryComparator extends QueryComparator<Song>
{
    @Override
    public String getComparable(Song from)
    {
        return from.getTitle();
    }
}
