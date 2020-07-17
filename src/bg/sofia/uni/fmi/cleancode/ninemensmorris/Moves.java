package bg.sofia.uni.fmi.cleancode.ninemensmorris;


import bg.sofia.uni.fmi.cleancode.ninemensmorris.enums.Colour;
import bg.sofia.uni.fmi.cleancode.ninemensmorris.enums.Position;

import java.util.Scanner;

import static bg.sofia.uni.fmi.cleancode.ninemensmorris.constants.Constants.A_ASCII_CODE;
import static bg.sofia.uni.fmi.cleancode.ninemensmorris.constants.Constants.ONE_ASCII_CODE;

public class Moves {

    private Board board;
    private Player firstPlayer;
    private Player secondPlayer;

    public Moves() {
        board = new Board();
        Player whitePlayer = new Player(Colour.WHITE);
        Player blackPlayer = new Player(Colour.BLACK);

        firstPlayer = chooseFirstPlayer(whitePlayer, blackPlayer);
        secondPlayer = firstPlayer.getPiecesColour().equals(whitePlayer.getPiecesColour()) ? blackPlayer : whitePlayer;
    }

    public void placingPieces() {
        board.print();

        while (firstPlayer.getUnplacedPieces() > 0 && secondPlayer.getUnplacedPieces() > 0) {
            System.out.println(firstPlayer.getPiecesColour() + " player turn.");
            placePiece(firstPlayer);

            System.out.println(secondPlayer.getPiecesColour() + " player turn.");
            placePiece(secondPlayer);
        }
    }

    public void movingPieces() {
        while (firstPlayer.getPiecesOnBoard() > 3 && secondPlayer.getPiecesOnBoard() > 3) {
            System.out.println(firstPlayer.getPiecesColour() + " player turn.");
            movePiece(firstPlayer);

            System.out.println(secondPlayer.getPiecesColour() + " player turn.");
            movePiece(secondPlayer);
        }
    }

    public void flying() {
        while (firstPlayer.getPiecesOnBoard() >= 3 && secondPlayer.getPiecesOnBoard() >= 3) {
            if (firstPlayer.canFly()) {
                while (!secondPlayer.canFly()) {
                    System.out.println(firstPlayer.getPiecesColour() + " player turn.");
                    String position = enterPosition();
                    fly(position, firstPlayer);

                    if (secondPlayer.canFly()) {
                        break;
                    }

                    System.out.println(secondPlayer.getPiecesColour() + " player turn.");
                    movePiece(secondPlayer);
                }
            } else if (secondPlayer.canFly()) {
                while (!firstPlayer.canFly()) {
                    System.out.println(firstPlayer.getPiecesColour() + " player turn.");
                    movePiece(firstPlayer);

                    System.out.println(secondPlayer.getPiecesColour() + " player turn.");
                    String position = enterPosition();
                    fly(position, secondPlayer);

                    if (firstPlayer.canFly()) {
                        break;
                    }
                }
            }
            System.out.println(firstPlayer.getPiecesColour() + " player turn.");
            String position1 = enterPosition();
            fly(position1, firstPlayer);

            if (secondPlayer.getPiecesOnBoard() < 3) {
                winner(firstPlayer);
            }

            System.out.println(secondPlayer.getPiecesColour() + " player turn.");
            String position2 = enterPosition();
            fly(position2, secondPlayer);

            if (firstPlayer.getPiecesOnBoard() < 3) {
                winner(secondPlayer);
            }
        }
    }

    private void placePiece(Player player) {
        while (true) {
            char[] position = enterPosition().toCharArray();

            if (!board.isValidPosition(position)) {
                System.out.println("This position is invalid! Please choose a valid one.");
                continue;
            }

            if (!board.isAvailablePosition(position)) {
                System.out.println("This position is unavailable! Please choose an available one.");
                continue;
            }

            place(position, player);
            break;
        }
    }

