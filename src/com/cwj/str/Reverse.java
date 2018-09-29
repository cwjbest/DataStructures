package com.cwj.str;

/**
 * Created by cwj on 18-3-27.
 * 给定一个范围为 32 位 int 的整数，将其颠倒。
 * 例 1:
 * 输入: 123
 * 输出:  321
 * 例 2:
 * 输入: -123
 * 输出: -321
 * 例 3:
 * 输入: 120
 * 输出: 21
 * 注意:
 * 假设我们的环境只能处理 32 位 int 范围内的整数。根据这个假设，如果颠倒后的结果超过这个范围，则返回 0。
 *
 * 思路：
 * 既然要考虑范围大于int的情况，不如直接将输入值转为long来处理，用绝对值消除-号的问题，但要注意：
 * 如果是对Integer(Long也一样)取绝对值，如果原值是Integer.MIN_VALUE，则得到的绝对值和原值相等，是一个负数。
 * 为什么呢？因为你看看abs的实现，它很简单，如果原值是正数，直接返回，如果是负数，就简单地在前面加个负号。
 * 然而Integer的范围是[-2147483648,2147483647]，如果原值是最小的值取反之后不就越界了嘛。
 */
public class Reverse {
//    public int reverse(int x){
//        Long a = new Long((long)x);//int转Long
//        char[] str = String.valueOf(Math.abs(a)).toCharArray();//Long转字符串再转字符数组
//
//        int i = 0, j = str.length-1;
//        char temp;
//        while (i<j){
//            temp = str[j];
//            str[j--] = str[i];
//            str[i++] = temp;
//        }
//        a = Long.parseLong(String.valueOf(str));//字符转数组再转回Long
//        if(a>Math.pow(2,-31) && a<Math.pow(2, 31)){
//            a = x > 0 ? a:-a;
//            return a.intValue();//long在转int
//        }else{
//            return 0;
//        }
//    }

    public int reverse(int x){
        long res = 0;
        while(x!=0){
            res = res*10 + x%10;
            x = x/10;
        }
        if (res<Integer.MIN_VALUE || res>Integer.MAX_VALUE){
           return 0;
        }else {
            return (int)res;
        }
    }

    public static void main(String[] args) {
        Reverse r = new Reverse();
        int a = 120;
        System.out.println(r.reverse(a));
    }
}
