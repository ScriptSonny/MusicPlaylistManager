package collection.doublylinkedlist;

public class Node<T> {
    private T data;
    private Node<T> previous;
    private Node<T> next;

    public Node(T data) {
        this.data = data;
        this.previous = null;
        this.next = null;
    }

    // Getters and setters
    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Node<T> getPrevious() {
        return this.previous;
    }

    public void setPrevious(Node<T> previous) {
        this.previous = previous;
    }

    public Node<T> getNext() {
        return this.next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }
}
