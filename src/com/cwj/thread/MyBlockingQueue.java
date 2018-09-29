package com.cwj.thread;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by cwj on 18-7-30.
 *
 */
public class MyBlockingQueue<E> {
    private int limit = 10;
    private Queue<E> queue = new ArrayDeque<E>(limit);
    private Lock lock = new ReentrantLock();
    private Condition notFull = lock.newCondition();
    private Condition notEmpty = lock.newCondition();

    public void put(E e) throws InterruptedException{
        lock.lock();
        try {
            while (queue.size() == limit){
                notFull.await();
            }
            queue.add(e);
            notEmpty.signal();
        }finally {
            lock.unlock();
        }
    }

    public E get() throws InterruptedException{
        lock.lock();
        try {
            while (queue.isEmpty()){
                notEmpty.await();
            }
            E e = queue.poll();
            notFull.signal();
            return e;
        }finally {
            lock.unlock();
        }
    }
}
