package com.cwj.str;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by cwj on 18-8-31.
 * 71. 简化路径
 给定一个文档 (Unix-style) 的完全路径，请进行路径简化。

 例如，
 path = "/home/", => "/home"
 path = "/a/./b/../../c/", => "/c"

 边界情况:

 你是否考虑了 路径 = "/../" 的情况？
 在这种情况下，你需返回 "/" 。
 此外，路径中也可能包含多个斜杠 '/' ，如 "/home//foo/" 。
 在这种情况下，你可忽略多余的斜杠，返回 "/home/foo" 。
 */
public class SimplifyPath_71 {
    public static String simplifyPath(String path) {
        if (path == null || path.length() == 0)
            return path;
        String res = "";
        String[] str = path.split("/");
        Deque<String> stack = new LinkedList<>();
        for (String s: str) {
            if (!s.equals("..") && !s.equals(".") && !s.equals("")){
                stack.push(s);
            }else if (s.equals("..") && !stack.isEmpty()){
                stack.pop();
            }
        }
        if (stack.isEmpty())
            return "/";
        else{
           while (!stack.isEmpty()){
               res = "/" + stack.pop() + res;
           }
        }
        return res;
    }

    public static void main(String[] args) {
        String str = "/abc/...";
        simplifyPath(str);
    }
}
