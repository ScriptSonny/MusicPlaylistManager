package sorting;

import song.SortResult;

import java.util.Collection;
import java.util.Comparator;

/**
 * Interface for sorting methods.
 * @param <T> - Type of the elements to be sorted.
 */
public interface SortingMethod<T extends Comparable<T>> {
    SortResult<T> sort(Collection<T> songs, Comparator<T> comparator);
}