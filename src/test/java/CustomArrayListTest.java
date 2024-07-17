import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.practice.hw2.CustomArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class CustomArrayListTest {

    private CustomArrayList<Integer> list;

    @BeforeEach
    public void setUp() {
        list = new CustomArrayList<>();
    }

    @Test
    public void testAdd() {
        list.add(1);
        assertEquals(1, list.size());
        assertEquals(1, list.get(0));
    }

    @Test
    public void testAddAtIndex() {
        list.add(1);
        list.add(2);
        list.add(1, 3);
        assertEquals(3, list.size());
        assertEquals(1, list.get(0));
        assertEquals(3, list.get(1));
        assertEquals(2, list.get(2));
    }

    @Test
    public void testAddAll() {
        Collection<Integer> collection = Arrays.asList(1, 2, 3);
        list.addAll(collection);
        assertEquals(3, list.size());
        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
        assertEquals(3, list.get(2));
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
        assertEquals(1, list.get(0));
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(1));
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
        assertEquals(2, list.get(0));
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(1));
    }

    @Test
    public void testRemoveByObject() {
        list.add(1);
        list.add(2);
        list.remove((Integer) 1);
        assertEquals(1, list.size());
        assertEquals(2, list.get(0));
    }

    @Test
    public void testSort() {
        list.add(3);
        list.add(1);
        list.add(2);
        list.sort(Comparator.naturalOrder());
        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
        assertEquals(3, list.get(2));
    }

    @Test
    public void testEnsureCapacity() {
        for (int i = 0; i < 15; i++) {
            list.add(i);
        }
        assertEquals(15, list.size());
        for (int i = 0; i < 15; i++) {
            assertEquals(i, list.get(i));
        }
    }

    @Test
    public void testAddNull() {
        assertThrows(NullPointerException.class, () -> list.add(null));
    }

    @Test
    public void testAddAtIndexNull() {
        assertThrows(NullPointerException.class, () -> list.add(0, null));
    }

    @Test
    public void testRemoveNull() {
        assertThrows(NullPointerException.class, () -> list.remove(null));
    }

    @Test
    public void testSortNull() {
        assertThrows(NullPointerException.class, () -> list.sort(null));
    }

    @Test
    public void testAddAllNull() {
        Collection<Integer> nullCollection = null;
        assertThrows(NullPointerException.class, () -> list.addAll(nullCollection));
    }
}
