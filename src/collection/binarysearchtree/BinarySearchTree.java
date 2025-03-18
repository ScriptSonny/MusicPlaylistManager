package collection.binarysearchtree;

import java.util.ArrayList;
import java.util.Collection;

public class BinarySearchTree<T extends Comparable<T>> implements Collection<T>
{
    private Node<T> root;
    private int size;
    
    public BinarySearchTree()
    {
        this.root = null;
        this.size = 0;
    }
    
    public Node<T> getRoot()
    {
        return this.root;
    }
    
    public Node<T> findNode(Node<T> node, T data)
    {
        if (node == null)
        {
            return null;
        }
        int cmp = data.compareTo(node.getData());
        if (cmp < 0)
        {
            return findNode(node.getLeft(), data);
        }
        else if (cmp > 0)
        {
            return findNode(node.getRight(), data);
        }
        else
        {
            return node;
        }
    }
    
    private Node<T> addNode(Node<T> node, T data)
    {
        if (node == null)
        {
            return new Node<>(data);
        }
        int cmp = data.compareTo(node.getData());
        if (cmp < 0)
        {
            node.setLeft(addNode(node.getLeft(), data));
        }
        else if (cmp > 0)
        {
            node.setRight(addNode(node.getRight(), data));
        }
        return node;
    }
    
    private Node<T> removeNode(Node<T> node, T data) {
        if (node == null)
        {
            return null;
        }
        int cmp = data.compareTo(node.getData());
        if (cmp < 0)
        {
            node.setLeft(removeNode(node.getLeft(), data));
        }
        else if (cmp > 0)
        {
            node.setRight(removeNode(node.getRight(), data));
        }
        else
        {
            if (node.getLeft() == null)
            {
                return node.getRight();
            }
            if (node.getRight() == null)
            {
                return node.getLeft();
            }
            
            Node<T> minNode = findMin(node.getRight());
            node.setData(minNode.getData());
            node.setRight(removeNode(node.getRight(), minNode.getData()));
        }
        return node;
    }
    
    private Node<T> findMin(Node<T> node)
    {
        while (node.getLeft() != null)
        {
            node = node.getLeft();
        }
        return node;
    }
    
    private void inorderTraversal(Node<T> node, ArrayList<T> list)
    {
        if (node != null)
        {
            inorderTraversal(node.getLeft(), list);
            list.add(node.getData());
            inorderTraversal(node.getRight(), list);
        }
    }
    
    @Override
    public int size()
    {
        return this.size;
    }
    
    @Override
    public boolean isEmpty()
    {
        return this.size() > 0;
    }
    
    @Override
    public BinarySearchTreeIterator<T> iterator()
    {
        return new BinarySearchTreeIterator<T>(this.root);
    }
    
    @Override
    public boolean contains(Object o)
    {
        if (o == null)
        {
            return false;
        }
        T data = (T) o;
        return findNode(root, data) != null;
    }
        
    @Override
    public Object[] toArray()
    {
        ArrayList<T> list = new ArrayList<>();
        inorderTraversal(root, list);
        return list.toArray();
    }
    
    @Override
    public <E> E[] toArray(E[] a)
    {
        ArrayList<T> list = new ArrayList<>();
        inorderTraversal(root, list);
        return list.toArray(a);
    }
    
    @Override
    public boolean add(T t)
    {
        if (contains(t))
        {
            return false;
        }
        root = addNode(root, t);
        this.size += 1;
        return true;
    }
    
    @Override
    public boolean remove(Object o)
    {
        if (o == null)
        {
            return false;
        }
        T data = (T) o;
        if (!contains(data))
        {
            return false;
        }
        root = removeNode(root, data);
        this.size -= 1;
        return true;
    }
    
    @Override
    public boolean containsAll(Collection<?> c)
    {
        for (Object obj : c)
        {
            if (!contains(obj))
            {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public boolean addAll(Collection<? extends T> c)
    {
        boolean changed = false;
        for (T item : c)
        {
            if (add(item))
            {
                changed = true;
            }
        }
        return changed;
    }
    
    @Override
    public boolean removeAll(Collection<?> c)
    {
        boolean changed = false;
        for (Object obj : c)
        {
            if (remove(obj))
            {
                changed = true;
            }
        }
        return changed;
    }
    
    @Override
    public boolean retainAll(Collection<?> c)
    {
        boolean changed = false;
        BinarySearchTreeIterator<T> iter = iterator();
        while (iter.hasNext())
        {
            T data = iter.next();
            if (!c.contains(data))
            {
                remove(data);
                changed = true;
            }
        }
        return changed;
    }
    
    @Override
    public void clear()
    {
        root = null;
        this.size = 0;
    }
}
