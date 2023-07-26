package Tut;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class kthMinTree {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    static class Solution {
        static int kthSmallest(TreeNode root, int k) {
            Stack<TreeNode> stack = new Stack<>();
            stack.push(root);
            while (!stack.isEmpty()) {
                TreeNode now = stack.peek();
                if (now.left != null && now.left.val != -1) {
                    stack.push(now.left);
                } else {
                    stack.pop();
                    k--;
                    if (k == 0) {
                        return now.val;
                    }
                    now.val = -1;
                    if (now.right != null && now.right.val != -1) {
                        stack.push(now.right);
                    }
                }

            }
            return -1;
        }

        static TreeNode addOneRow(TreeNode root, int val, int depth) {
            if (depth == 1) {
                TreeNode r = new TreeNode(val, root, null);
                return r;
            }
            Queue<TreeNode> queue = new LinkedList<>();
            Queue<TreeNode> mid = new LinkedList<>();
            TreeNode ans = root;
            queue.add(root);
            while (!queue.isEmpty()) {
                TreeNode now = queue.peek();
                if (now.left != null) {
                    mid.add(now.left);
                }
                if (now.right != null) {
                    mid.add(now.right);
                }
                if (depth == 2) {
                    TreeNode left = new TreeNode(val);
                    TreeNode right = new TreeNode(val);
                    left.left = now.left;
                    now.left = left;
                    right.right = now.right;
                    now.right = right;
                }
                queue.remove();
                if(depth == 2 && queue.isEmpty()){
                    break;
                }
                if (queue.isEmpty()) {
                    depth--;
                    while (!mid.isEmpty()) {
                        queue.add(mid.remove());
                    }
                }
            }
            return ans;
        }

    }

    public static void main(String[] args) {
        TreeNode tail = new TreeNode(1);
        TreeNode r1 = new TreeNode(2, tail, null);
        TreeNode r2 = new TreeNode(3, r1, new TreeNode(4));
        TreeNode root = new TreeNode(5, r2, new TreeNode(6));
        System.out.println(Solution.kthSmallest(root, 3));
    }
}