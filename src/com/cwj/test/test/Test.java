package com.cwj.test.test;

import java.util.*;

/**
 * Created by cwj on 18-9-13.
 * 假设有一个无限长的x轴，站在原地0处，每次只能朝左或者右
 * 第N次移动就要走N步，给定一个target坐标点，求达到目标点的最短步数
 * target = 3   2
 * target = 2   3
 */
public class Test {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        if (n == 0 || n == 1){
            System.out.println(n);
            return;
        }
        Queue<Integer> cur = new LinkedList<>();
        Queue<Integer> next = new LinkedList<>();
        Queue<Integer> temp;
        cur.add(0);
        int data = 0;
        int i = 1;
        while (!cur.isEmpty()) {
           while (!cur.isEmpty()) {
               data = cur.remove();
               if (data == n) {
                   System.out.println(i-1);
                   break;
               }
               next.add(data - i);
               next.add(data + i);
           }
           if (data == n)
               break;
           i++;
           temp = cur;
           cur = next;
           next = temp;
        }
    }
}
