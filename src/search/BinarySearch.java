package search;

import artist.Artist;
import collection.binarysearchtree.BinarySearchTree;
import collection.binarysearchtree.Node;
import song.SearchResult;
import song.Song;
import utils.StringQueryComparator;

import java.util.Collection;

public class BinarySearch implements SearchMethod {
    @Override
    public <T extends Collection<Song>> SearchResult search(String query, T songs) {
        SearchResult validSongs = new SearchResult();

        if (!(songs instanceof BinarySearchTree<?>)) {
            throw new IllegalArgumentException("Song parameter has to be BinarySearchTree");
        }
        if (query == null || query.isEmpty()) {
            return validSongs;
        }

        BinarySearchTree<Song> bst = (BinarySearchTree<Song>) songs;

        searchRecursive(bst.getRoot(), query, validSongs);

        return validSongs;
    }

    private void searchRecursive(Node<Song> node, String query, SearchResult results) {
        if (node == null) {
            return;
        }

        Song song = node.getData();
        if (StringQueryComparator.compare(song.getTitle(), query) ||
                StringQueryComparator.compare(song.getArtist().getName(), query)) {
            results.appendSong(song);
        }

        searchRecursive(node.getLeft(), query, results);
        searchRecursive(node.getRight(), query, results);
    }
}