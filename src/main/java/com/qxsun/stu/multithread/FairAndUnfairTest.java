package com.qxsun.stu.multithread;

import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FairAndUnfairTest {
    private static Lock fairLock = new ReentrantLock2(true);
    private static Lock unfairLock = new ReentrantLock2(false);
    private static CountDownLatch start;
    @Test
    public void fair() {
        testLock(fairLock);
    }
    @Test
    public void unfair() {
        testLock(unfairLock);
    }
    private void testLock(Lock lock) {
        start = new CountDownLatch(1);
        for (int i = 0; i < 5; i++) {
            Thread thread = new Job(lock);
            thread.setName("" + i);
            thread.start();
        }
        start.countDown();
// 启动5个Job（略）
    }
    private static class Job extends Thread {
        private Lock lock;
        public Job(Lock lock) {
            this.lock = lock;
        }
        public void run() {
// 连续2次打印当前的Thread和等待队列中的Thread（略）

            try {
                start.await();
            } catch (InterruptedException e) {
            }
            for (int i = 0; i < 2; i++) {
                lock.lock();
                try {
                    //SleepUtils.second(1);
                    System.out.println("Lock by [" + getName() + "], Waiting by " + ((ReentrantLock2) lock).getQueuedThreads());
                   // SleepUtils.second(1);
                } finally {
                    lock.unlock();
                }
            }

        }

        public String toString() {
            return getName();
        }
    }
    private static class ReentrantLock2 extends ReentrantLock {
        private static final long serialVersionUID = -6736727496956351588L;
        public ReentrantLock2(boolean fair) {
            super(fair);
        }
        public Collection<Thread> getQueuedThreads() {
            List<Thread> arrayList = new ArrayList<Thread>(super.
                    getQueuedThreads());
            Collections.reverse(arrayList);
            return arrayList;
        }
    }
}
