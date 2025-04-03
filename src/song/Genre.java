package song;

/**
 * This enum represents the different genres of songs.
 * It provides a method to convert a string to a Genre enum value.
 */
public enum Genre {
    POP,
    ROCK,
    METAL,
    RAP,
    HOUSE,
    TECHNO;

    /**
     * Converts a string to a Genre enum value.
     * @param text the string to convert
     * @return the corresponding Genre enum value
     */
    public static Genre fromString(String text) {
        for (Genre g : Genre.values()) {
            if (g.name().equalsIgnoreCase(text)) {
                return g;
            }
        }
        throw new IllegalArgumentException("Invalid genre: " + text);
    }
}
