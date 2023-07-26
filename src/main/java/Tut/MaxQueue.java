package Tut;

public class MaxQueue {
    static class MaxQueueInitial {
        class Node{
            int val;
            Node next;
            public Node(int v){
                val = v;
            }
        }
        Node head;
        Node tail;
        Node max;
        public MaxQueueInitial() {
            head = new Node(-1);
            tail = new Node(-1);
            head.next = tail;
            max = new Node(-1);
        }

        public int max_value() {
            return max.val;
        }

        public void push_back(int value) {
            if(tail.val == -1){
                tail.val = value;
            }else{
                tail.next = new Node(value);
                tail = tail.next;
            }
            if(value > max.val){
                max = tail;
            }
        }

        public int pop_front() {
            if(head.next == null){
                return -1;
            }
            int v = head.next.val;
            head.next = head.next.next;
            return v;

        }
    }

    public static void main(String[] args) {
        MaxQueueInitial MaxQueue = new MaxQueueInitial();
        MaxQueue.push_back(94);
        MaxQueue.push_back(16);
        MaxQueue.push_back(89);
        System.out.println(MaxQueue.pop_front());
        MaxQueue.push_back(22);
        System.out.println(MaxQueue.pop_front());
    }
}
