package  com.qxsun.stu.leetcode.editor.cn;

//给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, 
//ai) 和 (i, 0) 。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。 
//
// 说明：你不能倾斜容器。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：[1,8,6,2,5,4,8,3,7]
//输出：49 
//解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。 
//
// 示例 2： 
//
// 
//输入：height = [1,1]
//输出：1
// 
//
// 示例 3： 
//
// 
//输入：height = [4,3,2,1,4]
//输出：16
// 
//
// 示例 4： 
//
// 
//输入：height = [1,2,1]
//输出：2
// 
//
// 
//
// 提示： 
//
// 
// n = height.length 
// 2 <= n <= 3 * 104 
// 0 <= height[i] <= 3 * 104 
// 
// Related Topics 贪心 数组 双指针 
// 👍 2589 👎 0

public class ContainerWithMostWater{
    public static void main(String[] args) {
        Solution solution = new ContainerWithMostWater().new Solution();
        
    }

//leetcode submit region begin(Prohibit modification and deletion)
/*
方案1：暴力，每一个枚举，n的二次方复杂度，没有意义
方案2：双指针法：需要证明，从两边往中间夹，每次向内移动小边，因为移动大边后的所有结果都不可能比之前的更大了，再移动没有意义。
 */
class Solution {
    public int maxArea(int[] height) {
        int len = height.length;
        int left = 0, right = len - 1;
        int maxVal =  0;
        while(left < right) {
            maxVal =  Math.max(Math.min(height[left], height[right]) * (right - left), maxVal);
            if(height[left] <= height[right]){
                left++;
            } else {
                right--;
            }

        }

        return maxVal;
    }
}
//leetcode submit region end(Prohibit modification and deletion)




}