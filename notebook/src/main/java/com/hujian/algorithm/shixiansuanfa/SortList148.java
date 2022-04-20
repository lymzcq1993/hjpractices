package com.hujian.algorithm.shixiansuanfa;

import com.hujian.algorithm.dataStruce.ListNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 148. 排序链表
 * https://leetcode-cn.com/problems/sort-list/
 * @author hujian
 */
public class SortList148 {
    /**
     * 这里使用了取巧的办法，用一个排序的容器来存取链表的节点
     * 在将排好序的节点进行链接
     * @param head
     * @return
     */
    public ListNode sortList(ListNode head) {
        List<ListNode> treeSet = new ArrayList<>();
        while(head != null){
            treeSet.add(head);
            head = head.next;
        }
        Collections.sort(treeSet,(o1, o2)-> {
            return o1.val - o2.val;
        });
        ListNode node=null;
        for(int i=treeSet.size()-1;i>=0;i--){
            ListNode treeNode = treeSet.get(i);
            treeNode.next = node;
            node = treeNode;
        }
        return node;
    }
}
