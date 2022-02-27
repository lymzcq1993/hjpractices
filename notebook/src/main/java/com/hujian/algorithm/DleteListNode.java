package com.hujian.algorithm;

import java.util.*;

/**
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 *
 * 输入：head = [1,2,3,4,5], n = 2
 * 输出：[1,2,3,5]
 * 示例 2：
 *
 * 输入：head = [1], n = 1
 * 输出：[]
 * 示例 3：
 *
 * 输入：head = [1,2], n = 1
 * 输出：[1]
 *
 *
 */
public class DleteListNode {

    /**
     * 递归实现
     * @param head
     * @param n
     * @return
     */
    public static ListNode digui(ListNode head, int n) {
        return removeNode(head,n) == 1? null:head;
    }

    private static int removeNode(ListNode node,int n){
        if (node.next == null)return 1;
        int m =removeNode(node.next,n);
        if (m == n){
            //获取当前节点的顺序
            if (m == 1){
                node.next = null;
            }
            else{
                node.next = node.next.next;
            }
        }
        return ++m;
    }



    /**
     * 使用List额外存储了对象，然后用List下标来操作数组
     * @param head
     * @param n
     * @return
     */
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if(head.next == null){
            return null;
        }
        List<ListNode> list = new ArrayList<>();
        ListNode node = head;
        do{
            list.add(node);
            node = node.next;
        }while(node != null);
        int index = list.size()-n;
        //如果删除第一个
        if(index == 0){
            head = head.next;
        }
        else if(index == list.size()-1){
            list.get(index-1).next =null;
        }
        else{
            list.get(index-1).next = list.get(index+1);
        }
        return head;
    }
    
    private static class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
}
