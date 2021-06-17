package com.list.jike.tree;

/**
 * 二叉搜索树
 */
public class BinarySearchTree {

    private Node tree;


    public BinarySearchTree() {
    }

    public BinarySearchTree(Node tree) {
        this.tree = tree;
    }

    /**
     * 添加
     * 通过while循环方式
     * @param data
     * @return
     */
    public Node addByWhile(int data){
        if(tree == null){
            tree = new Node(data);
            return tree;
        }

        Node compareNode = tree;
        Node parentNode;
        Node addNode = new Node(data);
        while(compareNode != null){
            parentNode = compareNode;
            if(addNode.getData() > compareNode.getData()){
                compareNode = compareNode.getRight();
                if(compareNode == null){
                    parentNode.setRight(addNode);
                    return tree;
                }
            }
            if(addNode.getData() < compareNode.getData()){
                compareNode = compareNode.getLeft();
                if(compareNode == null){
                    parentNode.setLeft(addNode);
                    return tree;
                }
            }
        }
        return tree;
    }

    /**
     * 添加
     * 通过递归方式
     * @param data
     * @return
     */
    public Node addByRecursion(int data){
        if(tree == null){
            tree = new Node(data);
            return tree;
        }

        Node compareNode = tree;
        Node parentNode;
        Node addNode = new Node(data);
        return tree;
    }








    public static void main(String[] args) {
        BinarySearchTree binarySearchTree = new BinarySearchTree();
        binarySearchTree.addByWhile(10);
        binarySearchTree.addByWhile(5);
        binarySearchTree.addByWhile(9);
        binarySearchTree.addByWhile(11);
        binarySearchTree.addByWhile(1);
    }
}
