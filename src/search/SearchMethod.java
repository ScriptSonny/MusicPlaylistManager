package search;

import song.SongContainer;

import java.lang.reflect.InvocationTargetException;

public interface SearchMethod
{
    public abstract <T extends SongContainer> T search(String query, T songs) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException;
}
