package de.adventofcode.chrisgw.day12;

import lombok.Data;

import static de.adventofcode.chrisgw.day12.NavigationDirection.FORWARD;


@Data
public class FerryNavigationMoveInstruction implements FerryNavigationInstruction {

    private final NavigationDirection navigationDirection;
    private final int value;


    @Override
    public void executeAsShipMoveInstruction(FerryNavigationComputer navigationComputer) {
        NavigationDirection moveDirection = navigationDirection;
        if (FORWARD.equals(moveDirection)) {
            moveDirection = navigationComputer.getDirection();
        }
        int dx = moveDirection.getDx() * value;
        int dy = moveDirection.getDy() * value;
        navigationComputer.movePosition(dx, dy);
    }


    @Override
    public void executeAsWaypointInstruction(FerryNavigationComputer navigationComputer) {
        NavigationDirection moveDirection = navigationDirection;
        if (FORWARD.equals(moveDirection)) {
            int dx = navigationComputer.getWaypointX() * value;
            int dy = navigationComputer.getWaypointY() * value;
            navigationComputer.movePosition(dx, dy);
        } else {
            int dx = moveDirection.getDx() * value;
            int dy = moveDirection.getDy() * value;
            navigationComputer.moveWaypoint(dx, dy);
        }
    }


    @Override
    public String toString() {
        return navigationDirection.getCode() + "" + getValue();
    }

}
