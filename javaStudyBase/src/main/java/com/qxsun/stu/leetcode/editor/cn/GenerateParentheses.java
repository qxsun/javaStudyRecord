package  com.qxsun.stu.leetcode.editor.cn;

//数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 3
//输出：["((()))","(()())","(())()","()(())","()()()"]
// 
//
// 示例 2： 
//
// 
//输入：n = 1
//输出：["()"]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 8 
// 
// Related Topics 字符串 动态规划 回溯 
// 👍 1877 👎 0

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GenerateParentheses{
    public static void main(String[] args) {
        Solution solution = new GenerateParentheses().new Solution();

        solution.generateParenthesis(2);
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<String> generateParenthesis(int n) {
        //定义结果集
        List<String> ansList =  new ArrayList<>();
        //边界情况,放前面可以少些没意义的执行
        if(n == 0){
            return  ansList;
        }
        //正常情况
        backTrack(ansList, n, 0, new StringBuffer() , 0);
        return ansList;
    }

    /**
     * 回溯算法执行过程,这里需要剪枝，也就是当一些条件出现的实现就可以判断为返回
     * @param ansList 结果集
     * @param n 长度
     * @param deep 当前深度
     * @param curAns 当前结果
     * @param dp 表示当前的dp值
     */
    public void backTrack(List<String> ansList, int n, int deep, StringBuffer curAns, int dp){
        //到达遍历条件的情况
        if(deep == 2*n ){
            if(dp == 0) {
                ansList.add(curAns.toString());
            }
        } else {
            //继续深度遍历，遍历情况分两种，左括号和右括号
            for (int i = 0; i < 2; i++) {
                if(i == 0){
                    dp++;
                } else {
                    dp--;
                }
                //需要及时熔断
                if (dp < 0 || dp > n) {
                    //清理变化
                    if(i == 0){
                        dp--;
                    } else {
                        dp++;
                    }
                    continue;
                }
                if(i == 0){
                    curAns.append('(');
                } else {
                    curAns.append(')');
                }
                backTrack(ansList, n, deep+1 , curAns , dp);
                curAns.deleteCharAt(deep);
                if(i == 0){
                    dp--;
                } else {
                    dp++;
                }
            }
        }


    }
}
//leetcode submit region end(Prohibit modification and deletion)




}