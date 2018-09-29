package com.cwj.list;

import java.util.ArrayList;

/**
 * Created by cwj on 18-3-19.
 * 顺序表的查找，插入，删除
 */
public class ListTest {
    public int locate(ArrayList<Integer> a, int e){
        for (int i = 0; i < a.size(); i++) {
            if (e == a.get(i)){
                return i + 1;
            }
        }
        return -1;
    }

    public void insert(ArrayList<Integer> a, int p, int e){
        if (p < 0 || p > a.size() + 1){
            return;
        }
        a.add(0);//自增一位
        for (int i = a.size()-1; i >= p; i--) {
            a.set(i, a.get(i-1));
        }
        a.set(p-1,e);
    }

    public void delete(ArrayList<Integer> a, int p){
        int e = a.get(p-1);
        for (int i = p-1; i < a.size()-1; i++) {
            a.set(i, a.get(i+1));
        }
        a.remove(a.size()-1);
    }

    public static void main(String[] args) {
        ListTest listTest = new ListTest();
        ArrayList<Integer> a = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            a.add(i+1);
        }
        for (int data:a) {
            System.out.print(data + " ");
        }
        System.out.println("");
        System.out.println(listTest.locate(a, 3));
//        listTest.insert(a, 3, 11);
        listTest.delete(a, 3);
        for (int data:a) {
            System.out.print(data + " ");
        }

    }
}
