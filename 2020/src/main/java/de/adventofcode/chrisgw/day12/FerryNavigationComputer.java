package de.adventofcode.chrisgw.day12;

import de.adventofcode.chrisgw.AdventOfCodePuzzle;
import lombok.Getter;
import lombok.Setter;

import java.time.Year;
import java.util.List;
import java.util.stream.Collectors;

import static de.adventofcode.chrisgw.day12.NavigationDirection.EAST;


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
    private NavigationDirection direction = EAST;


    public FerryNavigationComputer(List<String> inputLines) {
        super(Year.of(2020), 12, inputLines);
        navigationInstructions = inputLines.stream()
                .map(FerryNavigationComputer::parseInstruction)
                .collect(Collectors.toList());
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


    public void executeNextInstruction() {
        if (terminated()) {
            throw new IllegalStateException("is alread terminated");
        }
        FerryNavigationInstruction instruction = navigationInstructions.get(instructionPointer++);
        instruction.execute(this);
    }

    public boolean terminated() {
        return instructionPointer >= navigationInstructions.size();
    }


    @Override
    public Integer solveFirstPart() {
        while (!terminated()) {
            executeNextInstruction();
        }
        return distanceFromStartingPoint();
    }

    private int distanceFromStartingPoint() {
        return Math.abs(x) + Math.abs(y);
    }


    @Override
    public Integer solveSecondPart() {
        return 0;
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
