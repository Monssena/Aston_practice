package org.practice.hw2;

import java.util.Collection;
import java.util.Comparator;

public interface CustomList<E> {

    void add(E e);

    void add(int index, E element);

    void addAll(Collection<? extends E> c);

    void clear();

    E get(int index);

    boolean isEmpty();

    void remove(int index);

    void remove(Object o);

    void sort(Comparator<? super E> c);

    int size();
}
