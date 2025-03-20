package collection.binarysearchtree;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BinarySearchTreeTest
{
    private BinarySearchTree<Integer> tree;
    
    @Before
    public void setup()
	{
        this.tree = new BinarySearchTree<Integer>();
    }
    
    @Test
    public void getRoot()
	{
        assertNull(tree.getRoot());
        
        tree.add(10);
        assertNotNull(tree.getRoot());
        assertEquals(Integer.valueOf(10), tree.getRoot().getData());
    }
    
    @Test
    public void findNode()
	{
        tree.add(10);
        tree.add(5);
        tree.add(15);
        
        assertNotNull(tree.findNode(tree.getRoot(), 5));
        assertNotNull(tree.findNode(tree.getRoot(), 15));
        assertNull(tree.findNode(tree.getRoot(), 100));
    }
    
    @Test
    public void size()
	{
        assertEquals(0, tree.size());
        
        tree.add(10);
        tree.add(5);
        tree.add(15);
        
        assertEquals(3, tree.size());
    }
    
    @Test
    public void isEmpty()
	{
        assertTrue(tree.isEmpty());
        
        tree.add(10);
        assertFalse(tree.isEmpty());
    }
    
    @Test
    public void iterator()
	{
        tree.add(10);
        tree.add(5);
        tree.add(15);
        
        int count = 0;
        for (Integer value : tree)
	    {
            count++;
        }
        
        assertEquals(3, count);
    }
    
    @Test
    public void contains()
	{
        tree.add(10);
        tree.add(5);
        tree.add(15);
        
        assertTrue(tree.contains(10));
        assertTrue(tree.contains(5));
        assertTrue(tree.contains(15));
        assertFalse(tree.contains(100));
    }
    
    @Test
    public void toArray()
	{
        tree.add(10);
        tree.add(5);
        tree.add(15);
        
        Object[] expected = {5, 10, 15};
        Object[] actual = tree.toArray();
        
        assertArrayEquals(expected, actual);
    }
    
    @Test
    public void testToArray()
	{
        tree.add(10);
        tree.add(5);
        tree.add(15);
        
        Object[] expected = {5, 10, 15};
        Object[] actual = tree.toArray();
        
        assertArrayEquals(expected, actual);
    }
    
    @Test
    public void add()
	{
        tree.add(10);
        tree.add(5);
        
        assertTrue(tree.contains(10));
        assertTrue(tree.contains(5));
        assertEquals(2, tree.size());
    }
    
    @Test
    public void remove()
	{
        tree.add(10);
        tree.add(5);
        tree.add(15);
        
        assertTrue(tree.contains(10));
        
        tree.remove(10);
        assertFalse(tree.contains(10));
        assertEquals(2, tree.size());
    }
    
    @Test
    public void containsAll()
	{
        tree.add(10);
        tree.add(5);
        tree.add(15);
        
        BinarySearchTree<Integer> otherTree = new BinarySearchTree<>();
        otherTree.add(10);
        otherTree.add(5);
        
        assertTrue(tree.containsAll(otherTree));
        
        otherTree.add(20);
        assertFalse(tree.containsAll(otherTree));
    }
    
    @Test
    public void addAll()
	{
        tree.add(10);
        tree.add(5);
        
        BinarySearchTree<Integer> otherTree = new BinarySearchTree<>();
        otherTree.add(15);
        otherTree.add(20);
        
        tree.addAll(otherTree);
        
        assertTrue(tree.contains(15));
        assertTrue(tree.contains(20));
        assertEquals(4, tree.size());
    }
    
    @Test
    public void removeAll()
	{
        tree.add(10);
        tree.add(5);
        tree.add(15);
        
        BinarySearchTree<Integer> otherTree = new BinarySearchTree<>();
        otherTree.add(5);
        otherTree.add(15);
        
        tree.removeAll(otherTree);
        
        assertFalse(tree.contains(5));
        assertFalse(tree.contains(15));
        assertEquals(1, tree.size());
    }
    
    @Test
    public void retainAll()
	{
        tree.add(10);
        tree.add(5);
        tree.add(15);
        
        BinarySearchTree<Integer> otherTree = new BinarySearchTree<>();
        otherTree.add(5);
        otherTree.add(15);
        
        tree.retainAll(otherTree);
        
        assertTrue(tree.contains(5));
        assertTrue(tree.contains(15));
        assertFalse(tree.contains(10));
    }
    
    @Test
    public void clear()
	{
        tree.add(10);
        tree.add(5);
        
        assertEquals(2, tree.size());
        
        tree.clear();
        
        assertTrue(tree.isEmpty());
        assertEquals(0, tree.size());
    }
}
