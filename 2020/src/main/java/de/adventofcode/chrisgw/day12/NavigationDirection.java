package de.adventofcode.chrisgw.day12;

import lombok.Getter;

import java.util.Arrays;


@Getter
public enum NavigationDirection {

    NORTH('N', 0, -1), //
    EAST('E', 1, 0), //
    SOUTH('S', 0, 1), //
    WEST('W', -1, 0), //
    FORWARD('F', 0, 0); //


    private final char code;
    private final int dx;
    private final int dy;


    NavigationDirection(char code, int dx, int dy) {
        this.code = code;
        this.dx = dx;
        this.dy = dy;
    }

    public static NavigationDirection fromCode(char code) {
        return Arrays.stream(NavigationDirection.values())
                .filter(navigationDirection -> navigationDirection.getCode() == code)
                .findAny()
                .orElse(null);
    }


    public NavigationDirection left() {
        switch (this) {
        case NORTH:
            return WEST;
        case EAST:
            return NORTH;
        case SOUTH:
            return EAST;
        case WEST:
            return SOUTH;
        case FORWARD:
        default:
            throw new IllegalArgumentException("unknown direction: " + this);
        }
    }

    public NavigationDirection right() {
        return left().left().left();
    }

}
