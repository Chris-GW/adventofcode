package de.adventofcode.chrisgw.day16;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.stream.Collectors;


public class ChronalClassification {

    private final List<DeviceRegisterInstruction> instructions = new ArrayList<>();


    public ChronalClassification() {
        instructions.addAll(registerInstructions("add", (a, b) -> a + b));  // addition
        instructions.addAll(registerInstructions("mul", (a, b) -> a * b));  // multiplication
        instructions.addAll(registerInstructions("ban", (a, b) -> a & b));  // bitwise and
        instructions.addAll(registerInstructions("bor", (a, b) -> a | b));  // bitwise or

        // assignment
        IntBinaryOperator assignmentOperator = (a, b) -> a;
        instructions.add(new DeviceRegisterInstruction("seti", assignmentOperator, false, false));
        instructions.add(new DeviceRegisterInstruction("setr", assignmentOperator, true, false));

        // greater-than testing
        IntBinaryOperator greaterThanTestingOperator = (a, b) -> a > b ? 1 : 0;
        instructions.addAll(registerInstructions("gtr", greaterThanTestingOperator));
        instructions.add(new DeviceRegisterInstruction("gtir", greaterThanTestingOperator, false, true));

        // equality testing
        IntBinaryOperator equalityTestingOperator = (a, b) -> a == b ? 1 : 0;
        instructions.addAll(registerInstructions("eqr", equalityTestingOperator));
        instructions.add(new DeviceRegisterInstruction("eqir", greaterThanTestingOperator, false, true));
    }

    private List<DeviceRegisterInstruction> registerInstructions(String name, IntBinaryOperator operator) {
        DeviceRegisterInstruction immideatlyRegisterInstruction = new DeviceRegisterInstruction( //
                name + "i", operator, true, false);
        DeviceRegisterInstruction referenceRegisterInstruction = new DeviceRegisterInstruction( //
                name + "r", operator, true, true);
        return Arrays.asList(immideatlyRegisterInstruction, referenceRegisterInstruction);
    }


    public Map<DeviceOperationSample, Set<DeviceRegisterInstruction>> mapOpCodeToRegisterInstructions(
            List<DeviceOperationSample> operationSamples) {
        return operationSamples.stream()
                .collect(Collectors.toMap(Function.identity(), this::collectPossibleInstructionsForOperationSample));
    }

    private Set<DeviceRegisterInstruction> collectPossibleInstructionsForOperationSample(
            DeviceOperationSample deviceOperationSample) {
        return instructions.stream()
                .filter(deviceOperationSample::matchesRegisterInstruction)
                .collect(Collectors.toSet());
    }


    public Optional<DeviceRegisterInstruction> registerInstructionForName(String operationName) {
        return instructions.stream()
                .filter(registerInstruction -> registerInstruction.getOperationName().equalsIgnoreCase(operationName))
                .findAny();
    }


    public List<DeviceRegisterInstruction> getInstructions() {
        return Collections.unmodifiableList(instructions);
    }


    public static void main(String[] args) throws Exception {
        if (args.length < 1) {
            System.out.println("Usage <puzzleInputFile>");
            return;
        }
        Path puzzleInputFile = Paths.get(args[0]);

    }

}
