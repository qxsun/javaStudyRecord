package  com.qxsun.stu.leetcode.editor.cn;

//给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重
//复的三元组。 
//
// 注意：答案中不可以包含重复的三元组。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [-1,0,1,2,-1,-4]
//输出：[[-1,-1,2],[-1,0,1]]
// 
//
// 示例 2： 
//
// 
//输入：nums = []
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：nums = [0]
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// 0 <= nums.length <= 3000 
// -105 <= nums[i] <= 105 
// 
// Related Topics 数组 双指针 排序 
// 👍 3476 👎 0

import java.util.*;

public class ThreeSum{
    public static void main(String[] args) {
        Solution solution = new ThreeSum().new Solution();
        
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    /*
    思路：先排序，一次循环，然后后续的是数据两个数据类似于一个两数之和的问题，但是由于去重，需要做一些处理
    边界1：长度小于3的，全部输出[]
    正常情况：
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        //边界情况，长度小于3的情况
        if(null == nums || nums.length <= 2){
            return ans;
        }
        Arrays.sort(nums);
        int len = nums.length;
        //正常情况
        for(int first = 0; first < len-2; first++){
            if(nums[first] > 0){
                continue;//如果第一个元素都比0大，可以直接跳出来
            }
            if(first>0 && nums[first] == nums[first-1]){
                continue;//重复元素，直接跳过去
            }
            int second = first + 1, third = len -1;
            int target = -nums[first];
            while(second < third){
                if(target == nums[second] + nums[third]){
                    ans.add(new ArrayList<>(Arrays.asList(nums[first], nums[second], nums[third])));
                    //此时还要继续遍历,保证不重复，所以先位移
                    second++;
                    third--;
                    while(second < third && nums[second] == nums[second-1]){
                        second++;
                    }
                    while(second < third && nums[third] == nums[third+1]){
                        third--;
                    }
                } else if (target > nums[second] + nums[third]){
                    second++;//当前遍历结果偏小，需要右移
                } else {
                    third--;
                }
            }

        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)




}