package chrisgw.day08;

import lombok.Data;


@Data
public class JumpInstruction implements HandHeldBootInstruction {

    private final int argument;

    @Override
    public void execute(HandHeldGameConsole handHeldGameConsole) {
        handHeldGameConsole.jumpRelative(argument);
    }

}
