package com.balance.gmall.streams;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: yunzhang.du
 * @date: 2019年12月30日
 * @version: v1.0
 * @since: JDK 1.8
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CollectionTest {

    private static List<String> word;

    static {
        String contents = null;
        try {
            contents = new String(Files.readAllBytes(Paths.get("test.txt")), StandardCharsets.UTF_8);
            word = Arrays.asList(contents.split("\\PL+"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void streamTest1() throws IOException {
        long begin = System.currentTimeMillis();
        long count = 0;
        for (String w : word) {
            if (w.length() > 5)
                count++;
        }
        long end = System.currentTimeMillis();
        System.out.println("耗时=====>" + (end - begin));
        System.out.println("result=====>" + count);
    }

    @Test
    public void streamTest2() throws IOException {
        long begin = System.currentTimeMillis();
        long count = word.stream().filter(w -> w.length()>5).count();
        long end = System.currentTimeMillis();
        System.out.println("耗时=====>" + (end - begin));
        System.out.println("result=====>" + count);
    }

    @Test
    public void streamTest3() throws IOException {
        long begin = System.currentTimeMillis();
        long count = word.parallelStream().filter(w -> w.length()>5).count();
        long end = System.currentTimeMillis();
        System.out.println("耗时=====>" + (end - begin));
        System.out.println("result=====>" + count);
    }

    @Test
    public void streamTest4() throws IOException {
        // for
        long begin;
        long end;
        final int lenth = 1;
        begin = System.currentTimeMillis();
        long count = 0;
        for (String w : word) {
            if (w.length() > lenth) {
                count++;
            }
        }
        end = System.currentTimeMillis();
        System.out.println("耗时=====>" + (end - begin));
        System.out.println("result=====>" + count);
        // stream
        begin = System.currentTimeMillis();
        count = word.stream().filter(w -> w.length() > lenth).count();
        end = System.currentTimeMillis();
        System.out.println("耗时=====>" + (end - begin));
        System.out.println("result=====>" + count);
        // parallelStream
        begin = System.currentTimeMillis();
        count = word.parallelStream().filter(w -> w.length() > lenth).count();
        end = System.currentTimeMillis();
        System.out.println("耗时=====>" + (end - begin));
        System.out.println("result=====>" + count);
    }

    @Test
    public void listTest() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        for (Integer i : list) {
            if (i == 3) {
                list.remove(i);
            }
        }
        System.out.println("result=====>" + list);
    }

    @Test
    public void listForEachTest() {
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

    public static void main(String[] args) {
        String s ="123456";

    }
}
