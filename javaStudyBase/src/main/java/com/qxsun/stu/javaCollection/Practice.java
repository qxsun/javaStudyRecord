package com.qxsun.stu.javaCollection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Practice {
    public static void main(String[] args){
        List c = new ArrayList();
        c.add("l");
        c.add("o");
        c.add("v");
        c.add("e");
        System.out.println(c);
        Collections.sort(c);
        System.out.println(c);
    }
}
