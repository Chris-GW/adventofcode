package de.adventofcode.chrisgw.day12;

import lombok.Getter;

import java.util.Arrays;
import java.util.function.UnaryOperator;


public enum TurnDirection {

    LEFT('L', NavigationDirection::left), //
    RIGHT('R', NavigationDirection::right);


    @Getter
    private final char code;
    private final UnaryOperator<NavigationDirection> turnOperation;


    TurnDirection(char code, UnaryOperator<NavigationDirection> turnOperation) {
        this.code = code;
        this.turnOperation = turnOperation;
    }

    public static TurnDirection fromCode(char code) {
        return Arrays.stream(TurnDirection.values())
                .filter(turnDirection -> turnDirection.getCode() == code)
                .findAny()
                .orElse(null);
    }


    public NavigationDirection turn(NavigationDirection direction) {
        return turnOperation.apply(direction);
    }

}
