package com.balance.gmall.streams;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: yunzhang.du
 * @date: 2020年01月10日
 * @version: v1.0
 * @since: JDK 1.8
 */
public class CollectionMainTest {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.forEach(i -> {
            if (i == 3) {
                list.remove(i);
            }
        });
        System.out.println("result=====>" + list);
    }
}
