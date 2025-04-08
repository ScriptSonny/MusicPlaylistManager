package enums;

/**
 * Enum representing different types of data structures.
 * Each enum constant has a display name associated with it.
 */
public enum DataStructureType {
    DOUBLY_LINKED_LIST("Doubly Linked List"),
    BINARY_SEARCH_TREE("Binary Search Tree (BST)"),
    HASH_SET("HashSet");

    private final String displayName;

    DataStructureType(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Get the display name of the data structure type.
     * @return the display name of the data structure type
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Get the string representation of the data structure type.
     * @return the string representation of the data structure type
     */
    @Override
    public String toString() {
        return displayName;
    }
}
