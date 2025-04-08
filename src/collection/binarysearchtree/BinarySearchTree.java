package collection.binarysearchtree;

import java.util.ArrayList;
import java.util.Collection;

public class BinarySearchTree<T extends Comparable<T>> implements Collection<T> {
    private Node<T> root;
    private int size;

    public BinarySearchTree() {
        this.root = null;
        this.size = 0;
    }

    /**
     * Add a node to the tree
     * @return - the new node
     */
    public Node<T> getRoot() {
        return this.root;
    }

    /**
     * Find a node in the tree
     * @param node - the current node
     * @param data - the data to find
     * @return - the node if found, null if not found
     */
    public Node<T> findNode(Node<T> node, T data) {
        if (node == null) {
            return null;
        }
        int cmp = data.compareTo(node.getData());
        if (cmp < 0) {
            return findNode(node.getLeft(), data);
        } else if (cmp > 0) {
            return findNode(node.getRight(), data);
        } else {
            return node;
        }
    }

    /**
     * Add a node to the tree
     * @param node - the current node
     * @param data - the data to add
     * @return - the new node
     */
    private Node<T> addNode(Node<T> node, T data) {
        if (node == null) {
            return new Node<>(data);
        }
        int cmp = data.compareTo(node.getData());
        if (cmp < 0) {
            node.setLeft(addNode(node.getLeft(), data));
        } else if (cmp > 0) {
            node.setRight(addNode(node.getRight(), data));
        }
        return node;
    }

    /**
     * Remove a node from the tree
     * @param node - the current node
     * @param data - the data to remove
     * @return - the new node
     */
    private Node<T> removeNode(Node<T> node, T data) {
        if (node == null) {
            return null;
        }
        int cmp = data.compareTo(node.getData());
        if (cmp < 0) {
            node.setLeft(removeNode(node.getLeft(), data));
        } else if (cmp > 0) {
            node.setRight(removeNode(node.getRight(), data));
        } else {
            if (node.getLeft() == null) {
                return node.getRight();
            }
            if (node.getRight() == null) {
                return node.getLeft();
            }

            Node<T> minNode = findMin(node.getRight());
            node.setData(minNode.getData());
            node.setRight(removeNode(node.getRight(), minNode.getData()));
        }
        return node;
    }

    /**
     * Find the minimum node in the tree
     * @param node - the current node
     * @return - the minimum node
     */
    private Node<T> findMin(Node<T> node) {
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return node;
    }

    /**
     * Find the maximum node in the tree
     * @param node - the current node
     */
    private void inorderTraversal(Node<T> node, ArrayList<T> list) {
        if (node != null) {
            inorderTraversal(node.getLeft(), list);
            list.add(node.getData());
            inorderTraversal(node.getRight(), list);
        }
    }

    /**
     * Get the size of the tree
     * @return - the size of the tree
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * Check if the tree is empty
     * @return - true if the tree is empty, false if not
     */
    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    /**
     * Get the iterator for the tree
     * @return - the iterator
     */
    @Override
    public BinarySearchTreeIterator<T> iterator() {
        return new BinarySearchTreeIterator<T>(this.root);
    }

    /**
     * Check if the tree contains a specific object
     * @param o - the object to check
     * @return - true if the tree contains the object, false if not
     */
    @Override
    public boolean contains(Object o) {
        if (o == null) {
            return false;
        }
        T data = (T) o;
        return findNode(root, data) != null;
    }

    /**
     * Convert the tree to an array
     * @return - the array
     */
    @Override
    public Object[] toArray() {
        ArrayList<T> list = new ArrayList<>();
        inorderTraversal(root, list);
        return list.toArray();
    }

    /**
     * Convert the tree to an array
     * @param a - the array to convert to
     * @return - the array
     */
    @Override
    public <E> E[] toArray(E[] a) {
        ArrayList<T> list = new ArrayList<>();
        inorderTraversal(root, list);
        return list.toArray(a);
    }

    /**
     * Add a node to the tree
     * @param t - the data to add
     * @return - true if the node was added, false if not
     */
    @Override
    public boolean add(T t) {
        if (contains(t)) {
            return false;
        }
        root = addNode(root, t);
        this.size += 1;
        return true;
    }

    /**
     * Remove a node from the tree
     * @param o - the data to remove
     * @return - true if the node was removed, false if not
     */
    @Override
    public boolean remove(Object o) {
        if (o == null) {
            return false;
        }
        T data = (T) o;
        if (!contains(data)) {
            return false;
        }
        root = removeNode(root, data);
        this.size -= 1;
        return true;
    }

    /**
     * Check if this collection contains all elements in the specified collection
     * @param c - the collection to check
     * @return - true if this collection contains all elements in the specified collection
     */
    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object obj : c) {
            if (!contains(obj)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Add all elements in the specified collection to this collection
     * @param c - the collection to add
     * @return - true if the collection was changed
     */
    @Override
    public boolean addAll(Collection<? extends T> c) {
        boolean changed = false;
        for (T item : c) {
            if (add(item)) {
                changed = true;
            }
        }
        return changed;
    }

    /**
     * Remove all elements in the specified collection from this collection
     * @param c - the collection to remove
     * @return - true if the collection was changed
     */
    @Override
    public boolean removeAll(Collection<?> c) {
        boolean changed = false;
        for (Object obj : c) {
            if (remove(obj)) {
                changed = true;
            }
        }
        return changed;
    }

    /**
     * Retain only the elements in this collection that are contained in the specified collection
     * @param c - the collection to retain
     * @return - true if the collection was changed
     */
    @Override
    public boolean retainAll(Collection<?> c) {
        boolean changed = false;
        BinarySearchTreeIterator<T> iter = iterator();
        while (iter.hasNext()) {
            T data = iter.next();
            if (!c.contains(data)) {
                remove(data);
                changed = true;
            }
        }
        return changed;
    }

    /**
     * Clear the tree
     */
    @Override
    public void clear() {
        root = null;
        this.size = 0;
    }
}
