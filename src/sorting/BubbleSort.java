package sorting;

import song.Song;
import song.SortResult;
import collection.doublylinkedlist.DoublyLinkedList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BubbleSort implements SortingMethod {
    /**
     * Sort a collection of songs using the BubbleSort algorithm.
     * @param songs - The collection of songs to be sorted.
     * @return - Returns a SortResult containing the sorted list of songs.
     */
    @Override
    public SortResult sort(Collection<Song> songs) {
        if (songs == null || songs.isEmpty()) {
            return new SortResult();
        }

        List<Song> songList = new ArrayList<>();
        for (Song song : songs) {
            songList.add(song);
        }

        bubbleSort(songList);

        DoublyLinkedList<Song> sortedList = new DoublyLinkedList<>();
        sortedList.addAll(songList);

        SortResult sortedResult = new SortResult();
        sortedResult.setSongs(sortedList);

        return sortedResult;
    }

    /**
     * Performs BubbleSort on the given list of songs.
     * @param list - The list of songs to be sorted.
     */
    private void bubbleSort(List<Song> list) {
        int n = list.size();
        boolean swapped;
        do {
            swapped = false;
            for (int i = 0; i < n - 1; i++) {
                if (list.get(i).compareTo(list.get(i + 1)) > 0) {
                    swap(list, i, i + 1);
                    swapped = true;
                }
            }
            n--;
        } while (swapped);
    }

    /**
     * Swaps to elements in the list.
     * @param list - The list of songs.
     * @param i - The index of the first element.
     * @param j - The index of the second element.
     */
    private void swap(List<Song> list, int i, int j) {
        Song temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }
}