package com.cwj.dp;

import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.util.Scanner;

/**
 * Created by cwj on 18-9-14.
 * 装配站问题
 * http://www.cnblogs.com/wuyuegb2312/p/3281264.html#q7
 */
public class EquipmentStation {
    public static void main(String[] args) {
        int NUM = 5;
        int[] first = new int[NUM];//到装备站1,i的最短时间
        int[] second = new int[NUM];//到装配站2,i的最短时间
        int[] linef = new int[NUM]; //从1进入的路径
        int[] lines = new int[NUM]; //从2进入的路径
        int[] a = new int[NUM]; //在装配站1,i 所需要呆的时间
        int[] b = new int[NUM]; //再装配站2,i 所需要呆的时间
        int[] m = new int[NUM-1]; //从装配站1,i-1 到装配站2,i的时间
        int[] n = new int[NUM-1]; //从装配站2,i-1 到装配站1,i的时间
        int ea,eb,xa,xb; // ea为进入装配站1时间，eb为进入2的时间，xa为出装配站1的时间，xb为出装配站2的
        int endTime;
        int endLine;
        Scanner sc = new Scanner(System.in);
        System.out.println("进站，出站时间4");
        ea = sc.nextInt();
        eb = sc.nextInt();
        xa = sc.nextInt();
        xb = sc.nextInt();
        System.out.println("停留时间5,2");
        for (int i = 0; i < NUM; i++) {
            a[i] = sc.nextInt();
            b[i] = sc.nextInt();
        }
        System.out.println("换站时间4,2");
        for (int i = 0; i < NUM-1; i++) {
            m[i] = sc.nextInt();
            n[i] = sc.nextInt();
        }
        first[0] = ea + a[0];
        second[0] = eb + b[0];
        for (int i = 1; i < NUM; i++) {
            if (first[i-1] < second[i-1] + n[i-1]){
                first[i] = first[i-1] + a[i];
                linef[i] = 1;
            }else {
                first[i] = second[i-1] + n[i-1] + a[i];
                linef[i] = 2;
            }
            if (second[i-1] < first[i-1] + m[i-1]){
                second[i] = second[i-1] + b[i];
                lines[i] = 2;
            }else {
                second[i] = first[i-1] + m[i-1] + b[i];
                lines[i] = 1;
            }
        }

        if (first[NUM-1] + xa < second[NUM-1] + xb){
            endTime = first[NUM-1] + xa;
            endLine = 1;
        }else {
            endTime = second[NUM-1] + xb;
            endLine = 2;
        }

        System.out.println(endTime);
        if (endLine == 1){
            for (int path:linef) {
                System.out.print(path + " ");
            }
        }else {
            for (int path:lines) {
                System.out.print(path + " ");
            }
        }
        System.out.print(endLine);
    }
}
