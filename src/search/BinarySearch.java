package search;

import song.SearchResult;
import song.Song;
import song.SongContainer;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;

public class BinarySearch implements SearchMethod
{
    @Override
    public SearchResult search(String query, Collection<Song> songs)
    {
        SearchResult validSongs = new SearchResult();
        return validSongs;
    }
}
