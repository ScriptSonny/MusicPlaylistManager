package sorting;

import collection.doublylinkedlist.DoublyLinkedList;
import song.SortResult;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public class BubbleSort<T extends Comparable<T>> implements SortingMethod<T> {
    /**
     * Sort a collection of songs using the BubbleSort algorithm.
     * @param songs - The collection of songs to be sorted.
     * @return - Returns a SortResult containing the sorted list of songs.
     */
    @Override
    public SortResult<T> sort(Collection<T> songs, Comparator<T> comparator) {
        if (songs == null || songs.isEmpty()) {
            return new SortResult<>(new DoublyLinkedList<T>());
        }

        List<T> songList = new ArrayList<>();
        for (T song : songs) {
            songList.add(song);
        }

        bubbleSort(songList, comparator);

        DoublyLinkedList<T> sortedList = new DoublyLinkedList<>();
        sortedList.addAll(songList);

        SortResult<T> sortedResult = new SortResult<>(new DoublyLinkedList<T>());
        sortedResult.setSongs(sortedList);

        return sortedResult;
    }

    /**
     * Performs BubbleSort on the given list of songs.
     * @param list - The list of songs to be sorted.
     */
    private void bubbleSort(List<T> list, Comparator<T> comparator) {
        int n = list.size();
        boolean swapped;
        do {
            swapped = false;
            for (int i = 0; i < n - 1; i++) {
                if (comparator.compare(list.get(i), list.get(i + 1)) > 0) {
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
     * @param i    - The index of the first element.
     * @param j    - The index of the second element.
     */
    private void swap(List<T> list, int i, int j) {
        T temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }
}