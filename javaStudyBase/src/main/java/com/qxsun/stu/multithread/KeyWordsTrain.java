package com.qxsun.stu.multithread;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author qxsun
 * @descprition 多线程中典型关键字的学习
 */
public class KeyWordsTrain {

    public static void main (String args[]){
        LinkedTransferQueue linkedTransferQueue = new LinkedTransferQueue<>();
    }

    class ReentrantLockExample {
        int a = 0;
        ReentrantLock lock = new ReentrantLock();
        public void writer() {
            lock.lock(); // 获取锁
            try {
                a++;
            } finally{
                lock.unlock();// 释放锁
            }
        }
        public void reader () {
            lock.lock(); // 获取锁
            try {
                int i = a;
            } finally{
                lock.unlock(); // 释放锁
            }
        }
    }
}

