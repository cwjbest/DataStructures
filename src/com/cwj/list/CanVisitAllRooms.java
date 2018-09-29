package com.cwj.list;

import java.util.*;

/**
 * Created by cwj on 18-8-30.
 * 841. 钥匙和房间
 * 有 N 个房间，开始时你位于 0 号房间。每个房间有不同的号码：0，1，2，...，N-1，并且房间里可能有一些钥匙能使你进入下一个房间。
 * <p>
 * 在形式上，对于每个房间 i 都有一个钥匙列表 rooms[i]，每个钥匙 rooms[i][j] 由 [0,1，...，N-1] 中的一个整数表示，
 * 其中 N = rooms.length。 钥匙 rooms[i][j] = v 可以打开编号为 v 的房间。
 * <p>
 * 最初，除 0 号房间外的其余所有房间都被锁住。
 * <p>
 * 你可以自由地在房间之间来回走动。
 * <p>
 * 如果能进入每个房间返回 true，否则返回 false。
 * <p>
 * 示例 1：
 * <p>
 * 输入: [[1],[2],[3],[]]
 * 输出: true
 * 解释:
 * 我们从 0 号房间开始，拿到钥匙 1。
 * 之后我们去 1 号房间，拿到钥匙 2。
 * 然后我们去 2 号房间，拿到钥匙 3。
 * 最后我们去了 3 号房间。
 * 由于我们能够进入每个房间，我们返回 true。
 * 示例 2：
 * <p>
 * 输入：[[1,3],[3,0,1],[2],[0]]
 * 输出：false
 * 解释：我们不能进入 2 号房间。
 * <p>
 * BFS写法
 */
public class CanVisitAllRooms {
    public boolean canVisitAllRoomsByBFS(List<List<Integer>> rooms) {
        Set<Integer> visit = new HashSet<>();
        Deque<Integer> queue = new LinkedList<>();
        queue.addLast(0);
        visit.add(0);
        while (!queue.isEmpty()) {
            int tmp = queue.removeFirst();
            for (int i = 0; i < rooms.get(tmp).size(); i++) {
                int next = rooms.get(tmp).get(i);
                if (!visit.contains(next)) {
                    queue.addLast(next);
                    visit.add(next);
                }
            }
        }
        return visit.size() == rooms.size();
    }

    /**
     * DFS的写法
     * @param rooms
     * @return
     */
    public static boolean canVisitAllRoomsByDFS(List<List<Integer>> rooms) {
        Set<Integer> set = new HashSet<>();
        set.add(0);
        dfs(rooms, 0, set);
        System.out.println(set);
        return set.size() == rooms.size();
    }

    private static void dfs(List<List<Integer>> rooms, int num, Set<Integer> set) {
        for (int a : rooms.get(num)) {
            if (!set.contains(a)){
                set.add(a);
                dfs(rooms, a, set);
            }
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> rooms = new ArrayList<>();
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        List<Integer> list3 = new ArrayList<>();
        List<Integer> list4 = new ArrayList<>();
        list1.add(1);
        list2.add(2);
        list3.add(3);

        rooms.add(list1);
        rooms.add(list2);
        rooms.add(list3);
        rooms.add(list4);

        System.out.println(rooms.toString());
        canVisitAllRoomsByDFS(rooms);
    }
}
