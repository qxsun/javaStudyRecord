package  com.qxsun.stu.leetcode.editor.cn;

//给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。 
//
// 如果数组中不存在目标值 target，返回 [-1, -1]。 
//
// 进阶： 
//
// 
// 你可以设计并实现时间复杂度为 O(log n) 的算法解决此问题吗？ 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [5,7,7,8,8,10], target = 8
//输出：[3,4] 
//
// 示例 2： 
//
// 
//输入：nums = [5,7,7,8,8,10], target = 6
//输出：[-1,-1] 
//
// 示例 3： 
//
// 
//输入：nums = [], target = 0
//输出：[-1,-1] 
//
// 
//
// 提示： 
//
// 
// 0 <= nums.length <= 105 
// -109 <= nums[i] <= 109 
// nums 是一个非递减数组 
// -109 <= target <= 109 
// 
// Related Topics 数组 二分查找 
// 👍 1110 👎 0

public class FindFirstAndLastPositionOfElementInSortedArray{
    public static void main(String[] args) {
        Solution solution = new FindFirstAndLastPositionOfElementInSortedArray().new Solution();
        int[] nums =  new int[]{5,7,7,8,8,10};
        solution.searchRange(nums, 8);

        
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    /**
     * 二分查找，就是找到结果后需要左右遍历找到左右边界
     * @param nums
     * @param target
     * @return
     */
    public  int[] searchRange(int[] nums, int target) {
        int[] ans = new int[]{-1, -1};
        int len = nums.length;
        //边界0
        if(0 == len){
            return ans;
        }
        //边界1
        if(1 == len){
            if(target == nums[0]){
                ans[0] =  0;
                ans[1] =  0;
                return ans;
            } else {
                return  ans;
            }
        }
        //正常逻辑
        int left = 0;
        int right =  len - 1;

        while(left <= right){
            int mid = (left + right)/2;
            //如果找到目标值
            if(target ==  nums[mid]){
                int leftAns = mid;
                int rightAns = mid;
                while(leftAns > 0 && nums[leftAns-1] == nums[leftAns]){
                    leftAns--;
                }
                while(rightAns < (len - 1) && nums[rightAns+1] == nums[rightAns]){
                    rightAns++;
                }
                ans[0] = leftAns;
                ans[1] = rightAns;
                return ans;
            }
            //没找到继续找
            if(target < nums[mid]){
                right =  mid - 1;
            }else{
                left = mid + 1;
            }
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)




}