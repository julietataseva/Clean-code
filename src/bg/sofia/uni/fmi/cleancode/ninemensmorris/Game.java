package bg.sofia.uni.fmi.cleancode.ninemensmorris;


public class Game {

    private Moves moves;

    public Game() {
        moves = new Moves();
    }

    public void play() {
        moves.placingPieces();
    }

}
