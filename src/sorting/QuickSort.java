package sorting;

import song.Song;
import song.SortResult;
import collection.doublylinkedlist.DoublyLinkedList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public class QuickSort<T extends Comparable<T>> implements SortingMethod<T> {
    /**
     * Method to sort songs in a list.
     * @param songs - Songs to be sorted.
     * @return - Returns a list of sorted songs.
     */
    @Override
    public SortResult<T> sort(Collection<T> songs, Comparator<T> comparator) {
        if (songs == null || songs.isEmpty()) {
            return new SortResult<>();
        }

        List<T> songList = new ArrayList<>(songs);

        quickSort(songList, 0, songList.size() - 1, comparator);

        DoublyLinkedList<T> sortedList = new DoublyLinkedList<>();
        sortedList.addAll(songList);

        SortResult<T> sortedResult = new SortResult<>();
        sortedResult.setSongs(sortedList);

        return sortedResult;
    }

    /**
     * Sorts a list of songs using the QuickSort algorithm.
     * @param list - The list of songs to be sorted.
     * @param low - The starting index of the subarray to be sorted.
     * @param high - The ending index of the subarray to be sorted.
     */
    private void quickSort(List<T> list, int low, int high, Comparator<T> comparator) {
        if (low < high) {
            int pivotIndex = partition(list, low, high, comparator);
            quickSort(list, low, pivotIndex - 1, comparator);
            quickSort(list, pivotIndex + 1, high, comparator);
        }
    }

    /**
     * Partitions the list around a pivot element, placing smaller elements to the left and larger elements to the right.
     * @param list - The list of songs to be sorted.
     * @param low - The starting index of the subarray to be sorted.
     * @param high - The ending index of the subarray to be sorted.
     * @return - The index position of the pivot after partitioning.
     */
    private int partition(List<T> list, int low, int high, Comparator<T> comparator) {
        T pivot = list.get(high);
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (comparator.compare(list.get(j), pivot) <= 0) {
                i++;
                swap(list, i, j);
            }
        }

        swap(list, i + 1, high);
        return i + 1;
    }

    /**
     * Swaps to elements in the list.
     * @param list - The list of songs.
     * @param i - The index of the first element.
     * @param j - The index of the second element.
     */
    private void swap(List<T> list, int i, int j) {
        T temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }
}