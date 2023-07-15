package Hw2;

import java.util.Scanner;

public class Box {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int[] num = new int[m];
        for (int i = 0; i < m; i++) {
            num[i] = sc.nextInt();
        }
        System.out.println(Choose(num));
    }
    static int Choose(int[] x){
        int max = 0;

        for(int i = 0; i < x.length; i++){
            int val = 0;
            for(int j = i; j < x.length; j++){
                val = val + x[j];
                if(val > 0){
                    max = Math.max(max, j - i + 1);
                }
            }
        }
        return max;
    }
}
