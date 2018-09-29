package com.cwj.str;

import java.util.*;

/**
 * Created by cwj on 18-9-15.
 * 1.给定一个包含括号和其他字符的字符串，假设输入均为有效的括号，（（），这种情况不会出现
 * 2.输出括号的总对数，以及每个括号对的下标
 * 例：输入 ()12((()))
 * 输出
 * <p>
 * 4
 * 0
 * 1
 * 4
 * 9
 * 5
 * 8
 * 6
 * 7
 * 注意输出顺序，一定是左括号越在前，输出就在前
 */
public class Brackets {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        Deque<Integer> stack1 = new LinkedList<>();
        Deque<Integer> stack2 = new LinkedList<>();
        Queue<Integer> queue = new LinkedList<>();
        int sum = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') {
                stack1.push(i);
            }
            if (str.charAt(i) == ')') {
                if (stack1.size() == 1) {
                    queue.add(stack1.pop());
                    queue.add(i);
                    while (!stack2.isEmpty()) {
                        queue.add(stack2.pop());
                    }
                } else {
                    stack2.push(i);
                    stack2.push(stack1.pop());
                }
                sum++;
            }
        }
        System.out.println(sum);
        queue.forEach(a -> System.out.println(a));
        scanner.close();
    }
}

/**
 * 20. 有效的括号
 * 题目描述提示帮助提交记录社区讨论阅读解答
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 * <p>
 * 有效字符串需满足：
 * <p>
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "()"
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: "()[]{}"
 * 输出: true
 * 示例 3:
 * <p>
 * 输入: "(]"
 * 输出: false
 * 示例 4:
 * <p>
 * 输入: "([)]"
 * 输出: false
 * 示例 5:
 * <p>
 * 输入: "{[]}"
 * 输出: true
 */
class IsValid_20 {
    public boolean isValid(String s) {
        if (s.length() == 0)
            return true;
        if (s.charAt(0) == '}' || s.charAt(0) == ')' || s.charAt(0) == ']' || s.length() % 2 != 0) {
            return false;
        }
        Deque<Character> stack = new LinkedList<>();
        for (Character ch : s.toCharArray()) {
            switch (ch) {
                case '(':
                    stack.push(ch);
                    break;
                case ')':
                    if (stack.isEmpty() || !stack.peek().equals('('))
                        return false;
                    stack.pop();
                    break;
                case '[':
                    stack.push(ch);
                    break;
                case ']':
                    if (stack.isEmpty() || !stack.peek().equals('['))
                        return false;
                    stack.pop();
                    break;
                case '{':
                    stack.push(ch);
                    break;
                case '}':
                    if (stack.isEmpty() || !stack.peek().equals('{'))
                        return false;
                    stack.pop();
                    break;
            }
        }
        return stack.isEmpty();
    }
}

/**
 *
 * 22. 括号生成
 题目描述提示帮助提交记录社区讨论阅读解答
 给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。

 例如，给出 n = 3，生成结果为：

 [
 "((()))",
 "(()())",
 "(())()",
 "()(())",
 "()()()"
 ]
 思路1：暴力递归
 */
class GenerateParenthesis_22_baoli {
    public static List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        Deque<Character> stack = new LinkedList<>();
        String str = "";
        dfs(res, 1, 2*n, '(', str, 0, 0);
//        res.forEach(a -> System.out.println(a));
        return res;
    }

    private static void dfs(List<String> res, int index, int n, Character root, String str, int l, int r) {
        str += root;
        if (index == n) {
            if (isValid(str))
                res.add(str);
            return;
        }
        if (l < n)
            dfs(res, index+1, n, '(', str, l+1, r);
        if (r < l)
            dfs(res, index+1, n, ')', str, l, r+1);
    }

    private static boolean isValid(String str){
        Deque<Character> stack = new LinkedList<>();
        for (Character ch:str.toCharArray()) {
            if (ch.equals('('))
                stack.push(ch);
            else if (!stack.isEmpty())
                stack.pop();
            else
                return false;
        }
        return stack.isEmpty();
    }
}

/**
 * 复杂度太高，剪枝
 * 当左括号的个数大于n时，减掉
 * 当右括号的个数大于左括号时减掉
 * 惊奇的发现，这样优化的话，最后的str一定是合格的括号，所以省去了判断括号是否有效
  */
class GenerateParenthesis_22 {
    public static List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        dfs(res, n, "", 0, 0);
        return res;
    }

    private static void dfs(List<String> res, int n, String str, int l, int r) {
        if (l + r == 2*n) {
            res.add(str);
            return;
        }
        if (l < n)
            dfs(res, n,  str+'(', l+1, r);
        if (r < l)
            dfs(res, n, str+')', l, r+1);
    }

    public static void main(String[] args) {
        generateParenthesis(3);
    }
}


