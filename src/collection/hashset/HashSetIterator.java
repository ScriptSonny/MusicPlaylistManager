package collection.hashset;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class HashSetIterator<T> implements Iterator<T> {
    private final HashSet<T> set;
    private final T[] array;
    private int currentIndex = 0;

    public HashSetIterator(HashSet<T> set) {
        this.set = set;
        this.currentIndex = 0;
        this.array = (T[]) this.set.toArray();
    }

    /**
     * Returns true if the iteration has more elements.
     * @return true if the iteration has more elements
     */
    @Override
    public boolean hasNext() {
        return this.currentIndex < this.set.size();
    }

    /**
     * Returns the next element in the iteration.
     * @return the next element in the iteration
     */
    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return this.array[currentIndex++];
    }

    /**
     * Removes the last element returned by this iterator from the underlying collection.
     */
    @Override
    public void remove() {
        if (this.currentIndex <= 0) {
            throw new IllegalStateException();
        }
        this.set.remove(this.array[this.currentIndex - 1]);
    }
}
