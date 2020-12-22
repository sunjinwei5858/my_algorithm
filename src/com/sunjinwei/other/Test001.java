package com.sunjinwei.other;

public class Test001 {

    public static void main(String[] args) {
        Person aaa = new Person(12, "aaa");
        change(aaa);
        System.out.println(aaa.getAge());

        int num = 10;
        changeNum(num);
        System.out.println(num);

    }


    public static void change(Person p) {
        p.setAge(100);
    }


    public static void changeNum(int num) {
        num = 99;
    }
}
