package search;

import collection.hashmap.HashMap;
import song.SearchResult;
import song.Song;

import java.util.Collection;

public class HashMapSearch implements SearchMethod
{
    @Override
    public <T extends Collection<Song>> SearchResult search(String query, T songs)
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
        Song[] values = songs.toArray(new Song[0]);
        String[] keys = new String[values.length];
        for (int i = 0; i < values.length; i++)
        {
            keys[i] = values[i].getTitle();
        }
        HashMap<String, Song> map = new HashMap<>(keys, values);
        validSongs.appendSong((Song) (map.get(query)));
        return validSongs;
    }
}
