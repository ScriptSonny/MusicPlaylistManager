package search;

import collection.doublylinkedlist.DoublyLinkedList;
import song.querycomparator.QueryComparator;

import java.util.Collection;

/**
 * This class implements the SearchMethod interface and provides a method to search for songs
 * @param <T> the type of the song
 */
public class LinearSearch<T extends Comparable<T>> implements SearchMethod<T> {
    @Override
    public SearchResult<T> search(String query, Collection<T> songs, QueryComparator<T> comparator) {
        SearchResult<T> validSongs = new SearchResult<T>(new DoublyLinkedList<>());
        if (songs == null) {
            return validSongs;
        }
        if (query.isEmpty()) {
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
