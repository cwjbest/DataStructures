package com.cwj.dp;

/**
 * Created by cwj on 18-8-24.
 * <p>
 * 263. 丑数
 * 编写一个程序判断给定的数是否为丑数。
 * <p>
 * 丑数就是只包含质因数 2, 3, 5 的正整数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 6
 * 输出: true
 * 解释: 6 = 2 × 3
 * 示例 2:
 * <p>
 * 输入: 8
 * 输出: true
 * 解释: 8 = 2 × 2 × 2
 * 示例 3:
 * <p>
 * 输入: 14
 * 输出: false
 * 解释: 14 不是丑数，因为它包含了另外一个质因数 7。
 * 说明：
 * <p>
 * 1 是丑数。
 * 输入不会超过 32 位有符号整数的范围: [−2^31,  2^31 − 1]。
 */
public class IsUgly {

    /**
     * 寻找第N个丑数
     * 1. 暴力枚举
     */
    public static boolean isUgly(int num) {
        if (num == 1) return true;
        while (num % 2 == 0)
            num = num / 2;
        while (num % 3 == 0)
            num = num / 3;
        while (num % 5 == 0)
            num = num / 5;
        return (num == 1);
    }
    public static int findUglyNumber(int index) {
        int num = 0;
        int count = 0;
        while (count < index) {
            num++;
            if (isUgly(num))
                count++;
        }
        return num;
    }

    /**
     *
     * 2.
     * 3指针法，每个丑数都是由前面的某个丑数或X2或X3或X5得到的
     * 刚开始，3个指针都指向1，分别是X2指针，X3指针，X5指针，找下一个丑数时，选出三个指针中最小的数
     * 比如下一个数X2指针值最小，所以下一个丑数为2，这时X2指针下移，，，
     * O（n）即可解决
     * 这种思想可以用在很多找数字的题目上面，后面的数字是由前面的数字通过计算组成的
     * @param n
     * @return
     */
    public static int findUglyNumber2(int n) {
        int[] help = new int[n];//存放丑数
        help[0] = 1;
        int i2 = 0;
        int i3 = 0;
        int i5 = 0;

        int index = 1;
        while (index < n) {
            help[index] = Math.min(help[i2] * 2, Math.min(help[i3] * 3, help[i5] * 5));
            if (help[index] == help[i2] * 2)
                i2++;
            if (help[index] == help[i3] * 3)
                i3++;
            if (help[index] == help[i5] * 5)
                i5++;
            index++;
        }
        return help[index-1];
    }

    /**
     *
     * 313. 超级丑数
     题目描述提示帮助提交记录社区讨论阅读解答
     编写一段程序来查找第 n 个超级丑数。

     超级丑数是指其所有质因数都是长度为 k 的质数列表 primes 中的正整数。

     示例:

     输入: n = 12, primes = [2,7,13,19]
     输出: 32
     解释: 给定长度为 4 的质数列表 primes = [2,7,13,19]，前 12 个超级丑数序列为：[1,2,4,7,8,13,14,16,19,26,28,32] 。
     说明:

     1 是任何给定 primes 的超级丑数。
     给定 primes 中的数字以升序排列。
     0 < k ≤ 100, 0 < n ≤ 106, 0 < primes[i] < 1000 。
     第 n 个超级丑数确保在 32 位有符整数范围内。

     丑数是2，3，5，超级丑数是给了个列表，思想一样
     */

    public int nthSuperUglyNumber(int n, int[] primes) {
        int[] help = new int[n];
        help[0] = 1;
        int[] idxPrimes = new int[primes.length];//指针数组，用来记住指针位置,i2,i3,i5...
        int index = 1;
        while (index < n){
            int min = Integer.MAX_VALUE;
            //找最小值
            for (int i = 0; i < primes.length; i++) {
                int temp = help[idxPrimes[i]] * primes[i];
                min = min > temp ? temp : min;
            }

            //判断是哪个指针
            for (int i = 0; i < primes.length; i++) {
                if (min == help[idxPrimes[i]] * primes[i])
                    idxPrimes[i]++;
            }

            help[index++] = min;
        }
        return help[index-1];
    }

    public static void main(String[] args) {
        boolean[] flag = new boolean[3];
        System.out.println(flag[1]);
    }

}
