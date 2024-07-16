package org.practice.hw2;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        CustomList<Integer> arrayListInt = new CustomArrayList<>();
        List<Integer> list = new ArrayList<>();

        arrayListInt.add(0, 0);

        list.add(1000, 100);


        for (int i = 0; i < arrayListInt.size(); i++) {
            System.out.print(arrayListInt.get(i) + " ");
        }
        System.out.println();
        System.out.println(arrayListInt.size());
    }
}
