package com.list.jike.tree;

/**
 * 节点
 */
public class Node {

    /**
     * 只是为方便，使用int, 实际应该是个对象
     */
    private int data;

    /**
     * 左子节点
     */
    private Node left;

    /**
     * 右子节点
     */
    private Node right;

    public Node() {
    }

    public Node(int data) {
        this.data = data;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }
}
