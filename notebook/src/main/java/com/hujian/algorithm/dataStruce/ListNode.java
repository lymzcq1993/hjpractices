package com.hujian.algorithm.dataStruce;

/**
 * @author hujian
 * @since 2022-04-18 13:13
 */
public class ListNode {
    public int val;
    public  ListNode next;
    public ListNode(){};

    public  ListNode(int x) {
        val = x;
        next = null;
    }

    public ListNode(int val,ListNode next)
    {
        this.val = val;
        this.next = next;
    }
}
