package com.hujian.algorithm.shixiansuanfa;

import java.util.Stack;

/**
 * 155. 最小栈
 * https://leetcode-cn.com/problems/min-stack/
 * @author hujian
 */
public class MinStatck155 {
    //每次存两个数，一个自身数，一个最小的值
    Stack<Integer> stack;
    public MinStatck155() {
        stack = new Stack<>();
    }

    public void push(int val) {
        if(stack.isEmpty()){
            stack.push(val);
            stack.push(val);
            return;
        }
        int mVal = stack.peek();
        stack.push(val);
        stack.push(Math.min(mVal,val));
    }

    public void pop() {
        stack.pop();
        stack.pop();
    }

    public int top() {
        return stack.get(stack.size()-2);
    }

    public int getMin() {
        return stack.peek();
    }
    //比较简单无脑，直接使用ArrayList和sort来进行排序
    // ArrayList<Integer> arrayList = new ArrayList<>();
    // Stack<Integer> stack = new Stack<>();
    // public MinStack() {

    // }

    // public void push(int val) {
    //     stack.push(val);
    //     arrayList.add(val);
    //     arrayList.sort(Comparator.comparingInt(a -> a));
    // }

    // public void pop() {
    //     Integer val=0;
    //     if((val = stack.pop()) == null){
    //         return;
    //     }
    //     arrayList.remove(arrayList.indexOf(val));
    // }

    // public int top() {
    //     return stack.peek();
    // }

    // public int getMin() {
    //     return arrayList.get(0);
    // }
}
