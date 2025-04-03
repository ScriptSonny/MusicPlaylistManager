package utils;

/**
 * Utility class for comparing two strings.
 */
public class StringQueryComparator {
    public static boolean compare(String a, String b) {
        a = a.toLowerCase();
        b = b.toLowerCase();
        if (a.equals(b)) {
            return true;
        }
        return a.contains(b) || b.contains(a);
    }
}
