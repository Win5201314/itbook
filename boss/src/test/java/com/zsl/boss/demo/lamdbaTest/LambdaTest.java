package com.zsl.boss.demo.lamdbaTest;

import org.junit.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class LambdaTest {

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(new Date() + "----------------------");
            }
        }).start();

        new Thread(() -> System.out.println(new Date())).start();
    }

    @Test
    public void Test1() {
        List<String> lists = Arrays.asList("hello", "wei", "drghi");
        lists.forEach(item -> {
            System.out.println(item);
            System.out.println("---------------------------");
        });

        lists.forEach(System.out::println);
    }
}
