package org.practice.hw2;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;

public class CustomArrayList<E> implements CustomList<E> {

    private int capacity = 10;
    private int size;
    private E[] array;

    public CustomArrayList() {

        size = 0;
        array = (E[]) new Object[capacity];
    }

    private void ensureCapacity(int size) {

        if (size > array.length) {
            capacity = Math.max((capacity * 3) / 2 + 1, size);
            array = Arrays.copyOf(array, capacity);
        }
    }

    @Override
    public void add(E o) {

        if (o == null) throw new NullPointerException();

        ensureCapacity(size + 1);
        array[size++] = o;
    }

    @Override
    public void add(int index, E element) {
        //!
        if (element == null) throw new NullPointerException();
        if (index < 0 || index > size) throw new IndexOutOfBoundsException();

        ensureCapacity(size + 1);
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = element;
        size++;
    }

    @Override
    public void addAll(Collection<? extends E> c) {

        if (!c.isEmpty()) {
            for (E e : c) add(e);
        }
    }

    @Override
    public void clear() {

        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
        size = 0;
    }

    @Override
    public E get(int index) {

        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        return array[index];
    }

    @Override
    public boolean isEmpty() {

        return size == 0;
    }

    @Override
    public void remove(int index) {

        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();

        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(array, index + 1, array, index, numMoved);
            array[--size] = null;
        }
    }

    @Override
    public void remove(Object o) {

        if (o == null) throw new NullPointerException();

        for (int i = 0; i < size; i++) {
            if (array[i] != null && array[i].equals(o)) {
                remove(i);
                return;
            }
        }
    }

    @Override
    public void sort(Comparator<? super E> c) {

        if (c == null) throw new NullPointerException();
        if (size < 2) return;

        E[] temp = (E[]) new Object[size];
        mergeSort(temp, 0, size - 1, c);
    }

    private void mergeSort(E[] tempArray, int leftElement, int rightElement, Comparator<? super E> c) {

        if (leftElement < rightElement) {
            int midElement = (rightElement + leftElement) / 2;
            mergeSort(tempArray, leftElement, midElement, c);
            mergeSort(tempArray, midElement + 1, rightElement, c);
            merge(tempArray, leftElement, midElement, rightElement, c);
        }
    }

    private void merge(E[] tempArray, int leftElement, int midElement, int rightElement, Comparator<? super E> c) {

        System.arraycopy(array, leftElement, tempArray, leftElement, rightElement - leftElement + 1);

        int i = leftElement;
        int j = midElement + 1;
        int k = leftElement;

        while (i <= midElement && j <= rightElement) {
            if (c.compare(tempArray[i], tempArray[j]) <= 0) {
                array[k++] = tempArray[i++];
            } else {
                array[k++] = tempArray[j++];
            }
        }

        while (i <= midElement) {
            array[k++] = tempArray[i++];
        }
    }

    @Override
    public int size() {

        return size;
    }

    public Object[] toArray() {

        return Arrays.copyOf(array, size);
    }
}
