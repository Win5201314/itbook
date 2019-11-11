package com.zsl.boss.demo;

import java.util.*;

public class Test {

    @org.junit.Test
    public void main() {
        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(3);
        List<Integer> list2 = new ArrayList<>();
        list2.add(1);
        list2.add(5);
        List<Integer> list = execute(list1, list2);
        for (Integer i : list) {
            System.out.println(i);
        }
    }

    public List<Integer> execute(List<Integer> list1, List<Integer> list2) {
        if (list1 != null && list2 == null) {
            list2 = Collections.emptyList();
        } else if (list1 == null && list2 != null) {
            list1 = Collections.emptyList();
        } else if (list1 == null && list2 == null) {
            return null;
        }

        List<Integer> arrayList = new ArrayList<>(list1);
        arrayList.addAll(list2);

        Set<Integer> set = new HashSet<>(arrayList);

        ArrayList<Integer> integers = new ArrayList<>(set);
        Collections.sort(integers);

        return integers;
    }
}
