package com.list.jike.tree;

import java.util.Stack;

/**
 * 深度优先遍历
 */
public class DFS {

    /**
     * 递归方式
     * @param node
     * @param target
     * @return
     */
    public static Node recursion(Node node, int target){
           if(node != null){
               if(node.getData() == target){
                   return node;
               }else{
                   Node leftNode = recursion(node.getLeft(), target);
                   if(leftNode != null){
                       return leftNode;
                   }
                   Node rightNode = recursion(node.getRight(), target);
                   if(rightNode != null){
                       return rightNode;
                   }
               }
           }
           return null;
    }

    /**
     * 非递归-栈
     * @param node
     * @param target
     * @return
     */
    public static Node noRecursion(Node node, int target){
        Stack<Node> stack = new Stack<>();
        stack.push(node);
        while(!stack.isEmpty()){
            Node popNode = stack.pop();
            if(popNode.getData() != target){
                if(popNode.getRight() != null){
                    stack.push(popNode.getRight());
                }
                if(popNode.getLeft() != null){
                    stack.push(popNode.getLeft());
                }
            }else{
                return popNode;
            }
        }
        return null;
    }


    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        Node node8 = new Node(8);
        node1.setLeft(node2);
        node1.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node7);
        node3.setRight(node8);
        node4.setLeft(node6);
        Node targetNode1 = noRecursion(node1, 1);
        Node targetNode3 = noRecursion(node1, 3);
        Node targetNode5 = noRecursion(node1, 5);
        Node targetNode6 = noRecursion(node1, 6);
        Node targetNode7 = noRecursion(node1, 7);
        System.out.println(targetNode1.getData());
        System.out.println(targetNode3.getData());
        System.out.println(targetNode5.getData());
        System.out.println(targetNode6.getData());
        System.out.println(targetNode7.getData());
        System.out.println(recursion(node1, 5).getData());
        System.out.println(BFS.bfs(node1, 4).getData());
    }
}
