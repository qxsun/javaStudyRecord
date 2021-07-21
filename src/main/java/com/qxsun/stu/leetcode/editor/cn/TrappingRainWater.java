package  com.qxsun.stu.leetcode.editor.cn;

//给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
//输出：6
//解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 
// 
//
// 示例 2： 
//
// 
//输入：height = [4,2,0,3,2,5]
//输出：9
// 
//
// 
//
// 提示： 
//
// 
// n == height.length 
// 0 <= n <= 3 * 104 
// 0 <= height[i] <= 105 
// 
// Related Topics 栈 数组 双指针 动态规划 单调栈 
// 👍 2499 👎 0

public class TrappingRainWater{
    public static void main(String[] args) {
        Solution solution = new TrappingRainWater().new Solution();
        
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int trap(int[] height) {
        int len = height.length;
        if(len <= 2){
            return 0;
        }
        int left = 0, right = len - 1;
        int ans = 0;
        int leftMax = 0;//左边到当前最高的
        int rightMax = 0;//右边到目标最高的
        while(left < right){
            leftMax =  Math.max(leftMax, height[left]);
            rightMax =  Math.max(rightMax, height[right]);
            //这里可以说明leftMax小于rightMax
            if(height[left] < height[right]){
                //这里可以计算出left位置的可以装的水
                ans += (leftMax - height[left]);
                left++;
            } else {
                ans += (rightMax - height[right]);
                right--;
            }
        }
        return ans;

    }
}
//leetcode submit region end(Prohibit modification and deletion)




}