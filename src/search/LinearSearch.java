package search;

import collection.doublylinkedlist.DoublyLinkedList;
import song.SearchResult;
import song.querycomparator.QueryComparator;

import java.util.Collection;

public class LinearSearch<T extends Comparable<T>> implements SearchMethod<T> {
    @Override
    public SearchResult search(String query, Collection<T> songs, QueryComparator<T> comparator) {
        SearchResult validSongs = new SearchResult(new DoublyLinkedList<>());
        if (songs == null) {
            return validSongs;
        }
        if (query.length() == 0) {
            validSongs.appendSongs(songs);
            return validSongs;
        }
        for (T song : songs) {
            if (comparator.compare(song, query)) {
                validSongs.appendSong(song);
            }
        }
        return validSongs;
    }
}
