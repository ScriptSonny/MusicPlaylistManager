package search;

import song.SearchResult;
import song.Song;
import utils.StringQueryComparator;

import java.util.Collection;

public class LinearSearch implements SearchMethod
{
    @Override
    public <T extends Collection<Song>> SearchResult search(String query, T songs)
    {
        SearchResult validSongs = new SearchResult();
        if (songs == null)
        {
            return validSongs;
        }
        for (Song song : songs)
        {
            if (StringQueryComparator.compare(song.getTitle(), query) || StringQueryComparator.compare(song.getArtist().getName(), query))
            {
                validSongs.appendSong(song);
            }
        }
        return validSongs;
    }
}
