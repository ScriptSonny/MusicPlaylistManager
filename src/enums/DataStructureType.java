package enums;

public enum DataStructureType
{
    DOUBLY_LINKED_LIST("Doubly Linked List"),
    BINARY_SEARCH_TREE("Binary Search Tree (BST)"),
    HASH_SET("HashSet");

    private final String displayName;

    DataStructureType(String displayName)
    {
        this.displayName = displayName;
    }

    public String getDisplayName()
    {
        return displayName;
    }

    @Override
    public String toString()
    {
        return displayName;
    }
}
