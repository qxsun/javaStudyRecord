
package com.qxsun.stu.javaCollection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Desc: 泛型锻炼
 * 包含泛型擦除
 */
public class GenericityPractice {
    public static void main(String[] args){
        List<String> l1 = new ArrayList<String>();
        List<Integer> l2 = new ArrayList<Integer>();
        /*
        l1 l2因为在编译后<>里面的类型就被擦除了，所以他们的类型是一样的
         */
        System.out.println(l1.getClass() == l2.getClass());

        Erasure<String> erasure = new Erasure<String>("hello");
        Class eclz = erasure.getClass();
        System.out.println("erasure class is:"+eclz.getName());
        /*
        打印泛型类的属性
        泛型类被类型擦除后，相应的类型就被替换成 Object 类型
         */
        Field[] fs = eclz.getDeclaredFields();
        for ( Field f:fs) {
            System.out.println("Field name "+f.getName()+" type:"+f.getType().getName());
        }
        Method[] methods = eclz.getDeclaredMethods();
        for ( Method m:methods ){
            System.out.println(" method:"+m.toString());
        }

        StringErasure<String> stringErasure = new StringErasure<String>("hello");
        Class stringEclz = stringErasure.getClass();
        System.out.println("stringErasure class is:"+stringEclz.getName());
        /*
        打印泛型类的属性
        泛型类被类型擦除后，相应的类型就被替换成 String 类型
         */
        Field[] fs1 = stringEclz.getDeclaredFields();
        for ( Field f:fs1) {
            System.out.println("Field name "+f.getName()+" type:"+f.getType().getName());
        }
        Method[] methods1 = stringEclz.getDeclaredMethods();
        for ( Method m:methods ){
            System.out.println(" method:"+m.toString());
        }



    }
}
 class Erasure <T>{
    T object;

    public Erasure(T object) {
        this.object = object;
    }

}

class StringErasure <T extends String>{
    T object;

    public StringErasure(T object) {
        this.object = object;
    }

}







