
package com.qxsun.stu.javaCollection;


import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public  class SetPractice {
    public static void main(String[] args) {
        Set<String> curSet = new HashSet<>();
        curSet.add("aaa");
        curSet.add("bbb");
        System.out.println(curSet);

        Set<String> curTreeSet = new TreeSet<>();
        curTreeSet.add("aaa");
        curTreeSet.add("bbb");
        curTreeSet.add("abs");
        System.out.println(curTreeSet);
    }
}

