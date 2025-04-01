package sorting;

import song.SortResult;

import java.util.Collection;
import java.util.Comparator;

public interface SortingMethod<T extends Comparable<T>> {
    SortResult<T> sort(Collection<T> songs, Comparator<T> comparator);
}