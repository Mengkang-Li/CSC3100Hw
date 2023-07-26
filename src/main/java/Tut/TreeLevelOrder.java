package Tut;

import java.util.*;

public class TreeLevelOrder {
    class TreeNode {
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

    class solution {
        List<List<Integer>> lists = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        List<Integer> m = new ArrayList<>();

        // 子代标记成-1 孙子标记成0
        List<List<Integer>> levelOrderBottom(TreeNode root) {
            Stack<List<Integer>> stack = new Stack<>();
            queue.add(root);
            if (root != null) {
                m.add(root.val);
                stack.push(m);
                m = new ArrayList<>();
                root.val = -1;
            }else{
                lists.add(m);
                return lists;
            }
            while (!queue.isEmpty()) {
                TreeNode now = queue.peek();
                if(now.val < 0){
                    if(now.left != null){
                        m.add(now.left.val);
                        now.left.val = now.val - 1;
                        queue.add(now.left);
                    }
                    if(now.right != null){
                        m.add(now.right.val);
                        now.right.val = now.val - 1;
                        queue.add(now.right);
                    }
                    queue.remove();
                }
                if(!queue.isEmpty() && now.val != queue.peek().val){
                    stack.push(m);
                    m = new ArrayList<>();
                }
            }
            while(!stack.isEmpty()){
                lists.add(stack.pop());
            }
            return lists;
        }
    }
}