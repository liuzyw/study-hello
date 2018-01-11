package com.study.util.tree;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Objects;
import java.util.Stack;

/**
 * Created on 2017-12-08
 *
 * <p>
 * 二叉树
 * </p>
 *
 * @author liuzhaoyuan
 */
public class BinaryTree<T> {

    /**
     * 根节点
     */
    private Node<T> root;

    public Node<T> getRoot() {
        return root;
    }

    public BinaryTree() {
        this.root = null;
    }

    public BinaryTree(Node<T> root) {
        this.root = root;
    }

    /**
     * 查找指定节点
     *
     * @return
     */
    public Node<T> getNode(Node<T> root, Node<T> node) {
        if (root == null) {
            return null;
        }
        if (root.equals(node)) {
            return root;
        }
        if (root.getLeftChild() != null) {
            return getNode(root.getLeftChild(), node);
        }
        if (root.getRightChild() != null) {
            return getNode(root.getRightChild(), node);
        }
        return null;
    }

    /**
     * 添加一个节点
     *
     * @return
     */
    public boolean addNode(Node<T> parent, Node<T> newNode, boolean isLeft) {

        if (parent == null || newNode == null) {
            return false;
        }
        if (this.root == null) {
            this.root = newNode;
            return true;
        }
        if (isLeft) {
            newNode.setLeftChild(parent.getLeftChild());
            parent.setLeftChild(newNode);
        } else {
            newNode.setRightChild(parent.getRightChild());
            parent.setRightChild(newNode);
        }
        return true;
    }

