package com.cwj.str;

import com.cwj.linklist.*;

import java.util.*;

/**
 * Created by cwj on 18-3-27.
 * 1. 判断一个整数是否是回文数。不能使用辅助空间。
 * 一些提示:
 * 负整数可以是回文数吗？（例如 -1） 不是
 * 如果你打算把整数转为字符串，请注意不允许使用辅助空间的限制。
 * 你也可以考虑将数字颠倒。但是如果你已经解决了 “颠倒整数” 问题的话，就会注意到颠倒整数时可能会发生溢出。你怎么来解决这个问题呢？
 * 本题有一种比较通用的解决方式。
 * 既然题目已经给了提示，既然reverse算法已经解决，那么最好是将X与X reverse 之后的值比较。
 * 但是这可能会出现溢出问题，回文，不一定要比较完，只需比较一半就好了，太经典了！！
 * 比较一半的话，怎么都不可能溢出，while条件是 x>res，一旦发现res>x时，循环马上停止，防止了溢出
 */

//这是考虑溢出时的情况
public class Palindrome {
    public boolean isPalindrome(int x) {
        int res = 0;
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }
        while (x > res) {
            res = res * 10 + x % 10;
            x /= 10;
        }
        return x == res || x == res / 10;
    }


    /**
     * 2. (leetcode 564)给一个字符串str， 代表一个整数，找到除了这个数之外，绝对值和这个数相差最小的回文数。
     * 例如 str=“123” 返回“121”
     * 注意，假设str一定能变成long型
     * <p>
     * 思路：前半部分copy到后半部分 12345-12321， 123456-123321，貌似很有道理，但是……
     * 199-191 是吗？不是，202才是距离199最近的回文数
     * 所以，要找两个数，一个是比当前数大的最小回文数，一个是比当前数小的最大回文数
     */
    public static String nearestPalindromic(String n) {
        Long num = Long.valueOf(n);
        Long raw = getRawPalindrome(n);
        Long big = raw > num ? raw : getBigPalindrome(raw);
        Long small = raw < num ? raw : getSmallPalindrome(raw);
        return String.valueOf(big - num >= num - small ? small : big);
    }

    //将string的前半部分逆序然后copy到后半部分
    // 1234-1221 12345-12321
    public static Long getRawPalindrome(String n) {
        char[] chars = n.toCharArray();
        for (int i = 0; i < chars.length / 2; i++) {
            chars[chars.length - 1 - i] = chars[i];
        }
        return Long.valueOf(String.valueOf(chars));
    }

    //找出比String大的最小的回文数
    //12345-12421 123456-124421
    //从中间位置+1，然后逆序copy
    public static Long getBigPalindrome(Long raw) {
        char[] chars = String.valueOf(raw).toCharArray();
        char[] res = new char[chars.length + 1];//加法要考虑进位，所以多申请一位空间
        res[0] = '0';
        for (int i = 0; i < chars.length; i++) {
            res[i + 1] = chars[i];
        }
        int size = chars.length;
        for (int i = (size - 1) / 2 + 1; i >= 0; i--) {
            if (++res[i] > '9')//进位操作
                res[i] = '0';
            else
                break;
        }
        int offset = res[0] == '1' ? 1 : 0;
        size = res.length;
        for (int i = size - 1; i >= (size + offset) / 2; i--) {
            res[i] = res[size - offset - i];//如果最高位为1，就从下标0开始换，如果最高位为0就从下标1开始换
        }
        return Long.valueOf(String.valueOf(res));
    }

    //找出比String小的最大的回文数
    //54321-54245 654321-653356
    //从中间位置-1，然后逆序copy
    //注意，减法退位情况特殊，1000-999，如果最高位退为0，则直接返回比他少一位的9...
    public static Long getSmallPalindrome(Long raw) {
        char[] chars = String.valueOf(raw).toCharArray();
        char[] res = new char[chars.length];
        int size = res.length;
        for (int i = 0; i < size; i++) {
            res[i] = chars[i];
        }
        for (int i = (size - 1) / 2; i >= 0; i--) {
            if (--res[i] < '0') {
                res[i] = '9';
            } else
                break;
        }

        if (res[0] == '0') {
            res = new char[size - 1];
            for (int i = 0; i < res.length; i++) {
                res[i] = '9';
            }
            //如果这个数是1，直接返回0
            return size == 1 ? 0 : Long.parseLong(String.valueOf(res));
        }
        for (int i = 0; i < size / 2; i++) {
            res[size - i - 1] = res[i];
        }
        return Long.valueOf(String.valueOf(res));
    }

    /**
     * 3.(125) 验证回文串
     * 题目描述提示帮助提交记录社区讨论阅读解答
     * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
     * <p>
     * 说明：本题中，我们将空字符串定义为有效的回文串。
     * <p>
     * 示例 1:
     * <p>
     * 输入: "A man, a plan, a canal: Panama"
     * 输出: true
     * 示例 2:
     * <p>
     * 输入: "race a car"
     * 输出: false
     * <p>
     * <p>
     * 思路：双指针循环时，while有时比for好用，注意合并循环！！
     */
    public static boolean isPalindrome2(String s) {
        if (s.equals("") || (s.length() == 1)) return true;
        int l = 0, r = s.length() - 1;
        while (l < r) {
            if (!Character.isLetterOrDigit(s.charAt(l)))
                l++;
            else if (!Character.isLetterOrDigit(s.charAt(r)))
                r--;
            else if (Character.toLowerCase(s.charAt(l)) == Character.toLowerCase(s.charAt(r))) {
                l++;
                r--;
            } else
                return false;
        }
        return true;
    }

    /**
     * 3. (234)回文链表
     * 请判断一个链表是否为回文链表。
     * <p>
     * 示例 1:
     * <p>
     * 输入: 1->2
     * 输出: false
     * 示例 2:
     * <p>
     * 输入: 1->2->2->1
     * 输出: true
     * 进阶：
     * 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
     * <p>
     * 要求空间为1，所以不能用数组，也不能用栈，可以将链表后半部分反转，然后与前半部分比较
     */
    public static boolean isPalindrome3(ListNode head) {
        if (head == null || head.next == null) return false;
        ListNode slow = head;
        ListNode fast = head;//快慢指针找出中点
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode mid = slow.next;//从中点的下一个开始反转

        //链表反转
        ListNode midHead = ReverseList.reverseList(mid);

        // 将链表的前半部分与反转后的后半部分比较
        while (midHead != null) {
            if (midHead.val == head.val) {
                midHead = midHead.next;
                head = head.next;
            } else
                return false;
        }
        return true;
    }

    /**
     * 680. 验证回文字符串 Ⅱ
     * 给定一个非空字符串 s，最多删除一个字符。判断是否能成为回文字符串。
     * <p>
     * 示例 1:
     * <p>
     * 输入: "aba"
     * 输出: True
     * 示例 2:
     * <p>
     * 输入: "abca"
     * 输出: True
     * 解释: 你可以删除c字符。
     * 注意:
     * <p>
     * 字符串只包含从 a-z 的小写字母。字符串的最大长度是50000。
     */
    public boolean validPalindrome(String s) {
        if (s.length() == 1) return true;
        char[] chs = s.toCharArray();
        int i = 0, j = s.length() - 1;
        while (i < j) {
            if (chs[i] != chs[j]) {
                return (isPalindrome(s, i + 1, j) || isPalindrome(s, i, j - 1));
//                if (isPalindrome(s, i + 1, j) || isPalindrome(s, i, j - 1))
//                    return true;
//                else
//                    return false;
//                这几句代码，可以用上面一句替代！
            }
//            这里就不能写成if(chs[i++] != chs[j--]),因为循环体中用到了i，j
            i++;
            j--;
        }

        return true;
    }

    public static boolean isPalindrome(String s, int i, int j) {
        char[] chs = s.toCharArray();
        while (i < j) {
            if (chs[i++] != chs[j--])
                return false;
//            if (chs[i] != chs[j])
//                return false;
//            i++;
//            j--;
//            如果i，j迭代体中没有其他i，j的操作，可以用上面的代码代替
        }
        return true;
    }

    /**
     * 409. 最长回文串
     给定一个包含大写字母和小写字母的字符串，找到通过这些字母构造成的最长的回文串。

     在构造过程中，请注意区分大小写。比如 "Aa" 不能当做一个回文字符串。

     注意:
     假设字符串的长度不会超过 1010。

     示例 1:

     输入:
     "abccccdd"

     输出:
     7

     解释:
     我们可以构造的最长的回文串是"dccaccd", 它的长度是 7。
     * 思路：用一个map存储字母及出现的次数，最后出现次数为偶数的加起来，有奇数的话加1，没有就返回
     */
    public int longestPalindrome(String s) {
        int[] arr = new int[58];//大写字母和小写字母之间有6个符号，所以申请26*2+6=58个空间
        char[] chs = s.toCharArray();
        int sum = 0;
        for (char c:chs) {
            arr[c-'A']++;//下标来标识字母
            if((arr[c-'A'] & 1) == 0)
                sum += 2;
        }
        for (int val:arr) {
            if((val & 1) == 1) {
                sum++;
                break;
            }
        }
        return sum;

//        Map<Character, Integer> map = new HashMap<>();
//        int sum = 0;
//        for(int i=0; i<s.length(); i++){
//            if(map.containsKey(s.charAt(i))){
//                map.put((s.charAt(i)), map.get(s.charAt(i))+1);
//                if((map.get(s.charAt(i)) & 1) == 0)
//                    sum += 2;
//
//            }
//            else
//                map.put(s.charAt(i), 1);
//        }
//
//        for(int i=0; i<s.length(); i++){
//            if((map.get(s.charAt(i)) & 1) == 1){
//                sum += 1;
//                break;
//            }
//        }
//        return sum;
    }

    /**
     * 5. 最长回文子串
     给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为1000。
     示例 1：
     输入: "babad"
     输出: "bab"
     注意: "aba"也是一个有效答案。
     示例 2：
     输入: "cbbd"
     输出: "bb"
     动态规划，主要找出状态转移方程 当s[i][j]是回文串的时候，s[i+1][j-1]也是回文串
     所以 f(i,j) = 1. true,                         i=j
                  2. s[i] = s[j],                 j=i+1
                 3. s[i] = s[j] and f(i+1, j-1), j>i+1
     */
    public static String longestPalindrome2(String s) {
        int length = s.length();
        boolean[][] dp = new boolean[length][length];
        int maxLen = 0;
        int begin = 0;
        for (int j = 0; j < length; j++) {
            for (int i = j; i >= 0; i--) {
                if (s.charAt(j) == s.charAt(i) && (j-i<2 || dp[i+1][j-1])){
                    dp[i][j] = true;
                    if (j-i+1 > maxLen){
                        maxLen = j-i+1;
                        begin = i;
                    }
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }

    /**
     * 思路2，中心扩展法，以每一个字符作为中心，然后向两边扩展
     * 注意奇偶性，即存在"YXaaXY"这样长度为偶数的特殊子串，这里可以将"aa"视为'a'。
     */
    private static int[] palindrome(String str, int mid){
        int left = mid - 1;
        int right = mid + 1;
        while (str.charAt(mid) == str.charAt(right))
            right++;
        while (left >= 0 && right < str.length() && str.charAt(left) == str.charAt(right)){
            left--;
            right++;
        }
        int[] res = new int[2];
        res[0] = left+1;
        res[1] = right-1;
        return res;
    }

    public static String longestPalindrome3(String str){
        if (str == null) return null;
        if (str.length() == 1) return str;
        int max = 1;
        int start = 0;
        for (int i = 0; i < str.length(); i++) {
            int[] tmp = palindrome(str, i);
            int longest = tmp[1] - tmp[0] + 1;
            if (longest > max){
                max = longest;
                start = tmp[0];
            }
        }
        return str.substring(start, start+max);
    }

    /**
     * 思路3：马拉车算法，比较牛逼
     * https://blog.csdn.net/liuwei0604/article/details/50414542
     */
    private static String preProcess(String s){
        int n = s.length();
        if (n == 0) return "^";
        String res = "^";
        for (int i = 0; i < n; i++) {
            res += "#" + s.charAt(i);
        }
        res += "#";
        return res;
    }
    public static String longestPalindrome4(String str){
        char[] t = preProcess(str).toCharArray();
        int n = t.length;
        int[] p = new int[n];
        int c = 0, r = 0;
        for (int i = 1; i < n-1; i++) {
            int i_mirror = c - (i - c);
            if (i < r)
                p[i] = Math.min(r-i, p[i_mirror]);
            else
                p[i] = 0;

            while (t[i+p[i]+1] == t[i-p[i]-1])
                p[i]++;

            if (i + p[i] > r){
                c = i;
                r = i + p[i];
            }
        }

        int maxLen = 0;
        int center = 0;
        for (int i = 0; i < n-1; i++) {
            if (p[i] > maxLen){
                maxLen = p[i];
                center = i;
            }
        }

        int begin = (center - 1 - maxLen) / 2;
        return str.substring(begin, begin + maxLen);
    }

    /**
     *336. 回文对
     给定一组唯一的单词， 找出所有不同 的索引对(i, j)，使得列表中的两个单词， words[i] + words[j] ，可拼接成回文串。

     示例 1:

     输入: ["abcd","dcba","lls","s","sssll"]
     输出: [[0,1],[1,0],[3,2],[2,4]]
     解释: 可拼接成的回文串为 ["dcbaabcd","abcddcba","slls","llssssll"]
     示例 2:

     输入: ["bat","tab","cat"]
     输出: [[0,1],[1,0]]
     解释: 可拼接成的回文串为 ["battab","tabbat"]

     思路1：暴力法，n的三次方，过不了
     思路2：将字符串逆序，然后看map里有没有,但是考虑这种 121345， 543，可以拼接，但并不是逆序……
     */
    /**
     * 思路3：思路2是一种情况，但不是全部的情况
     * 121345
     * 1拿出来，剩下的21345逆序，看看map里有没有
     * 121拿出来，剩下的345逆序，看看map中有没有
     * 把前缀的回文串拿出来，剩下的逆序，看看map里有没有
     * 把后缀的回文串拿出来，剩下的逆序，看看map里有没有
     * 现在问题就是怎么迅速的找出哪些前缀（后缀）是回文串
     * 答案是，马拉车算法！！！
     */
    public List<List<Integer>> palindromePairs2(String[] words) {
        List<List<Integer>> res = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            map.put(words[i], i);
        }

        for (String str:words) {
            String reverse = ReverseVowels.reverseString(str);
            if (map.containsKey(reverse)){
                List<Integer> list = new ArrayList<>();
                list.add(map.get(str));
                list.add(map.get(reverse));
                res.add(list);
            }
        }
        return res;
    }



    public static void main(String[] args) {
//         isPalindrome2("A man, a plan, a canal: Panama");
//        ListNode node1 = new ListNode(1);
//        ListNode node2 = new ListNode(2);
//        ListNode node3 = new ListNode(3);
//        ListNode node4 = new ListNode(4);
//        ListNode node5 = new ListNode(5);
//        node1.next = node2;
//        node2.next = node3;
//        node3.next = node4;
//        node4.next = node5;

//        isPalindrome3(node1);

//        Integer integer = 4;
//        System.out.println(integer & 1);
        String str = "babad";
        longestPalindrome2(str);

        System.out.println(preProcess(str));
    }
}
