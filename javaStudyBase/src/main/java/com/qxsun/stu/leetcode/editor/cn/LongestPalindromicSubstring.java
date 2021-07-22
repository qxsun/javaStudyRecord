package  com.qxsun.stu.leetcode.editor.cn;

//给你一个字符串 s，找到 s 中最长的回文子串。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "babad"
//输出："bab"
//解释："aba" 同样是符合题意的答案。
// 
//
// 示例 2： 
//
// 
//输入：s = "cbbd"
//输出："bb"
// 
//
// 示例 3： 
//
// 
//输入：s = "a"
//输出："a"
// 
//
// 示例 4： 
//
// 
//输入：s = "ac"
//输出："a"
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 1000 
// s 仅由数字和英文字母（大写和/或小写）组成 
// 
// Related Topics 字符串 动态规划 
// 👍 3798 👎 0

public class LongestPalindromicSubstring{
    public static void main(String[] args) {
        Solution solution = new LongestPalindromicSubstring().new Solution();
        
    }

//leetcode submit region begin(Prohibit modification and deletion)
/*
最长回文子串解题思路
定义数组p[i][j]来标识ij之间是不是回文
然后定义状态转移方程
 */
class Solution {
    public String longestPalindrome(String s) {
        //虽然限制了长度不会小于1，但是判断一下比较好
        if(s == null || s.length() < 1) {
            return "";
        }
        int len = s.length();
        //定义回文串的左下标和长度,长度最少是1
        int left = 0, curMaxLen = 1;
        //定义状态记录矩阵
        Boolean [][]status =  new Boolean[len][len];
        //初始化p[i][i]的值
        for(int i =0; i< len; i++){
            status[i][i] =  true;
        }
        //将字符串转成字符数组
        char[] sArray = s.toCharArray();
        //列状态转移过程
        //遍历长度
        for(int L=2; L <= len; L++){
            //遍历起始位置，左边界
            for(int i =0; i < len; i++){
                //定义右边界
                int j = i + L -1;
                if(j>=len){
                    //越界了
                    break;
                }
                //比较左右边界值
                if(sArray[i] != sArray[j]){
                    status[i][j] = false;
                } else {
                    //长度是2或者3
                    if(j-i < 3){
                        status[i][j] = true;
                    } else {
                       status[i][j] = status[i+1][j-1];
                    }
                }
                //如果ij是true
                if(status[i][j] && j-i+1 >curMaxLen) {
                    curMaxLen = j-i+1;
                    left = i;
                }

            }
        }
        return s.substring(left,left+curMaxLen);
    }
}
//leetcode submit region end(Prohibit modification and deletion)




}