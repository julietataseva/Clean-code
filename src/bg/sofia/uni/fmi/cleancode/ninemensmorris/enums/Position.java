package bg.sofia.uni.fmi.cleancode.ninemensmorris.enums;

public enum Position {
    UNAVAILABLE(-1),
    AVAILABLE(0),
    OCCUPIED_BY_WHITE_PLAYER(1),
    OCCUPIED_BY_BLACK_PLAYER(2);

    private final int value;

    Position(int value) {
        this.value = value;
    }

    public char getPiece() {
        switch (this) {
            case OCCUPIED_BY_WHITE_PLAYER:
                return '○';
            case OCCUPIED_BY_BLACK_PLAYER:
                return '●';
            default:
                return '·';
        }
    }
}