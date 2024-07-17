package org.practice.hw2;

import java.util.Arrays;
import java.util.Comparator;

public class Main {

    public static void main(String[] args) {

        // Создаем новый экземпляр CustomArrayList
        CustomArrayList<Integer> list = new CustomArrayList<>();

        // Добавляем элементы в список
        list.add(5);
        list.add(3);
        list.add(8);
        System.out.println("После добавления элементов: " + Arrays.toString(list.toArray()));

        // Добавляем элемент по индексу
        list.add(1, 4);
        System.out.println("После добавления элемента по индексу 1: " + Arrays.toString(list.toArray()));

        // Добавляем коллекцию элементов
        list.addAll(Arrays.asList(7, 6, 2));
        System.out.println("После добавления коллекции элементов: " + Arrays.toString(list.toArray()));

        // Получаем элемент по индексу
        System.out.println("Элемент по индексу 2: " + list.get(2));

        // Проверяем, пуст ли список
        System.out.println("Список пустой: " + list.isEmpty());

        // Удаляем элемент по индексу
        list.remove(1);
        System.out.println("После удаления элемента по индексу 1: " + Arrays.toString(list.toArray()));

        // Удаляем элемент по значению
        list.remove((Integer) 6);
        System.out.println("После удаления элемента 6: " + Arrays.toString(list.toArray()));

        // Сортируем список
        list.sort(Comparator.naturalOrder());
        System.out.println("После сортировки: " + Arrays.toString(list.toArray()));

        // Очищаем список
        list.clear();
        System.out.println("После очистки списка: " + Arrays.toString(list.toArray()));
        System.out.println("Список пустой: " + list.isEmpty());
    }
}
