package com.sunjinwei._03_tree;

import java.util.List;

/**
 * 派对的最大快乐值 【左神】树形dp题目 非常好!!!
 * 不能邀请直系下级员工
 * 分类：
 * 情况一：节点x去，最大快乐值= 节点x的快乐值+【子节点a不去的最大快乐值】+【子节点b不去的最大快乐值】+...
 * 情况二：节点x不去，最大快乐值= max{子节点a去的快乐值，子节点a不去的快乐值} + max{子节点b去的快乐值，子节点b不去的快乐值}+max{...}
 * 满足树型dp的套路，根据节点x去不去作为分类，返回结果包含哪些属性，这就需要自己进行分析推断
 * 这里就是 Result {
 * int 去的最大值；// 情况一
 * int 不去的最大值；// 情况二
 * }
 */
public class _15_tree_dp_IV {


    public int getMaxHappy(Employee employee) {
        if (employee == null) {
            return 0;
        }
        ReturnData data = process(employee);
        return Math.max(data.buQuValue, data.quValue);
    }

    private ReturnData process(Employee employee) {
        // base case1：
        if (employee == null) {
            return new ReturnData(0, 0);
        }

        int qu = employee.happy;
        int buQu = 0;

        // base case2: 没有直属下级 那就是最基层员工
        if (employee.subEmployees.isEmpty()) {
            return new ReturnData(qu, buQu);
        }

        // 有直属下级
        // dp逻辑处理
        for (Employee subEmployee : employee.subEmployees) {
            // 处理直属下级的快乐值 去或者不去的最大值
            ReturnData data = process(subEmployee);
            // 如果去 去的最大值 见分析的情况一
            qu += data.buQuValue;
            // 如果不去 不去的最大值 见分析的情况二
            buQu += Math.max(data.buQuValue, data.quValue);
        }
        return new ReturnData(qu, buQu);
    }

}

/**
 * 员工的快乐值和他的直属下级
 */
class Employee {

    int happy;

    List<Employee> subEmployees;

    public Employee(int happy, List<Employee> subEmployees) {
        this.happy = happy;
        this.subEmployees = subEmployees;
    }
}

/**
 * 树型dp的返回信息
 */
class ReturnData {
    int quValue;
    int buQuValue;

    public ReturnData(int quValue, int buQuValue) {
        this.quValue = quValue;
        this.buQuValue = buQuValue;
    }
}

