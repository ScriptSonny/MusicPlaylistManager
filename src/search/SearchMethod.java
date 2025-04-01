package search;

import song.SearchResult;
import song.querycomparator.QueryComparator;

import java.util.Collection;

public interface SearchMethod <T extends Comparable<T>>
{
    public abstract SearchResult search(String query, Collection<T> songs, QueryComparator<T> comparator);
}
