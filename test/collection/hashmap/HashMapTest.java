package collection.hashmap;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HashMapTest
{
    private HashMap<String, Integer> map;
    
    @Before
    public void setup()
    {
        this.map = new HashMap<>();
    }
    
    @Test
    public void put()
    {
        this.map.put("One", 1);
        assertEquals((Integer) 1, this.map.get("One"));
    }
    
    @Test
    public void putAll()
    {
        HashMap<String, Integer> otherMap = new HashMap<>();
        otherMap.put("Two", 2);
        otherMap.put("Three", 3);
        this.map.put("One", 1);
        this.map.putAll(otherMap);
        
        assertEquals((Integer) 1, this.map.get("One"));
        assertEquals((Integer) 2, this.map.get("Two"));
        assertEquals((Integer) 3, this.map.get("Three"));
    }
    
    @Test
    public void clear()
    {
        this.map.put("One", 1);
        this.map.put("Two", 2);
        this.map.clear();
        assertTrue(this.map.isEmpty());
    }
    
    @Test
    public void keySet()
    {
        this.map.put("One", 1);
        this.map.put("Two", 2);
        this.map.put("Three", 3);
        assertTrue(this.map.keySet().contains("One"));
        assertTrue(this.map.keySet().contains("Two"));
        assertTrue(this.map.keySet().contains("Three"));
    }
    
    @Test
    public void values()
    {
        this.map.put("One", 1);
        this.map.put("Two", 2);
        this.map.put("Three", 3);
        assertTrue(this.map.values().contains(1));
        assertTrue(this.map.values().contains(2));
        assertTrue(this.map.values().contains(3));
    }
    
    @Test
    public void entrySet()
    {
        this.map.put("One", 1);
        this.map.put("Two", 2);
        this.map.put("Three", 3);
        assertTrue(this.map.entrySet().contains(new java.util.AbstractMap.SimpleEntry<>("One", 1)));
        assertTrue(this.map.entrySet().contains(new java.util.AbstractMap.SimpleEntry<>("Two", 2)));
        assertTrue(this.map.entrySet().contains(new java.util.AbstractMap.SimpleEntry<>("Three", 3)));
    }
    
    @Test
    public void get()
    {
        this.map.put("One", 1);
        assertEquals((Integer) 1, this.map.get("One"));
        assertNull(this.map.get("NonExistent"));
    }
    
    @Test
    public void remove()
    {
        this.map.put("One", 1);
        this.map.remove("One");
        assertNull(this.map.get("One"));
    }
    
    @Test
    public void containsKey()
    {
        this.map.put("One", 1);
        assertTrue(this.map.containsKey("One"));
        assertFalse(this.map.containsKey("NonExistent"));
    }
    
    @Test
    public void size()
    {
        this.map.put("One", 1);
        this.map.put("Two", 2);
        assertEquals(2, this.map.size());
        this.map.remove("Two");
        assertEquals(1, this.map.size());
    }
    
    @Test
    public void isEmpty()
    {
        assertTrue(this.map.isEmpty());
        this.map.put("One", 1);
        assertFalse(this.map.isEmpty());
        this.map.clear();
        assertTrue(this.map.isEmpty());
    }
    
    @Test
    public void containsValue()
    {
        this.map.put("One", 1);
        this.map.put("Two", 2);
        assertTrue(this.map.containsValue(1));
        assertFalse(this.map.containsValue(3));
    }
}