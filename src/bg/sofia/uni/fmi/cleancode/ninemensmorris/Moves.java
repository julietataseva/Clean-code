package bg.sofia.uni.fmi.cleancode.ninemensmorris;


import bg.sofia.uni.fmi.cleancode.ninemensmorris.enums.Colour;
import bg.sofia.uni.fmi.cleancode.ninemensmorris.enums.Position;
import bg.sofia.uni.fmi.cleancode.ninemensmorris.exceptions.InvalidPositionException;
import bg.sofia.uni.fmi.cleancode.ninemensmorris.exceptions.NotAdjacentPositionException;

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
            placePiece(firstPlayer);
            placePiece(secondPlayer);
        }
    }

    public void movingPieces() {
        while (firstPlayer.getPiecesOnBoard() > 3 && secondPlayer.getPiecesOnBoard() > 3) {
            movePiece(firstPlayer);
            movePiece(secondPlayer);
        }
    }

    public void flying() {
        while (firstPlayer.getPiecesOnBoard() >= 3 && secondPlayer.getPiecesOnBoard() >= 3) {
            if (firstPlayer.canFly()) {
                while (!secondPlayer.canFly()) {
                    fly(firstPlayer);

                    if (secondPlayer.canFly()) {
                        break;
                    }

                    movePiece(secondPlayer);
                }
            } else if (secondPlayer.canFly()) {
                while (!firstPlayer.canFly()) {
                    movePiece(firstPlayer);

                    fly(secondPlayer);

                    if (firstPlayer.canFly()) {
                        break;
                    }
                }
            }

            fly(firstPlayer);

            if (secondPlayer.getPiecesOnBoard() < 3) {
                winner(firstPlayer);
            }

            fly(secondPlayer);

            if (firstPlayer.getPiecesOnBoard() < 3) {
                winner(secondPlayer);
            }
        }
    }

    private void placePiece(Player player) {
        while (true) {
            char[] position = enterPosition().toCharArray();

            if (board.isValidPosition(position) && board.isAvailablePosition(position)) {
                place(position, player);
                break;
            } else {
                System.out.println("This position is invalid or unavailable! Please try again.");
            }
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
            removePiece(player == firstPlayer ? secondPlayer : firstPlayer);
            board.print();
        }
    }

    private void movePiece(Player player) {
        String position = enterPosition();

        if (!positionIsAdjacent(position)) {
            System.out.println("The position is not adjacent. Please enter adjacent position!");
        }

        char[] from = position.substring(0, 2).toCharArray();
        char[] to = position.substring(2, 4).toCharArray();

        if (!board.isValidPosition(from)) {
            System.out.println("First position is invalid! Please try again.");
        }

        if (board.isAvailablePosition(from)) {
            System.out.println("First position is unavailable! Please try again.");
        }

        if (!board.isValidPosition(to)) {
            throw new InvalidPositionException("Second position is invalid! Please try again.");
        }

        if (board.isAvailablePosition(to)) {
            System.out.println("Second position is unavailable! Please try again.");
        }

        place(to, player);
    }

    private void fly(Player player) {
        while (true) {
            char[] position = enterPosition().toCharArray();

            if (board.isValidPosition(position) && board.isAvailablePosition(position)) {
                place(position, player);
                break;
            } else {
                System.out.println("This position is invalid or unavailable! Please try again.");
            }
        }
    }

    private void removePiece(Player player) {
        while (true) {
            char[] position = enterPosition().toCharArray();

            if (board.isValidPosition(position) && !board.isAvailablePosition(position)) {

                while (hasMill(player.getPiecesColour(), position)) {
                    position = enterPosition().toCharArray();
                }
                remove(position, player);
                break;
            } else {
                System.out.println("This position is invalid or unavailable! Please try again.");
            }
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

        if (checkUp(positionColour, row, column)) {
            return true;
        } else if (checkDown(positionColour, row, column)) {
            return true;
        } else if (checkLeft(positionColour, row, column)) {
            return true;
        } else if (checkRight(positionColour, row, column)) {
            return true;
        } else if (checkUpAndDown(positionColour, row, column)) {
            return true;
        } else return checkLeftAndRight(positionColour, row, column);
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

    private boolean positionIsAdjacent(String position) {
        String from = position.substring(0, 2);
        String to = position.substring(2, 4);

        return board.getNeighbours().get(from).contains(to);
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
