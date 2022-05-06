package com.hujian.algorithm;

import com.hujian.algorithm.dataStruce.ListNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 234. 回文链表
 * https://leetcode-cn.com/problems/palindrome-linked-list/
 * @author hujian
 */
public class IsPalindrome {
    public boolean isPalindrome(ListNode head) {
        //使用快慢指针查询链表的中点
        ListNode mid = findMid(head);
        //反转后半段链表
        ListNode lastNode = reverseList(mid.next);

        ListNode p1 = head;
        ListNode p2 = lastNode;
        while(p2 != null){
            if(p1.val != p2.val){
                return false;
            }
            p1 = p1.next;
            p2 = p2.next;
        }
        //这里如果是实际生产中如果改变了链表结构可能还需要反转回来
        reverseList(lastNode);
        return true;
    }

    ListNode findMid(ListNode head){
        ListNode fast = head;
        ListNode slow = head;
        while(fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    //链表反转
    ListNode reverseList(ListNode head){
        //记录前一个临时节点
        ListNode prev = null;
        //避免影响head引用
        ListNode curr = head;
        while(curr != null){
            //记录下一个节点
            ListNode next = curr.next;
            //赋值前一个节点到next
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }


/***********************使用额外的空间来保存数据，在用双向链表比较 时间复杂度O(n)  空间复杂度O(n) ****************************/
     public boolean isPalindrome2(ListNode head) {
         if(head.next == null){
             return true;
         }
         ListNode node = head;
         Deque<Integer> deque = new ArrayDeque<>();
         while(node != null){
             deque.add(node.val);
             node = node.next;
         }
         while(deque.size() > 1){
             if(deque.pollFirst() != deque.pollLast()){
                 return false;
             }
         }
         return true;
     }
}
