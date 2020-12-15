package de.adventofcode.chrisgw.day14;

import de.adventofcode.chrisgw.AdventOfCodePuzzle;
import org.apache.commons.lang3.StringUtils;

import java.time.Year;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.LongStream;


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


    private long applyBitMaskOnValue(long value) {
        StringBuilder valueBinary = toBinaryString(value);
        System.out.printf("value:  %s  (decimal %d)%n", valueBinary, value);
        System.out.printf("mask:   %s%n", bitMask);
        for (int i = 0; i < valueBinary.length(); i++) {
            char c = bitMask.charAt(i);
            if (c == '0' || c == '1') {
                valueBinary.setCharAt(i, c);
            }
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
                long memoryAddress = Long.parseLong(setMemoryMatcher.group(1));
                long value = Long.parseLong(setMemoryMatcher.group(2));
                memory.put(memoryAddress, applyBitMaskOnValue(value));
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
    public Long solveSecondPart() {
        for (String inputLine : getInitProgramLines()) {
            Matcher setMaskMatcher = SET_MASK_INSTRUCTION_PATTERN.matcher(inputLine);
            Matcher setMemoryMatcher = SET_MEMORY_INSTRUCTION_PATTERN.matcher(inputLine);
            if (setMaskMatcher.matches()) {
                this.bitMask = setMaskMatcher.group(1);
            } else if (setMemoryMatcher.matches()) {
                long memoryAddress = Long.parseLong(setMemoryMatcher.group(1));
                long value = Long.parseLong(setMemoryMatcher.group(2));
                applyBitMaskOnMemory(memoryAddress).forEach(memAddress -> memory.put(memAddress, value));
            } else {
                throw new IllegalArgumentException("unknown instruction: " + inputLine);
            }
        }
        return totalMemorySum();
    }

    private LongStream applyBitMaskOnMemory(long memoryAddress) {
        StringBuilder valueBinary = toBinaryString(memoryAddress);
        System.out.printf("value:  %s  (decimal %d)%n", valueBinary, memoryAddress);
        System.out.printf("mask:   %s%n", bitMask);
        for (int i = 0; i < valueBinary.length(); i++) {
            char c = bitMask.charAt(i);
            if (c == '1' || c == 'X') {
                valueBinary.setCharAt(i, c);
            }
        }
        System.out.printf("result: %s%n", valueBinary);
        return allBuildableMemoryAddress(valueBinary, 0);
    }

    private LongStream allBuildableMemoryAddress(StringBuilder valueBinary, int index) {
        if (index >= valueBinary.length()) {
            long memoryAddress = Long.parseLong(valueBinary.toString(), 2);
            return LongStream.of(memoryAddress);
        }
        if (valueBinary.charAt(index) == 'X') {
            StringBuilder valueBinaryWith0 = new StringBuilder(valueBinary);
            valueBinaryWith0.setCharAt(index, '0');
            LongStream memoryAddressesWith0 = allBuildableMemoryAddress(valueBinaryWith0, index + 1);

            StringBuilder valueBinaryWith1 = new StringBuilder(valueBinary);
            valueBinaryWith1.setCharAt(index, '1');
            LongStream memoryAddressesWith1 = allBuildableMemoryAddress(valueBinaryWith1, index + 1);
            return LongStream.concat(memoryAddressesWith1, memoryAddressesWith0);
        } else {
            return allBuildableMemoryAddress(valueBinary, index + 1);
        }
    }


    public List<String> getInitProgramLines() {
        return getInputLines();
    }

}
