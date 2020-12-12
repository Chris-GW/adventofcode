package de.adventofcode.chrisgw.day12;

import lombok.Data;

import static de.adventofcode.chrisgw.day12.TurnDirection.RIGHT;


@Data
public class FerryNavigationTurnInstruction implements FerryNavigationInstruction {

    private final TurnDirection turnDirection;
    private final int degree;


    @Override
    public void executeAsShipMoveInstruction(FerryNavigationComputer navigationComputer) {
        NavigationDirection direction = navigationComputer.getDirection();
        int neededTurns = (degree % 360) / 90;
        for (int i = 0; i < neededTurns; i++) {
            direction = turnDirection.turn(direction);
        }
        navigationComputer.setDirection(direction);
    }

    @Override
    public void executeAsWaypointInstruction(FerryNavigationComputer navigationComputer) {
        int neededTurns = (degree % 360) / 90;
        for (int i = 0; i < neededTurns; i++) {
            if (RIGHT.equals(turnDirection)) {
                rotateWaypoint90DegreeRight(navigationComputer);
            } else {
                rotateWaypoint90DegreeRight(navigationComputer);
                rotateWaypoint90DegreeRight(navigationComputer);
                rotateWaypoint90DegreeRight(navigationComputer);
            }
        }
    }

    private void rotateWaypoint90DegreeRight(FerryNavigationComputer navigationComputer) {
        int x = navigationComputer.getWaypointX();
        int y = navigationComputer.getWaypointY();
        navigationComputer.setWaypointX(-y);
        navigationComputer.setWaypointY(x);
    }


    @Override
    public String toString() {
        return turnDirection.getCode() + "" + getDegree();
    }

}
