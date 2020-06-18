package bg.sofia.uni.fmi.cleancode.ninemensmorris;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        int[][] board = new int[7][7];
        for (int[] row : board) {
            System.out.println(Arrays.toString(row));
        }
    }
}
