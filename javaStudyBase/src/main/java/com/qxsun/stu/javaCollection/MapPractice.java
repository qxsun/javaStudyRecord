
package com.qxsun.stu.javaCollection;


import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class MapPractice {
    public static void main(String[] args) {
        Map<String, String> curMap = new HashMap<>();
        curMap.put("aaa", "111");
        curMap.put("bbb", "222");
        System.out.println(curMap);

        Map<String, String> curTreeMap = new TreeMap<>();
        curTreeMap.put("aaa", "111");
        curTreeMap.put("bbb", "222");
        curTreeMap.put("aba", "121");
        System.out.println(curTreeMap);

        Map<String, String> curConcurrentMap = new ConcurrentHashMap<>();
        curConcurrentMap.put("aaa", "111");
        curConcurrentMap.put("bbb", "222");
        curConcurrentMap.put("aba", "121");
        System.out.println(curConcurrentMap);
    }
}
