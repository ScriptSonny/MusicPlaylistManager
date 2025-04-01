package search;

import collection.doublylinkedlist.DoublyLinkedList;
import collection.hashmap.HashMap;
import song.SearchResult;
import song.querycomparator.QueryComparator;

import java.util.Collection;

public class HashMapSearch <T extends Comparable<T>> implements SearchMethod <T>
{
    @Override
    public SearchResult search(String query, Collection<T> songs, QueryComparator<T> comparator) {
        SearchResult validSongs = new SearchResult(new DoublyLinkedList<>());

        if (songs == null || query == null) {
            return validSongs;
        }

        HashMap<String, T> map = new HashMap<>();
        for (T song : songs) {
            String key = comparator.getComparable(song);
            if (key != null && key.contains(query)) {
                validSongs.appendSong(song);
            }
        }

        return validSongs;
    }
}
