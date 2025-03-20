package search;

import artist.Artist;
import collection.binarysearchtree.BinarySearchTree;
import collection.binarysearchtree.Node;
import song.SearchResult;
import song.Song;

import java.util.Collection;

public class BinarySearch implements SearchMethod
{
    @Override
    public <T extends Collection<Song>> SearchResult search(String query, T songs)
    {
        SearchResult validSongs = new SearchResult();
        if (!(songs instanceof BinarySearchTree<?>))
        {
            throw new IllegalArgumentException("Song parameter has to be BinarySearchTree");
        }
        if (songs == null)
        {
            return validSongs;
        }
        if (query.length() == 0)
        {
            validSongs.appendSongs(songs);
            return validSongs;
        }
        BinarySearchTree<Song> bst = (BinarySearchTree<Song>) songs;
        Song searchSong = new Song();
        searchSong.setTitle(query);
        searchSong.setDuration(Integer.parseInt(query));
        searchSong.setPopularity(Integer.parseInt(query));
        searchSong.setArtist(new Artist(query));
        Node<Song> foundSong = bst.findNode(bst.getRoot(), searchSong);
        if (foundSong == null)
        {
            return validSongs;
        }
        validSongs.appendSong(foundSong.getData());
        return validSongs;
    }
}
