package com.example.qxsun.aopDemo.annotation;

import java.lang.annotation.*;

/**
 * @author qxsun
 * @version 1.0
 * @date 2020/10/16 11:17
 * @description
 * @modify
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PermissionAnnotation {
}
