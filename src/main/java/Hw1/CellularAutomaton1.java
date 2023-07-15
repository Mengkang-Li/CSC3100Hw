package Hw1;

import java.util.Scanner;

public class CellularAutomaton1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt(); // the length of the numbers
        int[] nums = new int[m];
        for (int i = 0; i < m; i++) {
            nums[i] = sc.nextInt();
        }
        int n = sc.nextInt(); // the steps of simulation
        for (int i = 0; i < n; i++) {
            nums = simulation(nums);
        }
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i]);
            if (i != nums.length - 1)
                System.out.print(" ");
        }
    }

    static int[] simulation(int[] a) {
        /*
        off off on -> off on on
        on off off -> on on off
        on on on -> on on on
         */
        if (a.length == 0 || a.length == 1) {
            return a;
        }else {
            int[] re = new int[a.length];
            // the number of re is always 0, only need judge whether it should be one
            if(a[0] == 0 && a[1] == 1){
                re[0] = 1;
            }
            for(int i = 1; i < a.length - 1; i++){
                if(a[i] == 0){
                    if((a[i - 1] == 1 && a[i + 1] == 0) || (a[i - 1] == 0 && a[i + 1] == 1)){
                        re[i] = 1;
                    }
                }
                if(a[i] == 1){
                    if(a[i - 1] == 1 && a[i + 1] == 1){
                        re[i] = 1;
                    }
                }
            }
            if(a[a.length - 1] == 0 && a[a.length - 2] == 1){
                re[a.length - 1] = 1;
            }
            return re;
        }

    }
}
