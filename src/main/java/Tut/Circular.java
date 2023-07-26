package Tut;

import java.util.Arrays;

public class Circular {
    static class MyCircularDeque {
        int[] arr;
        int head;
        int tail;
        int length;
        public MyCircularDeque(int k) {
            arr = new int[k];
            head = k / 2;
            tail = k / 2;
            length = k;
            Arrays.fill(arr, -1);
        }

        public boolean insertFront(int value) {
            if(!this.isFull()){
                arr[head] = value;
                head = help(head - 1);
                return true;
            }else{
                return false;
            }
        }

        public boolean insertLast(int value) {
            if(!this.isFull()){
                tail = help(++tail);
                arr[tail] = value;
                return true;
            }else{
                return false;
            }
        }

        public boolean deleteFront() {
            if(this.isEmpty()){
                return false;
            }else{
                head++;
                head = help(head);
                arr[head] = -1;
                return true;
            }
        }

        public boolean deleteLast() {
            if(this.isEmpty()){
                return false;
            }else{
                arr[tail] = -1;
                tail = help(--tail);
                return true;
            }
        }
        public int getFront() {
            return arr[help(head + 1)];
        }

        public int getRear() {
            return arr[tail];
        }

        public boolean isEmpty() {
            return arr[help(head + 1)] == -1 && arr[tail] == -1;
        }

        public boolean isFull() {
            return arr[help(tail + 1)] != -1;
        }
        int help(int x){
            if(x > length - 1){
                return x - length;
            }else if(x < 0){
                return x + length;
            }else{
                return x;
            }
        }
    }

    public static void main(String[] args) {
        MyCircularDeque deque = new MyCircularDeque(3);
        System.out.println(deque.insertLast(1));
        System.out.println(deque.insertLast(2));
        System.out.println(deque.insertFront(3));
        System.out.println(deque.insertFront(4));
        System.out.println(deque.getRear());
        System.out.println(deque.isFull());
        System.out.println(deque.deleteLast());
        System.out.println(deque.insertLast(4));
        System.out.println(deque.getFront());
    }
}
