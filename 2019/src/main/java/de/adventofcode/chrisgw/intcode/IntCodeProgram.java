package de.adventofcode.chrisgw.intcode;

import java.util.*;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.LongStream;


public class IntCodeProgram implements Iterator<IntCodeInstruction> {

    public static final int PARAMETER_POSITION_MODE = 0;
    public static final int PARAMETER_IMMEDIATE_MODE = 1;
    public static final int PARAMETER_RELATIVE_MODE = 2;

    private final long[] initialState;
    private final Map<Integer, IntCodeInstruction> instructionSet;

    private long noun;
    private long verb;
    private Deque<Long> inputs = new ArrayDeque<>();
    private Deque<Long> outputs = new ArrayDeque<>();

    private boolean movedInstructionPointer;
    private int instructionPointer;
    private int relativeBase = 0;
    private long[] memory;


    public IntCodeProgram(long[] initialState) {
        this(initialState, Set.of(new AddIntCodeInstruction(), new MulIntCodeInstruction(), // day 02
                new InputCodeInstruction(), new OutputCodeInstruction(), // day 05 part 01
                new JumpIfTrueCodeInstruction(), new JumpIfFalseCodeInstruction(), //
                new LessThanCodeInstruction(), new EqualsCodeInstruction(), // day 05 part 02
                new AdjustRelativeBaseCodeInstruction())); // day 09 part 01
    }

    public IntCodeProgram(long[] initialState, Collection<IntCodeInstruction> instructionSet) {
        this.initialState = Arrays.copyOf(initialState, initialState.length);
        this.instructionSet = instructionSet.stream()
                .collect(Collectors.toMap(IntCodeInstruction::opCode, Function.identity()));
        reset();
    }

    public IntCodeProgram(IntCodeProgram intCodeProgram) {
        this(intCodeProgram.initialState, intCodeProgram.instructionSet.values()); // day 07
    }

    public static IntCodeProgram parseIntCodeProgram(String intCodeProgramStr) {
        Pattern splitPattern = Pattern.compile(",");
        long[] initialState = splitPattern.splitAsStream(intCodeProgramStr).mapToLong(Long::parseLong).toArray();
        return new IntCodeProgram(initialState);
    }


    public void reset() {
        this.memory = Arrays.copyOf(initialState, initialState.length);
        this.instructionPointer = 0;
        this.relativeBase = 0;
        this.inputs.clear();
        this.outputs.clear();
    }


    @Override
    public boolean hasNext() {
        return nextOpCode() != 99 && !isWaitingForNextInput();
    }

    private boolean isWaitingForNextInput() {
        return !hasNextInput() && nextIntCodeInstruction().opCode() == new InputCodeInstruction().opCode();
    }

    @Override
    public IntCodeInstruction next() {
        if (!hasNext()) {
            throw new NoSuchElementException("IntCodeProgram is exited or waiting for input");
        }
        movedInstructionPointer = false;
        IntCodeInstruction intCodeInstruction = nextIntCodeInstruction();
        intCodeInstruction.execute(this);
        if (!movedInstructionPointer) {
            instructionPointer += intCodeInstruction.instructionSize();
        }
        return intCodeInstruction;
    }

    private IntCodeInstruction nextIntCodeInstruction() {
        int opCode = nextOpCode();
        int code = opCode % 100;
        IntCodeInstruction intCodeInstruction = instructionSet.get(code);
        if (intCodeInstruction == null) {
            throw new IllegalArgumentException("Unknown opCode: " + code);
        }
        return intCodeInstruction;
    }

    public void run() {
        while (hasNext()) {
            next();
        }
    }


    private int nextOpCode() {
        return (int) valueAt(instructionPointer);
    }


