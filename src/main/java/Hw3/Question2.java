package Hw3;

import java.util.*;


public class Question2 {

    static Edge[] edges;
    static int ans = 2147483647;
    static int vertices;
    static int edge;
    static UnionSet u;

    static class Edge {
        int front;
        int latter;
        int val;
        int chosen;

        public Edge(int x, int y, int z) {
            front = x;
            latter = y;
            val = z;
            chosen = 0;
        }
    }

    static int full;

    static int[] from;
    static int[] to;
    static int[] sum;
    static int k;
    static Must[] musts;
    static int mid;

    static class UnionSet {
        int[] set;
        int[] rank;

        public UnionSet(int m) {
            this.set = new int[m];
            for (int i = 0; i < m; i++) {
                set[i] = i;
            }
            rank = new int[m];
            Arrays.fill(rank, 1);
        }

        int findRoot(int m) {
            while (set[m] != m) {
                m = set[m];
            }
            return m;
        }

        void merge(int m, int n) {
            int fm = findRoot(m);
            int fn = findRoot(n);
            if (rank[fm] > rank[fn]) {
                set[fn] = fm;
                rank[fm] += rank[fn];
            } else if (rank[fm] < rank[fn]) {
                set[fm] = fn;
                rank[fn] += rank[fm];
            } else {
                if (fm >= fn) {
                    set[fm] = fn;
                    rank[fn] += rank[fm];
                } else {
                    set[fn] = fm;
                    rank[fm] += rank[fn];
                }
            }
        }
        boolean compare(int m, int n) {
            return findRoot(m) == findRoot(n);
        }

    }

    static class Must {
        Edge left;
        Edge right;
        int select;

        public Must(Edge x, Edge y) {
            left = x;
            right = y;
            select = -1;
        }

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        vertices = sc.nextInt();
        edge = sc.nextInt();
        edges = new Edge[edge];
        to = new int[edge];
        from = new int[edge];
        sum = new int[edge];
        for (int i = 0; i < edge; i++) {
            edges[i] = new Edge(sc.nextInt() - 1, sc.nextInt() - 1, sc.nextInt());
        }
        k = sc.nextInt();
        musts = new Must[k];
        for (int i = 0; i < k; i++) {
            musts[i] = new Must(edges[sc.nextInt() - 1], edges[sc.nextInt() - 1]);
        }
        Arrays.sort(edges, Comparator.comparing(a -> a.val));
        if (k == 0) {
            int count = 0;
            mid = 0;
            u = new UnionSet(vertices);
            for (int i = 0; i < edge; i++) {
                if (!u.compare(edges[i].front, edges[i].latter)) {
                    u.merge(edges[i].front, edges[i].latter);
                    mid += edges[i].val;
                    count++;
                }
                if(count == vertices - 1){
                    System.out.println(mid);
                    break;
                }else if(i == edge - 1){
                    System.out.println("orz");
                }
            }
            return;
        }
        KruskalWithMust();
        Bfs(0, k);
        System.out.println(ans);
    }

    static void KruskalWithMust() {
        u = new UnionSet(vertices);
        for (int i = 0; i < k; i++) {
            // 如果k组边中有重复的部分 那么继续选 但是这个重复的东西不加
            u.merge(musts[i].left.front, musts[i].left.latter);
            u.merge(musts[i].right.front, musts[i].right.latter);
        }
        for (int i = 0; i < edge; i++) {
            if (!u.compare(edges[i].front, edges[i].latter)) {
                u.merge(edges[i].front, edges[i].latter);
                edges[i].chosen = 1;
            }
        }
    }

    static void Bfs(int st, int en) {
        if (st == en) {
            u = new UnionSet(vertices);
            mid = 0;
            full = 0;
            for (int i = 0; i < en; i++) {

                if (musts[i].select == 0) {
                    if (!u.compare(musts[i].left.front, musts[i].left.latter)) {
                        u.merge(musts[i].left.front, musts[i].left.latter);
                        mid += musts[i].left.val;
                        musts[i].left.chosen = 1;
                        full++;
                    } else if(musts[i].left.chosen == 0){
                        ans = 2147483647;
                        return;
                    }
                } else if (musts[i].select == 1) {
                    if (!u.compare(musts[i].right.front, musts[i].right.latter)) {
                        u.merge(musts[i].right.front, musts[i].right.latter);
                        mid += musts[i].right.val;
                        musts[i].right.chosen = 1;
                        full++;
                    }else if(musts[i].right.chosen == 0){
                        ans = 2147483647;
                        return;
                    }
                }
            }
            for(int i = 0; i < k; i++){
                musts[i].left.chosen = 0;
                musts[i].right.chosen = 0;
            }
            Kruskal();
            ans = Math.min(ans, mid);
            return;
        }
        musts[st].select = 0;
        Bfs(st + 1, en);
        musts[st].select = 1;
        Bfs(st + 1, en);
    }

    static void Kruskal() {
        // 应该放在哪里？
        for (int i = 0; i < edge; i++) {
            if (edges[i].chosen == 1) {
                u.merge(edges[i].front, edges[i].latter);
                mid += edges[i].val;
                full++;
            }
        }
        for (int i = 0; i < edge; i++) {
            if (edges[i].chosen == 0 && !u.compare(edges[i].front, edges[i].latter)) {
                u.merge(edges[i].front, edges[i].latter);
                mid += edges[i].val;
                full++;
            }
            if (full == vertices - 1) {
                break;
            }
            // 考虑某种方法无法形成spanning tree  设置为最大值

            if (i == edge - 1) {
                mid = 2147483647;
            }
        }
    }
}