    private void place(char[] position, Player player) {
        if (player.getPiecesColour() == Colour.WHITE) {
            board.setBoard(position, Position.OCCUPIED_BY_WHITE_PLAYER);
        } else if (player.getPiecesColour() == Colour.BLACK) {
            board.setBoard(position, Position.OCCUPIED_BY_BLACK_PLAYER);
        }

        player.setPiecesOnBoard(player.getPiecesOnBoard() + 1);
        player.setUnplacedPieces(player.getUnplacedPieces() - 1);

        board.print();

        if (hasMill(player.getPiecesColour(), position)) {
            System.out.println("Remove an opponent's piece:");
            removePiece(player == firstPlayer ? firstPlayer : secondPlayer);
            board.print();
        }
    }

    private void movePiece(Player player) {
        while (true) {
            System.out.println("Choose which piece to move. ");
            String position = enterPosition();

            if (!board.isAdjacentPosition(position)) {
                System.out.println("This position is not adjacent! Please choose adjacent position.");
                continue;
            }

            char[] from = position.substring(0, 2).toCharArray();
            char[] to = position.substring(2, 4).toCharArray();

            if (!board.isValidPosition(from)) {
                System.out.println("First position is invalid! Please choose a valid one.");
                continue;
            }

            if (board.isAvailablePosition(from)) {
                System.out.println("First position is available! Please choose unavailable one.");
                continue;
            }

            if (!board.isValidPosition(to)) {
                System.out.println("Second position is invalid! Please choose a valid one.");
                continue;
            }

            if (!board.isAvailablePosition(to)) {
                System.out.println("Second position is unavailable! Please choose an available one.");
                continue;
            }

            board.setBoard(from, Position.AVAILABLE);
            place(to, player);
            break;
        }
    }

    private void fly(String position, Player player) {
        while (true) {
            char[] from = position.substring(0, 2).toCharArray();
            char[] to = position.substring(2, 4).toCharArray();

            if (!board.isValidPosition(from)) {
                System.out.println("First position is invalid! Please choose a valid one.");
                continue;
            }

            if (!board.isAvailablePosition(from)) {
                System.out.println("First position is unavailable! Please choose an available one.");
                continue;
            }

            if (!board.isValidPosition(to)) {
                System.out.println("Second position is invalid! Please choose a valid one.");
                continue;
            }

            if (!board.isAvailablePosition(to)) {
                System.out.println("Second position is unavailable! Please choose an available one.");
                continue;
            }

            place(to, player);
            break;
        }
    }

    private void removePiece(Player player) {
        while (true) {
            char[] position = enterPosition().toCharArray();

            if (!board.isValidPosition(position)) {
                System.out.println("This position is invalid! Please choose a valid one.");
                continue;
            }

            if (board.isAvailablePosition(position)) {
                System.out.println("This position is available! Please choose an available one.");
                continue;
            }

            Position positionColour = player.getPiecesColour() == Colour.WHITE ? Position.OCCUPIED_BY_WHITE_PLAYER : Position.OCCUPIED_BY_BLACK_PLAYER;
            int row = (int) position[1] - ONE_ASCII_CODE;
            int column = (int) position[0] - A_ASCII_CODE;
            if (board.getPosition(row, column) == positionColour) {
                System.out.println("This position is occupied by you. Please choose position occupied by the other player.");
                continue;
            }

            remove(position, player);
            break;
        }
    }

    private void remove(char[] position, Player player) {
        board.setBoard(position, Position.AVAILABLE);
        player.setPiecesOnBoard(player.getPiecesOnBoard() - 1);
    }

