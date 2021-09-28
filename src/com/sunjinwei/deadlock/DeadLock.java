package com.sunjinwei.deadlock;

/**
 * 模拟多线程死锁代码    【拼多多面试题2021-9-26】
 * ps：
 * 死锁：两个线程请求共享资源，一个持有上半部分共享资源 一个持有下半部分共享资源
 * 共享资源：两把锁即可模拟
 */
public class DeadLock {

    // 锁1
    public static Object lock1 = new Object();
    // 锁2
    public static Object lock2 = new Object();

    public static void main(String[] args) {

        Thread thread1 = new Thread(() -> {
            synchronized (DeadLock.lock1) {
                System.out.println("线程1 抢到 lock1 锁");

                // 给予线程2有足够多的时间获取lock2锁
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (DeadLock.lock2) {
                    System.out.println("线程1 抢到 lock1 锁");
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            synchronized (DeadLock.lock2) {
                System.out.println("线程2 抢到 lock2 锁");

                // 给予线程1有足够多的时间获取lock1锁
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (DeadLock.lock1) {
                    System.out.println("线程2 抢到 lock1 锁");
                }
            }
        });

        thread1.start();
        thread2.start();

        thread1.run();
        thread2.run();

    }

}
