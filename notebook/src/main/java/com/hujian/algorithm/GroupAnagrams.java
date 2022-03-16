package com.hujian.algorithm;

import java.util.*;

/**
 * @author hujian
 * 49. 字母异位词分组
 * https://leetcode-cn.com/problems/group-anagrams/
 *
 * 给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
 *
 * 字母异位词 是由重新排列源单词的字母得到的一个新单词，所有源单词中的字母通常恰好只用一次。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
 * 输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
 * 示例 2:
 *
 * 输入: strs = [""]
 * 输出: [[""]]
 * 示例 3:
 *
 * 输入: strs = ["a"]
 * 输出: [["a"]]
 *  
 *
 * 提示：
 *
 * 1 <= strs.length <= 104
 * 0 <= strs[i].length <= 100
 * strs[i] 仅包含小写字母
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/group-anagrams
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class GroupAnagrams {
    public static void main(String[] args) {
        System.out.println(groupAnagrams(new String[]{"eat","teaa","tan","ate","nat","bat","atae"}));
    }

    /**
     * 使用map储存排序后的key，一开始就想到了这个算法。
     * 但是又考虑到可能排序比较耗时就没有使用这个算法。。
     * @param strs
     * @return
     */
    public static List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> list= new ArrayList<>();
        Map<String,Integer> map = new HashMap<>();
        for(String str:strs){
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key =String.valueOf(chars);
            if(map.containsKey(key)){
                list.get(map.get(key)).add(str);
            }
            else{
                List<String> ll = new ArrayList<>();
                ll.add(str);
                list.add(ll);
                map.put(key,list.size()-1);
            }
        }
        return list;
    }

    /**
     * 用例都通过了但是超时。。
     * 这里是遍历了map的每个元素进行比较所有元素是否一致
     * @param strs
     * @return
     */
    public static List<List<String>> groupAnagrams2(String[] strs) {
        List<HashMap<Character,Integer>> rule = new ArrayList<>();
        List<List<String>> list= new ArrayList<>();
        for (String str : strs) {
            boolean flag = true;
            for (int j = 0; j < list.size(); j++) {
                if (list.get(j).get(0).length() != str.length()) {
                    continue;
                }
                HashMap<Character, Integer> r = new HashMap<>(rule.get(j));
                for (int l = 0; l < str.length(); l++) {
                    char c = str.charAt(l);
                    int result = 0;
                    if (r.get(c) == null ||
                            (result = r.get(c) - 1) < 0) {
                        break;
                    }
                    if (result == 0){
                        r.remove(c);
                    }
                    else{
                        r.put(c, result);
                    }
                }
                if (r.isEmpty()) {

                    flag = false;
                    list.get(j).add(str);
                    break;
                }

            }
            if (flag) {
                List<String> l = new ArrayList<>();
                l.add(str);
                list.add(l);
                //增加匹配的规则
                HashMap<Character, Integer> map = new HashMap<>();
                for (int k = 0; k < str.length(); k++) {
                    char c = str.charAt(k);
                    if (map.get(c) == null) {
                        map.put(c, 1);
                    } else {
                        map.put(c, map.get(c) + 1);
                    }
                }
                rule.add(map);
            }

        }
        return list;
    }
}
