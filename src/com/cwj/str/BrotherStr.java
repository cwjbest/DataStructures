package com.cwj.str;

import java.util.Scanner;

/**
 * Created by cwj on 18-9-13.
 *
 */
public class BrotherStr {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s1 = sc.nextLine();
        String s2 = sc.nextLine();
        if (s1.length() != s2.length() || s1.equals(s2))
            System.out.println(0);
        int[] nums1 = new int[26];
        int[] nums2 = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            nums1[s1.charAt(i) - 'a']++;
            nums2[s2.charAt(i) - 'a']++;
        }

        for (int i = 0; i < nums1.length; i++) {
            if (nums1[i] != nums2[i]) {
                System.out.println(0);
                break;
            }
        }
        System.out.println(1);
    }
}
