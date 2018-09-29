package com.cwj.list;

/**
 * Created by cwj on 18-8-26.
 * 335. 路径交叉
 * 给定一个含有 n 个正数的数组 x。从点 (0,0) 开始，先向北移动 x[0] 米，然后向西移动 x[1] 米，向南移动 x[2] 米，向东移动 x[3] 米，持续移动。也就是说，每次移动后你的方位会发生逆时针变化。
 * <p>
 * 编写一个 O(1) 空间复杂度的一趟扫描算法，判断你所经过的路径是否相交。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [2,1,1,2]
 * <p>
 * ?????
 * ?   ?
 * ???????>
 * ?
 * <p>
 * 输出: true
 * 解释: 路径交叉了
 * 示例 2:
 * <p>
 * 输入: [1,2,3,4]
 * <p>
 * ????????
 * ?      ?
 * ?
 * ?
 * ?????????????>
 * <p>
 * 输出: false
 * 解释: 路径没有相交
 * 示例 3:
 * <p>
 * 输入: [1,1,1,1]
 * <p>
 * ?????
 * ?   ?
 * ?????>
 * <p>
 * 输出: true
 * 解释: 路径相交了
 */
public class IsSelfCrossing {
    public boolean isSelfCrossing(int[] x) {
        if (x == null || x.length < 4)  //3条边怎么也不会相交
            return false;
        if (x.length > 3 && x[2] <= x[0] && x[3] >= x[1])//4条边相交的所有可能
            return true;
        if (x.length > 4 && ((x[3] <= x[1] && x[4] >= x[2]) || x[3] == x[1] && x[0] + x[4] >= x[2]))//5条边时相交的所有可能
            return true;
        for (int i = 5; i < x.length; i++) {//6条边的相交情况，大于6条边时情况会重复
            if (
                    x[i - 1] <= x[i - 3]
                    &&
                    ((x[i] >= x[i - 2])
                    ||
                     x[i - 2] >= x[i - 4] && x[i - 5] + x[i - 1] >= x[i - 3] && x[i - 4] + x[i] >= x[i - 2]))
                return true;
        }
        return false;
    }
}
