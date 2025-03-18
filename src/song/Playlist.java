package song;

import collection.doublylinkedlist.DoublyLinkedList;

import java.util.Collection;

public class Playlist extends SongContainer
{
    public Playlist()
    {
        super();
    }
    
    public Playlist(Collection<Song> songs)
    {
        super();
        DoublyLinkedList<Song> songList = new DoublyLinkedList<>();
        songList.addAll(songs);
        this.setSongs(songList);
    }
}
