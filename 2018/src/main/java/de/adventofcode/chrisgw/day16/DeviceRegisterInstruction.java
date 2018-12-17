package de.adventofcode.chrisgw.day16;

import lombok.EqualsAndHashCode;
import lombok.Value;

import java.util.function.IntBinaryOperator;


@Value
public class DeviceRegisterInstruction {

    private final String operationName;

    @EqualsAndHashCode.Exclude
    private final IntBinaryOperator operator;

    private final boolean isFirstValueRegisterReference;
    private final boolean isSecondValueRegisterReference;


    public void execute(int[] register, int valueA, int valueB, int outputC) {
        int a = valueA;
        if (isFirstValueRegisterReference()) {
            a = getRegister(register, valueA);
        }
        int b = valueB;
        if (isSecondValueRegisterReference()) {
            b = getRegister(register, valueB);
        }
        int result = operator.applyAsInt(a, b);
        setRegister(register, outputC, result);
    }


    private int getRegister(int[] register, int registerIndex) {
        return register[registerIndex];
    }

    private void setRegister(int[] register, int outputRegisterIndex, int value) {
        register[outputRegisterIndex] = value;
    }


    @Override
    public String toString() {
        return operationName;
    }

}
