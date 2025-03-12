package collection.doublylinkedlist;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class DoublyLinkedListTest
{
    private DoublyLinkedList<Integer> list;
    @BeforeEach
    public void setup()
    {
        list = new DoublyLinkedList<Integer>();
    }
    
    @Test
    public void toBST()
    {
    }
    
    @Test
    public void sizeEqualsZero()
    {
        assertEquals(0, list.size());
    }
    
    @Test
    public void sizeEqualsOne()
    {
        list.add(1);
        assertEquals(1, list.size());
    }
    
    @Test
    public void sizeEqualsHundred()
    {
        for (int i = 0; i < 100; i++)
        {
            list.add(i);
        }
        assertEquals(100, list.size());
    }
    
    @Test
    public void isEmptyTrue()
    {
        assertTrue(list.isEmpty());
    }
    
    @Test
    public void isEmptyFalse()
    {
        list.add(1);
        assertFalse(list.isEmpty());
    }
    
    @Test
    public void containsTrue()
    {
        for (int i = 0; i < 100; i++)
        {
            list.add(i);
        }
        assertTrue(list.contains(0));
        assertTrue(list.contains(66));
        assertTrue(list.contains(99));
    }
    
    @Test
    public void containsFalse()
    {
        for (int i = 0; i < 100; i++)
        {
            list.add(i);
        }
        assertFalse(list.contains(-1));
        assertFalse(list.contains(100));
    }
    
    @Test
    public void toArray()
    {
        // TODO
    }
    
    @Test
    public void testToArray()
    {
        // TODO
    }
    
    @Test
    public void addHead()
    {
        assertNull(list.getHead());
        for (int i = 0; i < 100; i++)
        {
            list.add(i);
        }
        assertEquals(0, list.getHead().getData());
    }
    
    @Test
    public void addTail()
    {
        assertNull(list.getTail());
        for (int i = 0; i < 50; i++)
        {
            list.add(i);
        }
        assertEquals(49, list.getTail().getData());
        for (int i = 50; i < 100; i++)
        {
            list.add(i);
        }
        assertEquals(99, list.getTail().getData());
    }
    
    @Test
    public void removeTrue()
    {
        for (int i = 0; i < 100; i++)
        {
            list.add(i);
        }
        assertTrue(list.contains(50));
        assertTrue(list.remove(50));
        assertFalse(list.contains(50));
    }
    
    @Test
    public void removeFalse()
    {
        for (int i = 0; i < 100; i++)
        {
            list.add(i);
        }
        assertFalse(list.contains(100));
        assertFalse(list.remove(100));
        assertFalse(list.contains(100));
    }
    
    @Test
    public void containsAllTrue()
    {
        ArrayList<Integer> compareList = new ArrayList<>();
        for (int i = 0; i < 100; i++)
        {
            compareList.add(i);
            list.add(i);
        }
        assertTrue(list.containsAll(compareList));
    }
    
    @Test
    public void containsAllFalse()
    {
        ArrayList<Integer> compareList = new ArrayList<>();
        for (int i = 0; i < 100; i++)
        {
            compareList.add(i);
            if (i % 2 == 0)
            {
                list.add(i);
            }
        }
        assertFalse(list.containsAll(compareList));
    }
    
    @Test
    public void addAll()
    {
        ArrayList<Integer> compareList = new ArrayList<>();
        for (int i = 0; i < 100; i++)
        {
            compareList.add(i);
        }
        assertTrue(list.addAll(compareList));
        assertTrue(list.containsAll(compareList));
    }
    
    @Test
    public void removeAllTrue()
    {
        ArrayList<Integer> compareList = new ArrayList<>();
        for (int i = 0; i < 100; i++)
        {
            compareList.add(i);
            list.add(i);
        }
        assertTrue(list.removeAll(compareList));
        assertFalse(list.containsAll(compareList));
        assertTrue(list.isEmpty());
    }
    
    @Test
    public void removeAllFalse()
    {
        ArrayList<Integer> compareList = new ArrayList<>();
        for (int i = 0; i < 100; i++)
        {
            compareList.add(i);
        }
        assertTrue(list.isEmpty());
        assertFalse(list.removeAll(compareList));
        assertFalse(list.containsAll(compareList));
        assertTrue(list.isEmpty());
    }
    
    @Test
    public void retainAll()
    {
        // TODO
    }
    
    @Test
    public void clear()
    {
        assertTrue(list.isEmpty());
        for (int i = 0; i < 100; i++)
        {
            list.add(i);
        }
        assertFalse(list.isEmpty());
        list.clear();
        assertTrue(list.isEmpty());
    }
}