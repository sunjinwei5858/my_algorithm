package com.sunjinwei.cpu;

import sun.misc.Contended;

/**
 * 伪共享
 *
 * @see https://www.cnblogs.com/tong-yuan/p/FalseSharing.html
 */
public class FalseShardinngTest {

    public static void main(String[] args) throws InterruptedException {

        // 18163ms
        //testPointer(new Pointer());


        // 9758ms
        //testPointer2(new Pointer2());

        // 15808ms
        //testPointer3(new Pointer3());


        // 9637ms 使用java8的@Contended 注解
        testPointer4(new Pointer4());

    }

    // 原始
    private static void testPointer(Pointer pointer) throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000000; i++) {
                System.out.println(pointer.x++);
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000000; i++) {
                System.out.println(pointer.y);
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("======");

        System.out.println(System.currentTimeMillis() - start);
        System.out.println(pointer);
    }

    /**
     * 第一种方法
     *
     * @param pointer
     * @throws InterruptedException
     */
    private static void testPointer2(Pointer2 pointer) throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000000; i++) {
                System.out.println(pointer.x++);
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000000; i++) {
                System.out.println(pointer.y);
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("======");

        System.out.println(System.currentTimeMillis() - start);
        System.out.println(pointer);
    }

    /**
     * 第二种方法
     *
     * @param pointer
     * @throws InterruptedException
     */
    private static void testPointer3(Pointer3 pointer) throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000000; i++) {
                System.out.println(pointer.x.value++);
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000000; i++) {
                System.out.println(pointer.y.value);
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println(System.currentTimeMillis() - start);
        System.out.println(pointer);
    }


    /**
     * 第三种方法
     *
     * @param pointer
     * @throws InterruptedException
     */
    private static void testPointer4(Pointer4 pointer) throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000000; i++) {
                System.out.println(pointer.x.value++);
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000000; i++) {
                System.out.println(pointer.y.value);
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println(System.currentTimeMillis() - start);
        System.out.println(pointer);
    }


}

/**
 * long占用8个字节，缓存行是64字节，也就是cpu除了会加载x变量 也会加载y变量，
 * 多线程条件下，线程1修改x 线程2读取y，这样就会导致伪共享，因为缓存行64字节 加载x或者加载y时，会把x和y一起放入同一个缓存行
 * 当前者修改x时，会把x和y同时加载到前者核心的缓存行中，更新完x后其它【所有包含y的缓存行都将失效】，因为其它缓存中的x不是最新值了。
 * <p>
 * 18163ms
 */
class Pointer {
    volatile long x;
    volatile long y;
}


/**
 * 避免伪共享的方式1：在两个long类型的变量之间再增加7个long类型进行填充 【加的字段又没有地方使用，可能会被jvm优化掉】
 * 8049ms
 */
class Pointer2 {
    volatile long x;
    long x1;
    long x2;
    long x3;
    long x4;
    long x5;
    long x6;
    long x7;
    volatile long y;
}

/**
 * 避免伪共享的方式2：自己声明long变量 【加的字段又没有地方使用，可能会被jvm优化掉】
 * 8183ms
 */
class Pointer3 {
    MyLong x = new MyLong();
    MyLong y = new MyLong();
}

class MyLong {
    volatile long value;
    long p1, p2, p3, p4, p5, p6, p7;
}

class Pointer4 {
    MyLong2 x = new MyLong2();
    MyLong2 y = new MyLong2();
}


/**
 * 避免伪共享的方式3：使用 @sun.misc.Contended 注解（java8）默认使用这个注解是无效的，
 * 需要在JVM启动参数加上-XX:-RestrictContended才会生效【推荐】
 * 8580ms
 */
@Contended
class MyLong2 {
    volatile long value;
}




