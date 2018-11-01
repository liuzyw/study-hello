package com.study.util.sort.offer;

import com.study.util.tree.ListNode;

/**
 * Created on 2018-11-01
 *
 * @author liuzhaoyuan
 */
public class SameListNode {

    public static void main(String[] args) {
        ListNode<Integer> node1 = new ListNode<>(1);
        ListNode<Integer> node2 = new ListNode<>(2);
        node1.next = node2;
        ListNode<Integer> node3 = new ListNode<>(3);
        node2.next = node3;

        ListNode<Integer> node4 = new ListNode<>(4);
        ListNode<Integer> node5 = new ListNode<>(5);
        node4.next = node5;

        ListNode<Integer> node6 = new ListNode<>(6);
        node3.next = node6;
        node5.next = node6;
        ListNode<Integer> node7 = new ListNode<>(7);
        node6.next = node7;

        findSanmeNode(node1, node4);

    }


    private static ListNode<Integer> findSanmeNode(ListNode<Integer> listNode1, ListNode<Integer> listNode2) {
        int len1 = 0;
        ListNode<Integer> p1 = listNode1;
        ListNode<Integer> p2 = listNode2;
        while (p1 != null) {
            len1++;
            p1 = p1.next;


        }
        int len2 = 0;
        while (p2 != null) {
            len2++;
            p2 = p2.next;

        }

        p1 = listNode1;
        p2 = listNode2;

        if (len2 >= len1) {
            for (int i = 0; i < len2 - len1; i++) {
                p2 = p2.next;
            }
        } else if (len2 < len1) {
            for (int i = 0; i < len1 - len2; i++) {
                p1 = p1.next;
            }
        }

        while (p1 != null && p2 != null) {
            if (p1 == p2) {
                System.out.println(p1.data);
                return p1;
            }
            p1 = p1.next;
            p2 = p2.next;
        }
        return null;

    }

}
