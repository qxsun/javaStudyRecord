package  com.qxsun.stu.leetcode.editor.cn;

//给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。 
//
// 进阶：你能尝试使用一趟扫描实现吗？ 
//
// 
//
// 示例 1： 
//
// 
//输入：head = [1,2,3,4,5], n = 2
//输出：[1,2,3,5]
// 
//
// 示例 2： 
//
// 
//输入：head = [1], n = 1
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：head = [1,2], n = 1
//输出：[1]
// 
//
// 
//
// 提示： 
//
// 
// 链表中结点的数目为 sz 
// 1 <= sz <= 30 
// 0 <= Node.val <= 100 
// 1 <= n <= sz 
// 
// Related Topics 链表 双指针 
// 👍 1450 👎 0

public class RemoveNthNodeFromEndOfList{
    public static void main(String[] args) {
        Solution solution = new RemoveNthNodeFromEndOfList().new Solution();
        
    }

//leetcode submit region begin(Prohibit modification and deletion)

    //Definition for singly-linked list.
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    /**
     * 思路：似曾相识，这题的难度在哪里呢？应该是只遍历一趟的要求吧
     * 构建一个步长相差是N的指针
     */
    class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        //边界条件，长度为1，而且删除是1
        if(head.next == null && n == 1){
            head = null;
            return  head;
        }
        //定义两个指针
        ListNode leftNode =  head;
        ListNode rightMode = head;
        //右指针偏移,考虑一种边界情况，就是长度跟偏移量一致
        for(int i = 0; i < n && rightMode != null ; i++){
            rightMode =  rightMode.next;
        }
        if(rightMode == null) {
            head = head.next;
            return  head;
        }
        //
        while(rightMode.next != null){
            leftNode = leftNode.next;
            rightMode =  rightMode.next;
        }
        if(1 == n){
            leftNode.next =  null;
        } else {
            leftNode.next =  leftNode.next.next;
        }

        return head;


    }
}
//leetcode submit region end(Prohibit modification and deletion)




}