    public long parameterAt(int index) {
        int parameterMode = parameterModeAt(index);
        int parameterIndex = instructionPointer + 1 + index;
        switch (parameterMode) {
        case PARAMETER_POSITION_MODE:
            int adress = addressAt(parameterIndex);
            return valueAt(adress);
        case PARAMETER_IMMEDIATE_MODE:
            return valueAt(parameterIndex);
        case PARAMETER_RELATIVE_MODE:
            int relativeAdress = addressAt(parameterIndex);
            return valueAt(relativeBase + relativeAdress);
        default:
            throw new RuntimeException("unknown parameterMode: " + parameterMode);
        }
    }

    public int parameterAddressAt(int index) {
        int parameterMode = parameterModeAt(index);
        int parameterIndex = instructionPointer + 1 + index;
        switch (parameterMode) {
        case PARAMETER_POSITION_MODE:
        case PARAMETER_IMMEDIATE_MODE:
            return (int) valueAt(parameterIndex);
        case PARAMETER_RELATIVE_MODE:
            int relativeAdress = addressAt(parameterIndex);
            return relativeBase + relativeAdress;
        default:
            throw new RuntimeException("unknown parameterMode: " + parameterMode);
        }

    }

    private int parameterModeAt(int index) {
        String opCodeStr = String.valueOf(nextOpCode());
        int parameterModeIndex = opCodeStr.length() - 1 - 2 - index;
        if (parameterModeIndex < 0 || parameterModeIndex >= opCodeStr.length()) {
            return 0;
        }
        return opCodeStr.charAt(parameterModeIndex) - '0';
    }


    public int addressAt(int address) {
        return (int) valueAt(address);
    }

    public long valueAt(int address) {
        if (address < 0) {
            throw new IllegalArgumentException("Can't access negative addresses, but was: " + address);
        } else if (address >= memory.length) {
            return 0;
        } else {
            return memory[address];
        }
    }

    public void setValueAt(int address, long value) {
        if (address < 0) {
            throw new IllegalArgumentException("Can't write negative addresses, but was: " + address);
        } else if (address >= memory.length) {
            growMemoryToAdress(address);
        }
        memory[address] = value;
    }

    private void growMemoryToAdress(int address) {
        int oldCapacity = memory.length;
        int minCapacity = address + 1;
        int grow = Math.max(minCapacity - oldCapacity, /* minimum growth */
                oldCapacity >> 1 /* preferred growth */);
        int newCapacity = oldCapacity + grow;
        memory = Arrays.copyOf(memory, newCapacity);
    }


    public void moveInstructionPointerTo(int instructionPointerDestination) {
        this.instructionPointer = instructionPointerDestination;
        this.movedInstructionPointer = true;
    }


    public void adjustRelativeBase(long parameterValue) {
        relativeBase += parameterValue;
    }


    public long getNoun() {
        return noun;
    }

    public void setNoun(long noun) {
        this.noun = noun;
        setValueAt(1, noun);
    }


    public long getVerb() {
        return verb;
    }

    public void setVerb(long verb) {
        this.verb = verb;
        setValueAt(2, verb);
    }


    public long getExitOutput() {
        return valueAt(0);
    }


    public long nextInput() {
        return inputs.removeFirst();
    }

    public void addInput(long input) {
        inputs.addLast(input);
    }

    public boolean hasNextInput() {
        return !inputs.isEmpty();
    }


    public void addOutput(long output) {
        outputs.addLast(output);
    }

    public long nextOutput() {
        return outputs.removeFirst();
    }

    public long lastOutput() {
        return outputs.getLast();
    }

    public LongStream getAllOutput() {
        return LongStream.generate(this::nextOutput).limit(outputs.size());
    }

    public boolean hasNextOutput() {
        return !outputs.isEmpty();
    }


    public long[] getInitialState() {
        return Arrays.copyOf(initialState, initialState.length);
    }

    public long[] getMemory() {
        return Arrays.copyOf(memory, memory.length);
    }


    @Override
    public String toString() {
        return Arrays.toString(memory);
    }

}