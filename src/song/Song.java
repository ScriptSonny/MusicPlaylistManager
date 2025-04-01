package song;

import artist.Artist;

public class Song implements Comparable<Song> {
    private String title;
    private Artist artist;
    private Genre genre;
    private int duration; // In seconds
    private int popularity; // Range from 0 to 100

    public Song() {

    }

    public Song(String title, Artist artist, Genre genre, int duration, int popularity) {
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.duration = duration;
        this.popularity = popularity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    @Override
    public String toString() {
        return "🎵 " + getTitle() + " - " + getArtist().getName();
    }

    @Override
    public int compareTo(Song other) {
        int titleComparison = this.title.compareToIgnoreCase(other.title);
        if (titleComparison != 0) {
            return titleComparison;
        }
        return this.artist.getName().compareToIgnoreCase(other.artist.getName());
    }
}
