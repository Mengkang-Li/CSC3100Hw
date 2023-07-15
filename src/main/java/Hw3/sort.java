package Hw3;


import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/*public class sort {
    static class Node {
        int x;
        int y;
        int re;

        public Node(int a, int b) {
            x = a;
            y = b;
            re = -1;
        }
    }
    static Node[] nodes;
    static int n;
    static int[][] ans;
    static int cnt;
    static void bfs(int st, int en) {
        if(st == en){
            for(int i = 0; i < n; i++){
                if(nodes[i].re == 0){
                    ans[cnt][i] = nodes[i].x;
                }else if(nodes[i].re == 1){
                    ans[cnt][i] = nodes[i].y;
                }
            }
            cnt++;
            return;
        }
        nodes[st].re = 0;
        bfs(st + 1, en);
        nodes[st].re = 1;
        bfs(st + 1, en);
    }

    public static void main(String[] args) {
        n = 5;
        cnt = 0;
        ans = new int[32][n];
        nodes = new Node[]{
                new Node(1, 2),
                new Node(3, 4),
                new Node(5, 6),
                new Node(7, 8),
                new Node(9, 10)
        };
        bfs(0, 5);
        for(int[] i : ans){
            System.out.println(Arrays.toString(i));
        }

    }
}*/
public class sort {
    static class Edge {
        int x;
        int y;
        int z;

        public Edge(int a, int b, int c) {
            x = a;
            y = b;
            z = c;
        }
    }

    static class M {
        Edge left;
        Edge right;

        public M(Edge m, Edge n) {
            left = m;
            right = n;
        }

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int v = sc.nextInt();
        int e = sc.nextInt();
        Edge[] edges = new Edge[e];
        for (int i = 0; i < e; i++) {
            edges[i] = new Edge(sc.nextInt() - 1, sc.nextInt() - 1, sc.nextInt());
        }
        M m = new M(edges[0], edges[1]);
        Arrays.sort(edges, Comparator.comparing(a -> a.z));
        System.out.println(m.left.z);
        System.out.println(edges[4].z);
        m.left.z = 0;
        System.out.println(edges[4].z);
    }
}
    /* static void KruskalWithMust() {
        u = new Question2.UnionSet(vertices);
        for (int i = 0; i < k * 2; i++) {
            //分类后的顺序无法整
            u.merge(edges[i].front, edges[i].latter);
        }
        for (int i = count; i < edge; i++) {
            if (!u.compare(edges[i].front, edges[i].latter)) {
                u.merge(edges[i].front, edges[i].latter);
                edges[i].chosen = 1;
            }
        }
    }

   static void Bfs(int st, int en) {
        if (st == en) {
            mid = 0;
            u = new Question2.UnionSet(vertices);
            for (int i = count; i < edge; i++) {
                if (edges[i].chosen == 1) {
                    u.merge(edges[i].front, edges[i].latter);
                    mid += edges[i].val;
                }
            }
            Count(st - 1);
            Kruskal();
            ans = Math.min(ans, mid);
            return;
        }
        musts[st].select = 0;
        // add 什么时候归零？
        Bfs(st + 1, en);
        musts[st].select = 1;
        Bfs(st + 1, en);
    }

    static void Kruskal() {
        for (int i = count; i < edge; i++) {
            if (!u.compare(edges[i].front, edges[i].latter)) {
                u.merge(edges[i].front, edges[i].latter);
                mid += edges[i].val;
            }
        }
    }

    static void Count(int st) {
        if (musts[st].select == 0) {
            u.merge(edges[add].front, edges[add].latter);
            mid += edges[add++].val;
        } else if (musts[st].select == 1) {
            u.merge(edges[add].front, edges[add].latter);
            mid += edges[add++].val;
        }
    }
    static void Change(Question2.Edge m, Question2.Edge n){
        int x = m.front;
        m.front = n.front;
        n.front = x;
        x = m.latter;
        m.latter = n.latter;
        n.latter = x;
        x = m.val;
        m.val = n.val;
        n.val = x;
    }
}*/