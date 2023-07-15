
package Hw3;

        import java.util.Arrays;
        import java.util.Comparator;
        import java.util.Scanner;
        import java.util.Stack;

public class Question1 {
    static Stack<Integer> stack = new Stack<>();
    static Edge[] edges;
    static int vertices;
    static int edge;
    static UnionSet u;
    static class Edge {
        int front;
        int latter;
        int val;

        public Edge(int x, int y, int z) {
            front = x;
            latter = y;
            val = z;
        }
    }
    static int[] from;
    static int[] to;
    static int[] sum;
    static int cnt;
    static long mid;

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
                stack.push(fn);
            } else if (rank[fm] < rank[fn]) {
                set[fm] = fn;
                rank[fn] += rank[fm];
                stack.push(fm);
            } else {
                if (fm >= fn) {
                    set[fm] = fn;
                    rank[fn] += rank[fm];
                    stack.push(fm);
                } else {
                    set[fn] = fm;
                    rank[fm] += rank[fn];
                    stack.push(fn);
                }
            }
        }

        void split() {
            int m = stack.pop();
            set[m] = m;
        }

        boolean compare(int m, int n) {
            return findRoot(m) == findRoot(n);
        }

    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        vertices = sc.nextInt();
        u = new UnionSet(vertices);
        edge = sc.nextInt();
        from = new int[edge];
        to = new int[edge];
        sum = new int[edge];
        edges = new Edge[edge];
        for (int i = 0; i < edge; i++) {
            edges[i] = new Edge(sc.nextInt() - 1, sc.nextInt() - 1, sc.nextInt());
        }
        Arrays.sort(edges, Comparator.comparing(a -> a.val));
        Kruskal();

        int sumT = 0;
        for(int i = 0; i < sum.length; i++){
            sumT += sum[i];
        }
        if(sumT != vertices - 1){
            System.out.println(0);
            return;
        }
        long re = 1;
        u = new UnionSet(vertices);
        for(int i = 0; i <= cnt; i++){
            mid = 0;
            Count(from[i], i, 0);
            re = re * mid % 1000000007;
            for(int j = from[i]; j <= to[i]; j++){
                if(!u.compare(edges[j].front, edges[j].latter)){
                    u.merge(edges[j].front, edges[j].latter);
                }
            }
        }
        System.out.println(re);
    }
    static void Kruskal(){
        if(!u.compare(edges[0].front, edges[0].latter)){
            u.merge(edges[0].front, edges[0].latter);
            sum[cnt]++;
        }
        for(int i = 1; i < edges.length; i++){
            if(edges[i - 1].val != edges[i].val){
                to[cnt] = i - 1;
                from[++cnt] = i;
            }
            if(!u.compare(edges[i].front, edges[i].latter)){
                u.merge(edges[i].front, edges[i].latter);
                sum[cnt]++;
            }
            to[cnt] = edges.length - 1;
        }
        /*System.out.println(Arrays.toString(from));
        System.out.println(Arrays.toString(to));
        System.out.println(Arrays.toString(sum));
        System.out.println(cnt);*/
    }
    static void Count(int f, int t, int all){
        if(f == to[t] + 1){
            if(all == sum[t]){
                mid++;
            }
            return;
        }
        if(!u.compare(edges[f].front, edges[f].latter)){
            u.merge(edges[f].front, edges[f].latter);
            Count(f + 1, t, all + 1);
            u.split();
        }
        Count(f + 1, t, all);
    }
}