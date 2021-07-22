package  com.qxsun.stu.leetcode.editor.cn;

//给定一个无重复元素的正整数数组 candidates 和一个正整数 target ，找出 candidates 中所有可以使数字和为目标数 target 的
//唯一组合。 
//
// candidates 中的数字可以无限制重复被选取。如果至少一个所选数字数量不同，则两种组合是唯一的。 
//
// 对于给定的输入，保证和为 target 的唯一组合数少于 150 个。 
//
// 
//
// 示例 1： 
//
// 
//输入: candidates = [2,3,6,7], target = 7
//输出: [[7],[2,2,3]]
// 
//
// 示例 2： 
//
// 
//输入: candidates = [2,3,5], target = 8
//输出: [[2,2,2,2],[2,3,3],[3,5]] 
//
// 示例 3： 
//
// 
//输入: candidates = [2], target = 1
//输出: []
// 
//
// 示例 4： 
//
// 
//输入: candidates = [1], target = 1
//输出: [[1]]
// 
//
// 示例 5： 
//
// 
//输入: candidates = [1], target = 2
//输出: [[1,1]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= candidates.length <= 30 
// 1 <= candidates[i] <= 200 
// candidate 中的每个元素都是独一无二的。 
// 1 <= target <= 500 
// 
// Related Topics 数组 回溯 
// 👍 1439 👎 0

import java.util.ArrayList;
import java.util.List;

public class CombinationSum{
    public static void main(String[] args) {
        Solution solution = new CombinationSum().new Solution();
        int[] nums =  new int[]{2,3,6,7};
        solution.combinationSum(nums, 7);
        
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    /**
     * 思路：深度搜索，遇到和比当前大的情况则回溯
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        int len =  candidates.length;
        //边界0
        if(len == 0){
            return ans;
        }
        //边界1
        if(len == 1 && candidates[0] > target){
            return ans;
        }
        ArrayList<Integer> curAns= new ArrayList<Integer>();
        //正常情况，用回溯方法
        backTrace(ans, 0, curAns, candidates, target, 0);
        return ans;

    }

    /**
     * 回溯方法：如果当前
     * @param ans 总结果
     * @param curSum 当前和的值
     * @param curAns 当前结果列表
     * @param candidates 数组
     */
    public void backTrace(List<List<Integer>> ans, int curSum, ArrayList<Integer> curAns, int[] candidates, int target, int index){
        //判断是否满足条件
        if(curSum == target){
            ans.add(new ArrayList<>(curAns));
        }
        //具备继续向下搜索的条件
        if(curSum < target) {
            for(int i = index; i <= candidates.length-1; i++){
                curAns.add(candidates[i]);
                backTrace(ans, curSum+candidates[i], curAns, candidates, target, i);
                curAns.remove(curAns.size()-1);
            }
        }
        //其他情况不处理，效果是直接不往下搜索

    }


}
//leetcode submit region end(Prohibit modification and deletion)




}