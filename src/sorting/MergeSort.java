package sorting;

import song.Song;
import song.SongComparators;
import song.SortResult;
import collection.doublylinkedlist.DoublyLinkedList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MergeSort implements SortingMethod {
    /**
     * Sorts a collection of songs using the MergeSort algorithm.
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

        songList = mergeSort(songList);

        DoublyLinkedList<Song> sortedList = new DoublyLinkedList<>();
        sortedList.addAll(songList);

        SortResult sortedResult = new SortResult();
        sortedResult.setSongs(sortedList);

        return sortedResult;
    }

    /**
     * Sorts the given list using MergeSort.
     * @param list - The list of songs to be sorted.
     * @return - A sorted list of songs.
     */
    private List<Song> mergeSort(List<Song> list) {
        if (list.size() <= 1) {
            return list;
        }

        int mid = list.size() / 2;
        List<Song> left = new ArrayList<>(list.subList(0, mid));
        List<Song> right = new ArrayList<>(list.subList(mid, list.size()));

        return merge(mergeSort(left), mergeSort(right));
    }

    /**
     * Merges two sorted lists into a single sorted list.
     * @param left - The left sublist.
     * @param right - The right sublist.
     * @return - A merged and sorted list.
     */
    private List<Song> merge(List<Song> left, List<Song> right) {
        List<Song> merged = new ArrayList<>();
        int i = 0, j = 0;

        while (i < left.size() && j < right.size()) {
            if (SongComparators.BY_TITLE.compare(left.get(i), right.get(j)) <= 0) {
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