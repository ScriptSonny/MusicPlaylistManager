package search;

import song.SongContainer;

import java.lang.reflect.InvocationTargetException;

public class BinarySearch implements SearchMethod
{
    @Override
    public <T extends SongContainer> T search(String query, T songs) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException
    {
        T validSongs = (T) songs.getClass().getDeclaredConstructor().newInstance();
        return validSongs;
    }
}
