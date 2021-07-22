package com.qxsun.stu.javabase;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.TreeSet;

/**
 * qxsun学习工程：java基础之基础集合框架
 * 学习教程：java8编程参考官方教程（第九版）-18章
 * 2020年12月9日
 * （1）
 * (2) 18.9 使用映射
 *
 */
public class BaseCollection {
    public static void main(String args[]) {
        int exampleNum = 4;
        //HashSet学习，默认容量是16，默认填充率是0.75，HashSet不能有序存储，如果需要有序，使用TreeSet
        //LinkedHashSet 扩展了 HashSet，使其按照插入顺序遍历
        if (2 == exampleNum) {
            //构建一个HashSet对象
           // HashSet<String> hs = new HashSet<>();
            LinkedHashSet<String> hs = new LinkedHashSet<>();
            //添加元素
            hs.add("Beta");
            hs.add("Alpha");
            hs.add("Eta");
            hs.add("Gama");
            hs.add("Epsilon");
            hs.add("Omega");
            System.out.println(hs);
        }
        if (3 == exampleNum) {
            //构建一个TreeSet对象
            TreeSet<String> ts = new TreeSet<>();

            //添加元素
            ts.add("Beta");
            ts.add("Alpha");
            ts.add("Eta");
            ts.add("Gama");
            ts.add("Epsilon");
            ts.add("Omega");
            System.out.println(ts);
        }

        if (4 == exampleNum) {
            //构建一个ArrayDeque对象
            ArrayDeque<String> adq = new ArrayDeque<>();

            //添加元素,这样可以实现一个栈的功能，后进先出。
            adq.push("Beta");
            adq.push("Alpha");
            adq.push("Eta");
            adq.push("Gama");
            adq.push("Epsilon");
            adq.push("Omega");
            while(adq.peek() != null)
                System.out.println(adq.pop() + " ");
        }

        //映射相关知识学习map

        //学习比较器相关知识 18.10
        if (6 == exampleNum) {

        }

        //18.11
        //18.12 Arrays类





    }
}
