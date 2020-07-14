package bg.sofia.uni.fmi.cleancode.ninemensmorris;

import bg.sofia.uni.fmi.cleancode.ninemensmorris.enums.Position;
import bg.sofia.uni.fmi.cleancode.ninemensmorris.exceptions.InvalidPositionException;
import bg.sofia.uni.fmi.cleancode.ninemensmorris.exceptions.OccupiedByBlackPlayerException;
import bg.sofia.uni.fmi.cleancode.ninemensmorris.exceptions.OccupiedByWhitePlayerException;
import bg.sofia.uni.fmi.cleancode.ninemensmorris.exceptions.UnavailablePositionException;

import static bg.sofia.uni.fmi.cleancode.ninemensmorris.constants.Constants.A_ASCII_CODE;
import static bg.sofia.uni.fmi.cleancode.ninemensmorris.constants.Constants.ONE_ASCII_CODE;

public class Board {
    private final int BOARD_SIZE = 7;

    private Position[][] board;

    public Board() {
        board = new Position[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = Position.UNAVAILABLE;
            }
        }
        board[0][0] = board[0][3] = board[0][6] =
                board[1][1] = board[1][3] = board[1][5] =
                        board[2][2] = board[2][3] = board[2][4] =
                                board[3][0] = board[3][1] = board[3][2] = board[3][4] = board[3][5] = board[3][6] =
                                        board[4][2] = board[4][3] = board[4][4] =
                                                board[5][1] = board[5][3] = board[5][5] =
                                                        board[6][0] = board[6][3] = board[6][6] = Position.AVAILABLE;
    }

    public int positionValue(int row, int column) {
        return board[row][column].getValue();
    }

    public Position getPosition(int row, int column){
        return board[row][column];
    }

    public boolean isValidPosition(char[] position) {
        position[0] = Character.toUpperCase(position[0]);

        if (position[0] < 'A' || position[0] > 'G'
                || position[1] < '1' || position[1] > '7') {
            throw new InvalidPositionException();
        }

        int row = (int) position[1] - ONE_ASCII_CODE;
        int column = (int) position[0] - A_ASCII_CODE;

        if (board[row][column] == Position.AVAILABLE) {
            return true;
        } else if (board[row][column] == Position.OCCUPIED_BY_WHITE_PLAYER) {
            throw new OccupiedByWhitePlayerException("This position is occupied by the white player!");
        } else if (board[row][column] == Position.OCCUPIED_BY_BLACK_PLAYER) {
            throw new OccupiedByBlackPlayerException("This position is occupied by the black player!");
        } else {
            throw new UnavailablePositionException("This position is unavailable!");
        }
    }

    public void print() {

        System.out.printf("%c-----------%c-----------%c%n" +
                        "|           |           |%n" +
                        "|   %c-------%c-------%c   |%n" +
                        "|   |       |       |   |%n" +
                        "|   |   %c---%c---%c   |   |%n" +
                        "|   |   |       |   |   |%n" +
                        "%c---%c---%c       %c---%c---%c%n" +
                        "|   |   |       |   |   |%n" +
                        "|   |   %c---%c---%c   |   |%n" +
                        "|   |       |       |   |%n" +
                        "|   %c-------%c-------%c   |%n" +
                        "|           |           |%n" +
                        "%c-----------%c-----------%c",
                board[0][0].getPiece(), board[0][3].getPiece(), board[0][6].getPiece(),
                board[1][1].getPiece(), board[1][3].getPiece(), board[1][5].getPiece(),
                board[2][2].getPiece(), board[2][3].getPiece(), board[2][4].getPiece(),
                board[3][0].getPiece(), board[3][1].getPiece(), board[3][2].getPiece(), board[3][4].getPiece(), board[3][5].getPiece(), board[3][6].getPiece(),
                board[4][2].getPiece(), board[4][3].getPiece(), board[4][4].getPiece(),
                board[5][1].getPiece(), board[5][3].getPiece(), board[5][5].getPiece(),
                board[6][0].getPiece(), board[6][3].getPiece(), board[6][6].getPiece()
        );
    }

    public void setBoard(int row, int column, Position value){
        board[row][column] = value;
    }
}
