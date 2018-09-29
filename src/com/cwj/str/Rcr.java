package com.cwj.str;

/**
 * Created by cwj on 18-3-19.
 * 数组循环左移P个位置
 * 先将0-p-1逆置， 再将p-n逆置，最后全部逆置
 */
public class Rcr {
    private void reverse(int a[], int start, int end){
        int temp;
        for (int i = start, j = end-1; i < j; i++, j--) {
            temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }
    }

    public static void main(String[] args) {
        int a[] = {1, 2, 3, 4, 5};
        int p = 3;

        for (int data:a) {
            System.out.print(data + " ");
        }
        System.out.println();

        Rcr r = new Rcr();
        r.reverse(a, 0, p);
        r.reverse(a, p+1, a.length);
        r.reverse(a, 0, a.length);

        for (int data:a) {
            System.out.print(data + " ");
        }
    }
}
