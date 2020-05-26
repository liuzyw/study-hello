package com.study.util.sort.offer;

import com.study.util.tree.BinaryTree;
import com.study.util.tree.Node;

/**
 * Created on 2018-10-28
 *
 * @author liuzhaoyuan
 */
public class HasSubTree {

    public static void main(String[] args) {
        Node<Integer> root = new Node<>(8);
        Node<Integer> a1 = new Node<>(8);
        root.setLeftChild(a1);
        Node<Integer> a2 = new Node<>(7);
        root.setRightChild(a2);
        Node<Integer> a3 = new Node<>(9);
        a1.setLeftChild(a3);
        Node<Integer> a4 = new Node<>(2);
        a1.setRightChild(a4);
        Node<Integer> a5 = new Node<>(4);
        a4.setLeftChild(a5);
        Node<Integer> a6 = new Node<>(7);
        a4.setRightChild(a6);

        Node<Integer> a42 = new Node<>(2);
        Node<Integer> a43 = new Node<>(4);

        Node<Integer> a44 = new Node<>(7);

        a42.setLeftChild(a43);
        a42.setRightChild(a44);

        System.out.println(hasSubTree(root, a42));


        BinaryTree<Integer> binaryTree = new BinaryTree<>();
        binaryTree.inOrderTraverse(root);

        pre(root);
        System.out.println();
        binaryTree.inOrderTraverse(root);


    }


    private static <T extends Comparable<? super T>> boolean hasSubTree(Node<T> root, Node<T> sub) {

        if (root == null && sub == null) {
            return true;
        }
        boolean re = false;
        if (root != null && sub != null) {
            if (root.getData().equals(sub.getData())) {
                re = doseEquals(root, sub);
            }

            if (!re) {
                re = hasSubTree(root.getLeftChild(), sub);
                if (!re) {
                    re = hasSubTree(root.getRightChild(), sub);
                }
            }
            return re;
        }
        return false;
    }

    private static <T extends Comparable<? super T>> boolean doseEquals(Node<T> root, Node<T> sub) {

        if (sub == null) {
            return true;
        }
        if (root == null) {
            return false;
        }
        if (!root.getData().equals(sub.getData())) {
            return false;
        }
        return doseEquals(root.getRightChild(), sub.getRightChild()) && doseEquals(root.getLeftChild(), sub.getLeftChild());
    }

    private static <T extends Comparable<? super T>> void mirl(Node<T> root) {

        if (root == null) {
            return;
        }

        Node<T> t = root.getRightChild();
        root.setRightChild(root.getLeftChild());
        root.setLeftChild(t);

    }

    private static <T extends Comparable<? super T>> void pre(Node<T> root) {

        if (root == null) {
            return;
        }

        mirl(root);
        pre(root.getLeftChild());
        pre(root.getRightChild());

    }

}



