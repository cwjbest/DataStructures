package com.cwj.str;


import java.util.*;

/**
 * Created by cwj on 18-9-20.
 *
 */
public class MostCommonWord_819 {
    public String mostCommonWord(String paragraph, String[] banned) {
        Map<String, Integer> map = new HashMap<>();
        Set<String> set = new HashSet<>();
        Collections.addAll(set, banned);
        String[] str = paragraph.split(" ");
        String res = "";
        int maxCount = Integer.MIN_VALUE;
        for (String s : str) {
            if (!Character.isLetter(s.indexOf(s.length() - 1)))
                s = s.substring(0, s.length() - 1);
            char[] chs = s.toCharArray();
            for (int i = 0; i < chs.length; i++) {
                chs[i] = Character.toLowerCase(chs[i]);
            }
            s = Arrays.toString(chs);
            if (map.containsKey(s)){
                map.put(s, map.get(s) + 1);
                if (map.get(s) > maxCount){
                    res = s;
                    maxCount = map.get(s);
                }
            }
            else if (!set.contains(s)) {
                map.put(s, 1);
                if (maxCount < 1)
                    res = s;
            }
        }
        return res;
    }
}
