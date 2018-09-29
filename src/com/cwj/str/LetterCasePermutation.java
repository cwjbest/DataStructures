package com.cwj.str;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cwj on 18-8-13.
 * 784. 字母大小写全排列
 给定一个字符串S，通过将字符串S中的每个字母转变大小写，我们可以获得一个新的字符串。返回所有可能得到的字符串集合。

 示例:
 输入: S = "a1b2"
 输出: ["a1b2", "a1B2", "A1b2", "A1B2"]

 输入: S = "3z4"
 输出: ["3z4", "3Z4"]

 输入: S = "12345"
 输出: ["12345"]
 注意：

 S 的长度不超过12。
 S 仅由数字和字母组成。
 */
public class LetterCasePermutation {
    public List<String> letterCasePermutation(String s) {
        List<String> res = new ArrayList<>();
        dfs("", s, res, 0);
        return res;
    }

    private void dfs(String pre, String s, List<String> res, int index){
        if (index == s.length()){
            res.add(pre);
        }else {
            char ch = s.charAt(index);
            if(!Character.isLetter(ch)){
                dfs(pre + ch, s, res, index+1);
            }else {
                ch = Character.toLowerCase(ch);
                dfs(pre+ch, s, res, index+1);

                ch = Character.toUpperCase(ch);
                dfs(pre+ch, s, res, index+1);
            }
        }
    }
}
