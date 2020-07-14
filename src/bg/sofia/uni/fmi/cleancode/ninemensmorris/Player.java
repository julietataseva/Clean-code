package bg.sofia.uni.fmi.cleancode.ninemensmorris;

import bg.sofia.uni.fmi.cleancode.ninemensmorris.enums.Colour;

import java.util.Scanner;

public class Player {
    private final int MAX_PIECES = 9;

    private int unplacedPieces;
    private int piecesOnBoard;
    private Colour piecesColour;

    public Player(Colour piecesColour) {
        unplacedPieces = MAX_PIECES;
        piecesOnBoard = 0;
        this.piecesColour = piecesColour;
    }


    public int getUnplacedPieces() {
        return unplacedPieces;
    }

    public void setUnplacedPieces(int unplacedPieces) {
        this.unplacedPieces = unplacedPieces;
    }

    public int getPiecesOnBoard() {
        return piecesOnBoard;
    }

    public void setPiecesOnBoard(int piecesOnBoard) {
        this.piecesOnBoard = piecesOnBoard;
    }

    public Colour getPiecesColour() {
        return piecesColour;
    }

    public boolean canFly() {
        return piecesOnBoard == 3 && unplacedPieces == 0;
    }
}
