package collection.hashmap;

public class Node<K, V> {
    private final K key;
    private V value;
    private Node<K, V> next;

    public Node(K key, V value) {
        this.key = key;
        this.value = value;
        this.next = null;
    }

    public K getKey() {
        return this.key;
    }

    public V getValue() {
        return this.value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public Node<K, V> getNext() {
        return this.next;
    }

    public void setNext(Node<K, V> next) {
        this.next = next;
    }
}
