package sorting;

import song.SortResult;
import collection.doublylinkedlist.DoublyLinkedList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public class MergeSort<T extends Comparable<T>> implements SortingMethod<T> {
    /**
     * Sorts a collection of songs using the MergeSort algorithm.
     * @param songs - The collection of songs to be sorted.
     * @return - Returns a SortResult containing the sorted list of songs.
     */
    @Override
    public SortResult<T> sort(Collection<T> songs, Comparator<T> comparator) {
        if (songs == null || songs.isEmpty()) {
            return new SortResult<>();
        }

        List<T> songList = new ArrayList<>();
        for (T song : songs) {
            songList.add(song);
        }

        List<T> sorted = mergeSort(songList, comparator);

        DoublyLinkedList<T> sortedList = new DoublyLinkedList<>();
        sortedList.addAll(sorted);

        SortResult<T> sortedResult = new SortResult<>();
        sortedResult.setSongs(sortedList);

        return sortedResult;
    }

    /**
     * Sorts the given list using MergeSort.
     * @param list - The list of songs to be sorted.
     * @return - A sorted list of songs.
     */
    private List<T> mergeSort(List<T> list, Comparator<T> comparator) {
        if (list.size() <= 1) {
            return list;
        }

        int mid = list.size() / 2;
        List<T> left = new ArrayList<>(list.subList(0, mid));
        List<T> right = new ArrayList<>(list.subList(mid, list.size()));

        return merge(mergeSort(left, comparator), mergeSort(right, comparator), comparator);
    }

    /**
     * Merges two sorted lists into a single sorted list.
     * @param left - The left sublist.
     * @param right - The right sublist.
     * @return - A merged and sorted list.
     */
    private List<T> merge(List<T> left, List<T> right, Comparator<T> comparator) {
        List<T> merged = new ArrayList<>();
        int i = 0, j = 0;

        while (i < left.size() && j < right.size()) {
            if (comparator.compare(left.get(i), right.get(j)) <= 0)
            {
                merged.add(left.get(i));
                i++;
            } else {
                merged.add(right.get(j));
                j++;
            }
        }

        while (i < left.size()) {
            merged.add(left.get(i));
            i++;
        }

        while (j < right.size()) {
            merged.add(right.get(j));
            j++;
        }

        return merged;
    }
}