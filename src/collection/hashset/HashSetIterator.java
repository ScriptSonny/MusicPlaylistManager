package collection.hashset;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class HashSetIterator<T> implements Iterator<T>
{
    private int currentIndex = 0;
    private HashSet<T> set;
    private T[] array;
    
    public HashSetIterator(HashSet<T> set)
    {
        this.set = set;
        this.currentIndex = 0;
        this.array = (T[]) this.set.toArray();
    }
    
    @Override
    public boolean hasNext()
    {
        return this.currentIndex < this.set.size();
    }
    
    @Override
    public T next()
    {
        if (!hasNext())
        {
            throw new NoSuchElementException();
        }
        return this.array[currentIndex++];
    }
    
    @Override
    public void remove()
    {
        if (this.currentIndex <= 0)
        {
            throw new IllegalStateException();
        }
        this.set.remove(this.array[this.currentIndex - 1]);
    }
}
