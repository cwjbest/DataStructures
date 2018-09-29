package com.cwj.exam;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by cwj on 18-9-17.
 * A B C三个数，如果A % B == C, 或者A的任意正整数倍 % B == C， 返回“YES”，否则返回“NO”
 */
public class Tecent_3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        for (int i = 0; i < t; i++) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            int c = scanner.nextInt();
            Set<Integer> set = new HashSet<>();
            int mod = a % b;
            if (c != 0 && mod == 0)
                System.out.println("NO");
            else {
                while (true) {
                    if (mod == c) {
                        System.out.println("YES");
                        break;
                    }
                    if (set.contains(mod)) {
                        System.out.println("NO");
                        break;
                    } else {
                        set.add(mod);
                        mod = (mod + a) % b;
                    }
                }
            }
        }
        scanner.close();
    }
}
