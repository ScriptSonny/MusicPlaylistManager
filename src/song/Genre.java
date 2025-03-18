package song;

public enum Genre
{
    POP,
    ROCK,
    METAL,
    HOUSE,
    TECHNO;

    public static Genre fromString(String text) {
        for (Genre g : Genre.values()) {
            if (g.name().equalsIgnoreCase(text)) {
                return g;
            }
        }
        throw new IllegalArgumentException("Invalid genre: " + text);
    }
}
