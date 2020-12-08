package chrisgw.day08;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


/**
 * https://adventofcode.com/2020/day/8
 */
public class AdventOfCodeDay08 {

    public static final Pattern BOOT_PROGRAM_INSTRUCTION_PATTERN = Pattern.compile("(nop|acc|jmp) ([+-]\\d+)");

    private final HandHeldGameConsole handHeldGameConsole;


    private AdventOfCodeDay08(HandHeldGameConsole handHeldGameConsole) {
        this.handHeldGameConsole = handHeldGameConsole;
    }


    public static AdventOfCodeDay08 parseHandHeldGameConsoleBootProgram(List<String> bootProgramLines) {
        List<HandHeldBootInstruction> bootProgram = bootProgramLines.stream()
                .map(AdventOfCodeDay08::parseBootProgramInstruction)
                .collect(Collectors.toList());
        HandHeldGameConsole handHeldGameConsole = new HandHeldGameConsole(bootProgram);
        return new AdventOfCodeDay08(handHeldGameConsole);
    }

    private static HandHeldBootInstruction parseBootProgramInstruction(String bootProgramLine) {
        Matcher matcher = BOOT_PROGRAM_INSTRUCTION_PATTERN.matcher(bootProgramLine);
        if (matcher.matches()) {
            String operationCode = matcher.group(1);
            int argument = Integer.parseInt(matcher.group(2));
            if ("nop".equals(operationCode)) {
                return new NoopInstruction(argument);
            } else if ("acc".equals(operationCode)) {
                return new AccumulatorInstruction(argument);
            } else if ("jmp".equals(operationCode)) {
                return new JumpInstruction(argument);
            }
        }
        throw new IllegalArgumentException(String.format("Expect bootProgramLine matching pattern '%s' but was: '%s'",
                BOOT_PROGRAM_INSTRUCTION_PATTERN, bootProgramLine));
    }


    public int findBootProgramInfiniteLoop() {
        return handHeldGameConsole.runBootProgram();
    }


    // part 02

    public int repairBootProgram() {
        List<HandHeldBootInstruction> bootProgram = handHeldGameConsole.getBootProgram();
        for (int i = 0; i < bootProgram.size(); i++) {
            HandHeldBootInstruction instruction = bootProgram.get(i);
            HandHeldBootInstruction correctedInstruction;
            int argument = instruction.getArgument();
            if (instruction instanceof JumpInstruction) {
                correctedInstruction = new NoopInstruction(argument);
            } else if (instruction instanceof NoopInstruction) {
                correctedInstruction = new JumpInstruction(argument);
            } else {
                continue;
            }

            bootProgram.set(i, correctedInstruction);
            handHeldGameConsole.setBootProgram(bootProgram);
            int accumulator = handHeldGameConsole.runBootProgram();
            if (handHeldGameConsole.isBootProgramTerminated()) {
                return accumulator;
            }
            bootProgram.set(i, instruction);
        }
        throw new IllegalStateException("could not fix bootProgram: " + bootProgram);
    }


}
