package search;

import song.SearchResult;
import song.Song;
import song.SongContainer;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashSet;

public class LinearSearch implements SearchMethod
{
    @Override
    public SearchResult search(String query, Collection<Song> songs)
    {
        SearchResult validSongs = new SearchResult();
        for (Song song : songs)
        {
            if (song.getTitle().compareTo(query) > 0)
            {
                validSongs.appendSong(song);
            }
        }
        return validSongs;
    }
}
