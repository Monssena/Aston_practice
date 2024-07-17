package org.practice.hw2;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Objects;

public class CustomArrayList<E> implements CustomList<E> {

    private final int DEFAULT_CAPACITY = 10;
    private int size;
    private E[] array;

    public CustomArrayList() {
        size = 0;
        array = (E[]) new Object[DEFAULT_CAPACITY];
    }

    private void ensureCapacity(int size) {
        if (size > array.length) {
            array = Arrays.copyOf(array, Math.max((array.length << 1) - (array.length >> 1), size));
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public void add(E o) {
        Objects.requireNonNull(o);
        ensureCapacity(size + 1);
        array[size++] = o;
    }

    @Override
    public void add(int index, E element) {
        Objects.requireNonNull(element);
        checkIndex(index);
        ensureCapacity(size + 1);
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = element;
        size++;
    }

    @Override
    public void addAll(Collection<? extends E> collection) {
        Objects.requireNonNull(collection);
        if (!collection.isEmpty()) {
            for (E e : collection) add(e);
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
        checkIndex(index);
        return array[index];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void remove(int index) {
        checkIndex(index);
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(array, index + 1, array, index, numMoved);
            array[--size] = null;
        }
    }

    @Override
    public void remove(Object o) {
        Objects.requireNonNull(o);
        for (int i = 0; i < size; i++) {
            if (array[i] != null && array[i].equals(o)) {
                remove(i);
                return;
            }
        }
    }

    @Override
    public void sort(Comparator<? super E> comparator) {
        Objects.requireNonNull(comparator);
        if (size < 2) {
            return;
        }

        E[] temp = (E[]) new Object[size];
        mergeSort(temp, 0, size - 1, comparator);
    }

    private void mergeSort(E[] tempArray, int leftElement, int rightElement, Comparator<? super E> comparator) {
        if (leftElement < rightElement) {
            int midElement = (rightElement + leftElement) / 2;
            mergeSort(tempArray, leftElement, midElement, comparator);
            mergeSort(tempArray, midElement + 1, rightElement, comparator);
            merge(tempArray, leftElement, midElement, rightElement, comparator);
        }
    }

    private void merge(E[] tempArray, int leftElement, int midElement, int rightElement, Comparator<? super E> comparator) {
        System.arraycopy(array, leftElement, tempArray, leftElement, rightElement - leftElement + 1);

        int i = leftElement;
        int j = midElement + 1;
        int k = leftElement;

        while (i <= midElement && j <= rightElement) {
            if (comparator.compare(tempArray[i], tempArray[j]) <= 0) {
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
