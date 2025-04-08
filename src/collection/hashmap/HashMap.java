package collection.hashmap;

import collection.doublylinkedlist.DoublyLinkedList;
import collection.hashset.HashSet;

import java.util.AbstractMap;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class HashMap<K, V extends Comparable<V>> implements Map<K, V> {
    private static final int DEFAULT_SIZE = 16;
    private Node<K, V>[] table;

    public HashMap() {
        this.clear();
    }

    public HashMap(K[] keys, V[] values) {
        this.clear();
        for (int i = 0; i < values.length; i++) {
            this.put(keys[i], values[i]);
        }
    }

    /**
     * Returns the number of elements in this collection
     */
    private int hash(K key) {
        return Math.floorMod(key.hashCode(), this.table.length);
    }

    /**
     * Returns the number of elements in this collection
     * @return the number of elements in this collection
     */
    @Override
    public V put(K key, V value) {
        int hash = this.hash(key);
        Node<K, V> newNode = new Node<>(key, value);

        if (this.table[hash] == null) {
            this.table[hash] = newNode;
        } else {
            Node<K, V> current = this.table[hash];
            while (current != null) {
                if (current.getKey().equals(key)) {
                    current.setValue(value);
                    return value;
                }
                if (current.getNext() == null) {
                    break;
                }
                current = current.getNext();
            }
            current.setNext(newNode);
        }
        return value;
    }

    /**
     * Copies all the mappings from the specified map to this map
     * @param m mappings to be stored in this map
     */
    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for (K key : m.keySet()) {
            this.put(key, m.get(key));
        }
    }

    /**
     * Removes all the mappings from this map
     */
    @Override
    public void clear() {
        this.table = new Node[DEFAULT_SIZE];
    }

    /**
     * Returns a set view of the keys contained in this map
     * @return a set view of the keys contained in this map
     */
    @Override
    public Set<K> keySet() {
        Set<K> keySet = new HashSet<>();
        for (Node<K, V> bucket : this.table) {
            Node<K, V> current = bucket;
            while (current != null) {
                keySet.add(current.getKey());
                current = current.getNext();
            }
        }
        return keySet;
    }

    /**
     * Returns a collection view of the values contained in this map
     * @return a collection view of the values contained in this map
     */
    @Override
    public Collection<V> values() {
        Collection<V> values = new DoublyLinkedList<>();
        for (Node<K, V> bucket : this.table) {
            Node<K, V> current = bucket;
            while (current != null) {
                values.add(current.getValue());
                current = current.getNext();
            }
        }
        return values;
    }

    /**
     * Returns a set view of the mappings contained in this map
     * @return a set view of the mappings contained in this map
     */
    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K, V>> entrySet = new HashSet<>();
        for (Node<K, V> bucket : this.table) {
            Node<K, V> current = bucket;
            while (current != null) {
                entrySet.add(new AbstractMap.SimpleEntry<>(current.getKey(), current.getValue()));
                current = current.getNext();
            }
        }
        return entrySet;
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this map contains no mapping for the key
     * @param o the key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or null if this map contains no mapping for the key
     */
    @Override
    public V get(Object o) {
        K key = (K) o;
        int hash = this.hash(key);

        if (hash < 0 || hash >= this.table.length) {
            return null;
        }

        Node<K, V> current = this.table[hash];

        while (current != null) {
            if (current.getKey().equals(key)) {
                return current.getValue();
            }
            current = current.getNext();
        }
        return null;
    }

    /**
     * Removes the mapping for the specified key from this map if present
     * @param o key whose mapping is to be removed from the map
     * @return the previous value associated with the specified key, or null if there was no mapping for the key
     */
    @Override
    public V remove(Object o) {
        K key = (K) o;
        int hash = this.hash(key);

        if (hash < 0 || hash >= this.table.length) {
            return null;
        }

        Node<K, V> current = this.table[hash];
        Node<K, V> previous = null;

        while (current != null) {
            if (current.getKey().equals(key)) {
                if (previous == null) {
                    this.table[hash] = current.getNext();
                } else {
                    previous.setNext(current.getNext());
                }
                return current.getValue();
            }
            previous = current;
            current = current.getNext();
        }
        return null;
    }

    /**
     * Checks if the map contains a key
     * @param o key whose presence in this map is to be tested
     * @return true if this map contains the specified key
     */
    @Override
    public boolean containsKey(Object o) {
        K key = (K) o;
        int hash = this.hash(key);

        if (hash < 0 || hash >= this.table.length) {
            return false;
        }

        Node<K, V> current = this.table[hash];

        while (current != null) {
            if (current.getKey().equals(key)) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    /**
     * Returns the number of elements in this collection
     * @return the number of elements in this collection
     */
    @Override
    public int size() {
        int size = 0;
        for (Node<K, V> bucket : this.table) {
            Node<K, V> current = bucket;
            while (current != null) {
                size++;
                current = current.getNext();
            }
        }
        return size;
    }

    /**
     * Checks if the map is empty
     * @return true if the map is empty
     */
    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    /**
     * Checks if the map contains a value
     * @param value value whose presence in this map is to be tested
     * @return true if this map contains a value
     */
    @Override
    public boolean containsValue(Object value) {
        for (Node<K, V> bucket : this.table) {
            Node<K, V> current = bucket;
            while (current != null) {
                if (value.equals(current.getValue())) {
                    return true;
                }
                current = current.getNext();
            }
        }
        return false;
    }

}
