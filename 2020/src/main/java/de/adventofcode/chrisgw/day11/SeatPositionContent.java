package de.adventofcode.chrisgw.day11;

public enum SeatPositionContent {

    FLOOR('.'), EMPTY_SEAT('L'), OCCUPIED_SEAT('#');

    private char symbol;

    SeatPositionContent(char symbol) {
        this.symbol = symbol;
    }

    public static SeatPositionContent fromSymbol(char symbol) {
        for (SeatPositionContent content : SeatPositionContent.values()) {
            if (content.getSymbol() == symbol) {
                return content;
            }
        }
        throw new IllegalArgumentException("unknown seat position symbol: " + symbol);
    }

    public char getSymbol() {
        return symbol;
    }

}
