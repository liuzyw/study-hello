package com.study.util.sort.offer;

import com.study.util.tree.ListNode;

/**
 * Created on 2020-05-02
 *
 * @author liuzhaoyuan
 */
public class ReverseList2 {

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

        ListNode<Integer> node2 = new ListNode<>(2);
        node1.next = node2;

        ListNode<Integer> node3 = new ListNode<>(3);
        node2.next = node3;

        ListNode<Integer> node4 = new ListNode<>(4);
        node3.next = node4;

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
            p.next = q.next;
            q.next = p;
            p = t;
        }


    }

}
