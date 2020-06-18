package com.juli;

import java.util.Scanner;

public class Player {
    private final int MAX_PIECES = 9;

    private int unplacedPieces;
    private int piecesOnBoard;
    private String piecesColour;

    public Player(String piecesColour) {
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

    public String getPiecesColour() {
        return piecesColour;
    }


}
