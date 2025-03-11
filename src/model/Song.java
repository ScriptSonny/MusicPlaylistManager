package model;

public class Song
{
    private String title;
    private String artist;
    private String album;
    private String genre;
    private int duration; // seconden
    private int popularity; // 0 - 100

    public Song(String title, String artist, String album, String genre, int duration, int popularity)
    {
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.genre = genre;
        this.duration = duration;
        this.popularity = popularity;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getAlbum() {
        return album;
    }

    public String getGenre() {
        return genre;
    }

    public int getDuration() {
        return duration;
    }

    public int getPopularity() {
        return popularity;
    }

    @Override
    public String toString()
    {
        return "🎵 " + getTitle() + " - " + getArtist() + " (" + getAlbum() + ")";
    }
}
