package de.adventofcode.chrisgw.day16;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Value;

import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;


@Value
@Builder
public class DeviceOperationSample {

    public static final Pattern REGISTER_VALUE_LINE_PATTERN = Pattern.compile( //
            "(Before|After):\\s+\\[(\\d+(,\\s*\\d+){3})]", Pattern.CASE_INSENSITIVE);


    @Getter(AccessLevel.PRIVATE)
    private final int[] registerValuesBefore;

    @Getter(AccessLevel.PRIVATE)
    private final int[] registerValuesAfter;

    private final int opCode;
    private final int valueA;
    private final int valueB;
    private final int outputC;


    public static DeviceOperationSample parseOperationSampleEntry(String operationSampleStr) {
        Scanner sc = new Scanner(operationSampleStr);
        DeviceOperationSampleBuilder operationSampleBuilder = DeviceOperationSample.builder();
        operationSampleBuilder.registerValuesBefore(parseRegisterValues(sc));

        operationSampleBuilder.opCode(sc.nextInt());
        operationSampleBuilder.valueA(sc.nextInt());
        operationSampleBuilder.valueB(sc.nextInt());
        operationSampleBuilder.outputC(sc.nextInt());

        operationSampleBuilder.registerValuesAfter(parseRegisterValues(sc));
        return operationSampleBuilder.build();
    }

    private static int[] parseRegisterValues(Scanner sc) {
        String registerValueLine = sc.findWithinHorizon(REGISTER_VALUE_LINE_PATTERN, 70);
        int beginIndex = registerValueLine.indexOf('[') + 1;
        int endIndex = registerValueLine.length() - 1;
        registerValueLine = registerValueLine.substring(beginIndex, endIndex);
        String[] splittedRegisterValues = registerValueLine.split("\\s*,\\s*");
        return Arrays.stream(splittedRegisterValues).mapToInt(Integer::parseInt).toArray();
    }


    public boolean matchesRegisterInstruction(DeviceRegisterInstruction registerInstruction) {
        int[] registerValues = Arrays.copyOf(registerValuesBefore, registerValuesBefore.length);
        registerInstruction.execute(registerValues, valueA, valueB, outputC);
        return Arrays.equals(registerValues, registerValuesAfter);
    }


    public int registerValueBefore(int registerIndex) {
        return registerValuesBefore[registerIndex];
    }

    public int registerValueAfter(int registerIndex) {
        return registerValuesAfter[registerIndex];
    }


    public int registerLength() {
        return registerValuesBefore.length;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Before: ").append(Arrays.toString(registerValuesBefore)).append("\n");
        sb.append(opCode).append(" ");
        sb.append(valueA).append(" ");
        sb.append(valueB).append(" ");
        sb.append(outputC).append("\n");
        sb.append("After:  ").append(Arrays.toString(registerValuesAfter));
        return sb.toString();
    }

}
