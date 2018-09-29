package com.cwj.test.aspectj;

/**
 * Created by cwj on 18-4-4.
 *
 */
public class HelloWorld {
    public void sayHello(){
        System.out.println("Hello world!");
    }

    public static void main(String[] args) {
        HelloWorld helloWorld = new HelloWorld();
        helloWorld.sayHello();
    }
}
