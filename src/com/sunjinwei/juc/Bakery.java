package com.sunjinwei.juc;


import java.util.ArrayList;
import java.util.List;

/**
 * 模拟生产者和消费者 联想到mq
 * 参考博客：https://blog.csdn.net/prestigeding/article/details/120111201
 */
public class Bakery {

    private List<Bread> breads = new ArrayList<>();

    private int capacity = 15;

    // 锁对象，主要是用来保护List< Bread> 数据结构，众所周知，ArrayList是多线程不安全的，也就是说多个线程对其进行访问，必须加锁，
    // 这里之所以单独创建一个对象，主要是想突出锁概念，在代码中，其实可以用 synchronized(breads) 来代替。
    private Object lock = new Object();

    // 该方法是被面包生产者调用，向面包厂中添加面包，但是面包厂的容量是有限的，即生产者不能一直往里面添加，
    // 即当达到最大容量后，需要阻止生产者继续往里面添加，故这里涉及到条件等待与wait方法，细细说明如下：
    public void put(Bread bread) throws InterruptedException {
        synchronized (lock) {
            while (breads.size() == capacity) {
                // 仓库已经满了
                // notify,wait方法是调用的锁对象，并不是生产者或消费者线程
                lock.wait();
            }
            breads.add(bread);
            // notify,wait方法是调用的锁对象，并不是生产者或消费者线程
            lock.notifyAll();
        }
    }

    // 该方法主要是被面包消费者调用，从面包中获取面包，但要能获取面包也是有条件的：仓库中存在面包，否则需要阻塞等待消费者创建面包，故这里的要点如下：
    public Bread get() throws InterruptedException {
        Bread res = null;
        synchronized (lock) {
            while (breads.isEmpty()) {
                // notify,wait方法是调用的锁对象，并不是生产者或消费者线程
                lock.wait();
            }
            res = breads.remove(breads.size() - 1);
            lock.notifyAll();
        }
        return res;
    }

    public static void main(String[] args) {
        Bakery bakery = new Bakery();

        Consumer consumer = new Consumer(bakery);
        Producer producer = new Producer(bakery);

        new Thread(producer).start();
        new Thread(consumer).start();

    }
}
