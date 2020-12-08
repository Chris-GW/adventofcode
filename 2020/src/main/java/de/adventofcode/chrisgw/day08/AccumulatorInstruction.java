package de.adventofcode.chrisgw.day08;

import lombok.Data;


@Data
public class AccumulatorInstruction implements HandHeldBootInstruction {

    private final int argument;

    @Override
    public void execute(HandHeldGameConsole handHeldGameConsole) {
        handHeldGameConsole.addToAccumulator(argument);
        handHeldGameConsole.incrementInstructionPointer();
    }

}
