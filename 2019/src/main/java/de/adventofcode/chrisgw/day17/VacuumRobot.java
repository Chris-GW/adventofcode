package de.adventofcode.chrisgw.day17;

import de.adventofcode.chrisgw.day03.Direction;
import de.adventofcode.chrisgw.day17.RobotMovement.RobotMovementType;
import lombok.Data;

import static de.adventofcode.chrisgw.day17.RobotMovement.RobotMovementType.*;


@Data
public class VacuumRobot {

    private final AdventOfCodeDay17 camera;

    private int column;
    private int row;
    private Direction direction;


    public RobotMovementFunction followScaffold() {
        findVacuumRobotOnCameraImage();
        RobotMovementFunction completePath = new RobotMovementFunction("completePath");
        // TODO followScaffold
        return completePath;
    }

    private void findVacuumRobotOnCameraImage() {
        for (int row = 0; row < camera.cameraImageHeight(); row++) {
            for (int column = 0; column < camera.cameraImageWidth() - 1; column++) {
                CameraPixel cameraPixel = camera.cameraPixelAt(row, column);
                if (cameraPixel.isRobotPixel()) {
                    this.row = row;
                    this.column = column;
                    this.direction = toDirection(cameraPixel);
                    return;
                }
            }
        }
        throw new IllegalStateException("Could not find vacuum robot on cameraImage: " + camera);
    }


    public void executeMovement(RobotMovement movement) {
        RobotMovementType movementType = movement.getMovementType();
        if (FORWARD.equals(movementType)) {
            moveForward(movement.getUnits());
        } else if (LEFT.equals(movementType)) {
            turnLeft();
        } else if (RIGHT.equals(movementType)) {
            turnRight();
        } else {
            throw new IllegalArgumentException("Unknown movement: " + movement);
        }
    }

    private void moveForward(int units) {
        this.column += direction.getDx() * units;
        this.row += direction.getDy() * units;
    }

    private void turnLeft() {
        this.direction = this.direction.rotateLeft();
    }

    private void turnRight() {
        this.direction = this.direction.rotateRight();
    }


    public static Direction toDirection(CameraPixel cameraPixel) {
        switch (cameraPixel) {
        case ROBOT_UP:
            return Direction.UP;
        case ROBOT_RIGHT:
            return Direction.RIGHT;
        case ROBOT_DOWN:
            return Direction.DOWN;
        case ROBOT_LEFT:
            return Direction.LEFT;
        case SCAFFOLD:
        case SCAFFOLD_INTERSECTION:
        case EMPTY:
        default:
            throw new IllegalArgumentException("Given cameraPixel isnt robot" + cameraPixel);
        }
    }

}
