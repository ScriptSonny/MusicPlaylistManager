package search;

import collection.hashmap.HashMap;
import song.SearchResult;
import song.querycomparator.QueryComparator;

import java.util.Collection;

public class HashMapSearch <T extends Comparable<T>> implements SearchMethod <T>
{
    @Override
    public SearchResult search(String query, Collection<T> songs, QueryComparator<T> comparator)
    {
        SearchResult validSongs = new SearchResult();
        if (songs == null)
        {
            return validSongs;
        }
        if (query.length() == 0)
        {
            validSongs.appendSongs(songs);
            return validSongs;
        }
        T[] values = (T[]) songs.toArray(new Object[0]);
        String[] keys = new String[values.length];
        for (int i = 0; i < values.length; i++)
        {
            keys[i] = comparator.getComparable(values[i]);;
        }
        HashMap<String, T> map = new HashMap<>(keys, values);
        validSongs.appendSong(map.get(query));
        return validSongs;
    }
}
