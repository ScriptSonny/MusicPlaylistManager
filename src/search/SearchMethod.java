package search;

import song.SearchResult;
import song.querycomparator.QueryComparator;

import java.util.Collection;

public interface SearchMethod<T extends Comparable<T>> {
    SearchResult<T> search(String query, Collection<T> songs, QueryComparator<T> comparator);
}
