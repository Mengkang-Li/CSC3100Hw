package Hw1;

import java.util.Scanner;

public class CellularAutomaton2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt(); // the scale of the cells

        int[][] cells = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                cells[i][j] = sc.nextInt();
            }
        }

        int x = sc.nextInt(); // the steps of simulation
        // rule out zeros
        if(m == 0 || n == 0){
            System.out.println(-1);
            return;
        }
        // operations
        for (int i = 0; i < x; i++) {
            cells = simulation(cells);
        }
        // output
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(cells[i][j]);
                if (j != n - 1)
                    System.out.print(" ");
            }
            if (i != m - 1)
                System.out.println();
        }

    }

    static int[][] simulation(int[][] a) {
        int[][] re = new int[a.length][a[0].length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                int liveOnes = check(a, i, j);
                if (a[i][j] == 0) {
                    if (liveOnes < 2 || liveOnes > 5) {
                        re[i][j] = 1;
                    }
                } else {
                    if (liveOnes >= 3 && liveOnes <= 6) {
                        re[i][j] = 1;
                    }
                }
            }
        }
        return re;
    }

    static int check(int[][] a, int i, int j) {
        //to test how many are live ones among the eight neighbourhoods
        /*
        live: with less than 3 or more than 6 will die
        die: with less than 2 or more than 5 will live to the next generation
         */
        int count = 0;
        if (i - 1 >= 0 && i + 1 < a.length) {
            if (j - 1 >= 0 && j + 1 < a[i].length) {
                if (a[i - 1][j - 1] == 1) {
                    count++;
                }
                if (a[i][j - 1] == 1) {
                    count++;
                }
                if (a[i + 1][j - 1] == 1) {
                    count++;
                }
                if (a[i - 1][j] == 1) {
                    count++;
                }
                if (a[i + 1][j] == 1) {
                    count++;
                }
                if (a[i - 1][j + 1] == 1) {
                    count++;
                }
                if (a[i][j + 1] == 1) {
                    count++;
                }
                if (a[i + 1][j + 1] == 1) {
                    count++;
                }
                return count;
            } else if (j - 1 >= 0 && j + 1 >= a[i].length) {
                if (a[i - 1][j - 1] == 1) {
                    count++;
                }
                if (a[i][j - 1] == 1) {
                    count++;
                }
                if (a[i + 1][j - 1] == 1) {
                    count++;
                }
                if (a[i - 1][j] == 1) {
                    count++;
                }
                if (a[i + 1][j] == 1) {
                    count++;
                }
                return count;
            } else if (j - 1 < 0 && j + 1 < a[i].length) {
                if (a[i - 1][j] == 1) {
                    count++;
                }
                if (a[i + 1][j] == 1) {
                    count++;
                }
                if (a[i - 1][j + 1] == 1) {
                    count++;
                }
                if (a[i][j + 1] == 1) {
                    count++;
                }
                if (a[i + 1][j + 1] == 1) {
                    count++;
                }
                return count;
            } else {
                return 0;
            }
        } else if (i - 1 >= 0 && i + 1 >= a.length) {
            if (j - 1 >= 0 && j + 1 < a[i].length) {
                if (a[i - 1][j - 1] == 1) {
                    count++;
                }
                if (a[i][j - 1] == 1) {
                    count++;
                }
                if (a[i - 1][j] == 1) {
                    count++;
                }
                if (a[i - 1][j + 1] == 1) {
                    count++;
                }
                if (a[i][j + 1] == 1) {
                    count++;
                }
                return count;
            } else if (j - 1 >= 0 && j + 1 >= a[i].length) {
                if (a[i - 1][j - 1] == 1) {
                    count++;
                }
                if (a[i][j - 1] == 1) {
                    count++;
                }
                if (a[i - 1][j] == 1) {
                    count++;
                }
                return count;
            } else if (j - 1 < 0 && j + 1 < a[i].length) {
                if (a[i - 1][j] == 1) {
                    count++;
                }
                if (a[i - 1][j + 1] == 1) {
                    count++;
                }
                if (a[i][j + 1] == 1) {
                    count++;
                }
                return count;
            } else {
                return 0;
            }
        } else if (i - 1 < 0 && i + 1 < a.length) {
            if (j - 1 >= 0 && j + 1 < a[i].length) {
                if (a[i][j - 1] == 1) {
                    count++;
                }
                if (a[i + 1][j - 1] == 1) {
                    count++;
                }
                if (a[i + 1][j] == 1) {
                    count++;
                }
                if (a[i][j + 1] == 1) {
                    count++;
                }
                if (a[i + 1][j + 1] == 1) {
                    count++;
                }
                return count;
            } else if (j - 1 >= 0 && j + 1 >= a[i].length) {
                if (a[i][j - 1] == 1) {
                    count++;
                }
                if (a[i + 1][j - 1] == 1) {
                    count++;
                }
                if (a[i + 1][j] == 1) {
                    count++;
                }
                return count;
            } else if (j - 1 < 0 && j + 1 < a[i].length) {
                if (a[i + 1][j] == 1) {
                    count++;
                }
                if (a[i][j + 1] == 1) {
                    count++;
                }
                if (a[i + 1][j + 1] == 1) {
                    count++;
                }
                return count;
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }
}
