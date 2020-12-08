package chrisgw.day08;

import lombok.Data;


@Data
public class JumpInstruction implements HandHeldBootInstruction {

    private final int jumpAmount;

    @Override
    public void execute(HandHeldGameConsole handHeldGameConsole) {
        handHeldGameConsole.jumpRelative(jumpAmount);
    }

}
