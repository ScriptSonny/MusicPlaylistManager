package sorting;

import song.Song;
import song.SongContainer;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class QuickSort implements SortingMethod {
    @Override
    public <T extends SongContainer> T sort(T songs) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        List<Song> songList = new ArrayList<>(songs.getSongs());

        quickSort(songList, 0, songList.size() - 1);
        return songs;
    }

    private void quickSort(List<Song> songs, int low, int high) {
        if (low < high) {
            int pi = partition(songs, low, high);
            quickSort(songs, low, pi - 1);
            quickSort(songs, pi + 1, high);
        }
    }

    private int partition(List<Song> songs, int low, int high) {
        Song pivot = songs.get(high);
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (songs.get(j).getTitle().compareTo(pivot.getTitle()) < 0) {
                i++;
                swap(songs, i, j);
            }
        }
        swap(songs, i + 1, high);
        return i + 1;
    }

    private void swap(List<Song> songs, int i, int j) {
        Song temp = songs.get(i);
        songs.set(i, songs.get(j));
        songs.set(j, temp);
    }
}