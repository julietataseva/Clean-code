package bg.sofia.uni.fmi.cleancode.ninemensmorris;

import bg.sofia.uni.fmi.cleancode.ninemensmorris.enums.Colour;

import static bg.sofia.uni.fmi.cleancode.ninemensmorris.constants.Constants.MAX_PIECES;

public class Player {
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