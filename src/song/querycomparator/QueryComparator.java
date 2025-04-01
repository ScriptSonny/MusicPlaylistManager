package song.querycomparator;

import utils.StringQueryComparator;

public abstract class QueryComparator<T extends Comparable<T>>
{
    public abstract String getComparable(T from);
    
    public boolean compare(T from, String query)
    {
        return StringQueryComparator.compare(getComparable(from), query);
    }
}
