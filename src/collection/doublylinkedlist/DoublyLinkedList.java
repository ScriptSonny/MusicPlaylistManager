package collection.doublylinkedlist;

import collection.BinarySearchTree;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoublyLinkedList<T> implements Collection<T>
{
    private Node<T> head;
    private Node<T> tail;
    
    public DoublyLinkedList()
    {
        this.clear();
    }
    
    public BinarySearchTree<T> toBST()
    {
        // TODO
        return new BinarySearchTree<T>();
    }
    
    @Override
    public int size()
    {
        if (this.head == null)
        {
            return 0;
        }
        DoublyLinkedListIterator<T> iterator = this.iterator();
        int count = 1;
        while (iterator.hasNext())
        {
            iterator.next();
            if (iterator.hasNext())
            {
                count += 1;
            }
        }
        return count;
    }
    
    @Override
    public boolean isEmpty()
    {
        return this.head == null && this.tail == null;
    }
    
    @Override
    public boolean contains(Object o)
    {
        if (o == null)
        {
            return false;
        }
        DoublyLinkedListIterator<T> iterator = this.iterator();
        while (iterator.hasNext())
        {
            if (iterator.next().equals(o))
            {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public DoublyLinkedListIterator<T> iterator()
    {
        return new DoublyLinkedListIterator<T>(this.head);
    }
    
    @Override
    public Object[] toArray()
    {
        // TODO
        return new Object[0];
    }
    
    @Override
    public <T1> T1[] toArray(T1[] a)
    {
        // TODO
        return null;
    }
    
    @Override
    public boolean add(T t)
    {
        Node newNode = new Node(t);
        if (this.isEmpty())
        {
            this.head = newNode;
            this.tail = newNode;
            return true;
        }
        this.tail.setNext(newNode);
        newNode.setPrevious(this.tail);
        this.tail = newNode;
        return true;
    }
    
    @Override
    public boolean remove(Object o)
    {
        DoublyLinkedListIterator<T> iterator = this.iterator();
        while (iterator.hasNext()) {
            T current = iterator.next();
            if (current.equals(o))
            {
                // Remove head
                if (this.head.getData().equals(current))
                {
                    if (this.head.equals(this.tail))
                    {
                        this.clear();
                    }
                    else
                    {
                        this.head = this.head.getNext();
                        this.head.setPrevious(null);
                    }
                }
                // Remove tail
                else if (this.tail.getData().equals(current))
                {
                    if (this.head.equals(this.tail))
                    {
                        this.clear();
                    }
                    else
                    {
                        this.tail = tail.getPrevious();
                        this.tail.setNext(null);
                    }
                }
                // Remove in between
                else
                {
                    Node<T> currentNode = iterator.getCurrentNode();
                    currentNode.getPrevious().setNext(currentNode.getNext());
                    currentNode.getNext().setPrevious(currentNode.getPrevious());
                }
                return true;
            }
        }
        return false;
    }
    
    @Override
    public boolean containsAll(Collection<?> c)
    {
        Iterator<?> i = c.iterator();
        while (i.hasNext())
        {
            if (!this.contains(i.next()))
            {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public boolean addAll(Collection<? extends T> c)
    {
        boolean success = false;
        Iterator<? extends T> i = c.iterator();
        while (i.hasNext())
        {
            if (this.add(i.next()))
            {
                success = true;
            }
        }
        return success;
    }
    
    @Override
    public boolean removeAll(Collection<?> c)
    {
        boolean success = false;
        Iterator<?> i = c.iterator();
        while (i.hasNext())
        {
            if (this.remove(i.next()))
            {
                success = true;
            }
        }
        return success;
    }
    
    @Override
    public boolean retainAll(Collection<?> c)
    {
        // TODO
        return false;
    }
    
    @Override
    public void clear()
    {
        this.head = null;
        this.tail = null;
    }
    
    @Override
    public String toString()
    {
        String ret = "";
        DoublyLinkedListIterator<T> iterator = this.iterator();
        while (iterator.hasNext())
        {
            ret += iterator.next().toString() + ", ";
        }
        return "DoublyLinkedList{" + ret + "Head: " + this.getHead() + ", Tail: " + this.getTail() + '}';
    }
    
    public Node<T> getHead()
    {
        return this.head;
    }
    
    public Node<T> getTail()
    {
        return this.tail;
    }
}
