package com.study.util.tree;

import java.util.Objects;

/**
 * Created on 2017-12-08
 * <p>
 * 树节点
 * </p>
 *
 * @author liuzhaoyuan
 */
public class Node<T> {

    /**
     * 数据域
     */
    private T data;
    /**
     * 左孩子节点
     */
    private Node<T> leftChild;
    /**
     * 右孩子节点
     */
    private Node<T> rightChild;

    public Node() {
    }

    public Node(T value) {
        this.data = value;
        this.leftChild = null;
        this.rightChild = null;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Node<T> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(Node<T> leftChild) {
        this.leftChild = leftChild;
    }

    public Node<T> getRightChild() {
        return rightChild;
    }

    public void setRightChild(Node<T> rightChild) {
        this.rightChild = rightChild;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Node)) {
            return false;
        }
        Node<?> node = (Node<?>) o;
        return Objects.equals(getData(), node.getData()) &&
            Objects.equals(getLeftChild(), node.getLeftChild()) &&
            Objects.equals(getRightChild(), node.getRightChild());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getData(), getLeftChild(), getRightChild());
    }

    @Override
    public String toString() {
        return "Node{" +
            "data=" + data +
            ", leftChild=" + leftChild +
            ", rightChild=" + rightChild +
            '}';
    }


}
