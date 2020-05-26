package com.study.util.sort.offer;

import com.study.util.tree.BinaryTree;
import com.study.util.tree.Node;

/**
 * Created on 2018-10-26
 *
 * @author liuzhaoyuan
 */
public class ConstructTree {

    public static void main(String[] args) {
        int[] preorder = {1, 2, 4, 7, 3, 5, 6, 8};
        int[] inorder = {4, 7, 2, 1, 5, 3, 8, 6};
        Node<Integer> root = constructTree(preorder, 0, inorder, 0, inorder.length - 1);

        BinaryTree<Integer> binaryTree = new BinaryTree<>(root);
        binaryTree.preOrderTraverse(root);
        System.out.println();
        binaryTree.inOrderTraverse(root);
        System.out.println();

        binaryTree.postOrderTraverse(root);
        binaryTree.postOrderUnrec2(root);

    }


    private static Node<Integer> constructTree(int[] preorder, int beg,  int[] midorder, int from1, int to1) {
        Node<Integer> root = new Node<>();

        if (beg >= preorder.length || from1 > to1) {
            return null;
        }

        root.setData(preorder[beg]);

        int index = findIndex(midorder, from1, to1, preorder[beg]);

        if (index > from1) {
            root.setLeftChild(constructTree(preorder, beg + 1,  midorder, from1, index - 1));
        }
        if (index < to1) {
            root.setRightChild(constructTree(preorder, beg + index - from1 + 1, midorder, index + 1, to1));
        }
        return root;

    }

    private static int findIndex(int[] arr, int from, int to, int num) {
        for (int i = from; i <= to; i++) {
            if (arr[i] == num) {
                return i;
            }
        }
        return -1;
    }

}
