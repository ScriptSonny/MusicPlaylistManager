package search;

import song.SearchResult;
import song.querycomparator.QueryComparator;

import java.util.Collection;

/**
 * This interface defines a method for searching songs in a collection.
 * @param <T> the type of the song
 */
public interface SearchMethod<T extends Comparable<T>> {
    SearchResult<T> search(String query, Collection<T> songs, QueryComparator<T> comparator);
}
