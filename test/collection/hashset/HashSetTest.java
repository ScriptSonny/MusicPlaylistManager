package collection.hashset;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class HashSetTest {
    private HashSet<Integer> set;
    @Before
    public void setup() {
        this.set = new HashSet<>();
    }
    
    @Test
    public void add() {
        assertEquals(0, this.set.size());
        for (int i = 0; i < 10; i++) {
            this.set.add(i);
        }
        for (int i = 0; i < 10; i++) {
            assertEquals(i, this.set.toArray()[i]);
        }
        assertEquals(10, this.set.size());
        assertFalse(this.set.add(0));
    }
    
    @Test
    public void addAll() {
        assertEquals(0, this.set.size());
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        this.set.addAll(list);
        for (int i = 0; i < 10; i++) {
            assertEquals(i, this.set.toArray()[i]);
        }
        assertEquals(10, this.set.size());
    }
    
    @Test
    public void clear() {
        assertEquals(0, this.set.size());
        for (int i = 0; i < 10; i++) {
            this.set.add(i);
        }
        assertEquals(10, this.set.size());
        this.set.clear();
        assertEquals(0, this.set.size());
    }
    
    @Test
    public void contains() {
        assertFalse(this.set.contains(0));
        this.set.add(0);
        assertTrue(this.set.contains(0));
    }
    
    @Test
    public void containsAll() {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        assertFalse(this.set.containsAll(list));
        this.set.addAll(list);
        assertTrue(this.set.containsAll(list));
        this.set.add(10);
        assertTrue(this.set.containsAll(list));
        list.add(55);
        assertFalse(this.set.containsAll(list));
    }
    
    @Test
    public void isEmpty() {
        assertTrue(this.set.isEmpty());
        this.set.add(0);
        assertFalse(this.set.isEmpty());
    }
    
    @Test
    public void remove() {
        this.set.add(5);
        assertTrue(this.set.contains(5));
        this.set.remove(5);
        assertFalse(this.set.contains(5));
        assertEquals(0, this.set.size());
        assertFalse(this.set.remove(10));  // element doesn't exist
    }
    
    @Test
    public void removeAll() {
        this.set.add(1);
        this.set.add(2);
        this.set.add(3);
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1, 2));
        assertTrue(this.set.removeAll(list));
        assertFalse(this.set.contains(1));
        assertFalse(this.set.contains(2));
        assertTrue(this.set.contains(3));
        assertEquals(1, this.set.size());
        assertFalse(this.set.removeAll(list));  // no more elements to remove
    }
    
    @Test
    public void retainAll()
    {
        this.set.add(1);
        this.set.add(2);
        this.set.add(3);
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(2, 3));
        assertTrue(this.set.retainAll(list));
        assertTrue(this.set.contains(2));
        assertTrue(this.set.contains(3));
        assertFalse(this.set.contains(1));
        assertEquals(2, this.set.size());
        assertFalse(this.set.retainAll(list));  // no changes
    }
    
    @Test
    public void size() {
        assertEquals(0, this.set.size());
        this.set.add(1);
        assertEquals(1, this.set.size());
        this.set.add(2);
        assertEquals(2, this.set.size());
        this.set.remove(1);
        assertEquals(1, this.set.size());
    }
    
    @Test
    public void iterator() {
        this.set.add(1);
        this.set.add(2);
        this.set.add(3);
        
        HashSetIterator<Integer> iterator = this.set.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(1, (int) iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(2, (int) iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(3, (int) iterator.next());
        assertFalse(iterator.hasNext());
    }
    
    @Test
    public void testEquals() {
        HashSet<Integer> otherSet = new HashSet<>();
        assertEquals(this.set, otherSet);
        this.set.add(1);
        otherSet.add(1);
        assertEquals(this.set, otherSet);
        this.set.add(2);
        otherSet.add(2);
        assertEquals(this.set, otherSet);
        otherSet.add(3);
        assertNotEquals(this.set, otherSet);
    }
    
    @Test
    public void testHashCode() {
        this.set.add(1);
        this.set.add(2);
        HashSet<Integer> otherSet = new HashSet<>();
        otherSet.add(1);
        otherSet.add(2);
        assertEquals(this.set.hashCode(), otherSet.hashCode());
        otherSet.add(3);
        assertNotEquals(this.set.hashCode(), otherSet.hashCode());
    }
    
    @Test
    public void testToString() {
        assertEquals("HashSet{Size: 0}", this.set.toString());
        this.set.add(1);
        this.set.add(2);
        this.set.add(3);
        String expected = "HashSet{1, 2, 3, Size: 3}";
        String actual = this.set.toString();
        assertTrue(actual.startsWith("HashSet{"));
        assertTrue(actual.endsWith("}"));
        assertTrue(actual.contains("1"));
        assertTrue(actual.contains("2"));
        assertTrue(actual.contains("3"));
        assertEquals(expected, actual);
    }
    
    @Test
    public void toArray() {
        this.set.add(1);
        this.set.add(2);
        this.set.add(3);
        Object[] array = this.set.toArray();
        assertEquals(3, array.length);
        assertTrue(Arrays.asList(array).contains(1));
        assertTrue(Arrays.asList(array).contains(2));
        assertTrue(Arrays.asList(array).contains(3));
    }
    
    @Test
    public void testToArray() {
        this.set.add(1);
        this.set.add(2);
        this.set.add(3);
        Integer[] array = this.set.toArray(new Integer[0]);
        assertEquals(3, array.length);
        assertTrue(Arrays.asList(array).contains(1));
        assertTrue(Arrays.asList(array).contains(2));
        assertTrue(Arrays.asList(array).contains(3));
    }
}