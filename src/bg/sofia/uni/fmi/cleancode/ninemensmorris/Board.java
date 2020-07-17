package bg.sofia.uni.fmi.cleancode.ninemensmorris;

import bg.sofia.uni.fmi.cleancode.ninemensmorris.enums.Position;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static bg.sofia.uni.fmi.cleancode.ninemensmorris.constants.Constants.*;

public class Board {
    private Position[][] board;
    private Map<String, List<String>> neighbours;

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

        neighbours = new HashMap<>();

        neighbours.put("A1", List.of("A4", "D1"));
        neighbours.put("D1", List.of("A1", "G1", "D2"));
        neighbours.put("G1", List.of("D1", "G4"));

        neighbours.put("B2", List.of("B4", "D2"));
        neighbours.put("D2", List.of("D1", "B2", "F2", "D3"));
        neighbours.put("F2", List.of("D2", "F4"));

        neighbours.put("C3", List.of("D3", "C4"));
        neighbours.put("D3", List.of("D2", "C3", "E3"));
        neighbours.put("E3", List.of("D3", "E4"));

        neighbours.put("A4", List.of("A1", "B4", "A7"));
        neighbours.put("B4", List.of("B2", "A4", "C4", "B6"));
        neighbours.put("C4", List.of("C3", "B4", "C5"));
        neighbours.put("E4", List.of("E3", "F4", "E5"));
        neighbours.put("F4", List.of("F2", "E4", "G4", "F6"));
        neighbours.put("G4", List.of("G1", "F4", "G7"));

        neighbours.put("C5", List.of("C4", "D5"));
        neighbours.put("D5", List.of("C5", "E5", "D6"));
        neighbours.put("E5", List.of("E4", "D5"));

        neighbours.put("B6", List.of("B4", "D6"));
        neighbours.put("D6", List.of("D5", "B6", "F6", "D7"));
        neighbours.put("F6", List.of("F4", "D6"));

        neighbours.put("A7", List.of("A4", "D7"));
        neighbours.put("D7", List.of("A7", "D6", "G7"));
        neighbours.put("G7", List.of("G4", "D7"));
    }

    public Position getPosition(int row, int column) {
        return board[row][column];
    }

    public boolean isValidPosition(char[] position) {
        return position[0] >= 'A' && position[0] <= 'G'
                && position[1] >= '1' && position[1] <= '7';
    }

    public boolean isAvailablePosition(char[] position) {
        int row = (int) position[1] - ONE_ASCII_CODE;
        int column = (int) position[0] - A_ASCII_CODE;

        return board[row][column] == Position.AVAILABLE;
    }

    public boolean isAdjacentPosition(String position) {
        String from = position.substring(0, 2);
        String to = position.substring(2, 4);

        return neighbours.get(from).contains(to);
    }

    public void print() {

        System.out.printf("    A   B   C   D   E   F   G%n" +
                        "1   %c-----------%c-----------%c%n" +
                        "    |           |           |%n" +
                        "2   |   %c-------%c-------%c   |%n" +
                        "    |   |       |       |   |%n" +
                        "3   |   |   %c---%c---%c   |   |%n" +
                        "    |   |   |       |   |   |%n" +
                        "4   %c---%c---%c       %c---%c---%c%n" +
                        "    |   |   |       |   |   |%n" +
                        "5   |   |   %c---%c---%c   |   |%n" +
                        "    |   |       |       |   |%n" +
                        "6   |   %c-------%c-------%c   |%n" +
                        "    |           |           |%n" +
                        "7   %c-----------%c-----------%c%n",
                board[0][0].getPiece(), board[0][3].getPiece(), board[0][6].getPiece(),
                board[1][1].getPiece(), board[1][3].getPiece(), board[1][5].getPiece(),
                board[2][2].getPiece(), board[2][3].getPiece(), board[2][4].getPiece(),
                board[3][0].getPiece(), board[3][1].getPiece(), board[3][2].getPiece(), board[3][4].getPiece(), board[3][5].getPiece(), board[3][6].getPiece(),
                board[4][2].getPiece(), board[4][3].getPiece(), board[4][4].getPiece(),
                board[5][1].getPiece(), board[5][3].getPiece(), board[5][5].getPiece(),
                board[6][0].getPiece(), board[6][3].getPiece(), board[6][6].getPiece()
        );
    }

    public void setBoard(char[] position, Position value) {
        int row = (int) position[1] - ONE_ASCII_CODE;
        int column = (int) position[0] - A_ASCII_CODE;

        board[row][column] = value;
    }
}