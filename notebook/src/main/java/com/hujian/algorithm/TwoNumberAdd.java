package com.hujian.algorithm;

import com.hujian.algorithm.dataStruce.ListNode;

/**
 * https://leetcode-cn.com/problems/add-two-numbers
 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储一位数字。

 请你将两个数相加，并以相同形式返回一个表示和的链表。

 你可以假设除了数字 0 之外，这两个数都不会以 0开头。
 输入：l1 = [2,4,3], l2 = [5,6,4]
 输出：[7,0,8]
 解释：342 + 465 = 807.
 ps:  个位  十位  百位   需要进位
 */
public class TwoNumberAdd {
    public static void main(String[] args) {
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode listNode = new ListNode();
        ListNode result =  listNode;
        int temp = 0;
        //此处判断temp != 0是因为如果最后一位进1，则还需要添加一个node
        while (l1 !=null || l2 != null || temp != 0){
            int val1 = l1 == null?0:l1.val;
            int val2 = l2 == null?0:l2.val;
            int sum = val1 + val2 + temp;
            temp = sum / 10;

            ListNode nextNode = new ListNode(sum %10);
            listNode.next = nextNode;
            listNode = nextNode;
            if (l1 != null){
                l1 = l1.next;
            }
            if (l2 != null){
                l2 = l2.next;
            }
        }
        //旧的报错代码
//        do {
//            ListNode listNode1 = new ListNode();
//            int i = l1.val + l2.val+temp;
//            temp = i /10;
//            listNode.val = i%10;
//            next = l1.next;
//            if (next != null){
//                listNode.next = listNode1;
//                listNode = listNode1;
//                l1 = l1.next;
//                l2 = l2.next;
//            }
//        }while (next !=null);
        return result;
    }

}
