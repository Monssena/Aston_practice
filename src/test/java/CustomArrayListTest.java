import org.junit.Before;
import org.junit.Test;
import org.practice.hw2.CustomArrayList;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;

public class CustomArrayListTest {

    private CustomArrayList<Integer> list;

    @Before
    public void setUp() {
        list = new CustomArrayList<>();
    }

    @Test
    public void testAdd() {
        list.add(1);
        assertEquals(1, list.size());
        assertEquals((Integer) 1, list.get(0));
    }

    @Test
    public void testAddAtIndex() {
        list.add(1);
        list.add(2);
        list.add(1, 3);
        assertEquals(3, list.size());
        assertEquals((Integer) 1, list.get(0));
        assertEquals((Integer) 3, list.get(1));
        assertEquals((Integer) 2, list.get(2));
    }

    @Test
    public void testAddAll() {
        Collection<Integer> collection = Arrays.asList(1, 2, 3);
        list.addAll(collection);
        assertEquals(3, list.size());
        assertEquals((Integer) 1, list.get(0));
        assertEquals((Integer) 2, list.get(1));
        assertEquals((Integer) 3, list.get(2));
    }

    @Test
    public void testClear() {
        list.add(1);
        list.add(2);
        list.clear();
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
    }

    @Test
    public void testGet() {
        list.add(1);
        assertEquals((Integer) 1, list.get(0));
        try {
            list.get(1);
            fail("Expected IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            // expected
        }
    }

    @Test
    public void testIsEmpty() {
        assertTrue(list.isEmpty());
        list.add(1);
        assertFalse(list.isEmpty());
    }

    @Test
    public void testRemoveByIndex() {
        list.add(1);
        list.add(2);
        list.remove(0);
        assertEquals(1, list.size());
        assertEquals((Integer) 2, list.get(0));
        try {
            list.get(1);
            fail("Expected IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            // expected
        }
    }

    @Test
    public void testRemoveByObject() {
        list.add(1);
        list.add(2);
        list.remove((Integer) 1);
        assertEquals(1, list.size());
        assertEquals((Integer) 2, list.get(0));
    }

    @Test
    public void testSort() {
        list.add(3);
        list.add(1);
        list.add(2);
        list.sort(Comparator.naturalOrder());
        assertEquals((Integer) 1, list.get(0));
        assertEquals((Integer) 2, list.get(1));
        assertEquals((Integer) 3, list.get(2));
    }

    @Test
    public void testEnsureCapacity() {
        for (int i = 0; i < 15; i++) {
            list.add(i);
        }
        assertEquals(15, list.size());
        for (int i = 0; i < 15; i++) {
            assertEquals((Integer) i, list.get(i));
        }
    }

    @Test(expected = NullPointerException.class)
    public void testAddNull() {
        list.add(null);
    }

    @Test(expected = NullPointerException.class)
    public void testAddAtIndexNull() {
        list.add(0, null);
    }

    @Test(expected = NullPointerException.class)
    public void testRemoveNull() {
        list.remove(null);
    }

    @Test(expected = NullPointerException.class)
    public void testSortNull() {
        list.sort(null);
    }

    @Test(expected = NullPointerException.class)
    public void testAddAllNull() {
        Collection<Integer> nullCollection = null;
        list.addAll(nullCollection);
    }
}
