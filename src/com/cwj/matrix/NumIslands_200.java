package com.cwj.matrix;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by cwj on 18-9-9.
 * 200. 岛屿的个数
 题目描述提示帮助提交记录社区讨论阅读解答
 给定一个由 '1'（陆地）和 '0'（水）组成的的二维网格，计算岛屿的数量。一个岛被水包围，并且它是通过水平方向或垂直方向上相邻的陆地连接而成的。你可以假设网格的四个边均被水包围。

 示例 1:

 输入:
 11110
 11010
 11000
 00000

 输出: 1
 示例 2:

 输入:
 11000
 11000
 00100
 00011

 输出: 3

 BFS:1. 遍历每一个结点，如果某结点是陆地且未访问过，岛数目加1，修改未访问标志位，然后把该点放入队列中，
        以备扩展岛屿使用，进入2
     2. 队列不为空时，取出点，然后尝试对其上下左右的四个点进行扩展，如果其邻点是陆地且未访问过，则修改标志位并入队列．
        第2步运行结束时一个新岛的扩展结束，回到1．
     3. 1中提到的遍历结束时，程序结束．
 DFS: 遍历每个节点，当为陆地时。递归遍历四个方向所有能走的点，将陆地变为0， 直到遇见0或者边界时停止。
 */
public class NumIslands_200 {
    public static int numIslands(char[][] grid) {
        int res = 0;
        int rows = grid.length;
        int cols = rows > 0 ? grid[0].length : 0;
        if (rows == 0 || cols == 0) return res;
        int[][] visited = new int[rows][cols];
        Queue<Integer> queue = new LinkedList<>();
        int dx[] = {-1, 1, 0, 0};
        int dy[] = {0, 0, -1, 1};
        int x = 0, y = 0, xx = 0, yy = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '1' && visited[i][j] == 0){
                    queue.add(i);
                    queue.add(j);
                    visited[i][j] = 1;
                    res++;
                    while (!queue.isEmpty()){
                        x = queue.remove();
                        y = queue.remove();
                        for (int k = 0; k < 4; k++) {
                            xx = x + dx[k];
                            yy = y + dy[k];
                            if (xx < 0 || xx >= rows || yy < 0 || yy >= cols)
                                continue;
                            if (grid[xx][yy] == '1' && visited[xx][yy] == 0){
                                queue.add(xx);
                                queue.add(yy);
                                visited[xx][yy] = 1;
                            }
                        }
                    }

                }
            }
        }
        return res;
    }

    /**
     * dfs
     * @param grid
     * @return
     */
    public static int numIslandsByDFS(char[][] grid){
        int res = 0;
        int rows = grid.length;
        int cols = rows == 0 ? 0 : grid[0].length;
        if (rows == 0 || cols == 0)
            return 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '1'){
                    dfs(grid, i, j);
                    res++;
                }
            }
        }
        return res;
    }

    public static void dfs(char[][] grid, int i, int j){
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == '0')
            return;
        grid[i][j] = '0';
        dfs(grid, i+1, j);
        dfs(grid, i-1, j);
        dfs(grid, i, j+1);
        dfs(grid, i, j-1);
    }


    /**
     *695. 岛屿的最大面积
     题目描述提示帮助提交记录社区讨论阅读解答
     给定一个包含了一些 0 和 1的非空二维数组 grid , 一个 岛屿 是由四个方向 (水平或垂直) 的 1 (代表土地) 构成的组合。
     你可以假设二维矩阵的四个边缘都被水包围着。

     找到给定的二维数组中最大的岛屿面积。(如果没有岛屿，则返回面积为0。)

     示例 1:

     [[0,0,1,0,0,0,0,1,0,0,0,0,0],
     [0,0,0,0,0,0,0,1,1,1,0,0,0],
     [0,1,1,0,1,0,0,0,0,0,0,0,0],
     [0,1,0,0,1,1,0,0,1,0,1,0,0],
     [0,1,0,0,1,1,0,0,1,1,1,0,0],
     [0,0,0,0,0,0,0,0,0,0,1,0,0],
     [0,0,0,0,0,0,0,1,1,1,0,0,0],
     [0,0,0,0,0,0,0,1,1,0,0,0,0]]
     对于上面这个给定矩阵应返回 6。注意答案不应该是11，因为岛屿只能包含水平或垂直的四个方向的‘1’。

     示例 2:

     [[0,0,0,0,0,0,0,0]]
     对于上面这个给定的矩阵, 返回 0。

     注意: 给定的矩阵grid 的长度和宽度都不超过 50。
     *
     */
    public static int maxAreaOfIsland(int[][] grid) {
        int rows = grid.length;
        int cols = rows > 0 ? grid[0].length : 0;
        if (rows == 0 || cols == 0)
            return 0;
        int maxArea = 0;
        int area = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1){
                    area = dfsMaxArea(grid, i, j);
                    maxArea = Math.max(maxArea, area);
                }
            }
        }
        return maxArea;
    }

    public static int dfsMaxArea(int[][] grid, int i, int j){
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == 0)
            return 0;
        grid[i][j] = 0;
        return dfsMaxArea(grid, i+1, j) +
               dfsMaxArea(grid, i-1, j) +
                dfsMaxArea(grid, i, j+1) +
                dfsMaxArea(grid, i, j-1) + 1;
    }

    /**
     * 463. 岛屿的周长
     题目描述提示帮助提交记录社区讨论阅读解答
     给定一个包含 0 和 1 的二维网格地图，其中 1 表示陆地 0 表示水域。网格中的格子水平和垂直方向相连（对角线方向不相连）。整个网格被水完全包围，但其中恰好有一个岛屿（或者说，一个或多个表示陆地的格子相连组成的岛屿）。岛屿中没有“湖”（“湖” 指水域在岛屿内部且不和岛屿周围的水相连）。格子是边长为 1 的正方形。网格为长方形，且宽度和高度均不超过 100 。计算这个岛屿的周长。

     示例 :

     [[0,1,0,0],
     [1,1,1,0],
     [0,1,0,0],
     [1,1,0,0]]

     答案: 16
     解释: 它的周长是下面图片中的 16 个黄色的边：
     遍历每个点，朝四个方向走，到边界或者为0时，周长+1

     */
    public int islandPerimeter(int[][] grid) {
        int res = 0;
        int rows = grid.length;
        int cols = rows > 0 ? grid[0].length : 0;
        if (rows == 0 || cols == 0) return res;
        int dx[] = {-1, 1, 0, 0};
        int dy[] = {0, 0, -1, 1};
        int xx, yy;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1){
                    for (int k = 0; k < 4; k++) {
                        xx = i + dx[k];
                        yy = j + dy[k];
                        if (xx < 0 || xx >= rows || yy < 0 || yy >= cols || grid[xx][yy] == 0)
                            res++;
                    }
                }
            }
        }
        return res;
    }

    /**
     * 科学研究表明，拼接周长的计算，只要求右边和下边的邻居
     * 然后总的方块数×4 - 邻居数×2就是最后的周长
     * @param grid
     * @return
     */
    public static int islandPerimeter2(int[][] grid) {
        int islands = 0;
        int neighbors = 0;
        int rows = grid.length;
        int cols = rows > 0 ? grid[0].length : 0;
        if (rows == 0 || cols == 0) return 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1){
                    islands++;
                    if(i < rows-1 && grid[i+1][j] == 1)
                        neighbors++;
                    if(j < cols-1 && grid[i][j+1] == 1)
                        neighbors++;
                }
            }
        }
        return islands * 4 - neighbors * 2;
    }



    public static void main(String[] args) {
        char[][] nums = new char[][]{{'1','1','1','1','0'}, {'1','1','0','1','0'}, {'1','1','0','0','0'},
                {'0','0','0','0','0'}};
        System.out.println(numIslands(nums));
    }
}
