package de.adventofcode.chrisgw.day03;

import lombok.Getter;


public enum Direction {

    UP(0, 1, 'U'),  //
    RIGHT(1, 0, 'R'), //
    DOWN(0, -1, 'D'), //
    LEFT(-1, 0, 'L'); // clockwise order

    @Getter
    private final int dx;

    @Getter
    private final int dy;

    @Getter
    private final char directionLetter;


    Direction(int dx, int dy, char directionLetter) {
        this.dx = dx;
        this.dy = dy;
        this.directionLetter = directionLetter;
    }

    public static Direction forDirectionLetter(char directionLetter) {
        for (Direction direction : values()) {
            if (direction.getDirectionLetter() == directionLetter) {
                return direction;
            }
        }
        throw new IllegalArgumentException("Unknown Direction with letter: " + directionLetter);
    }


    public Direction rotateRight() {
        Direction[] directions = values();
        int nextIndex = (ordinal() + 1) % directions.length;
        return directions[nextIndex];
    }

    public Direction rotateLeft() {
        return rotateOpposite().rotateRight();
    }

    public Direction rotateOpposite() {
        return rotateRight().rotateRight();
    }

}
