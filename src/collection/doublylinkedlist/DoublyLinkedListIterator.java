package collection.doublylinkedlist;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoublyLinkedListIterator<T> implements Iterator<T> {
    private Node<T> currentNode;

    public DoublyLinkedListIterator(Node<T> head) {
        this.currentNode = new Node<T>(null);
        this.currentNode.setNext(head);
    }

    /**
     * Returns the current node
     * @return the current node
     */
    public Node<T> getCurrentNode() {
        return this.currentNode;
    }

    /**
     * Checks if there is a next node
     * @return true if there is a next node
     */
    @Override
    public boolean hasNext() {
        if (this.currentNode == null) {
            return false;
        }
        return this.currentNode.getNext() != null;
    }

    /**
     * Returns the next node
     * @return the next node
     */
    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        this.currentNode = this.currentNode.getNext();
        T data = this.currentNode.getData();
        return data;
    }
}
