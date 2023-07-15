package Tut;

public class PolyNode {
    class Node {
        int exponent;
        int coefficient;
        Node next;

        Node add(Node m, Node n) {
            Node re = new Node();
            while (m != null || n != null) {
                if (m == null) {
                    re.next = n;
                    n = n.next;
                } else if (n == null) {
                    re.next = m;
                    m = m.next;
                } else if (m.exponent == n.exponent) {
                    re.next.exponent = m.exponent;
                    re.next.coefficient = m.coefficient + n.coefficient;
                    m = m.next;
                    n = n.next;
                } else if (m.exponent < n.exponent) {
                    re.next = m;
                    m = m.next;
                } else {
                    re.next = n;
                    n = n.next;
                }
                re = re.next;
            }
            return re;
        }
    }


}
