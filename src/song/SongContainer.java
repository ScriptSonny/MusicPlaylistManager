package song;

import collection.doublylinkedlist.DoublyLinkedList;

import java.util.Collection;

public abstract class SongContainer<T extends Comparable<T>>
{
    private DoublyLinkedList<T> songs;
    
    public SongContainer()
    {
        this.songs = new DoublyLinkedList<>();
    }
    
    public DoublyLinkedList<T> getSongs()
    {
        return this.songs;
    }
    
    public void setSongs(DoublyLinkedList<T> songs)
    {
        this.songs = songs;
    }
    
    public void appendSong(T newSong)
    {
        if (newSong == null)
        {
            return;
        }
        this.songs.add(newSong);
    }
    
    public void appendSongs(Collection<T> songs)
    {
        this.songs.addAll(songs);
    }
    
    @Override
    public String toString()
    {
        return "SongContainer{" +
                "songs=" + this.songs.toString() +
                '}';
    }
}
