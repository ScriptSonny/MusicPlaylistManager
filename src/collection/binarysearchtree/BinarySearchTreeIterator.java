package collection.binarysearchtree;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

public class BinarySearchTreeIterator<T extends Comparable<T>> implements Iterator<T> {
    private final Stack<Node<T>> stack;

    public BinarySearchTreeIterator(Node<T> root) {
        stack = new Stack<>();
        pushLeftNodes(root);
    }

    /**
     * Push all left nodes of the given node onto the stack
     * @param node - the current node
     */
    private void pushLeftNodes(Node<T> node) {
        while (node != null) {
            this.stack.push(node);
            node = node.getLeft();
        }
    }

    /**
     * Check if the stack is empty
     * @return - true if the stack is empty, false otherwise
     */
    @Override
    public boolean hasNext() {
        return !this.stack.isEmpty();
    }

    /**
     * Get the next element in the stack
     * @return - the next element
     */
    @Override
    public T next() {
        if (!this.hasNext()) {
            throw new NoSuchElementException();
        }

        Node<T> node = stack.pop();
        T result = node.getData();

        if (node.getRight() != null) {
            pushLeftNodes(node.getRight());
        }

        return result;
    }
}

