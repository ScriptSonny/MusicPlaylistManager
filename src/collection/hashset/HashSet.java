package collection.hashset;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class HashSet<T> implements Set<T> {
    private static final int DEFAULT_SIZE = 16;
    private T[] elements;
    private int size;

    public HashSet() {
        this.elements = (T[]) new Object[DEFAULT_SIZE];
        this.size = 0;
    }

    // Adds an element to this set.
    @Override
    public boolean add(T t) {
        // Element already exists
        if (this.contains(t)) {
            return false;
        }

        this.ensureSize();
        this.elements[this.size++] = t;

        return true;
    }

    // Update size of this set if necessary.
    private void ensureSize() {
        if (this.size == this.elements.length) {
            // Store a copy of the original array with double size
            this.elements = Arrays.copyOf(this.elements, this.elements.length * 2);
        }
    }

    // Adds all elements from the given collection to this set.
    @Override
    public boolean addAll(Collection<? extends T> c) {
        boolean modified = false;
        for (T t : c) {
            if (this.add(t)) {
                modified = true;
            }
        }
        return modified;
    }

    // Clears this set.
    @Override
    public void clear() {
        this.elements = (T[]) new Object[DEFAULT_SIZE];
        this.size = 0;
    }

    // Checks if this set contains a given element
    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < this.size; i++) {
            if (this.elements[i].equals(o)) {
                return true;
            }
        }
        return false;
    }

    // Checks if this set contains all the elements from the other collection
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

    // Checks if this set is empty
    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    // Remove the given element from this set
    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < this.size; i++) {
            if (this.elements[i].equals(o)) {
                // Shift elements to the left to fill the gap created by the removed element
                System.arraycopy(this.elements, i + 1, this.elements, i, this.size - i - 1);

                // Remove the element
                this.elements[--size] = null;
                return true;
            }
        }
        return false;
    }

    // Remove all the elements in the given collection from this set
    @Override
    public boolean removeAll(Collection<?> c) {
        boolean modified = false;
        Iterator<?> i = c.iterator();
        while (i.hasNext()) {
            if (this.remove(i.next())) {
                modified = true;
            }
        }
        return modified;
    }

    // Retain only the elements that are also contained in the given collection
    @Override
    public boolean retainAll(Collection<?> c) {
        boolean modified = false;
        HashSetIterator<T> iterator = this.iterator();
        while (iterator.hasNext()) {
            if (!c.contains(iterator.next())) {
                iterator.remove();
                modified = true;
            }
        }
        return modified;
    }

    // Get the size of the set
    @Override
    public int size() {
        return this.size;
    }

    // Get an iterator over the elements of the set
    @Override
    public HashSetIterator<T> iterator() {
        return new HashSetIterator<T>(this);
    }

    // Check if this set is equal to another object.
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        Set<?> that = (Set<?>) o;
        return this.containsAll(that) && that.containsAll(this);
    }

    // Hash code for this set
    @Override
    public int hashCode() {
        int hashCode = 0;
        HashSetIterator<T> i = this.iterator();
        while (i.hasNext()) {
            T t = i.next();
            if (t != null) {
                hashCode += t.hashCode();
            }
        }
        return hashCode;
    }

    // Converts this set to a string
    @Override
    public String toString() {
        String ret = "";
        HashSetIterator<T> iterator = this.iterator();
        while (iterator.hasNext()) {
            ret += iterator.next().toString() + ", ";
        }
        return "HashSet{" + ret + "Size: " + this.size() + '}';
    }

    // Convert this set to an array
    @Override
    public Object[] toArray() {
        return Arrays.copyOf(this.elements, this.size);
    }

    // Convert this set to an array of a specific type
    @Override
    public <E> E[] toArray(E[] a) {
        if (a.length < this.size) {
            return (E[]) Arrays.copyOf(this.elements, this.size, a.getClass());
        }
        System.arraycopy(this.elements, 0, a, 0, size);
        if (a.length > this.size) {
            a[this.size] = null;
        }
        return a;
    }
}