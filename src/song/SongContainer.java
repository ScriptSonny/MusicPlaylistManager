package song;

import collection.DoublyLinkedList;

public abstract class SongContainer
{
    private DoublyLinkedList<Song> songs;
    
    public SongContainer()
    {
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
}
