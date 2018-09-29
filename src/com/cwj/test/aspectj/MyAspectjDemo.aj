package com.cwj.test.aspectj;

public aspect MyAspectjDemo{
    pointcut recordLog():call(* HelloWorld.sayHello(..));

    pointcut authCheck():call(* HelloWorld.sayHello(..));

    before():authCheck(){
        System.out.println("before...");
    }

    after():authCheck(){
        System.out.println("after...");
    }
}