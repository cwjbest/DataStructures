package com.cwj.thread;

/**
 * Created by cwj on 18-8-28.
 *
 */
public class ThreadLocalTest {

    public static void main(String[] args) throws InterruptedException {

        Increment increment = new Increment();
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
//                increment.printNum();
                increment.printLocal();
            }).start();
        }
    }
}

class Increment{
    ThreadLocal<Integer> local = ThreadLocal.withInitial(() -> 0);
    int num = 0;
    public int increaseNum(){
        return ++num;
    }

    public int increaseLocal(){
        local.set(local.get()+1);
        return local.get();
    }
    public void printNum(){
        for (int i = 0; i < 3; i++) {
            System.out.println(Thread.currentThread().getName() + " " + increaseNum());
        }
    }
    public void printLocal(){
        for (int i = 0; i < 3; i++) {
            System.out.println(Thread.currentThread().getName() + " " + increaseLocal());
        }
    }
}
