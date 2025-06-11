package com.sunjinwei.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 进阶：使用lock+condition实现生产者消费者
 */
public class ProducerConsumerDemo2 {
    // 生产者和消费者对缓冲区进行读写
    private static int buffer = 0;

    private static final Lock reentrantLock = new ReentrantLock();

    private static final Condition notFull = reentrantLock.newCondition();

    private static final Condition notEmpty = reentrantLock.newCondition();

    static class Producer extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                reentrantLock.lock();
                try {
                    while (buffer == 1) {
                        // 缓冲区满 等待
                        notFull.await();
                    }
                    buffer = 1;
                    System.out.println(Thread.currentThread().getName() + "生产者进行生产了产品" + (i + 1));
                    notEmpty.signalAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    reentrantLock.unlock();
                }
            }
        }
    }

    static class Consumer extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                reentrantLock.lock();
                try {
                    while (buffer == 0) {
                        notEmpty.await();
                    }
                    buffer = 0;
                    System.out.println(Thread.currentThread().getName() + "消费者消费了产品" + (i + 1));
                    notFull.signalAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    reentrantLock.unlock();
                }
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            new Producer().start();
        }
        for (int i = 0; i < 3; i++) {
            new Consumer().start();
        }
    }

}
