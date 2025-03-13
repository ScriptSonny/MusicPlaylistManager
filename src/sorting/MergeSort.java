package sorting;

import song.Song;
import song.SongContainer;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.ArrayList;

public class MergeSort implements SortingMethod {
    @Override
    public <T extends SongContainer> T sort(T songs) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        List<Song> songList = new ArrayList<>(songs.getSongs());

        if (songList.size() < 2) return songs;
        mergeSort(songList, 0, songList.size() - 1);
        return songs;
    }

    private void mergeSort(List<Song> songs, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(songs, left, mid);
            mergeSort(songs, mid + 1, right);
            merge(songs, left, mid, right);
        }
    }

    private void merge(List<Song> songs, int left, int mid, int right) {
        List<Song> leftList = new ArrayList<>(songs.subList(left, mid + 1));
        List<Song> rightList = new ArrayList<>(songs.subList(mid + 1, right + 1));

        int i = 0, j = 0, k = left;
        while (i < leftList.size() && j < rightList.size()) {
            if (leftList.get(i).getTitle().compareTo(rightList.get(j).getTitle()) <= 0) {
                songs.set(k++, leftList.get(i++));
            } else {
                songs.set(k++, rightList.get(j++));
            }
        }

        while (i < leftList.size()) {
            songs.set(k++, leftList.get(i++));
        }

        while (j < rightList.size()) {
            songs.set(k++, rightList.get(j++));
        }
    }
}