package de.adventofcode.chrisgw.day12;

import de.adventofcode.chrisgw.AdventOfCodePuzzle;
import lombok.Getter;
import lombok.Setter;

import java.time.Year;
import java.util.List;
import java.util.stream.Collectors;

import static de.adventofcode.chrisgw.day12.NavigationDirection.EAST;
import static de.adventofcode.chrisgw.day12.NavigationDirection.NORTH;


/**
 * https://adventofcode.com/2020/day/12
 */
@Getter
@Setter
public class FerryNavigationComputer extends AdventOfCodePuzzle {

    private final List<FerryNavigationInstruction> navigationInstructions;
    private int instructionPointer = 0;

    private int x = 0;
    private int y = 0;

    private int waypointX = 0;
    private int waypointY = 0;
    private NavigationDirection direction = EAST;


    public FerryNavigationComputer(List<String> inputLines) {
        super(Year.of(2020), 12, inputLines);
        navigationInstructions = inputLines.stream()
                .map(FerryNavigationComputer::parseInstruction)
                .collect(Collectors.toList());
        moveWaypointIntoStartingPosition();
    }

    private void moveWaypointIntoStartingPosition() {
        new FerryNavigationMoveInstruction(NORTH, 1).executeAsWaypointInstruction(this);
        new FerryNavigationMoveInstruction(EAST, 10).executeAsWaypointInstruction(this);
    }

    public static FerryNavigationInstruction parseInstruction(String instructionStr) {
        char code = instructionStr.charAt(0);
        int value = Integer.parseInt(instructionStr.substring(1));
        TurnDirection turnDirection = TurnDirection.fromCode(code);
        NavigationDirection navigationDdirection = NavigationDirection.fromCode(code);
        if (turnDirection != null) {
            return new FerryNavigationTurnInstruction(turnDirection, value);
        } else if (navigationDdirection != null) {
            return new FerryNavigationMoveInstruction(navigationDdirection, value);
        } else {
            throw new IllegalArgumentException("unknown code " + code);
        }
    }


    @Override
    public Integer solveFirstPart() {
        while (!terminated()) {
            FerryNavigationInstruction instruction = navigationInstructions.get(instructionPointer++);
            instruction.executeAsShipMoveInstruction(this);
        }
        return distanceFromStartingPoint();
    }

    @Override
    public Integer solveSecondPart() {
        while (!terminated()) {
            FerryNavigationInstruction instruction = navigationInstructions.get(instructionPointer++);
            instruction.executeAsWaypointInstruction(this);
        }
        return distanceFromStartingPoint();
    }

    private int distanceFromStartingPoint() {
        return Math.abs(x) + Math.abs(y);
    }


    public boolean terminated() {
        return instructionPointer >= navigationInstructions.size();
    }


    public void movePosition(int dx, int dy) {
        x += dx;
        y += dy;
    }

    public void moveWaypoint(int dx, int dy) {
        waypointX += dx;
        waypointY += dy;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof FerryNavigationComputer))
            return false;
        if (!super.equals(o))
            return false;

        FerryNavigationComputer that = (FerryNavigationComputer) o;
        if (getInstructionPointer() != that.getInstructionPointer())
            return false;
        if (getX() != that.getX())
            return false;
        if (getY() != that.getY())
            return false;
        if (getNavigationInstructions() != null ?
                !getNavigationInstructions().equals(that.getNavigationInstructions()) :
                that.getNavigationInstructions() != null)
            return false;
        return getDirection() == that.getDirection();
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getNavigationInstructions() != null ? getNavigationInstructions().hashCode() : 0);
        result = 31 * result + getInstructionPointer();
        result = 31 * result + getX();
        result = 31 * result + getY();
        result = 31 * result + (getDirection() != null ? getDirection().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return String.format("(%3d;%3d) - %s", getX(), getY(), getDirection());
    }

}
