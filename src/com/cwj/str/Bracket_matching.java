package com.cwj.str;

import java.util.Stack;

/**
 * Created by cwj on 18-3-26.
 * 给定一个字符串，由（  *  ）三个字符组成，判断是否满足要求左括号和有括号一一对应，
 * 且对应的左括号必定在右括号前面。其中，*可以被当做一个单独的左括号，右括号或者可以当做不存在
 * 1. Input: "()"            Output: True
 * 2. Input: "(*)"          Output: True      //*被当做空字符，不存在
 * 3. Input: "(*))"         Output: True     //星号被当做左括号
 * <p>
 * 首先进行最基础的考虑，（在不考虑星号的情况下）我们必定会选择位置最接近的左右括号配对，
 * 这样避免了人为造成的右括号前面没有左括号匹配的惨剧。因此我们在写程序进行处理的时候，
 * 对于每个右括号判断前面是否有1个左括号能被他拥有，如果左括号数量不足，这个字符串必定是false，
 * 或者当整个串被匹配完之后发现有多余的左括号，这个字符串同样是false
 * <p>
 * 接下来考虑有星号的情况:”)”必须由位置在它之前的”(”或”*”匹配，如果”(”或者”*”数量不足导致的false是无法避免的，
 * 而如果”(“ 比”）”多，将”(”与”*”优先匹配可以减小false的可能性。
 * 举个例子如样例3，从左往右遍历的时候，优先匹配”（”和”*”，遇见第一个”)”，
 * 发现没有单独的”(”,从”(*”的组合中拆出一个”(“与之匹配，而原先匹配中的*因为可以等同于不存在便不予理会，
 * 接着遇到第二个”)”,拿走刚才剩余的”*”。综上我们可以观察到，”(”容易受制于”)”而将其与”*”匹配后就很灵活，
 * 不仅避免了数量太多带来的麻烦，也能在和*匹配后再次提供自身给”)”进行匹配。而如果这样匹配结束还有多余的”(”则必定false
 * <p>
 * 我们设l（left）为必须被右括号匹配的左括号数量，cp（couple）为前面左括号和星号数量。
 * 遍历字符串，遇到左括号和星号的时候,cp++; 遇到右括号的时候cp—;  遇到星号，默认先于前面的左括号(l>0)匹配,此时(l—)，
 * 遇到右括号，默认先与前面必须与右括号匹配的左括号匹配，此时(l—;cp—;)或者在支援兵中考虑(cp—)
 * 注意cp是前方左右的左括号和星号数量，一旦cp<0即false.  匹配完发现(l>0)即多出了左括号，也为false。剩下的情况就是true了
 */
public class Bracket_matching {
    public boolean checkValidString(String s) {
        int len = s.length();
        int l = 0, cp = 0;

        for (int i = 0; i < len; i++) {
            if (s.charAt(i) == '(') {
                cp++;
                l++;
            } else if (s.charAt(i) == '*') {
                if (l > 0) {
                    l--;
                }
                cp++;
            } else {
                if (l > 0) {
                    l--;
                }
                cp--;
                if (cp < 0) {
                    return false;//(+*数量<0
                }
            }
        }
        if (l == 0)
            return true;
        else
            return false;//l>0
    }

    public static void main(String[] args) {
        Bracket_matching bm = new Bracket_matching();
        String s1 = "()";
        String s2 = "(()";
        String s3 = "()(";
        String s4 = "(*)";
        String s5 = "(*))";
        System.out.println(bm.checkValidString(s1));
        System.out.println(bm.checkValidString(s2));
        System.out.println(bm.checkValidString(s3));
        System.out.println(bm.checkValidString(s4));
        System.out.println(bm.checkValidString(s5));
    }
}

/**
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 * 括号必须以正确的顺序关闭，"()" 和 "()[]{}" 是有效的但是 "(]" 和 "([)]" 不是。
 * 涉及到配对的问题，很明显要用stack了，出现右括号时，跟栈顶元素比较，配对则出栈，否则false
 * 最后栈为空，true，不为空，false
 */

class Solution {
    public boolean isValid(String s) {
        if (s.charAt(0) == '}' || s.charAt(0) == ')' || s.charAt(0) == ']') {
            return false;
        }
        Stack stack = new Stack();
        for (int i = 0; i < s.length(); i++) {
            switch (s.charAt(i)){
                case '(':
                    stack.push(s.charAt(i));
                    break;
                case ')':
                    if (stack.empty() || !stack.peek().equals("("))
                        return false;
                    stack.pop();
                    break;
                case '[':
                    stack.push(s.charAt(i));
                    break;
                case ']':
                    if (stack.empty() || !stack.peek().equals("["))
                        return false;
                    stack.pop();
                    break;
                case '{':
                    stack.push(s.charAt(i));
                    break;
                case '}':
                    if (stack.empty() || !stack.peek().equals("{"))
                        return false;
                    stack.pop();
                    break;
            }
        }
        return stack.empty();
    }
}