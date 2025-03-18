package search;

import song.SearchResult;
import song.Song;

import java.util.Collection;

public interface SearchMethod
{
    public abstract <T extends Collection<Song>> SearchResult search(String query, T songs);
}
