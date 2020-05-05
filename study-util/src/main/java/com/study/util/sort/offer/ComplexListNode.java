package com.study.util.sort.offer;

import com.study.util.tree.ListNode;

/**
 * Created on 2020-05-02
 * <p>
 * 复杂链表的复制
 *
 * @author liuzhaoyuan
 */
public class ComplexListNode {

    private static final Long serialVersionUID = 1L;


    public static void main(String[] args) throws Exception {

        ListNode<Integer> node = genList();
        node.visitList();

        copyNext(node);

        node.visitList();

        copySibling(node);

        node.visitList();

        ListNode<Integer> node1 = newListNode(node);

        node1.visitList();

    }


    private static ListNode<Integer> genList() {
        ListNode<Integer> head = new ListNode<>();

        ListNode<Integer> node1 = new ListNode<>(1);
        head.next = node1;

        ListNode<Integer> node2 = new ListNode<>(2);
        node1.next = node2;

        ListNode<Integer> node3 = new ListNode<>(3);
        node2.next = node3;
        node1.sibling = node3;

        ListNode<Integer> node4 = new ListNode<>(4);
        node3.next = node4;
        node2.sibling = node4;

        return head;
    }


    private static void copyNext(ListNode<Integer> head) {

        if (head == null) {
            return;
        }

        ListNode<Integer> q = head.next;
        ListNode<Integer> p;

        while (q != null) {
            p = q.next;

            ListNode<Integer> node = new ListNode<>(q.data);

            q.next = node;

            node.next = p;

            q = p;

        }
    }

    private static void copySibling(ListNode<Integer> head) {

        if (head == null) {
            return;
        }

        ListNode<Integer> q = head.next;
        ListNode<Integer> p;

        while (q != null) {

            if (q.sibling != null) {
                q.next.sibling = q.sibling.next;
            }

            q = q.next.next;

        }


    }

    private static ListNode<Integer> newListNode(ListNode<Integer> head) {

        ListNode<Integer> root = new ListNode<>();
        ListNode<Integer> se = root;

        if (head == null) {
            return null;
        }

        ListNode<Integer> q = head.next;
        ListNode<Integer> p;

        while (q != null) {

            se.next = q.next;
            se = q.next;

            q.next = q.next.next;

            q = q.next;

        }

        return root;
    }


}
