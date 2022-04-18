package com.hujian.algorithm;

import com.hujian.algorithm.dataStruce.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * 141. 环形链表
 * https://leetcode-cn.com/problems/linked-list-cycle/
 * @author hujian
 * @since 2022-04-18 13:14
 */
public class hasCycle141 {
    /**
     * 空间复杂度为O(1)，使用龟兔赛跑快慢指针
     */
    public boolean hasCycle(ListNode head) {
        if(head == null){
            return false;
        }
        ListNode fast = head.next;
        ListNode slow = head;
        while(fast != slow){
            //fast先行，fast会先遇到null所以不需要判断slow
            if(fast == null || fast.next == null){
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }
    /**
     *使用dp动态规划存储节点，节点出现次数大于0时则是环形链表
     */
     public boolean hasCycle2(ListNode head) {
         Set<ListNode> set = new HashSet<>();
         while(head != null){
             if(!set.add(head)){
                 return true;
             }
             head = head.next;
         }
         return false;
     }
}