    /**
     * 以二叉树排序树形式添加节点
     *
     * @return
     */
    public <T extends Comparable<? super T>> boolean addNodeSort(Node<T> root, Node<T> newNode) {
        if (this.root == null) {
            this.root = new Node(newNode.getData());
            return true;
        }
        if (root.getData().compareTo(newNode.getData()) > 0) {
            if (root.getLeftChild() == null) {
                root.setLeftChild(newNode);
            } else {
                addNodeSort(root.getLeftChild(), newNode);
            }
            return true;
        } else if (root.getData().compareTo(newNode.getData()) < 0) {
            if (root.getRightChild() == null) {
                root.setRightChild(newNode);
            } else {
                addNodeSort(root.getRightChild(), newNode);
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * 前序递归遍历
     */
    public void preOrderTraverse(Node<T> root) {
        if (root == null) {
            return;
        }
        System.out.print(root.getData() + ",  ");
        preOrderTraverse(root.getLeftChild());
        preOrderTraverse(root.getRightChild());
    }

    /**
     * 前序非递归
     */
    public void preOrderUnrec(Node<T> root) {
        Stack<Node<T>> stack = new Stack<Node<T>>();
        Node<T> p = root;
        while ((p != null) || !stack.isEmpty()) {
            while (p != null) {
                System.out.print(p.getData() + ",  ");
                stack.push(p);
                p = p.getLeftChild();
            }

            if (stack.isEmpty()) {
                return;
            } else {
                p = stack.pop();
                p = p.getRightChild();
            }
        }
    }

    /**
     * 中序递归遍历
     */
    public void inOrderTraverse(Node<T> root) {
        if (root == null) {
            return;
        }
        inOrderTraverse(root.getLeftChild());
        System.out.print(root.getData() + ",  ");
        inOrderTraverse(root.getRightChild());
    }

    /**
     * 中序非递归遍历
     */
    public void inOrderUnrec(Node<T> root) {
        Stack<Node<T>> stack = new Stack<Node<T>>();
        Node<T> p = root;
        while ((p != null) || !stack.isEmpty()) {
            while (p != null) {
                stack.push(p);
                p = p.getLeftChild();
            }

            if (stack.isEmpty()) {
                return;
            } else {
                p = stack.pop();
                System.out.print(p.getData() + ",  ");
                p = p.getRightChild();
            }
        }
    }

    /**
     * 后序递归遍历
     */
    public void postOrderTraverse(Node<T> root) {
        if (root == null) {
            return;
        }
        postOrderTraverse(root.getLeftChild());
        postOrderTraverse(root.getRightChild());
        System.out.print(root.getData() + ",  ");
    }

    /**
     * 后序非递归遍历
     */
    public void postOrderUnrec(Node<T> root) {

        Stack<Node<T>> stack = new Stack<Node<T>>();
        Node<T> p = root;
        // pre标记最近出栈的节点，用于判断是否是p节点的右孩子，如果是的话，就可以访问p节点
        Node<T> pre = p;
        // flag标记是出栈还是继续将左孩子进栈
        boolean flag = true;
        while (p != null || !stack.isEmpty()) {
            if (p != null && flag) {
                stack.push(p);
                p = p.getLeftChild();
            } else {
                if (stack.isEmpty()) {
                    return;
                }
                p = stack.peek();
                if (p.getRightChild() != null && p.getRightChild() != pre) {
                    p = p.getRightChild();
                    flag = true;
                } else {
                    // 若没有右孩子，或者右孩子已经出栈，则可访问
                    p = stack.pop();
                    System.out.print(p.getData() + ",  ");
                    flag = false;
                    pre = p;
                }
            }
        }
    }

    /**
     * 层次遍历
     */
    public void levelTraverse(Node<T> root) {
        ArrayDeque<Node<T>> queue = new ArrayDeque<Node<T>>();
        if (root != null) { // 先将根结点入队列
            queue.offer(root);
        }
        // 队列不空时，说明遍历还未结束
        while (!queue.isEmpty()) {
            Node<T> p = queue.poll();// 队头元素出队列
            System.out.print(p.getData() + ",  ");
            if (p.getLeftChild() != null) {
                // 队头元素的左孩子入队列
                queue.offer(p.getLeftChild());
            }
            if (p.getRightChild() != null) {
                // 队头元素的右孩子入队列
                queue.offer(p.getRightChild());
            }
        }
    }

    /**
     * 计算节点数
     *
     * @return
     */
    public int getNodeNum(Node<T> root) {
        if (root == null) // 递归出口
        {
            return 0;
        }
        return getNodeNum(root.getRightChild()) + getNodeNum(root.getLeftChild()) + 1;
    }

    /**
     * 计算叶子数
     *
     * @return
     */
    public int getLeafNum(Node<T> root) {
        if (root == null) {
            return 0;
        }
        if (root.getLeftChild() == null && root.getRightChild() == null) {
            return 1;
        } else {
            return (getLeafNum(root.getLeftChild()) + getLeafNum(root.getRightChild()));
        }
    }

    /**
     * 递归方法求取二叉树的深度
     *
     * @return
     */
    public int getDepth(Node<T> root) {
        int dep1, dep2;
        if (root != null) {
            dep1 = getDepth(root.getLeftChild());
            dep2 = getDepth(root.getRightChild());
            return dep1 > dep2 ? dep1 + 1 : dep2 + 1;
        }
        return 0;
    }

    /**
     * 非递归求深度
     *
     * @return
     */
    public int getDepthAd(Node<T> root) {
        if (root == null) {
            return 0;
        }
        int level = 0;
        int start = 1;
        int end = 1;
        ArrayDeque<Node<T>> queue = new ArrayDeque<>();
        queue.offer(root);
        Node<T> p;
        while (!queue.isEmpty()) {
            if (start == end) {
                // 每行最后一个
                p = queue.poll();
                level++;
                start = 1;
                if (p.getLeftChild() != null) {
                    queue.offer(p.getLeftChild());
                }
                if (p.getRightChild() != null) {
                    queue.offer(p.getRightChild());
                }
                end = queue.size();
            } else {
                p = queue.poll();
                start++;
                if (p.getLeftChild() != null) {
                    queue.offer(p.getLeftChild());
                }
                if (p.getRightChild() != null) {
                    queue.offer(p.getRightChild());
                }
            }
        }
        return level;
    }


    /**
     * 求二叉树的镜像
     * 翻转二叉树
     */
    public void mirrorRecursively(Node<T> root) {
        if (root == null) {
            return;
        }
        if (root.getLeftChild() == null && root.getRightChild() == null) {
            return;
        }
        Node<T> temp = root.getLeftChild();
        root.setLeftChild(root.getRightChild());
        root.setRightChild(temp);

        if (root.getLeftChild() != null) {
            mirrorRecursively(root.getLeftChild());
        }
        if (root.getRightChild() != null) {
            mirrorRecursively(root.getRightChild());
        }
    }

    /**
     * 计算某一层节点个数
     *
     * @return
     */
    public int getNodeNumByLevel(Node<T> root, int k) {
        if (root == null || k < 1 || k > this.getDepth(root)) {
            return 0;
        }
        if (k == 1) {
            return 1;
        }
        // 左子树中k-1层的节点个数
        int numLeft = getNodeNumByLevel(root.getLeftChild(), k - 1);
        // 右子树中k-1层的节点个数
        int numRight = getNodeNumByLevel(root.getRightChild(), k - 1);
        return (numLeft + numRight);
    }

    /**
     * 求两个节点的公共节点
     *
     * @return
     */
    public Node<T> getPublicNode(Node<T> root, Node<T> node1, Node<T> node2) {
        if (root == null) {
            return null;
        }
        if (root.equals(node1) || root.equals(node2)) {
            return root;
        }
        Node<T> p = getPublicNode(root.getLeftChild(), node1, node2);
        Node<T> q = getPublicNode(root.getRightChild(), node1, node2);
        if ((p != null) && (q != null)) {
            return root;
        }
        return (p != null) ? p : q;
    }

    /**
     * 清空树
     */
    public void clearTree(Node<T> root) {

        ArrayDeque<Node<T>> stack = new ArrayDeque<Node<T>>();
        Node<T> p = root;
        // pre标记最近出栈的节点，用于判断是否是p节点的右孩子，如果是的话，就可以访问p节点
        Node<T> pre = p;
        // flag标记是出栈还是继续将左孩子进栈
        boolean flag = true;
        while (p != null || !stack.isEmpty()) {
            if (p != null && flag) {
                stack.push(p);
                p = p.getLeftChild();
            } else {
                if (stack.isEmpty()) {
                    return;
                }
                p = stack.peek();
                if (p.getRightChild() != null && p.getRightChild() != pre) {
                    p = p.getRightChild();
                    flag = true;
                } else {
                    // 若没有右孩子，或者右孩子已经出栈，则可访问
                    p = stack.pop();
                    flag = false;
                    pre = p;

                    p.setData(null);
                    p.setRightChild(null);
                    p.setLeftChild(null);
                    p = null;
                }
            }
        }
        this.root = null;
    }


    /**
     * 查找指定值的路径
     */
    public void findPath(Node<Integer> root, int sum) {
        Stack<Node<Integer>> stack = new Stack<Node<Integer>>();
        Node<Integer> p = root;
        // pre标记最近出栈的节点，用于判断是否是p节点的右孩子，如果是的话，就可以访问p节点
        Node<Integer> pre = p;
        // flag标记是出栈还是继续将左孩子进栈
        boolean flag = true;
        while (p != null || !stack.isEmpty()) {
            if (p != null && flag) {
                stack.push(p);
                p = p.getLeftChild();
            } else {
                if (stack.isEmpty()) {
                    return;
                }
                p = stack.peek();
                if (p.getRightChild() != null && p.getRightChild() != pre) {
                    p = p.getRightChild();
                    flag = true;
                } else {
                    // 若没有右孩子，或者右孩子已经出栈，则可访问
                    p = stack.peek();
                    if (p.getLeftChild() == null && p.getRightChild() == null) {
                        int sumValue = 0;

                        Iterator<Node<Integer>> iter = stack.iterator();
                        while (iter.hasNext()) {
                            Node<Integer> temp = iter.next();
                            sumValue += temp.getData();
                        }
                        if (sumValue == sum) {
                            System.out.println(stack.toString());
                        }
                    }
                    p = stack.pop();
                    flag = false;
                    pre = p;

                }
            }
        }
    }

    /**
     * sub 时候是 bt 的子树
     *
     * @return
     */
    public boolean hasSubtree(Node<Integer> bt, Node<Integer> sub) {
        boolean ret = false;
        if (bt != null && sub != null) {
            ret = doesTree1HaveTree2(bt, sub);
        }
        if (!ret && bt.getLeftChild() != null) {
            ret = hasSubtree(bt.getLeftChild(), sub);
        }
        if (!ret && bt.getRightChild() != null) {
            ret = hasSubtree(bt.getRightChild(), sub);
        }
        return ret;
    }

    /**
     * 一个节点判断
     *
     * @return
     */
    private boolean doesTree1HaveTree2(Node<Integer> bt, Node<Integer> sub) {
        if (sub == null) {
            return true;
        }
        if (bt == null) {
            return false;
        }

        if (!Objects.equals(bt.getData(), sub.getData())) {
            return false;
        }
        return doesTree1HaveTree2(bt.getLeftChild(), sub.getLeftChild())
            && doesTree1HaveTree2(bt.getRightChild(), sub.getRightChild());
    }


}
