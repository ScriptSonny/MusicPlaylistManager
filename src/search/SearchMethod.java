package search;

import song.SearchResult;
import song.Song;
import song.SongContainer;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;

public interface SearchMethod
{
    public abstract SearchResult search(String query, Collection<Song> songs);
}
