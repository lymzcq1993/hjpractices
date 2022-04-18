package com.hujian.algorithm;

import com.hujian.algorithm.dataStruce.ListNode;

/**
 * @author hujian
 * @description
 * @create 2022-03-01 17:07
 *
 * 合并两个有序队列
 * https://leetcode-cn.com/problems/merge-two-sorted-lists/
 *
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 * 输入：l1 = [1,2,4], l2 = [1,3,4]
 * 输出：[1,1,2,3,4,4]
 * 示例 2：
 *
 * 输入：l1 = [], l2 = []
 * 输出：[]
 * 示例 3：
 *
 * 输入：l1 = [], l2 = [0]
 * 输出：[0]
 *  
 *
 * 提示：
 *
 * 两个链表的节点数目范围是 [0, 50]
 * -100 <= Node.val <= 100
 * l1 和 l2 均按 非递减顺序 排列
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-two-sorted-lists
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MergeTwoOrderedQueues {
    public static void main(String[] args) {
        ListNode l3 = new ListNode(3,null);
        ListNode l2 = new ListNode(2,l3);
        ListNode list1 = new ListNode(1,l2);


        ListNode ll3 = new ListNode(6,null);
        ListNode ll2 = new ListNode(4,ll3);
        ListNode list2 = new ListNode(1,ll2);

        System.out.println(mergeTwoLists(list1,list2));
    }

    /**
     * 使用的合并链表，而不是用一个新的链表去存储，主要难点是怎么降低空间复杂度。
     * @param list1
     * @param list2
     * @return
     */
    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode myNode =new ListNode();
        //这里刚开始陷入了一个误区，一直把curNode指向了myNode.next属性
        //导致返回的一直是空的node
        ListNode curNode =myNode;
        while(list1!=null && list2!=null){
            if(list1.val <= list2.val){
                curNode.next=list1;
                list1=list1.next;
            }
            else{
                curNode.next =list2;
                list2=list2.next;
            }
            curNode=curNode.next;
        }
        if(list1 ==null){
            curNode=list2;
        }
        else{
            curNode =list1;
        }
        return myNode.next;    }

}
