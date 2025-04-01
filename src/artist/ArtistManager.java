package artist;

import collection.hashset.HashSet;

public class ArtistManager {
    private static ArtistManager instance;

    private HashSet<Artist> artists;

    private ArtistManager() {
    }

    public static ArtistManager getInstance() {
        if (ArtistManager.instance == null) {
            ArtistManager.instance = new ArtistManager();
        }
        return ArtistManager.instance;
    }

    public HashSet<Artist> getArtists() {
        return this.artists;
    }

    public void addArtists(Artist artist) {
        this.artists.add(artist);
    }
}
