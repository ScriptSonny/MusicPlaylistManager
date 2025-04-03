package collection.doublylinkedlist;

import java.util.Collection;
import java.util.Iterator;

public class DoublyLinkedList<T extends Comparable<T>> implements Collection<T> {
    private Node<T> head;
    private Node<T> tail;

    public DoublyLinkedList() {
        this.clear();
    }

    /**
     * Returns the number of elements in this collection
     */
    @Override
    public int size() {
        if (this.head == null) {
            return 0;
        }
        DoublyLinkedListIterator<T> iterator = this.iterator();
        int count = 1;
        while (iterator.hasNext()) {
            iterator.next();
            if (iterator.hasNext()) {
                count += 1;
            }
        }
        return count;
    }

    /**
     * Checks if the list is empty
     * @return true if the list is empty
     */
    @Override
    public boolean isEmpty() {
        return this.head == null && this.tail == null;
    }

    /**
     * Checks if the list contains the specified element
     * @param o the object to check
     * @return true if this list contains the specified element
     */
    @Override
    public boolean contains(Object o) {
        if (o == null) {
            return false;
        }
        DoublyLinkedListIterator<T> iterator = this.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().equals(o)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns an iterator over the elements in this collection
     */
    @Override
    public DoublyLinkedListIterator<T> iterator() {
        return new DoublyLinkedListIterator<T>(this.head);
    }

    /**
     * Converts the list to an array
     * @return the array containing all elements in this list
     */
    @Override
    public Object[] toArray() {
        Object[] array = new Object[this.size()];
        DoublyLinkedListIterator<T> iterator = this.iterator();
        int index = 0;
        while (iterator.hasNext()) {
            array[index++] = iterator.next();
        }
        return array;
    }

    /**
     * Converts the list to an array of the specified type
     * @param a the array to be filled
     * @return the array containing all elements in this list
     */
    @Override
    public <T1> T1[] toArray(T1[] a) {
        if (a.length < this.size()) {
            a = (T1[]) java.lang.reflect.Array.newInstance(a.getClass().getComponentType(), this.size());
        }
        int index = 0;
        DoublyLinkedListIterator<T> iterator = this.iterator();
        while (iterator.hasNext()) {
            a[index++] = (T1) iterator.next();
        }
        return a;
    }

    /**
     * Adds the specified element to this collection
     * @param t element to be added
     * @return true if this collection changed as a result of the call
     */
    @Override
    public boolean add(T t) {
        Node newNode = new Node(t);
        if (this.isEmpty()) {
            this.head = newNode;
            this.tail = newNode;
            return true;
        }
        this.tail.setNext(newNode);
        newNode.setPrevious(this.tail);
        this.tail = newNode;
        return true;
    }

    /**
     * Removes the first occurrence of the specified element from this collection, if it is present
     * @param o element to be removed from this collection, if present
     * @return true if this collection contained the specified element
     */
    @Override
    public boolean remove(Object o) {
        DoublyLinkedListIterator<T> iterator = this.iterator();
        while (iterator.hasNext()) {
            T current = iterator.next();
            if (current.equals(o)) {
                // Remove head
                if (this.head.getData().equals(current)) {
                    if (this.head.equals(this.tail)) {
                        this.clear();
                    } else {
                        this.head = this.head.getNext();
                        this.head.setPrevious(null);
                    }
                }
                // Remove tail
                else if (this.tail.getData().equals(current)) {
                    if (this.head.equals(this.tail)) {
                        this.clear();
                    } else {
                        this.tail = tail.getPrevious();
                        this.tail.setNext(null);
                    }
                }
                // Remove in between
                else {
                    Node<T> currentNode = iterator.getCurrentNode();
                    currentNode.getPrevious().setNext(currentNode.getNext());
                    currentNode.getNext().setPrevious(currentNode.getPrevious());
                }
                return true;
            }
        }
        return false;
    }

    /**
     * @param c the collection to check
     * @return true if this list contains all elements in the specified collection
     */
    @Override
    public boolean containsAll(Collection<?> c) {
        Iterator<?> i = c.iterator();
        while (i.hasNext()) {
            if (!this.contains(i.next())) {
                return false;
            }
        }
        return true;
    }

    /**
     * Adds all elements in the specified collection to this list
     * @param c the collection to add
     * @return true if this list was modified as a result of the call
     */
    @Override
    public boolean addAll(Collection<? extends T> c) {
        boolean success = false;
        Iterator<? extends T> i = c.iterator();
        while (i.hasNext()) {
            if (this.add(i.next())) {
                success = true;
            }
        }
        return success;
    }

    /**
     * Removes all elements in the specified collection from this list
     * @param c the collection to remove
     * @return true if this list was modified as a result of the call
     */
    @Override
    public boolean removeAll(Collection<?> c) {
        boolean success = false;
        Iterator<?> i = c.iterator();
        while (i.hasNext()) {
            if (this.remove(i.next())) {
                success = true;
            }
        }
        return success;
    }

    /**
     * Retains only the elements in this list that are contained in the specified collection
     * @param c the collection to retain
     * @return true if this list was modified as a result of the call
     */
    @Override
    public boolean retainAll(Collection<?> c) {
        boolean modified = false;
        DoublyLinkedListIterator<T> iterator = this.iterator();
        while (iterator.hasNext()) {
            if (!c.contains(iterator.next())) {
                iterator.remove();
                modified = true;
            }
        }
        return modified;
    }


    /**
     * Removes all elements from the list
     */
    @Override
    public void clear() {
        this.head = null;
        this.tail = null;
    }

    /**
     * @return the string representation of the list
     */
    @Override
    public String toString() {
        String ret = "";
        DoublyLinkedListIterator<T> iterator = this.iterator();
        while (iterator.hasNext()) {
            ret += iterator.next().toString() + ", ";
        }
        return "DoublyLinkedList{" + ret + "Head: " + this.getHead() + ", Tail: " + this.getTail() + '}';
    }

    /**
     * @return the head of the list
     */
    public Node<T> getHead() {
        return this.head;
    }

    /**
     * @return the tail of the list
     */
    public Node<T> getTail() {
        return this.tail;
    }
}
