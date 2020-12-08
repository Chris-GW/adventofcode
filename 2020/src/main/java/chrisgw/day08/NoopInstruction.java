package chrisgw.day08;

import lombok.Data;


@Data
public class NoopInstruction implements HandHeldBootInstruction {

    private final int argumentValue;


    @Override
    public void execute(HandHeldGameConsole handHeldGameConsole) {
        handHeldGameConsole.incrementInstructionPointer();
    }

}
