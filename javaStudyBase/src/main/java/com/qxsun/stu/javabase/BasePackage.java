package com.qxsun.stu.javabase;

/**
 * qxsun学习工程：java基础之基础类库
 * 学习教程：
 * 2020年11月12日
 * （1）学习String类
 *(2)关于字符串比较 equals
 *
 */
public class BasePackage {
    public static void main (String args[]){
        int exampleNum = 2;
        //字符串String初始化
        if(0 == exampleNum)
        {
            String s = new String();
            byte ascii[] = {65, 66, 67, 68, 69, 70};
            String s1 = new String(ascii);
            System.out.println(s1);
            String s2 = new String(ascii, 2, 4);
            System.out.println(s2);

            int intPoints[] = {31, 32, 33, 34, 35, 36};
            String s3 = new String(intPoints, 2, 4);
            System.out.println(s3);
        }

        //equals方法
        if(1 == exampleNum) {
            String s01 = "Hello";
            String s02 = "Hello";
            String s03 = "Hello World";
            String s04 = "HELLO";
            System.out.println(s01 + " equals() " + s02 + "->" + s01.equals(s02));
            System.out.println(s01 + " equals() " + s03 + "->" + s01.equals(s03));
            System.out.println(s01 + " equals() " + s04 + "->" + s01.equals(s04));
            System.out.println(s01 + " equalsIgnoreCase() " + s04 + "->" + s01.equalsIgnoreCase(s04));
        }

        //equals()方法比较String对象中的字符，符号“==”运算符比较两个对象的引用
        if(2 == exampleNum) {
            String s21 = "hello";
            String s22 = new String(s21);
            System.out.println(s21 + " equals() " + s22 + "->" + s21.equals(s22));
            System.out.println(s21 + " == " + s22 + "->" + (s21==s22) );
        }

    }

}

