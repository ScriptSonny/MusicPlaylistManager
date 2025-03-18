package song;

import collection.doublylinkedlist.DoublyLinkedList;

public abstract class SongContainer
{
    private DoublyLinkedList<Song> songs;
    
    public SongContainer()
    {
        this.songs = new DoublyLinkedList<>();
    }
    
    public DoublyLinkedList<Song> getSongs()
    {
        return this.songs;
    }
    
    public void setSongs(DoublyLinkedList<Song> songs)
    {
        this.songs = songs;
    }
    
    public void appendSong(Song newSong)
    {
        this.songs.add(newSong);
    }
    
    @Override
    public String toString()
    {
        return "SongContainer{" +
                "songs=" + this.songs.toString() +
                '}';
    }
}
