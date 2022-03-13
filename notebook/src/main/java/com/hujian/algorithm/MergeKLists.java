package com.hujian.algorithm;

/**
 * https://leetcode-cn.com/problems/merge-k-sorted-lists/submissions/
 *
 * 给你一个链表数组，每个链表都已经按升序排列。
 *
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：lists = [[1,4,5],[1,3,4],[2,6]]
 * 输出：[1,1,2,3,4,4,5,6]
 * 解释：链表数组如下：
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * 将它们合并到一个有序链表中得到。
 * 1->1->2->3->4->4->5->6
 * 示例 2：
 *
 * 输入：lists = []
 * 输出：[]
 * 示例 3：
 *
 * 输入：lists = [[]]
 * 输出：[]
 */
public class MergeKLists {
    public static void main(String[] args) {
        ListNode listNo1 = new ListNode(1);
        ListNode listNode2 = new ListNode(4);
        ListNode listNode3 = new ListNode(5);
        listNo1.next = listNode2;
        listNode2.next = listNode3;

        ListNode listNo4 = new ListNode(1);
        ListNode listNode5 = new ListNode(3);
        ListNode listNode6 = new ListNode(4);
        listNo4.next = listNode5;
        listNode5.next = listNode6;

        ListNode listNo7 = new ListNode(2);
        ListNode listNode8 = new ListNode(6);
        listNo7.next = listNode8;
        ListNode listNode = mergeKLists(new ListNode[]{listNo1, listNo4, listNo7});
        System.out.println(listNode);
    }

    //直接递归怼出来了。。每次循环都都要比较一遍数组每个链表的第一个数
    //next直接指向每次返回的第一个数
    public static ListNode mergeKLists(ListNode[] lists) {
        ListNode myNode = new ListNode();
        ListNode curNode = myNode;
        while(true){
            int c = compare(-1, 0, lists);
            if (c == -1)break;
                curNode.next = lists[c];
                lists[c]  = lists[c].next;
                curNode = curNode.next;

        }
        return myNode.next;
    }

    public static int compare(int c,int cur,ListNode[] lists){
        if (cur == lists.length)return c;
        ListNode curNode = lists[cur];
        if (curNode == null){
            return compare(c,cur+1,lists);
        }
        if (c == -1){
            return compare(cur,cur+1,lists);
        }
        return compare(curNode.val < lists[c].val?cur:c,cur+1,lists);
    }


    private static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) {
            this.val = val;
        }
        ListNode(int val, ListNode next)
        {
            this.val = val;
            this.next = next;
        }
    }

}
