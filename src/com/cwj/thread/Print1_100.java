package com.cwj.thread;

/**
 * Created by cwj on 18-7-30.
 */
public class Print1_100 implements Runnable {
    int i = 1;
    @Override
    public void run() {
        while (true){
            synchronized (this) {
                notify();
                try {
                    Thread.currentThread().sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (i <= 100) {
                    System.out.println(Thread.currentThread().getName() + ":" + i);
                    ++i;
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Print1_100 t = new Print1_100();
        Thread t1 = new Thread(t);
        Thread t2 = new Thread(t);

        t1.start();
        t2.start();
    }
}
