package bg.sofia.uni.fmi.cleancode.ninemensmorris;

import bg.sofia.uni.fmi.cleancode.ninemensmorris.enums.Position;

public class Board {
    private final int BOARD_SIZE = 7;

    private Position[][] board;

    public Board() {
        board = new Position[BOARD_SIZE][BOARD_SIZE];
    }

    public int positionValue(int row, int column) {
        return board[row][column].getValue();
    }

    public boolean isValidPosition(char[] position) {
        if (position[0] >= 'A' && position[0] <= 'G'
                && position[1] >= 1 && position[1] <= 7) {
            return true;
        }
        return false;
    }
}
