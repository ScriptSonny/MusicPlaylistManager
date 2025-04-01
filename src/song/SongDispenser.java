package song;

import collection.binarysearchtree.BinarySearchTree;
import collection.doublylinkedlist.DoublyLinkedList;
import search.BinarySearch;
import search.SearchMethod;
import song.querycomparator.QueryComparator;
import sorting.SortingMethod;

import java.util.Comparator;

public class SongDispenser {
    private static SongDispenser instance;
    private SongContainer<Song> songContainer;

    private SongDispenser() {
        this.songContainer = new Album<Song>(new DoublyLinkedList<>());
    }

    public static SongDispenser getInstance() {
        if (SongDispenser.instance == null) {
            SongDispenser.instance = new SongDispenser();
        }
        return SongDispenser.instance;
    }

    // Getters and setters
    public SongContainer<Song> getSongContainer() {
        return songContainer;
    }

    public void setSongContainer(SongContainer<Song> songContainer) {
        this.songContainer = songContainer;
    }

    // Methods
    public SearchResult<Song> search(String query, SearchMethod<Song> method, QueryComparator<Song> comparator) {
        if (method instanceof BinarySearch) {
            BinarySearchTree<Song> bst = new BinarySearchTree<>();
            bst.addAll(songContainer.getSongs());
            return method.search(query, bst, comparator);
        } else {
            return method.search(query, songContainer.getSongs(), comparator);
        }
    }

    public SortResult<Song> sort(SortingMethod<Song> method, Comparator<Song> comparator) {
        return method.sort(songContainer.getSongs(), comparator);
    }
}