package com.sunjinwei.juc;

public class Producer implements Runnable {

    private Bakery bakery;

    public Producer(Bakery bakery) {
        this.bakery = bakery;
    }

    @Override
    public void run() {
        for (int i = 0; i < 15; i++) {
            try {
                System.out.println("生产者生产面包 id = " + i);
                bakery.put(new Bread(i));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
