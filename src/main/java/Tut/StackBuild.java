package Tut;

public class StackBuild {
    public static void main(String[] args) {

    }
    class Node{
        int val;
        Node next;
        public Node(){
            this.val = 0;
            this.next = null;
        }
    }
    public StackBuild(){
        this.head = null;
    }
    public boolean isEmpty(){
        return (head.next == null);
    }
    public void clearElement(){
        while(this.head != null){
            Node tmp = this.head;
            this.head = this.head.next;
        }
    }
    Node head;
    public void push(){

    }
    public void pop(){

    }
}
