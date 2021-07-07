package  com.qxsun.stu.leetcode.editor.cn;

//给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums1 = [1,3], nums2 = [2]
//输出：2.00000
//解释：合并数组 = [1,2,3] ，中位数 2
// 
//
// 示例 2： 
//
// 
//输入：nums1 = [1,2], nums2 = [3,4]
//输出：2.50000
//解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
// 
//
// 示例 3： 
//
// 
//输入：nums1 = [0,0], nums2 = [0,0]
//输出：0.00000
// 
//
// 示例 4： 
//
// 
//输入：nums1 = [], nums2 = [1]
//输出：1.00000
// 
//
// 示例 5： 
//
// 
//输入：nums1 = [2], nums2 = []
//输出：2.00000
// 
//
// 
//
// 提示： 
//
// 
// nums1.length == m 
// nums2.length == n 
// 0 <= m <= 1000 
// 0 <= n <= 1000 
// 1 <= m + n <= 2000 
// -106 <= nums1[i], nums2[i] <= 106 
// 
//
// 
//
// 进阶：你能设计一个时间复杂度为 O(log (m+n)) 的算法解决此问题吗？ 
// Related Topics 数组 二分查找 分治 
// 👍 4232 👎 0

public class MedianOfTwoSortedArrays{
    public static void main(String[] args) {
        Solution solution = new MedianOfTwoSortedArrays().new Solution();
        /*int[] a = new int[]{0,0};
        int[] b = new int[]{0,0};
        System.out.println(solution.findMedianSortedArrays(a, b));*/
    }

//leetcode submit region begin(Prohibit modification and deletion)
/*
分奇数偶数情况，如果奇数就是长度中间值中间的一个，偶数就找中间两个的中间值
遍历，主要注意边界条件，时间复杂度O(m+n)
 */
class Solution1 {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int len = m+n; //表示两个数组的总长度
        int before = -1;//表示当前值的前一个值；
        int cur = -1;//表示当前值
        int aIndex =0, bIndex = 0;//两个数组的前景后缀
        //循环遍历数组，最多只用遍历到len/2+1
        for(int i=0; i < len/2+1; i++ ) {
            //首先将前一个循环的结果赋值给before
            before = cur;
            //当aIndex小于m的情况，并且1的值小于2的值或者2已经越界了
            //bIndex >= n 这个必须得先判断，不然后面会抛边界超出异常
            if(aIndex < m && ( bIndex >= n || (nums1[aIndex] < nums2[bIndex ]))) {
                cur = nums1[aIndex++];
            } else {
                cur = nums2[bIndex++];
            }
        }
        //循环后，要使用before，cur
        //当结果是偶数时
        if((len&1) == 0){
            return (before + cur)/2.0;
        } else {
            return cur;
        }

    }
}
/*
O(log(m+n))的时间复杂度，需要分治法
主要思路：
 */
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        return 0;
    }
}
//leetcode submit region end(Prohibit modification and deletion)




}