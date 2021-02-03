package com.list.jike.sort;

import com.list.leetcode.commmon.structure.ListNode;

/**
 * 使用链表结构实现冒泡排序
 */
public class BubbleSortList {

    public static void sort(ListNode head){
        if(head == null || head.next == null){
            return;
        }
        //每次重头遍历，记录起始位置
        ListNode first = head;
        //每次遍历的截止位置(即上次排到最后的节点)，而不是每次都遍历整个链表的最后节点
        ListNode end = null;
        while(first != end) {
            //有数据交换
            boolean exchanged = false;
            while (head != null && head.next != end) {
                //只比较值，改变值，不改变节点指向
                //将数组存储，改为链表存储而已
                if (head.val > head.next.val) {
                    int tmp = head.val;
                    head.val = head.next.val;
                    head.next.val = tmp;
                    exchanged = true;
                }
                head = head.next;
            }
            //记录下次遍历的截止节点
            end = head;
            //重置下次遍历开始节点
            head = first;
            //已经顺序的就可以提前退出循环
            if(!exchanged){
              break;
            }
        }
    }

    public static void main(String[] args) {
        ListNode node5 = new ListNode(5);
        ListNode node3 = new ListNode(3,node5);
        ListNode node1 = new ListNode(1,node3);
        ListNode node4 = new ListNode(4,node1);
        ListNode node2 = new ListNode(2,node4);
        sort(node2);
    }
}
