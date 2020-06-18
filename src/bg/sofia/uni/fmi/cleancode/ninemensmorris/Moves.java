package bg.sofia.uni.fmi.cleancode.ninemensmorris;


import java.util.Scanner;

public class Moves {

    public void placingPieces(Board board, Player firstPlayer, Player secondPlayer) {

        while (firstPlayer.getUnplacedPieces() > 0 && secondPlayer.getUnplacedPieces() > 0) {
            placePiece(board, firstPlayer);

            if (hasMill(board, firstPlayer)) {
                removePiece(board, firstPlayer);
            }

            placePiece(board, secondPlayer);

            if (hasMill(board, secondPlayer)) {
                removePiece(board, secondPlayer);
            }
        }

        movingPieces(board, firstPlayer, secondPlayer);
    }

    private void placePiece(Board board, Player player) {
        char[] position = enterPosition();

        if (board.isValidPosition(position)) { //TODO: check if the position is available
            place(position, player);
        } else {
            System.out.println("This position is invalid or unavailable!");
        }
    }

    private void place(char[] position, Player player) {
        if (player.getPiecesColour().equals("white")) {
            //TODO: turn "." into "○" on the board
        } else if (player.getPiecesColour().equals("black")) {
            //TODO: turn "." into "●" on the board
        }
    }


    public void movingPieces(Board board, Player firstPlayer, Player secondPlayer) {

        while (firstPlayer.getPiecesOnBoard() > 3 && secondPlayer.getPiecesOnBoard() > 3) {
            movePiece(board, firstPlayer);

            if (hasMill(board, firstPlayer)) {
                removePiece(board, firstPlayer);
            }

            movePiece(board, secondPlayer);

            if (hasMill(board, secondPlayer)) {
                removePiece(board, secondPlayer);
            }
        }

        flying(board, firstPlayer, secondPlayer);
    }


    public void flying(Board board, Player firstPlayer, Player secondPlayer) {
        boolean firstPlayerCanFly = firstPlayer.getPiecesOnBoard() > 3;
        boolean secondPlayerCanFly = secondPlayer.getPiecesOnBoard() > 3;

        while (firstPlayer.getPiecesOnBoard() >= 3 && secondPlayer.getPiecesOnBoard() >= 3) {
            if (firstPlayerCanFly) {
                while (!secondPlayerCanFly) {
                    fly(board, firstPlayer);

                    if (hasMill(board, firstPlayer)) {
                        removePiece(board, firstPlayer);

                        if(secondPlayer.getPiecesOnBoard() == 3){
                            secondPlayerCanFly = true;
                        }
                    }

                    movePiece(board, secondPlayer);

                    if (hasMill(board, secondPlayer)) {
                        removePiece(board, secondPlayer);
                    }
                }
            } else if (secondPlayerCanFly) {
                while (!firstPlayerCanFly) {
                    movePiece(board, firstPlayer);

                    if (hasMill(board, firstPlayer)) {
                        removePiece(board, firstPlayer);
                    }

                    fly(board, secondPlayer);

                    if (hasMill(board, secondPlayer)) {
                        removePiece(board, secondPlayer);

                        if(firstPlayer.getPiecesOnBoard() == 3){
                            firstPlayerCanFly = true;
                        }
                    }
                }
            }

            fly(board, firstPlayer);

            if (hasMill(board, firstPlayer)) {
                removePiece(board, firstPlayer);
            }

            fly(board, secondPlayer);

            if (hasMill(board, secondPlayer)) {
                removePiece(board, secondPlayer);
            }
        }
    }

    private void movePiece(Board board, Player player) {
        char[] from;
        char[] to;

        if (player.getPiecesColour().equals("white")) {
            //TODO
        } else if (player.getPiecesColour().equals("black")) {
            //TODO
        }
    }

    private void fly(Board board, Player player) {
        char[] position = enterPosition();

        if (board.isValidPosition(position)) { //TODO: check if the position is available
            place(position, player);
        } else {
            System.out.println("This position is invalid or unavailable!");
        }
    }

    private void removePiece(Board board, Player player) {
        char[] position = enterPosition();

        if (board.isValidPosition(position)) { //TODO: check if the position is taken by the other player
            remove(position, player);
        } else {
            System.out.println("This position is invalid or there is no piece on it!");
        }
    }

    private void remove(char[] position, Player player) {
        if (player.getPiecesColour().equals("white")) {
            //TODO: turn "●" into "." on the board
        } else if (player.getPiecesColour().equals("black")) {
            //TODO: turn "○" into "."  on the board
        }

        player.setPiecesOnBoard(player.getPiecesOnBoard() - 1);
    }


    private char[] enterPosition() {
        System.out.println("Enter a position: ");

        Scanner in = new Scanner(System.in);

        return in.nextLine().toCharArray();
    }


    private boolean hasMill(Board board, Player player) {
        //TODO: check for a mill
        return true;
    }

}
