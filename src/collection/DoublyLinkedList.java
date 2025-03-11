package collection;

import java.util.Collection;
import java.util.Iterator;

public class DoublyLinkedList<T> implements Collection<T>
{
    protected class Node
    {
        private T data;
        private Node previous;
        private Node next;
        
        public Node(T data)
        {
            this.data = data;
            this.previous = null;
            this.next = null;
        }
        
        public T getData()
        {
            return this.data;
        }
        
        public void setData(T data)
        {
            this.data = data;
        }
        
        public Node getPrevious()
        {
            return this.previous;
        }
        
        public void setPrevious(Node previous)
        {
            this.previous = previous;
        }
        
        public Node getNext()
        {
            return this.next;
        }
        
        public void setNext(Node next)
        {
            this.next = next;
        }
    }
    
    private Node head;
    private Node tail;
    
    public DoublyLinkedList()
    {
        this.head = null;
        this.tail = null;
    }
    
    public BinarySearchTree<T> toBST()
    {
        return new BinarySearchTree<T>();
    }
    
    @Override
    public int size()
    {
        if (head == null)
        {
            return 0;
        }
        Node current = head;
        int count = 1;
        while (current.next != null) {
            current = current.next;
            count += 1;
        }
        return count;
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
