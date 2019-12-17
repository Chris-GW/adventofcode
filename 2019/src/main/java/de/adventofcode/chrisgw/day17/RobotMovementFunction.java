package de.adventofcode.chrisgw.day17;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Data
public class RobotMovementFunction {

    private final String functionName;
    private final List<RobotMovement> robotMovements = new ArrayList<>();


    public void addRobotMovement(RobotMovement robotMovement) {
        robotMovements.add(robotMovement);
    }

    public RobotMovementFunction addRobotMovements(RobotMovementFunction otherRobotMovementFunction) {
        String combinedFunctionName = this.getFunctionName() + ", " + otherRobotMovementFunction.getFunctionName();
        RobotMovementFunction combinedMovementFunction = new RobotMovementFunction(combinedFunctionName);
        combinedMovementFunction.robotMovements.addAll(this.getRobotMovements());
        combinedMovementFunction.robotMovements.addAll(otherRobotMovementFunction.getRobotMovements());
        return combinedMovementFunction;
    }


    public String toAsciiCode() {
        return robotMovements.stream().map(RobotMovement::toString).collect(Collectors.joining(","));
    }


    public int memorySize() {
        return robotMovements.size();
    }

    public boolean isFull() {
        return memorySize() > 20;
    }


}
