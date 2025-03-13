package sorting;

import song.Song;
import song.SongContainer;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface SortingMethod {
    public abstract <T extends SongContainer> T sort(T songs) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException;
}