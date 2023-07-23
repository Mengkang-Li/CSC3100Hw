package Tut;


import java.util.Stack;

public class Numbers {
    static Stack<Integer> stack = new Stack<>();

    public static void main(String[] args) {
        dfs(0, 8, 1);
    }

    static void dfs(int m, int n, int k) {
        if (m == n) {
            if (k == 0) {
                System.out.println(stack.toString());
            }
        } else {
                stack.push(m);
                dfs(m + 1, n, k - 1);
                stack.pop();
            dfs(m + 1, n, k);
        }
    }
}
