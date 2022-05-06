package com.hujian.algorithm.shixiansuanfa;

import com.hujian.algorithm.dataStruce.ListNode;

import java.util.HashMap;

/**
 * 160. 相交链表
 * https://leetcode-cn.com/problems/intersection-of-two-linked-lists/
 * @author hujian
 */
public class GetIntersectionNode160 {
    /**
     * 通过遍历两个联表用map来存储比对
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        HashMap<ListNode,Integer> map = new HashMap<>();
        while(headA != null){
            map.put(headA,0);
            headA = headA.next;
        }
        while(headB != null){
            if(map.containsKey(headB)){
                return headB;
            }
            headB = headB.next;
        }
        return null;
    }
}
