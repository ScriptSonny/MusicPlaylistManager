package artist;

import collection.hashset.HashSet;

public class ArtistManager
{
    private static ArtistManager instance;
    
    private HashSet<Artist> artists;
    
    private ArtistManager()
    {
    }
    
    public static ArtistManager getInstance()
    {
        if (instance == null)
        {
            instance = new ArtistManager();
        }
        return instance;
    }
    
    public HashSet<Artist> getArtists()
    {
        return this.artists;
    }
    
    public void addArtists(Artist artist)
    {
        this.artists.add(artist);
    }
}
