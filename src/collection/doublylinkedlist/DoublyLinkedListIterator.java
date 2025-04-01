package collection.doublylinkedlist;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoublyLinkedListIterator<T> implements Iterator<T> {
    private Node<T> currentNode;

    public DoublyLinkedListIterator(Node<T> head) {
        this.currentNode = new Node<T>(null);
        this.currentNode.setNext(head);
    }

    public Node<T> getCurrentNode() {
        return this.currentNode;
    }

    @Override
    public boolean hasNext() {
        if (currentNode == null) {
            return false;
        }
        return currentNode.getNext() != null;
    }

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
