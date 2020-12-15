package de.adventofcode.chrisgw.day14;

import de.adventofcode.chrisgw.AdventOfCodePuzzle;
import org.apache.commons.lang3.StringUtils;

import java.time.Year;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * https://adventofcode.com/2020/day/14
 */
public class DockingProgram extends AdventOfCodePuzzle {

    public static final Pattern SET_MASK_INSTRUCTION_PATTERN = Pattern.compile("mask = ([X01]{36})");
    public static final Pattern SET_MEMORY_INSTRUCTION_PATTERN = Pattern.compile("mem\\[(\\d+)] = (\\d+)");

    private final Map<Long, Long> memory = new HashMap<>();
    private String bitMask;


    public DockingProgram(List<String> inputLines) {
        super(Year.of(2020), 14, inputLines);
    }


    private void setMemoryValueAt(long memoryAdress, long value) {
        memory.put(memoryAdress, applyBitMask(value));
    }

    private long applyBitMask(long value) {
        StringBuilder valueBinary = toBinaryString(value);
        System.out.printf("value:  %s  (decimal %d)%n", valueBinary, value);
        System.out.printf("mask:   %s%n", bitMask);
        for (int i = 0; i < valueBinary.length(); i++) {
            char c = bitMask.charAt(i);
            if (c == 'X') {
                continue;
            }
            valueBinary.setCharAt(i, c);
        }

        long newValue = Long.parseLong(valueBinary.toString(), 2);
        System.out.printf("result: %s  (decimal %d)%n", valueBinary, newValue);
        return newValue;
    }

    private StringBuilder toBinaryString(long value) {
        return new StringBuilder(StringUtils.leftPad(Long.toBinaryString(value), bitMask.length(), '0'));
    }


    @Override
    public Long solveFirstPart() {
        for (String inputLine : getInitProgramLines()) {
            Matcher setMaskMatcher = SET_MASK_INSTRUCTION_PATTERN.matcher(inputLine);
            Matcher setMemoryMatcher = SET_MEMORY_INSTRUCTION_PATTERN.matcher(inputLine);
            if (setMaskMatcher.matches()) {
                this.bitMask = setMaskMatcher.group(1);
            } else if (setMemoryMatcher.matches()) {
                long memoryAdress = Long.parseLong(setMemoryMatcher.group(1));
                long value = Long.parseLong(setMemoryMatcher.group(2));
                setMemoryValueAt(memoryAdress, value);
            } else {
                throw new IllegalArgumentException("unknown instruction: " + inputLine);
            }
        }
        return totalMemorySum();
    }

    private Long totalMemorySum() {
        return memory.values().stream().reduce(0L, Long::sum);
    }


    // part 02

    @Override
    public Number solveSecondPart() {
        // TODO solveSecondPart()
        return 0;
    }


    public List<String> getInitProgramLines() {
        return getInputLines();
    }

}
