package com.study.util.sort.offer;

import com.study.util.tree.ListNode;

/**
 * Created on 2018-10-27
 *
 * @author liuzhaoyuan
 */
public class FindKtoTail {


    public static void main(String[] args) {
        ListNode<Integer> head = new ListNode<>();

        ListNode<Integer> a1 = new ListNode<>(1);
        head.next = a1;

        ListNode<Integer> a2 = new ListNode<>(2);

        a1.next = a2;

        ListNode<Integer> a3 = new ListNode<>(3);

        a2.next = a3;

        ListNode<Integer> a4 = new ListNode<>(4);

        a3.next = a4;

        ListNode<Integer> a5 = new ListNode<>(5);

        a4.next = a5;
        ListNode<Integer> a6 = new ListNode<>(6);

        a5.next = a6;
        ListNode<Integer> a7 = new ListNode<>(7);

        a6.next = a7;

        ListNode<Integer> a8 = new ListNode<>(8);

        a7.next = a8;

        System.out.println(findK(head, 3).data);

    }


    private static <T extends Comparable<? super T>> ListNode findK(ListNode<T> head, int k) {

        ListNode<T> fast = head;

        ListNode<T> low = head;

        for (int i = 0; i < k && fast != null; i++) {
            fast = fast.next;
        }

        while (fast.next != null) {
            fast = fast.next;
            low = low.next;
        }
        return low;

    }


}