    private boolean hasMill(Colour playerColour, char[] position) {
        int row = (int) position[1] - ONE_ASCII_CODE;
        int column = (int) position[0] - A_ASCII_CODE;

        Position positionColour = playerColour == Colour.WHITE ? Position.OCCUPIED_BY_WHITE_PLAYER : Position.OCCUPIED_BY_BLACK_PLAYER;

        if (column != 3 && checkUp(positionColour, row, column)) {
            return true;
        } else if (column != 3 && checkDown(positionColour, row, column)) {
            return true;
        } else if (row != 3 && checkLeft(positionColour, row, column)) {
            return true;
        } else if (row != 3 && checkRight(positionColour, row, column)) {
            return true;
        } else if (column != 3 && checkUpAndDown(positionColour, row, column)) {
            return true;
        } else if (row != 3 && checkLeftAndRight(positionColour, row, column)) {
            return true;
        } else if (row == 3 && checkMiddleRow(positionColour, row, column)) {
            return true;
        } else if (column == 3 && checkMiddleColumn(positionColour, row, column)) {
            return true;
        }

        return false;
    }

    private boolean checkUp(Position position, int row, int column) {
        int countPieces = 0;

        for (int i = 1; 0 <= row - i && row - i < 7; i++) {
            if (board.getPosition(row - i, column) == position) {
                countPieces++;
            }
        }

        return countPieces == 2;
    }

    private boolean checkDown(Position position, int row, int column) {
        int countPieces = 0;
        for (int i = 1; row + i < 7; i++) {
            if (board.getPosition(row + i, column) == position) {
                countPieces++;
            }
        }

        return countPieces == 2;
    }

    private boolean checkLeft(Position position, int row, int column) {
        int countPieces = 0;
        for (int i = 1; 0 <= column - i && column - i < 7; i++) {
            if (board.getPosition(row, column - i) == position) {
                countPieces++;
            }
        }

        return countPieces == 2;
    }

    private boolean checkRight(Position position, int row, int column) {
        int countPieces = 0;
        for (int i = 1; column + i < 7; i++) {
            if (board.getPosition(row, column + i) == position) {
                countPieces++;
            }
        }

        return countPieces == 2;
    }

    private boolean checkUpAndDown(Position position, int row, int column) {
        int countPieces = 0;
        for (int i = 1; i < 4; i++) {
            if (0 <= row - i && row - i < 4 && board.getPosition(row - i, column) == position) {
                countPieces++;
            }

            if (row + i < 4 && board.getPosition(row + i, column) == position) {
                countPieces++;
            }
        }

        return countPieces == 2;
    }

    private boolean checkLeftAndRight(Position position, int row, int column) {
        int countPieces = 0;
        for (int i = 1; i < 4; i++) {
            if (0 <= column - i && column - i < 4 && board.getPosition(row, column - i) == position) {
                countPieces++;
            }

            if (column + i < 4 && board.getPosition(row, column + i) == position) {
                countPieces++;
            }
        }

        return countPieces == 2;
    }

    private boolean checkMiddleColumn(Position position, int row, int column) {
        int countPieces = 0;
        for (int i = 1; i < 3; i++) {
            if (0 <= row - i && row - i < 3 && board.getPosition(row - i, column) == position) {
                countPieces++;
            }

            if (row + i < 3 && board.getPosition(row + i, column) == position) {
                countPieces++;
            }
        }

        return countPieces == 2;
    }

    private boolean checkMiddleRow(Position position, int row, int column) {
        int countPieces = 0;
        for (int i = 1; i < 3; i++) {
            if (0 <= column - i && column - i < 3 && board.getPosition(row, column - i) == position) {
                countPieces++;
            }

            if (column + i < 3 && board.getPosition(row, column + i) == position) {
                countPieces++;
            }
        }

        return countPieces == 2;
    }

    private void winner(Player player) {
        System.out.println("The winner is the " + player.getPiecesColour() + " player!");
    }

    private Player chooseFirstPlayer(Player whitePlayer, Player blackPlayer) {
        return Math.random() < 0.5 ? whitePlayer : blackPlayer;
    }

    private String enterPosition() {
        System.out.println("Enter a position: ");

        Scanner in = new Scanner(System.in);

        return in.nextLine().toUpperCase();
    }
}