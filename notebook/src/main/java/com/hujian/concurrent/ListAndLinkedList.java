package com.hujian.concurrent;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * ArrayList ArraySet Set
 */
public class ListAndLinkedList {
    public static void main(String[] args) {
        //底层使用数组实现，有无参和有参两种构造方法
        List<Integer> list = new ArrayList<>(8);
        list.add(122);
        System.out.println(list.size());
        LinkedList linkedList = new LinkedList(list);
        linkedList.add(2);
        System.out.println(linkedList);

    }
}
