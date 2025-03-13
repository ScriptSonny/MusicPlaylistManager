package sorting;

import song.Song;
import song.SongContainer;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class BubbleSort implements SortingMethod {
    @Override
    public <T extends SongContainer> T sort(T songs) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        List<Song> songList = new ArrayList<>(songs.getSongs());

        int n = songList.size();
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (songList.get(j).getTitle().compareTo(songList.get(j + 1).getTitle()) > 0) {
                    swap(songList, j, j + 1);
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
        return songs;
    }

    private void swap(List<Song> songs, int i, int j) {
        Song temp = songs.get(i);
        songs.set(i, songs.get(j));
        songs.set(j, temp);
    }
}