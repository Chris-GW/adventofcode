package de.adventofcode.chrisgw.day11;

public enum Direction {

    NORTH(0, -1), //
    EAST(1, 0), //
    SOUTH(0, 1), //
    WEST(-1, 0), //
    NORTH_EAST(EAST.dx, NORTH.dy), //
    NORTH_WEST(WEST.dx, NORTH.dy), //
    SOTUH_EAST(EAST.dx, SOUTH.dy), //
    SOUTH_WEST(WEST.dx, SOUTH.dy);

    private final int dx;
    private final int dy;


    Direction(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }


    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }


    public boolean isDiagonalDirection() {
        return dx != 0 && dy != 0;
    }


}
