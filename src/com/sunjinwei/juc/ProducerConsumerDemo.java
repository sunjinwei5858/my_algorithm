package com.sunjinwei.juc;

/**
 * 再写一个使用wait/notify实现生产者消费者
 */
public class ProducerConsumerDemo {

    // 锁
    private static final Object lock = new Object();
    // 缓冲区
    private static int buffer = 0;

    static class Producer extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                synchronized (lock) {
                    while (buffer == 1) {
                        // 缓冲区满了 等待消费者消费 让出锁
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                    // 生产一个产品
                    buffer = 1;
                    System.out.println("生产者生产了一个产品" + (i + 1));
                    lock.notifyAll();
                }

            }
        }
    }

    static class Consumer extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                synchronized (lock) {
                    while (buffer == 0) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                    buffer = 0;
                    System.out.println("消费者消费了一个产品" + (i + 1));
                    lock.notifyAll();
                }

            }
        }
    }

    public static void main(String[] args) {
        new Producer().start();
        new Consumer().start();
    }
}
