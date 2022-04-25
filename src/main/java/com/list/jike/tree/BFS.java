package com.list.jike.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 广度优先遍历
 */
public class BFS {

    /**
     * 遍历，使用队列
     * @param node
     * @param target
     * @return
     */
    public static Node bfs(Node node, int target){
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        while(!queue.isEmpty()){
           Node pollNode = queue.poll();
           if(pollNode.getData() == target){
               return pollNode;
           }
           if(pollNode.getLeft() != null){
               queue.add(pollNode.getLeft());
           }
           if(pollNode.getRight() != null){
               queue.add(pollNode.getRight());
           }
        }
        return null;
    }

}
