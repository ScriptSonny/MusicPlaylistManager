package collection.binarysearchtree;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

public class BinarySearchTreeIterator<T extends Comparable<T>> implements Iterator<T>
{
    private Stack<Node<T>> stack;
    
    public BinarySearchTreeIterator(Node<T> root)
    {
        stack = new Stack<>();
        pushLeftNodes(root);
    }
    
    private void pushLeftNodes(Node<T> node)
    {
        while (node != null)
        {
            this.stack.push(node);
            node = node.getLeft();
        }
    }
    
    @Override
    public boolean hasNext()
    {
        return !this.stack.isEmpty();
    }
    
    @Override
    public T next()
    {
        if (!this.hasNext())
        {
            throw new NoSuchElementException();
        }
        
        Node<T> node = stack.pop();
        T result = node.getData();
        
        if (node.getRight() != null)
        {
            pushLeftNodes(node.getRight());
        }
        
        return result;
    }
}

