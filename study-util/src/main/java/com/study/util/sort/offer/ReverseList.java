package com.study.util.sort.offer;

import com.study.util.tree.ListNode;

/**
 * Created on 2020-05-02
 *
 * @author liuzhaoyuan
 */
public class ReverseList {

    private static final Long serialVersionUID = 1L;


    public static void main(String[] args) throws Exception {

        ListNode<Integer> node = genList();
        node.visitList();

        reverse(node);

        node.visitList();

    }


    private static ListNode<Integer> genList() {
        ListNode<Integer> head = new ListNode<>();

        ListNode<Integer> node1 = new ListNode<>(1);
        head.next = node1;
        node1.pre = head;

        ListNode<Integer> node2 = new ListNode<>(2);
        node1.next = node2;
        node2.pre = node1;

        ListNode<Integer> node3 = new ListNode<>(3);
        node2.next = node3;
        node3.pre = node2;

        ListNode<Integer> node4 = new ListNode<>(4);
        node3.next = node4;
        node4.pre = node3;

        return head;
    }


    private static void reverse(ListNode<Integer> head) {

        if (head == null) {
            return;
        }

        ListNode<Integer> q = head;
        ListNode<Integer> p = head.next;
        ListNode<Integer> t;
        q.next = null;

        while (p != null) {

            t = p.next;

            if (q.next == null) {
                p.next = null;
                p.pre = q;
                q.next = p;
            } else {
                q.next.pre = q;
                p.next = q.next;
                p.pre = q;
                q.next = p;
            }
            p = t;
        }
    }


}
