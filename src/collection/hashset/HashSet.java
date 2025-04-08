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

    /**
     * Adds the specified element to this set if it is not already present.
     * @param t element whose presence in this collection is to be ensured
     * @return true if this set did not already contain the specified element
     */
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

    /**
     * Ensures that the internal array has enough space to store the elements.
     */
    private void ensureSize() {
        if (this.size == this.elements.length) {
            // Store a copy of the original array with double size
            this.elements = Arrays.copyOf(this.elements, this.elements.length * 2);
        }
    }

    /**
     * Adds all the elements in the specified collection to this set.
     * @param c collection containing elements to be added to this collection
     * @return true if this set changed as a result of the call
     */
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

    /**
     * Removes all elements from this set.
     */
    @Override
    public void clear() {
        this.elements = (T[]) new Object[DEFAULT_SIZE];
        this.size = 0;
    }

    /**
     * Checks if this set contains the specified element.
     * @param o element whose presence in this set is to be tested
     * @return true if this set contains the specified element
     */
    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < this.size; i++) {
            if (this.elements[i].equals(o)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if this set contains all elements in the specified collection.
     * @param c collection to be checked for containment in this set
     * @return true if this set contains all elements in the specified collection
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
     * Checks if this set is empty.
     * @return true if this set contains no elements
     */
    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * Checks if this set contains the specified element.
     * @param o element whose presence in this set is to be tested
     * @return true if this set contains the specified element
     */
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

    /**
     * Removes all elements in the specified collection from this set.
     * @param c collection containing elements to be removed from this set
     * @return true if this set changed as a result of the call
     */
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

    /**
     * Retains only the elements in this set that are contained in the specified collection.
     * @param c collection containing elements to be retained in this set
     * @return true if this set changed as a result of the call
     */
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

    /**
     * Returns the number of elements in this set.
     * @return the number of elements in this set
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * Returns an array containing all elements in this set.
     * @return an array containing all elements in this set
     */
    @Override
    public HashSetIterator<T> iterator() {
        return new HashSetIterator<T>(this);
    }

    /**
     * Checks if this set is equal to the specified object.
     * @param o object to be compared for equality with this set
     * @return true if the specified object is equal to this set
     */
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

    /**
     * Returns the hash code value for this set.
     * @return the hash code value for this set
     */
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

    /**
     * Returns a string representation of this set.
     * @return a string representation of this set
     */
    @Override
    public String toString() {
        String ret = "";
        HashSetIterator<T> iterator = this.iterator();
        while (iterator.hasNext()) {
            ret += iterator.next().toString() + ", ";
        }
        return "HashSet{" + ret + "Size: " + this.size() + '}';
    }

    /**
     * Returns an array containing all elements in this set.
     * @return an array containing all elements in this set
     */
    @Override
    public Object[] toArray() {
        return Arrays.copyOf(this.elements, this.size);
    }

    /**
     * Returns an array containing all elements in this set.
     * @param a the array into which the elements of this set are to be
     *        stored, if it is big enough; otherwise, a new array of the same
     *        runtime type is allocated for this purpose.
     * @return an array containing all elements in this set
     * @param <E> the runtime type of the array to contain the elements of this set
     */
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