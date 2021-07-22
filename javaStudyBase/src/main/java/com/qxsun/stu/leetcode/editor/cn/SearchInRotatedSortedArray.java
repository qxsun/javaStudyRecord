package  com.qxsun.stu.leetcode.editor.cn;

//整数数组 nums 按升序排列，数组中的值 互不相同 。 
//
// 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，使数组变为 [nums[k], nums[
//k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,2
//,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。 
//
// 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [4,5,6,7,0,1,2], target = 0
//输出：4
// 
//
// 示例 2： 
//
// 
//输入：nums = [4,5,6,7,0,1,2], target = 3
//输出：-1 
//
// 示例 3： 
//
// 
//输入：nums = [1], target = 0
//输出：-1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 5000 
// -10^4 <= nums[i] <= 10^4 
// nums 中的每个值都 独一无二 
// 题目数据保证 nums 在预先未知的某个下标上进行了旋转 
// -10^4 <= target <= 10^4 
// 
//
// 
//
// 进阶：你可以设计一个时间复杂度为 O(log n) 的解决方案吗？ 
// Related Topics 数组 二分查找 
// 👍 1455 👎 0

public class SearchInRotatedSortedArray{
    public static void main(String[] args) {
        Solution solution = new SearchInRotatedSortedArray().new Solution();
        
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    /**
     * 思路：中间劈开，总有一个是有序的，根据中间值以及边界值，可以判断目标字啊哪个区域，变种二分查找
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        int len = nums.length;
        //边界：长度是0,返回-1
        if(0 == len){
            return -1;
        }
        //边界1：直接判断是否相等
        if(1 == len){
            return nums[0] == target ? 0 : -1;
        }
        //正常情况
       int  left = 0, right = len -1;
        while(left <= right){
            int mid = (left + right)/2;
            if(target == nums[mid]){
                return mid;
            }

            //需要继续搜索,根据中间点与开头的比较，可以知道顺序序列是哪一段,一下表示前半段是顺序的
            if(nums[left] <= nums[mid]){
                //表示取值在左边的情况
                if(target < nums[mid] && target >= nums[left]){
                    right =  mid -1;
                } else {
                    //在右边搜
                    left = mid + 1;
                }

            } else {
                //进到这里说明右边是顺序的
                if(target > nums[mid] && target <= nums[right]){
                    left =  mid + 1;
                } else {
                    //在左边搜
                    right = mid - 1;
                }
            }

        }
        //表示没搜索到
        return -1;

    }
}
//leetcode submit region end(Prohibit modification and deletion)




}