package Hw4;

import java.util.Scanner;

public class Hash {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        int[] arr = new int[m];
        for (int i = 0; i < m; i++) {
            arr[i] = sc.nextInt();
        }
        for(int i = 0; i < n; i++){
            char c = sc.next().charAt(0);
            int x = sc.nextInt();
            int y = sc.nextInt();
            if(c == 'C'){
                arr[x - 1] = y;
            }else{
                hash(arr, x, y);
            }
        }
    }
    static void hash(int[] arr, int x, int y){
        int sum = 0;
        for(int i = 0; i < arr.length; i++){
            if((i + 1) % x == y){
                sum += arr[i];
            }
        }
        System.out.println(sum);
    }
}
