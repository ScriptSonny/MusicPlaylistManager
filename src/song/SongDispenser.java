package song;

import search.SearchMethod;

public class SongDispenser
{
    private static SongDispenser instance;
    private SongContainer songContainer;
    
    private SongDispenser() {}
    
    public static SongDispenser getInstance()
    {
        if (SongDispenser.instance == null)
        {
            SongDispenser.instance = new SongDispenser();
        }
        return SongDispenser.instance;
    }
    
    // Getters and setters
    public SongContainer getSongContainer()
    {
        return songContainer;
    }
    
    public void setSongContainer(SongContainer songContainer)
    {
        this.songContainer = songContainer;
    }
    
    // Methods
    public SearchResult search(String query, SearchMethod method)
    {
        return method.search(query, songContainer.getSongs());
    }
}