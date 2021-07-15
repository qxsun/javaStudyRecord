package  com.qxsun.stu.leetcode.editor.cn;

//实现获取 下一个排列 的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。 
//
// 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。 
//
// 必须 原地 修改，只允许使用额外常数空间。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,3]
//输出：[1,3,2]
// 
//
// 示例 2： 
//
// 
//输入：nums = [3,2,1]
//输出：[1,2,3]
// 
//
// 示例 3： 
//
// 
//输入：nums = [1,1,5]
//输出：[1,5,1]
// 
//
// 示例 4： 
//
// 
//输入：nums = [1]
//输出：[1]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 100 
// 0 <= nums[i] <= 100 
// 
// Related Topics 数组 双指针 
// 👍 1221 👎 0

public class NextPermutation{
    public static void main(String[] args) {
        Solution solution = new NextPermutation().new Solution();
        
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    /**
     * 关键要明白题目意思和方法
     * @param nums
     */
    public void nextPermutation(int[] nums) {
        int len = nums.length;
        //边界：小于1,或者等于1，直接返回
        if(len <= 1){
            return ;
        }
        //正常情况，从右边开始找第一对相邻的升序的邻居
        int index = len - 2 ;//倒数第二个数据的下标开始比较，如果长度是2，则直接匹配到了第一个
        while(index>=0 && nums[index] >= nums[index+1]){
            index--;
        }

        //index可能有两种情况，-1表示找到最前面都没找到第一个升序对子，说明是逆序的，直接翻转；
        //如果index找到了，需要将从右边开始往左找第一个比当前index大的数据，然后换位置，这里可以知道这个就是index右边比它大的最小的数据，因为此时右边是降序的
        if(index >= 0) {
            int j = len - 1;
            while(j>=0 && nums[j] <= nums[index]){
                j--;
            }
            //这里j是肯定可以找到的，最前面就是他后面邻居,替换位置
            swap(nums, index, j);
        }
        reverse(nums, index+1);


    }
    //其实工具函数应该加一些异常判断，参数校验
    public void swap (int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    //
    public void reverse(int[] nums, int i){
        int len = nums.length;
        int j =  len -1;
        while(i < j){
            swap(nums, i, j);
            i++;
            j--;
        }
    }

}
//leetcode submit region end(Prohibit modification and deletion)




}