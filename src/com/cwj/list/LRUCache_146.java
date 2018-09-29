package com.cwj.list;

import java.util.*;

/**
 * Created by cwj on 18-9-19.
 * 运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制。它应该支持以下操作：
 * 获取数据 get 和 写入数据 put 。
 * <p>
 * 获取数据 get(key) - 如果密钥 (key) 存在于缓存中，则获取密钥的值（总是正数），否则返回 -1。
 * 写入数据 put(key, value) - 如果密钥不存在，则写入其数据值。当缓存容量达到上限时，
 * 它应该在写入新数据之前删除最近最少使用的数据值，从而为新的数据值留出空间。
 * <p>
 * 进阶:
 * <p>
 * 你是否可以在 O(1) 时间复杂度内完成这两种操作？
 * <p>
 * 示例:
 * <p>
 * LRUCache cache = new LRUCache( 2 * 缓存容量);
 * <p>
 * cache.put(1, 1);
 * cache.put(2, 2);
 * cache.get(1);       返回  1
 * cache.put(3, 3);    该操作会使得密钥 2 作废
 * cache.get(2);       返回 -1 (未找到)
 * cache.put(4, 4);    该操作会使得密钥 1 作废
 * cache.get(1);       返回 -1 (未找到)
 * cache.get(3);       返回  3
 * cache.get(4);       返回  4
 * <p>
 * <p>
 * 第一版，两个map，一个存储数据，一个存储权值
 * 每次get和put都会更新权值，put时，权值最小的key会被remove
 */
public class LRUCache_146 {
    private int capacity;
    private int level;
    private HashMap<Integer, Integer> dataMap = new HashMap<>();
    private HashMap<Integer, Integer> levelMap = new HashMap<>();

    public LRUCache_146(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        if (dataMap.containsKey(key)) {
            levelMap.put(key, level++);
            return dataMap.get(key);
        } else
            return -1;
    }

    public void put(int key, int value) {
        dataMap.put(key, value);
        levelMap.put(key, level++);
        if (dataMap.size() > capacity) {
            int minKey = 0;
            int minValue = Integer.MAX_VALUE;
            for (Map.Entry entry : levelMap.entrySet()) {
                if (minValue > (Integer) entry.getValue()) {
                    minValue = (Integer) entry.getValue();
                    minKey = (Integer) entry.getKey();
                }
            }
            dataMap.remove(minKey);
            levelMap.remove(minKey);
        }
    }

    public static void main(String[] args) {
        LRUCache_146 cache = new LRUCache_146(1);
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));       // 返回  1
        cache.put(3, 3);                        // 该操作会使得密钥 2 作废
        System.out.println(cache.get(2));       // 返回 -1 (未找到)
        cache.put(4, 4);    // 该操作会使得密钥 1 作废
        System.out.println(cache.get(1));       // 返回 -1 (未找到)
        System.out.println(cache.get(3));       // 返回  3
        System.out.println(cache.get(4));       // 返回  4
    }
}

/**
 * 第二版，想到用queue来代替levelMap，只是需要知道key的访问时间，并不需要确切知道权值
 * 所以，每次get或put时，都将key从queue中移除并添加到队列头；
 * 然而还是很慢
 */
class LRUCache_2 {
    private int capacity;
    private HashMap<Integer, Integer> dataMap = new HashMap<>();
    private Queue<Integer> queue = new LinkedList<>();

    public LRUCache_2(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        if (dataMap.containsKey(key)) {
            queue.remove(key);
            queue.add(key);
            return dataMap.get(key);
        } else
            return -1;
    }

    public void put(int key, int value) {
        dataMap.put(key, value);
        queue.remove(key);
        queue.add(key);
        if (dataMap.size() > capacity) {
            dataMap.remove(queue.remove());
        }
    }

    public static void main(String[] args) {
        LRUCache_146 cache = new LRUCache_146(2);
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));       // 返回  1
        cache.put(3, 3);                        // 该操作会使得密钥 2 作废
        System.out.println(cache.get(2));       // 返回 -1 (未找到)
        cache.put(4, 4);    // 该操作会使得密钥 1 作废
        System.out.println(cache.get(1));       // 返回 -1 (未找到)
        System.out.println(cache.get(3));       // 返回  3
        System.out.println(cache.get(4));       // 返回  4
    }
}

/**
 * 用java自带的LinkedHashMap更简单一些，默认key按照插入有序， 但它可以设置按照访问有序
 * 但是需要继承这个类，然后重写一下removeEldestEntry方法
 */
class LRUCache_3 {
    final int cacheSize;
    Map<Integer, Integer> map;

    public LRUCache_3(int capacity) {
        this.cacheSize = capacity;
        map = new LinkedHashMap<Integer, Integer>
                ((int) Math.ceil(cacheSize / 0.75f) + 1, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                return size() > cacheSize;
            }
        };
    }

    public int get(int key) {
        return map.get(key);
    }

    public void put(int key, int value) {
        map.put(key, value);
    }
}
