package song;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum Genre
{
    POP,
    ROCK,
    METAL,
    HOUSE,
    TECHNO;

    @JsonCreator
    public static Genre fromString(String text) {
        for (Genre g : Genre.values()) {
            if (g.name().equalsIgnoreCase(text)) {
                return g;
            }
        }
        throw new IllegalArgumentException("Invalid genre: " + text);
    }
}
