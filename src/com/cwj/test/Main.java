package com.cwj.test;

/**
 * Created by cwj on 18-3-25.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("---- new Child()");
        Child c = new Child();

        System.out.println("\n---- c.action()");
        c.action();

        Base b = c;
        System.out.println("\n---- b.action()");
        b.action();


        System.out.println("\n---- b.s: " + b.s);
        System.out.println("\n---- c.s: " + c.s);
    }
}
