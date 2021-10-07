package com.sunjinwei.juc;

public class Consumer implements Runnable {

    private Bakery bakery;


    public Consumer(Bakery bakery) {
        this.bakery = bakery;
    }

    @Override
    public void run() {
        for (int i = 0; i < 15; i++) {
            try {
                Bread bread = bakery.get();
                System.out.println("消费者消费面包，id = " + bread.id);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
