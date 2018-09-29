package com.cwj.test.Case;
/**
 * Created by cwj on 18-9-12.
 *
 */

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s=sc.nextLine();
        String sarr[]=s.split(" ");
        int arr[]=new int[sarr.length];
        for(int i=0;i<sarr.length;i++){
            arr[i]=Integer.parseInt(sarr[i]);
        }

        sc.close();
    }

//    public static void main(String[] args) {
//        // TODO Auto-generated method stub
//        Scanner sc = new Scanner(System.in);
//        String s=sc.nextLine();
//        String sarr[]=s.split(" ");
//        int arr[]=new int[sarr.length];
//        for(int i=0;i<sarr.length;i++){
//            arr[i]=Integer.parseInt(sarr[i]);
//        }
//
//
//
//        sc.close();
//    }
}

