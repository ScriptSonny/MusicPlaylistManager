package collection.binarysearchtree;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BinarySearchTreeTest {
    private BinarySearchTree<Integer> tree;
    
    @Before
    public void setup() {
        this.tree = new BinarySearchTree<Integer>();
    }
    
    @Test
    public void getRoot() {
        assertNull(this.tree.getRoot());
        
        this.tree.add(10);
        assertNotNull(this.tree.getRoot());
        assertEquals(Integer.valueOf(10), this.tree.getRoot().getData());
    }
    
    @Test
    public void findNode() {
        this.tree.add(10);
        this.tree.add(5);
        this.tree.add(15);
        
        assertNotNull(this.tree.findNode(this.tree.getRoot(), 5));
        assertNotNull(this.tree.findNode(this.tree.getRoot(), 15));
        assertNull(this.tree.findNode(this.tree.getRoot(), 100));
    }
    
    @Test
    public void size() {
        assertEquals(0, this.tree.size());
        
        this.tree.add(10);
        this.tree.add(5);
        this.tree.add(15);
        
        assertEquals(3, this.tree.size());
    }
    
    @Test
    public void isEmpty() {
        assertTrue(this.tree.isEmpty());
        
        this.tree.add(10);
        assertFalse(this.tree.isEmpty());
    }
    
    @Test
    public void iterator() {
        this.tree.add(10);
        this.tree.add(5);
        this.tree.add(15);
        
        int count = 0;
        for (Integer _value : this.tree) {
            count++;
        }
        
        assertEquals(3, count);
    }
    
    @Test
    public void contains() {
        this.tree.add(10);
        this.tree.add(5);
        this.tree.add(15);
        
        assertTrue(this.tree.contains(10));
        assertTrue(this.tree.contains(5));
        assertTrue(this.tree.contains(15));
        assertFalse(this.tree.contains(100));
    }
    
    @Test
    public void toArray() {
        this.tree.add(10);
        this.tree.add(5);
        this.tree.add(15);
        
        Object[] expected = {5, 10, 15};
        Object[] actual = this.tree.toArray();
        
        assertArrayEquals(expected, actual);
    }
    
    @Test
    public void add() {
        this.tree.add(10);
        this.tree.add(5);
        
        assertTrue(this.tree.contains(10));
        assertTrue(this.tree.contains(5));
        assertEquals(2, this.tree.size());
    }
    
    @Test
    public void remove() {
        this.tree.add(10);
        this.tree.add(5);
        this.tree.add(15);
        
        assertTrue(this.tree.contains(10));
        
        this.tree.remove(10);
        assertFalse(this.tree.contains(10));
        assertEquals(2, this.tree.size());
    }
    
    @Test
    public void containsAll() {
        this.tree.add(10);
        this.tree.add(5);
        this.tree.add(15);
        
        BinarySearchTree<Integer> otherTree = new BinarySearchTree<>();
        otherTree.add(10);
        otherTree.add(5);
        
        assertTrue(this.tree.containsAll(otherTree));
        
        otherTree.add(20);
        assertFalse(this.tree.containsAll(otherTree));
    }
    
    @Test
    public void addAll() {
        this.tree.add(10);
        this.tree.add(5);
        
        BinarySearchTree<Integer> otherTree = new BinarySearchTree<>();
        otherTree.add(15);
        otherTree.add(20);
        
        this.tree.addAll(otherTree);
        
        assertTrue(this.tree.contains(15));
        assertTrue(this.tree.contains(20));
        assertEquals(4, this.tree.size());
    }
    
    @Test
    public void removeAll() {
        this.tree.add(10);
        this.tree.add(5);
        this.tree.add(15);
        
        BinarySearchTree<Integer> otherTree = new BinarySearchTree<>();
        otherTree.add(5);
        otherTree.add(15);
        
        this.tree.removeAll(otherTree);
        
        assertFalse(this.tree.contains(5));
        assertFalse(this.tree.contains(15));
        assertEquals(1, this.tree.size());
    }
    
    @Test
    public void retainAll() {
        this.tree.add(10);
        this.tree.add(5);
        this.tree.add(15);
        
        BinarySearchTree<Integer> otherTree = new BinarySearchTree<>();
        otherTree.add(5);
        otherTree.add(15);
        
        this.tree.retainAll(otherTree);
        
        assertTrue(this.tree.contains(5));
        assertTrue(this.tree.contains(15));
        assertFalse(this.tree.contains(10));
    }
    
    @Test
    public void clear() {
        this.tree.add(10);
        this.tree.add(5);
        
        assertEquals(2, this.tree.size());
        
        this.tree.clear();
        
        assertTrue(this.tree.isEmpty());
        assertEquals(0, this.tree.size());
    }
}
