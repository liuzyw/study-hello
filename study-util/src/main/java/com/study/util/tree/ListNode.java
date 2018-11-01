package com.study.util.tree;

/**
 * Created on 2018-10-27
 *
 * @author liuzhaoyuan
 */
public class ListNode<T extends Comparable<? super T>> {

    public T data;

    public ListNode<T> next;

    public ListNode<T> pre;


    public ListNode() {
    }

    public ListNode(T data) {
        this.data = data;
    }


    public ListNode(T data, ListNode<T> next, ListNode<T> pre) {
        this.data = data;
        this.next = next;
        this.pre = pre;
    }


}
