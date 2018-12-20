package de.adventofcode.chrisgw.day20;

import lombok.Getter;


@Getter
public enum FacilityRouteDirection {

    NORTH(0, 1), EAST(1, 0), SOUTH(0, -1), WEST(-1, 0);

    int dx;
    int dy;


    FacilityRouteDirection(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public static FacilityRouteDirection valueOf(char directionLetter) {
        for (FacilityRouteDirection direction : values()) {
            if (direction.letter().equalsIgnoreCase(String.valueOf(directionLetter))) {
                return direction;
            }
        }
        throw new IllegalArgumentException("Unexpected direction letter: " + directionLetter);
    }


    public FacilityRouteDirection oppositeDirection() {
        FacilityRouteDirection[] directions = values();
        int oppositeDirectionIndex = ordinal() + directions.length / 2;
        return directions[oppositeDirectionIndex % directions.length];
    }

    public String letter() {
        return name().substring(0, 1);
    }

}
