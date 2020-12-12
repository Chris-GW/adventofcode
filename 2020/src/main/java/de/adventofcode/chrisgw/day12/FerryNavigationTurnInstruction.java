package de.adventofcode.chrisgw.day12;

import lombok.Data;


@Data
public class FerryNavigationTurnInstruction implements FerryNavigationInstruction {

    private final TurnDirection turnDirection;
    private final int degree;


    @Override
    public void execute(FerryNavigationComputer navigationComputer) {
        NavigationDirection direction = navigationComputer.getDirection();
        int neededTurns = (degree % 360) / 90;
        for (int i = 0; i < neededTurns; i++) {
            direction = turnDirection.turn(direction);
        }
        navigationComputer.setDirection(direction);
    }


    @Override
    public String toString() {
        return turnDirection.getCode() + "" + getDegree();
    }

}
