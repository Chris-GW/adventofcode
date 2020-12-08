package chrisgw.day08;

import lombok.Data;


@Data
public class AccumulatorInstruction implements HandHeldBootInstruction {

    private final int amount;

    @Override
    public void execute(HandHeldGameConsole handHeldGameConsole) {
        handHeldGameConsole.addToAccumulator(amount);
        handHeldGameConsole.incrementInstructionPointer();
    }

}
