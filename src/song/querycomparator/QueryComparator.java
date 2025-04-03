package song.querycomparator;

import utils.StringQueryComparator;

/**
 * This class is an abstract class that defines a method to compare songs based on a query.
 * @param <T> the type of the song
 */
public abstract class QueryComparator<T extends Comparable<T>> {
    public abstract String getComparable(T from);

    public boolean compare(T from, String query) {
        return StringQueryComparator.compare(getComparable(from), query);
    }
}
