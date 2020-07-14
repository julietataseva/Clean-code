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

        while (firstPlayer.getUnplacedPieces() > 0 && secondPlayer.getUnplacedPieces() > 0) {
            placePiece(firstPlayer);

            placePiece(secondPlayer);
        }

        movingPieces();
    }


    public void movingPieces() {

        while (firstPlayer.getPiecesOnBoard() > 3 && secondPlayer.getPiecesOnBoard() > 3) {
            movePiece(firstPlayer);

            movePiece(secondPlayer);
        }

        flying();
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

            if (board.isValidPosition(position)) {
                place(position, player);

                break;
            } else {
                System.out.println("This position is invalid or unavailable! Please try again.");
            }
        }
    }


    private void place(char[] position, Player player) {
        int row = (int) position[1] - ONE_ASCII_CODE;
        int column = (int) position[0] - A_ASCII_CODE;

        if (player.getPiecesColour() == Colour.WHITE) {
            board.setBoard(row, column, Position.OCCUPIED_BY_WHITE_PLAYER);
        } else if (player.getPiecesColour() == Colour.BLACK) {
            board.setBoard(row, column, Position.OCCUPIED_BY_BLACK_PLAYER);
        }

        if (hasMill(player.getPiecesColour(), row, column)) {
            removePiece(player == firstPlayer ? secondPlayer : firstPlayer);
        }
    }


    private void movePiece(Player player) {
        String position = enterPosition();
        char[] from = position.substring(0, 2).toCharArray();
        char[] to = position.substring(2, 4).toCharArray();

        if (!board.isValidPosition(from)) {
            throw new InvalidPositionException("First position is invalid or unavailable!");
        }

        if (!board.isValidPosition(to)) {
            throw new InvalidPositionException("Second position is invalid or unavailable!");
        }

        move(player, from, to);
    }


    private void move(Player player, char[] from, char[] to) {
        int fromRow = (int) from[1] - ONE_ASCII_CODE;
        int fromColumn = (int) from[0] - A_ASCII_CODE;
        int toRow = (int) to[1] - ONE_ASCII_CODE;
        int toColumn = (int) to[0] - A_ASCII_CODE;

        //TODO: check if the position is adjacent

        if (!positionIsAdjacent(fromRow, fromColumn, toRow, toColumn)) {
            throw new NotAdjacentPositionException("The position is not adjacent. Please enter adjacent position!");
        }

        if (player.getPiecesColour() == Colour.WHITE) {
            board.setBoard(toRow, toColumn, Position.OCCUPIED_BY_WHITE_PLAYER);
        } else if (player.getPiecesColour() == Colour.BLACK) {
            board.setBoard(toRow, toColumn, Position.OCCUPIED_BY_BLACK_PLAYER);
        }

        if (hasMill(player.getPiecesColour(), toRow, toColumn)) {
            removePiece(player == firstPlayer ? secondPlayer : firstPlayer);
        }
    }


    private void fly(Player player) {
        char[] position = enterPosition().toCharArray();

        if (board.isValidPosition(position)) {
            place(position, player);
        }
    }


    private void removePiece(Player player) {
        char[] position = enterPosition().toCharArray();

        if (board.isValidPosition(position)) {
            remove(position, player);
        }
    }


    private void remove(char[] position, Player player) {
        int row = (int) position[1] - ONE_ASCII_CODE;
        int column = (int) position[0] - A_ASCII_CODE;

        board.setBoard(row, column, Position.AVAILABLE);

        player.setPiecesOnBoard(player.getPiecesOnBoard() - 1);
    }


    private String enterPosition() {
        System.out.println("Enter a position: ");

        Scanner in = new Scanner(System.in);

        return in.nextLine();
    }


    private boolean hasMill(Colour playerColour, int row, int column) {
        Position position = playerColour == Colour.WHITE ? Position.OCCUPIED_BY_WHITE_PLAYER : Position.OCCUPIED_BY_BLACK_PLAYER;

        if (checkUp(position, row, column)) {
            return true;
        } else if (checkDown(position, row, column)) {
            return true;
        } else if (checkLeft(position, row, column)) {
            return true;
        } else if (checkRight(position, row, column)) {
            return true;
        } else if(checkUpAndDown(position, row,column)){
            return true;
        }else if(checkLeftAndRight(position,row,column)){
            return true;
        }

        return false;
    }

    private boolean checkUp(Position position, int row, int column) {
        int countPieces = 0;
        for (int i = 1; i < 7; i++) {
            if (board.getPosition(row - i, column) == position) {
                countPieces++;
            }
        }

        return countPieces == 2;
    }

    private boolean checkDown(Position position, int row, int column) {
        int countPieces = 0;
        for (int i = 1; i < 7; i++) {
            if (board.getPosition(row + i, column) == position) {
                countPieces++;
            }
        }

        return countPieces == 2;
    }

    private boolean checkLeft(Position position, int row, int column) {
        int countPieces = 0;
        for (int i = 1; i < 7; i++) {
            if (board.getPosition(row, column - i) == position) {
                countPieces++;
            }
        }

        return countPieces == 2;
    }

    private boolean checkRight(Position position, int row, int column) {
        int countPieces = 0;
        for (int i = 1; i < 7; i++) {
            if (board.getPosition(row, column + i) == position) {
                countPieces++;
            }
        }

        return countPieces == 2;
    }

    private boolean checkUpAndDown(Position position, int row, int column) {
        int countPieces = 0;
        for (int i = 1; i < 4; i++) {
            if (board.getPosition(row - i, column) == position) {
                countPieces++;
            }

            if (board.getPosition(row + i, column) == position) {
                countPieces++;
            }
        }

        return countPieces == 2;
    }

    private boolean checkLeftAndRight(Position position, int row, int column) {
        int countPieces = 0;
        for (int i = 1; i < 4; i++) {
            if (board.getPosition(row, column - i) == position) {
                countPieces++;
            }

            if (board.getPosition(row, column + i) == position) {
                countPieces++;
            }
        }

        return countPieces == 2;
    }


    private boolean positionIsAdjacent(int fromRow, int fromColumn, int row, int column) {
        return false;
    }


    private void winner(Player player) {
        System.out.println("The winner is the " + player.getPiecesColour() + " player!");
    }


    private Player chooseFirstPlayer(Player whitePlayer, Player blackPlayer) {
        return Math.random() < 0.5 ? whitePlayer : blackPlayer;
    }
}
