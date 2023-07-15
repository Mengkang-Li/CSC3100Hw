package Hw2;

import java.util.Arrays;
import java.util.Scanner;

public class Battle {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] m = new int[sc.nextInt()];
        int[] n = new int[sc.nextInt()];
        for (int i = 0; i < m.length; i++) {
            m[i] = sc.nextInt();
        }
        for (int i = 0; i < n.length; i++) {
            n[i] = sc.nextInt();
        }
        System.out.println(WinMost(m, n));
    }
    static int WinMost(int[] m, int[] n){
        int re = 0;
        Arrays.sort(m);
        Arrays.sort(n);
        int i = m.length - 1;
        int j = n.length - 1;
        while(i >= 0 && j >= 0){
            if(m[i] > n[j]){
                i--;
                j--;
                re++;
            }else{
                j--;
            }
        }
        return re;
    }
}
