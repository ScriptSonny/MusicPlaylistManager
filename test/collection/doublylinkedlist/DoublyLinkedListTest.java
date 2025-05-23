package collection.doublylinkedlist;

import collection.binarysearchtree.BinarySearchTree;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class DoublyLinkedListTest {
    private DoublyLinkedList<Integer> list;
    
    @Before
    public void setup() {
        this.list = new DoublyLinkedList<Integer>();
    }
    
    @Test
    public void sizeEqualsZero() {
        assertEquals(0, this.list.size());
    }
    
    @Test
    public void sizeEqualsOne() {
        this.list.add(1);
        assertEquals(1, this.list.size());
    }
    
    @Test
    public void sizeEqualsHundred() {
        for (int i = 0; i < 100; i++) {
            this.list.add(i);
        }
        assertEquals(100, this.list.size());
    }
    
    @Test
    public void isEmptyTrue() {
        assertTrue(this.list.isEmpty());
    }
    
    @Test
    public void isEmptyFalse() {
        this.list.add(1);
        assertFalse(this.list.isEmpty());
    }
    
    @Test
    public void containsTrue() {
        for (int i = 0; i < 100; i++) {
            this.list.add(i);
        }
        assertTrue(this.list.contains(0));
        assertTrue(this.list.contains(66));
        assertTrue(this.list.contains(99));
    }
    
    @Test
    public void containsFalse() {
        for (int i = 0; i < 100; i++) {
            this.list.add(i);
        }
        assertFalse(this.list.contains(-1));
        assertFalse(this.list.contains(100));
    }
    
    @Test
    public void toArray() {
        this.list.add(10);
        this.list.add(5);
        this.list.add(15);
        
        Object[] expected = {10, 5, 15};
        Object[] actual = this.list.toArray();
        
        assertArrayEquals(expected, actual);
    }
    
    @Test
    public void addHead() {
        assertNull(list.getHead());
        for (int i = 0; i < 100; i++) {
            this.list.add(i);
        }
        assertEquals(0, this.list.getHead().getData());
    }
    
    @Test
    public void addTail() {
        assertNull(this.list.getTail());
        for (int i = 0; i < 50; i++) {
            this.list.add(i);
        }
        assertEquals(49, this.list.getTail().getData());
        for (int i = 50; i < 100; i++) {
            this.list.add(i);
        }
        assertEquals(99, this.list.getTail().getData());
    }
    
    @Test
    public void removeTrue() {
        for (int i = 0; i < 100; i++) {
            this.list.add(i);
        }
        assertTrue(this.list.contains(50));
        assertTrue(this.list.remove(50));
        assertFalse(this.list.contains(50));
    }
    
    @Test
    public void removeFalse() {
        for (int i = 0; i < 100; i++) {
            this.list.add(i);
        }
        assertFalse(this.list.contains(100));
        assertFalse(this.list.remove(100));
        assertFalse(this.list.contains(100));
    }
    
    @Test
    public void containsAllTrue() {
        ArrayList<Integer> compareList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            compareList.add(i);
            this.list.add(i);
        }
        assertTrue(this.list.containsAll(compareList));
    }
    
    @Test
    public void containsAllFalse() {
        ArrayList<Integer> compareList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            compareList.add(i);
            if (i % 2 == 0) {
                this.list.add(i);
            }
        }
        assertFalse(this.list.containsAll(compareList));
    }
    
    @Test
    public void addAll() {
        ArrayList<Integer> compareList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            compareList.add(i);
        }
        assertTrue(this.list.addAll(compareList));
        assertTrue(this.list.containsAll(compareList));
    }
    
    @Test
    public void removeAllTrue() {
        ArrayList<Integer> compareList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            compareList.add(i);
            this.list.add(i);
        }
        assertTrue(this.list.removeAll(compareList));
        assertFalse(this.list.containsAll(compareList));
        assertTrue(this.list.isEmpty());
    }
    
    @Test
    public void removeAllFalse() {
        ArrayList<Integer> compareList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            compareList.add(i);
        }
        assertTrue(this.list.isEmpty());
        assertFalse(this.list.removeAll(compareList));
        assertFalse(this.list.containsAll(compareList));
        assertTrue(this.list.isEmpty());
    }
    
    @Test
    public void retainAll() {
        this.list.add(10);
        this.list.add(5);
        this.list.add(15);
        
        BinarySearchTree<Integer> otherTree = new BinarySearchTree<>();
        otherTree.add(5);
        otherTree.add(15);
        
        this.list.retainAll(otherTree);
        
        assertTrue(this.list.contains(5));
        assertTrue(this.list.contains(15));
        assertFalse(this.list.contains(10));
    }
    
    @Test
    public void clear() {
        assertTrue(this.list.isEmpty());
        for (int i = 0; i < 100; i++) {
            this.list.add(i);
        }
        assertFalse(this.list.isEmpty());
        this.list.clear();
        assertTrue(this.list.isEmpty());
    }
}