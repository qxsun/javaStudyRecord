package  com.qxsun.stu.leetcode.editor.cn;

//给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。 
//
// 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。 
//
// 
//
// 
//
// 示例 1： 
//
// 
//输入：digits = "23"
//输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
// 
//
// 示例 2： 
//
// 
//输入：digits = ""
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：digits = "2"
//输出：["a","b","c"]
// 
//
// 
//
// 提示： 
//
// 
// 0 <= digits.length <= 4 
// digits[i] 是范围 ['2', '9'] 的一个数字。 
// 
// Related Topics 哈希表 字符串 回溯 
// 👍 1391 👎 0

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LetterCombinationsOfAPhoneNumber{
    public static void main(String[] args) {
        Solution solution = new LetterCombinationsOfAPhoneNumber().new Solution();
        
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    /**
     * 思路：用一个hash表存数字和字母的关系，本题其实就是一个深度搜索，搜到底之后回溯，列举所有额情况
     *
     */
    public List<String> letterCombinations(String digits) {
        //定义结果集
        List<String> ansList =  new ArrayList<>();
        //边界情况,放前面可以少些没意义的执行
        if(digits.length() == 0){
            return  ansList;
        }
       //初始化hash表
        Map<Character, String> numTable =  new HashMap<>();
        numTable.put('2', "abc");
        numTable.put('3', "def");
        numTable.put('4', "ghi");
        numTable.put('5', "jkl");
        numTable.put('6', "mno");
        numTable.put('7', "pqrs");
        numTable.put('8', "tuv");
        numTable.put('9', "wxyz");
        //正常情况
        backTrack(ansList, numTable, digits, 0, new StringBuffer());
        return ansList;
    }

    /**
     * 回溯算法实现，深度搜索
     * @param ansList 结果集合
     * @param numTable hash表
     * @param digits 原始数据
     * @param deep 深度
     * @param curAns 当前遍历结果
     */
    public void backTrack(List<String> ansList, Map<Character, String> numTable, String digits, int deep, StringBuffer curAns){
        //定义返回条件,搜索到足够深度了
        if(digits.length() == deep) {
            ansList.add(curAns.toString());
        } else {
            //遍历第deep个字符对应的字符串
            String curStr =  numTable.get(digits.charAt(deep));
            for(int i = 0; i < curStr.length(); i++){
                curAns.append(curStr.charAt(i));
                backTrack(ansList, numTable, digits, deep + 1, curAns);
                curAns.deleteCharAt(deep);
            }
        }

    }
}
//leetcode submit region end(Prohibit modification and deletion)




}