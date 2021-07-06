package com.sunjinwei._05_string;

/**
 * 415 字符串相加 【联想到链表相加】
 * 给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和。
 * 提示：
 * <p>
 * num1 和num2 的长度都小于 5100
 * num1 和num2 都只包含数字 0-9
 * num1 和num2 都不包含任何前导零
 * 你不能使用任何內建 BigInteger 库， 也不能直接将输入的字符串转换为整数形式
 * <p>
 * 心得：
 * char和int的转换 并且如何让字符串不变  通过 减 '0' 这样来处理 保证了差值
 * a % b 这是取余 可以用于两个int相加 得到的个位数
 * a / b 这是取商 可以用于高位进1
 * 从最左边 最低位开始进行处理 然后再进行反转
 */
public class _04_Add_String {

    /**
     * 解题思路：
     * 算法流程： 设定 i，j 两指针分别指向 num1，num2 尾部，模拟人工加法；
     * <p>
     * 计算进位： 计算 add = tmp / 10，代表当前位相加是否产生进位, / 取商；
     * 添加当前位： 计算 result = n1 + n2 + carry，并将当前位 result % 10 添加至res头部, result % 10 代表取余，
     * 比如
     * 9 + 3 = 12 ，12 % 10 = 2 那么取2；
     * 1 + 4 = 5，5 % 10 = 5 那么取5
     * 索引溢出处理： 当指针 i或j 走过数字首部后，给 x，y 赋值为 0，相当于给 num1，num2 中长度较短的数字前面填 0，以便后续计算。
     * 当遍历完 num1，num2 后跳出循环，并根据 carry 值决定是否在头部添加进位1，最终返回 res 即可。
     */
    public String addStrings(String num1, String num2) {
        // 定义两个指针i和j分别指向nums1 num2的末尾，即最低位，
        // 同时定义一个变量add维护当前是否有进位，然后从末尾到开头逐位相加即可
        int i = num1.length() - 1;
        int j = num2.length() - 1;
        int add = 0;
        StringBuffer ans = new StringBuffer();
        // 三种情况
        // i >= 0
        // j >= 0
        // add != 0 将这个也放在while条件里面
        while (i >= 0 || j >= 0 || add != 0) {
            // 你可能会想两个数字位数不同怎么处理，这里我们统一在指针当前下标处于负数的时候返回0，
            // 等价于对位数较短的数字进行了补零操作，这样就可以除去两个数字位数不同情况的处理
            int x = 0;
            int y = 0;
            if (i >= 0) {
                // 因为x是int类型 char转int类型 是ascii码对应的数字 所以这里还需要减去 '0'对应的ascii码表的值就是对应差值了!!!!!!
                x = num1.charAt(i) - '0';
            } else {
                // 设置为0 方便计算
                x = 0;
            }
            if (j >= 0) {
                y = num2.charAt(j) - '0';
            } else {
                // 设置为0 方便计算
                y = 0;
            }
            // 将结果进行相加 其中add为上一个的进位
            int result = x + y + add;
            // 将result进行对10取余
            // result除以10 然后得到余数
            ans.append(result % 10);
            // 如果当前位和超过10，则向高位进一位 使用除法 /
            add = result / 10;
            i--;
            j--;
        }
        // 计算完以后的答案需要翻转过来
        ans.reverse();
        return ans.toString();
    }


    /**
     * 方法2：将最后一个高位进1 放在while外面进行处理
     *
     * @param num1
     * @param num2
     * @return
     */
    public String addStrings2(String num1, String num2) {
        StringBuffer res = new StringBuffer();

        int len1 = num1.length() - 1;
        int len2 = num2.length() - 1;
        int add = 0;
        int x = 0;
        int y = 0;
        int total = 0;
        while (len1 >= 0 || len2 >= 0) {
            if (len1 >= 0) {
                x = num1.charAt(len1) - '0';
            }
            if (len2 >= 0) {
                y = num2.charAt(len2) - '0';
            }
            total = x + y + add;
            // 取余数
            res.append(total % 10);
            // 取商 高位进1
            add = total / 10;
            len1--;
            len2--;
        }
        if (add == 1) {
            res.append(1);
        }
        return res.reverse().toString();

    }


    public static void main(String[] args) {

        _04_Add_String addStrings = new _04_Add_String();

        String s = addStrings.addStrings2("11", "27");

        System.out.println(s);



    }


}

