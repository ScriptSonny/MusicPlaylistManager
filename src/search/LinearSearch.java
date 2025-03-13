package search;

import song.Song;
import song.SongContainer;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;

public class LinearSearch implements SearchMethod
{
    @Override
    public <T extends SongContainer> T search(String query, T songs) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException
    {
        T validSongs = (T) songs.getClass().getDeclaredConstructor().newInstance();
        for (Song song : songs.getSongs())
        {
            if (song.getTitle().compareTo(query) > 0) {
                validSongs.appendSong(song);
            }
        }
        return validSongs;
    }
}
