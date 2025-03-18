package song;

import artist.Artist;

public class Song
{
    private String title;
    private Artist artist;
    private Genre genre;
    private int duration; // In seconds
    private int popularity; // Range from 0 to 100

    public Song()
    {

    }

    public Song(String title, Artist artist, Genre genre, int duration, int popularity)
    {
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.duration = duration;
        this.popularity = popularity;
    }

    public String getTitle() {
        return title;
    }

    public Artist getArtist() {
        return artist;
    }

    public Genre getGenre() {
        return genre;
    }

    public int getDuration() {
        return duration;
    }

    public int getPopularity() {
        return popularity;
    }
    
    public void setTitle(String title)
    {
        this.title = title;
    }
    
    public void setArtist(Artist artist)
    {
        this.artist = artist;
    }
    
    public void setGenre(Genre genre)
    {
        this.genre = genre;
    }
    
    public void setDuration(int duration)
    {
        this.duration = duration;
    }
    
    public void setPopularity(int popularity)
    {
        this.popularity = popularity;
    }
    
    @Override
    public String toString()
    {
        return "🎵 " + getTitle() + " - " + getArtist().getName();
    }
}
