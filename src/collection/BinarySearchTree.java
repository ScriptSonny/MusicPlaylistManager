package collection;

import java.util.Collection;
import java.util.Iterator;

public class BinarySearchTree<T> implements Collection<T>
{
    protected class Node
    {
        private T data;
        private Node left;
        private Node right;
        
        public Node(T data)
        {
            this.data = data;
            this.left = null;
            this.right = null;
        }
        
        public T getData()
        {
            return this.data;
        }
        
        public void setData(T data)
        {
            this.data = data;
        }
        
        public Node getLeft()
        {
            return this.left;
        }
        
        public void setLeft(Node left)
        {
            this.left = left;
        }
        
        public Node getRight()
        {
            return this.right;
        }
        
        public void setRight(Node right)
        {
            this.right = right;
        }
    }
    
    private Node root;
    
    public BinarySearchTree()
    {
        this.root = null;
    }
    
    @Override
    public int size()
    {
        return 0;
    }
    
    @Override
    public boolean isEmpty()
    {
        return false;
    }
    
    @Override
    public boolean contains(Object o)
    {
        return false;
    }
    
    @Override
    public Iterator<T> iterator()
    {
        return null;
    }
    
    @Override
    public Object[] toArray()
    {
        return new Object[0];
    }
    
    @Override
    public <T1> T1[] toArray(T1[] a)
    {
        return null;
    }
    
    @Override
    public boolean add(T t)
    {
        return false;
    }
    
    @Override
    public boolean remove(Object o)
    {
        return false;
    }
    
    @Override
    public boolean containsAll(Collection<?> c)
    {
        return false;
    }
    
    @Override
    public boolean addAll(Collection<? extends T> c)
    {
        return false;
    }
    
    @Override
    public boolean removeAll(Collection<?> c)
    {
        return false;
    }
    
    @Override
    public boolean retainAll(Collection<?> c)
    {
        return false;
    }
    
    @Override
    public void clear()
    {
    
    }
}
