package chrisgw.day08;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class HandHeldGameConsole {

    private final List<HandHeldBootInstruction> bootProgram;
    private int accumulator = 0;
    private int instructionPointer = 0;


    public HandHeldGameConsole(List<HandHeldBootInstruction> bootProgram) {
        this.bootProgram = bootProgram;
    }


    public int runBootProgram() {
        Set<Integer> ranInstruction = new HashSet<>(bootProgram.size());
        while (instructionPointer < bootProgram.size()) {
            boolean isNewInstructionIndex = ranInstruction.add(instructionPointer);
            if (isNewInstructionIndex) {
                HandHeldBootInstruction nextInstruction = bootProgram.get(instructionPointer);
                nextInstruction.execute(this);
            } else {
                // boot loop detected
                break;
            }
        }
        return accumulator;
    }


    public void addToAccumulator(int amount) {
        accumulator += amount;
    }


    public void jumpRelative(int jumpAmount) {
        instructionPointer += jumpAmount;
    }

    public void incrementInstructionPointer() {
        jumpRelative(+1);
    }

}
