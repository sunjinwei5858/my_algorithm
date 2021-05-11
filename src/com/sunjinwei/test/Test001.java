package com.sunjinwei.test;

import com.sunjinwei.domain.ListNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Test001 {


    public void add(List<ListNode> list) {
        System.out.println("嘻嘻嘻");
    }


    public static void main(String[] args) {

        ArrayList arrayList = new ArrayList();
        arrayList.add("hahahah");
        arrayList.add(123);

        Vector vector = new Vector();

        for (Object o : arrayList) {
            System.out.println(o);
        }

    }
}
