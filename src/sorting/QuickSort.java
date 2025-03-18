package sorting;

import song.Song;
import song.SongComparators;
import song.SortResult;
import collection.doublylinkedlist.DoublyLinkedList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class QuickSort implements SortingMethod {
    /**
     * Method to sort songs in a list.
     * @param songs - Songs to be sorted.
     * @return - Returns a list of sorted songs.
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

        quickSort(songList, 0, songList.size() - 1);

        DoublyLinkedList<Song> sortedList = new DoublyLinkedList<>();
        sortedList.addAll(songList);

        SortResult sortedResult = new SortResult();
        sortedResult.setSongs(sortedList);

        return sortedResult;
    }

    /**
     * Sorts a list of songs using the QuickSort algorithm.
     * @param list - The list of songs to be sorted.
     * @param low - The starting index of the subarray to be sorted.
     * @param high - The ending index of the subarray to be sorted.
     */
    private void quickSort(List<Song> list, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(list, low, high);
            quickSort(list, low, pivotIndex - 1);
            quickSort(list, pivotIndex + 1, high);
        }
    }

    /**
     * Partitions the list around a pivot element, placing smaller elements to the left and larger elements to the right.
     * @param list - The list of songs to be sorted.
     * @param low - The starting index of the subarray to be sorted.
     * @param high - The ending index of the subarray to be sorted.
     * @return - The index position of the pivot after partitioning.
     */
    private int partition(List<Song> list, int low, int high) {
        Song pivot = list.get(high);
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (SongComparators.BY_TITLE.compare(list.get(j), pivot) <= 0)
            {
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
    private void swap(List<Song> list, int i, int j) {
        Song temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }
}