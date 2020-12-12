package de.adventofcode.chrisgw.day12;

import lombok.Data;

import static de.adventofcode.chrisgw.day12.NavigationDirection.FORWARD;


@Data
public class FerryNavigationMoveInstruction implements FerryNavigationInstruction {

    private final NavigationDirection navigationDirection;
    private final int value;


    @Override
    public void execute(FerryNavigationComputer navigationComputer) {
        NavigationDirection moveDirection = navigationDirection;
        if (FORWARD.equals(moveDirection)) {
            moveDirection = navigationComputer.getDirection();
        }
        int x = navigationComputer.getX() + (moveDirection.getDx() * value);
        int y = navigationComputer.getY() + (moveDirection.getDy() * value);
        navigationComputer.setX(x);
        navigationComputer.setY(y);
    }


    @Override
    public String toString() {
        return navigationDirection.getCode() + "" + getValue();
    }

}
