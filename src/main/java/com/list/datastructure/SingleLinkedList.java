package com.list.datastructure;

public class SingleLinkedList<T> {

    private Node<T> head;

    public void add(T t){
        Node<T> node = head;
        Node<T> newNode = new Node<T>(t, node);
        head = newNode;
    }

    public void remove(){
        if(head == null){
           return;
        }
        if(head.next == null){
            head = null;
            return;
        }
        Node<T> removeNode = head;
        head = head.next;
        removeNode.next = null;
    }

    class Node<T>{
        private T data;
        private Node next;

        public Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }
    }
}
