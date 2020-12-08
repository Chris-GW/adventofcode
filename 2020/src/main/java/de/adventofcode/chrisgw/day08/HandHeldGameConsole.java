package de.adventofcode.chrisgw.day08;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class HandHeldGameConsole {

    private List<HandHeldBootInstruction> bootProgram;
    private int accumulator = 0;
    private int instructionPointer = 0;


    public HandHeldGameConsole(List<HandHeldBootInstruction> bootProgram) {
        this.bootProgram = bootProgram;
    }


    public int runBootProgram() {
        Set<Integer> ranInstruction = new HashSet<>(bootProgram.size());
        while (!isBootProgramTerminated()) {
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


    public boolean isBootProgramTerminated() {
        return instructionPointer >= bootProgram.size();
    }

    public void reset() {
        instructionPointer = 0;
        accumulator = 0;
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


    public List<HandHeldBootInstruction> getBootProgram() {
        return new ArrayList<>(bootProgram);
    }

    public void setBootProgram(List<HandHeldBootInstruction> bootProgram) {
        this.bootProgram = bootProgram;
        reset();
    }

}
