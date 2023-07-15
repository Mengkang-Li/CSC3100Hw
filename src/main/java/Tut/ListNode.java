package Tut;


public class ListNode {
    int val;
    ListNode next;
    public ListNode(int v){
        this.val = v;
    }
    public ListNode insert(int m){
        ListNode fin = new ListNode(m);
        fin.next = this.next;
        this.next = fin;
        return fin;
    }
    class Solution {
        static ListNode deleteDuplicates(ListNode head) {
            if(head == null){
                return null;
            }
            ListNode re = head;
            ListNode mid = new ListNode(0);
            while(head != null){
                if(head.next != null && head.next.next != null){
                    ListNode prev = head;
                    ListNode curr = head.next;
                    ListNode next = head.next.next;
                    if(prev.val == curr.val && curr.val != next.val){
                        mid.next = next;
                        head = mid;
                    }else if(prev.val != curr.val && curr.val == next.val){
                        mid = prev;
                    }
                }else if(head.next != null){
                    if(head.val == head.next.val){
                        return null;
                    }else{
                        return head;
                    }
                }else{
                    return head;
                }
                head = head.next;
            }
            return re;
        }
    }

    public static void main(String[] args) {
        ListNode s = new ListNode(1);
        s.next = new ListNode(2);
        s.next.next = new ListNode(3);
        s.next.next.next = new ListNode(3);
        s.next.next.next.next = new ListNode(4);
        s.next.next.next.next.next = new ListNode(4);
        s.next.next.next.next.next.next = new ListNode(5);
        ListNode m = Solution.deleteDuplicates(s);
        while(m != null){
            System.out.println(m.val);
            m = m.next;
        }
    }

}