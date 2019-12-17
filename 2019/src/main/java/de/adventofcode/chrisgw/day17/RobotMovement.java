package de.adventofcode.chrisgw.day17;

import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;


@Data
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class RobotMovement {

    private final RobotMovementType movementType;
    private final int units;


    public static RobotMovement forward(int units) {
        return new RobotMovement(RobotMovementType.FORWARD, units);
    }

    public static RobotMovement left() {
        return new RobotMovement(RobotMovementType.LEFT, 1);
    }

    public static RobotMovement right() {
        return new RobotMovement(RobotMovementType.RIGHT, 1);
    }


    public String toAsciiCode() {
        switch (getMovementType()) {
        case FORWARD:
            return String.valueOf(units);
        case LEFT:
            return "L";
        case RIGHT:
            return "R";
        default:
            throw new IllegalArgumentException("Unknown robotMovementType: " + getMovementType());
        }
    }


    public enum RobotMovementType {

        FORWARD, LEFT, RIGHT;

    }

}
