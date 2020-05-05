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

    public ListNode<T> sibling;


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

    public void visitList() {
        if (this != null) {

            ListNode<T> p = this.next;

            while (p != null && p.data != null) {
                System.out.print(p.data + ", ");
                p = p.next;
            }
            System.out.println();

        }
    }


}
