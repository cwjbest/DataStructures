package com.cwj.thread;

/**
 * Created by cwj on 18-9-22.
 *
 */
public class Add1_10000 {
    private int i;

    public void add(){
        synchronized (this){
            i++;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[10];
        Add1_10000 a = new Add1_10000();
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 10000; j++) {
                        a.add();
                    }
                }
            });
            threads[i].start();
            threads[i].join();
        }
        System.out.println(a.i);

        String s1 = "a";
        String s2 = s1;
        s2 = "b";
        System.out.println(s1);
    }
}
