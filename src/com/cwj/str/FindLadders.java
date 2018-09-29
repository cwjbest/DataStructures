package com.cwj.str;

import java.util.*;

/**
 * Created by cwj on 18-8-16.
 * 126. 单词接龙 II
 * 题目描述提示帮助提交记录社区讨论阅读解答
 * 给定两个单词（beginWord 和 endWord）和一个字典 wordList，找出所有从 beginWord 到 endWord 的最短转换序列。转换需遵循如下规则：
 * <p>
 * 每次转换只能改变一个字母。
 * 转换过程中的中间单词必须是字典中的单词。
 * 说明:
 * <p>
 * 如果不存在这样的转换序列，返回一个空列表。
 * 所有单词具有相同的长度。
 * 所有单词只由小写字母组成。
 * 字典中不存在重复的单词。
 * 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
 * 示例 1:
 * <p>
 * 输入:
 * beginWord = "hit",
 * endWord = "cog",
 * wordList = ["hot","dot","dog","lot","log","cog"]
 * <p>
 * 输出:
 * [
 * ["hit","hot","dot","dog","cog"],
 * ["hit","hot","lot","log","cog"]
 * ]
 * 示例 2:
 * <p>
 * 输入:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 * <p>
 * 输出: []
 * <p>
 * 解释: endWord "cog" 不在字典中，所以不存在符合要求的转换序列。
 */
public class FindLadders {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        wordList.add(beginWord);
        HashMap<String, List<String>> nexts = getNexts(wordList);
        HashMap<String, Integer> distances = getDistances(beginWord, nexts);
        LinkedList<String> pathList = new LinkedList<>();
        List<List<String>> res = new ArrayList<>();
        getShortestPath(beginWord, endWord, nexts, distances, pathList, res);
        return res;
    }

    //找beginWord的邻居，也就是beginWord改变一个字母后的字符串如果在dict中，则这个字符串就是它的邻居
    //将其加入到一个list中，注意如果用两个for（n）,复杂度是n方，用26个字母依次比较优化到26*String.length
    private static List<String> getNext(String word, Set<String> dict) {
        List<String> res = new ArrayList<>();
        char[] chars = word.toCharArray();
        for (char cur = 'a'; cur <= 'z'; cur++) {
            for (int i = 0; i < chars.length; i++) {
                if (chars[i] != cur) {
                    char tmp = chars[i];
                    chars[i] = cur;
                    if (dict.contains(String.valueOf(chars)))
                        res.add(String.valueOf(chars));
                    chars[i] = tmp;
                }
            }
        }
        return res;
    }

    //找出一个单词的所有邻居
    private static HashMap<String, List<String>> getNexts(List<String> words){
        Set<String> dict = new HashSet<>(words);
        HashMap<String, List<String>> nexts = new HashMap<>();
        for (int i = 0; i < words.size(); i++) {
            nexts.put(words.get(i), getNext(words.get(i), dict));
        }
//        for (int i = 0; i < words.size(); i++) {
//            nexts.put(words.get(i), getNext(words.get(i), dict));
//        }
        return nexts;
    }

    //层次遍历找出每个单词距离beginWorld的长度
    private static HashMap<String, Integer> getDistances(String beginWord, HashMap<String, List<String>> nexts){
        HashMap<String, Integer> distances = new HashMap<>();
        distances.put(beginWord, 0);
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        HashSet<String> isVisited = new HashSet<>();
        isVisited.add(beginWord);
        while (!queue.isEmpty()){
            String cur = queue.poll();
            for (String str : nexts.get(cur)){
                if (!isVisited.contains(str)){
                    distances.put(str, distances.get(cur) + 1);
                    queue.add(str);
                    isVisited.add(str);
                }
            }
        }
        return distances;
    }

    //最牛逼的来了，递归找到最小长度
    private static void getShortestPath(String cur, String end, HashMap<String, List<String>> nexts,
                                        HashMap<String, Integer> distances, LinkedList<String> solution,
                                        List<List<String>> res){
        solution.add(cur);
        if (cur.equals(end)){
            res.add(new LinkedList<>(solution));
        }else {
            for (String next:nexts.get(cur)) {
                if (distances.get(next) == distances.get(cur) + 1){
                    getShortestPath(next, end, nexts, distances, solution, res);
                }
            }
        }
        //这一步很重要，当一条路径找到时，每层归约回来的时候都要把当前路径的最后一个点拿掉，才能重新开始找
        solution.pollLast();
    }

    public static void main(String[] args) {
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = new ArrayList<>();
        wordList.add("hit");
        wordList.add("hot");
        wordList.add("dot");
        wordList.add("dog");
        wordList.add("lot");
        wordList.add("log");
        wordList.add("cog");

        HashMap<String, List<String>> map = getNexts(wordList);
        HashMap<String, Integer> map2 = getDistances(beginWord, map);
//        for (Map.Entry entry : map.entrySet()) {
//            System.out.println(entry.getKey() + " " + entry.getValue().toString());
//        }

        for (Map.Entry entry : map2.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }


    }
}
