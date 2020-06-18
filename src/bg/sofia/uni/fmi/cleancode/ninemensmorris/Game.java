package bg.sofia.uni.fmi.cleancode.ninemensmorris;


public class Game {
    private Board board;
    private Player whitePlayer;
    private Player blackPlayer;
    private Moves moves;

    public Game() {
        board = new Board();
        whitePlayer = new Player("white");
        blackPlayer = new Player("black");
        moves = new Moves();
    }

    public void play() {
        Player firstPlayer = chooseTheFirstPlayer();
        Player secondPlayer = firstPlayer.getPiecesColour().equals(whitePlayer.getPiecesColour()) ? blackPlayer : whitePlayer;

        moves.placingPieces(board, firstPlayer, secondPlayer);
    }

    private Player chooseTheFirstPlayer() {
        return Math.random() < 0.5 ? whitePlayer : blackPlayer;
    }
}
