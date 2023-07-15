package Hw3;

import java.util.*;

public class Question {
    // 使用kruskal 算法来求出对应最小生成树的各个距离对应的数量
    // 使用乘法原理计算最终的数量

    /**
     * kruskal算法：take一个edges集合，return一个各个距离对应的数量
     * 1. 按照权重对edges进行排序（权重用一个数组表示）
     * 2. 然后遍历edges，划分不同的集合，如果相同，则进行下一个，如果不同，则合并两个集合
     * 3. 根据最终的集合进行统计，返回对应的数组
     */
    static long mid;
    static Stack<Integer> stack;
    static int[] from = new int[1003]; // 表示权值边的起点
    static int[] to = new int[1003]; // 某权值边的终点
    static int[] sum = new int[1003];
    static int cnt = 1;
    static int middle = 0;
    static UnionSet uni;

    static class Edge {
        int front;
        int latter;
        int val;

        public Edge(int front, int latter, int val) {
            this.front = front;
            this.latter = latter;
            this.val = val;
        }

    }

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
        stack = new Stack<>();
        Scanner sc = new Scanner(System.in);
        int vertices = sc.nextInt();
        int edge = sc.nextInt();
        Edge[] edges = new Edge[edge];
        for (int i = 0; i < edge; i++) {
            edges[i] = new Edge(sc.nextInt() - 1, sc.nextInt() - 1, sc.nextInt());
        }
        // 对edges进行排序
        Arrays.sort(edges, Comparator.comparing(a -> a.val));
        Edge[] reset = Kruskal(vertices, edges);
        if (reset == null) {
            System.out.println(0);
            return;
        }
        // 根据reset进行乘法原理
        System.out.println(Count(reset, edges, vertices));



    }

    static Edge[] Kruskal(int v, Edge[] edges) {
        UnionSet u = new UnionSet(v);
        Edge[] reset = new Edge[v - 1];
        int k = 0;
        for (int i = 0; i < edges.length; i++) {
            if (!u.compare(edges[i].front, edges[i].latter)) {
                u.merge(edges[i].front, edges[i].latter);
                reset[k++] = edges[i];
            }
            if (k == v - 1) {
                break;
            } else if (i == edges.length - 1) {
                return null;
            }
        }
        return reset;
    }

    static long Count(Edge[] reset, Edge[] edges, int vertices) {
        // 选中该权值下的指定条边，然后进行Kruskal算法，看是否能求出一个合理的最小生成树，如果可以，加入计数
        // 判定是否可以使用并查集，查：找root，并：合并
        for (int i = 1; i < edges.length; i++) {
            if (edges[i].val != edges[i - 1].val || i == edges.length - 1) {
                to[cnt] = i - 1;
                from[++cnt] = i;
            }
        }
        to[cnt] = reset.length - 1;

        int count = 0;
        long re = 1;

        while (count != reset.length) {
            int weight = reset[count].val;
            int begin = count;
            while (count != reset.length - 1 && reset[count].val == reset[count + 1].val) {
                count++;
            }
            count++;

            // 从n个数中选count - begin个 选weight = weight的
            int m = 0;
            while (m < edges.length) {

                if (edges[m].val == weight) {
                    break;
                }
                m++;
            }
            int n = m;
            while (n < edges.length) {
                if (edges[n].val != weight) {
                    break;
                }
                n++;
            }
            UnionSet u = new UnionSet(vertices);
            for (int x = 0; x < begin; x++) {
                u.merge(reset[x].front, reset[x].latter);
            }
            for (int x = count; x < reset.length; x++) {
                u.merge(reset[x].front, reset[x].latter);
            }
            find(edges, m, n, count - begin, u);
            re = re * mid % 1000000007;
            mid = 0;
        }
        uni = new UnionSet(vertices);
        int may = 1;
        for(int i = 1; i <= cnt; i++){
            middle = 0;
            dfs(edges, from[i], i, 0, uni);
            may = may * middle % 1000000007;
            for(int j = from[i]; j < to[i]; j++){
                if(!uni.compare(edges[j].front, edges[j].latter)){
                    uni.merge(edges[j].front, edges[j].latter);
                }
            }
        }
        System.out.println(may);
        return re;
    }

    // 基础问题：从arr数列的第i个开始，到j结束，选k个数字，这数字能形成最小生成树，然后mid加一
    // 子问题：从arr数列的第i+1开始，到j结束，选k-1个数字，能形成加入k之后的最小生成树 然后mid+1
    static void find(Edge[] arr, int i, int j, int k, UnionSet u) {
        if (j - i < k) {
            return;
        }
        // 基础情况是如果k是1 直接遍历i到j 找到合适的 然后输出
        if (k == 1) {
            for (int x = i; x < j; x++)
                if (!u.compare(arr[x].latter, arr[x].front)) {
                    mid++;
                }
        } else {
            // 如果不是1，查看是否能加入
            // 如果可以加入，则加入，然后对arr的i+1到j的子序列进行查询，能否形成加入k之后的最小生成树
            for (int p = i; p < j; p++) {
                if (!u.compare(arr[p].front, arr[p].latter)) {
                    u.merge(arr[p].front, arr[p].latter);
                    find(arr, p + 1, j, k - 1, u);
                    u.split();
                }
            }
        }
    }

    static void dfs(Edge[] edges, int f, int t, int c, UnionSet u) {
        if (f == to[t] + 1) {
            if (c == sum[t]) {
                middle++;
            }
            return;
        }
        if (u.compare(edges[f].front, edges[f].latter)) {
            u.merge(edges[f].front, edges[f].latter);
            dfs(edges, f + 1, t, c + 1, u);
            u.split();
        }
        dfs(edges, f + 1, t, c, u);
    }

}