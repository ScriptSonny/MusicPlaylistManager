package search;

import collection.binarysearchtree.BinarySearchTree;
import collection.binarysearchtree.Node;
import song.querycomparator.QueryComparator;

import java.util.Collection;

public class BinarySearch<T extends Comparable<T>> implements SearchMethod<T> {
    public SearchResult<T> search(String query, Collection<T> songs, QueryComparator<T> comparator) {
        SearchResult<T> validSongs = new SearchResult<T>(new BinarySearchTree<>());

        if (!(songs instanceof BinarySearchTree<?>)) {
            throw new IllegalArgumentException("Song parameter has to be BinarySearchTree");
        }
        if (query == null || query.isEmpty()) {
            return validSongs;
        }

        BinarySearchTree<T> bst = (BinarySearchTree<T>) songs;

        searchRecursive(bst.getRoot(), query, validSongs, comparator);

        return validSongs;
    }

    /**
     * Recursively searches the binary search tree for songs that match the query.
     * @param node the current node in the binary search tree
     * @param query the search query
     * @param results the search results to be populated
     * @param comparator the comparator used to compare songs with the query
     */
    private void searchRecursive(Node<T> node, String query, SearchResult<T> results, QueryComparator<T> comparator) {
        if (node == null) {
            return;
        }

        T song = node.getData();
        if (comparator.compare(song, query)) {
            results.appendSong(song);
        }

        searchRecursive(node.getLeft(), query, results, comparator);
        searchRecursive(node.getRight(), query, results, comparator);
    }
}