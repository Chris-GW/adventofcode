package de.adventofcode.chrisgw.day04;

import lombok.Getter;

import java.util.Set;
import java.util.function.Predicate;
import java.util.regex.Pattern;


public enum PassportField {
    PASSPORT_FIELD("Birth Year", "byr", numberBetweenRange(1920, 2002)), //
    PASSPORT_FIELD1("Issue Year", "iyr", numberBetweenRange(2010, 2020)), //
    PASSPORT_FIELD2("Expiration Year", "eyr", numberBetweenRange(2020, 2030)), //
    PASSPORT_FIELD3("Height", "hgt", heightValidator()), //
    PASSPORT_FIELD4("Hair Color", "hcl", hexColorValidator()), //
    PASSPORT_FIELD5("Eye Color", "ecl", eyeColorValidator()), //
    PASSPORT_FIELD6("Passport ID", "pid", passportIdValidator()), //
    PASSPORT_FIELD7("Country ID", "cid", value -> true), //
    ;


    @Getter
    private final String fieldName;

    @Getter
    private final String fieldShortName;

    @Getter
    private final Predicate<String> valueValidation;


    PassportField(String fieldName, String fieldShortName, Predicate<String> valueValidation) {
        this.fieldName = fieldName;
        this.fieldShortName = fieldShortName;
        this.valueValidation = valueValidation;
    }

    public static PassportField ofFieldShortName(String fieldShortName) {
        for (PassportField passportField : PassportField.values()) {
            if (passportField.getFieldShortName().equals(fieldShortName)) {
                return passportField;
            }
        }
        throw new IllegalArgumentException("unknown passport field short name: " + fieldShortName);
    }


    @Override
    public String toString() {
        return fieldName;
    }


    public static Predicate<String> numberBetweenRange(int min, int max) {
        return value -> {
            int number = Integer.parseInt(value);
            return min <= number && number <= max;
        };
    }

    public static Predicate<String> heightValidator() {
        return value -> {
            String heightStr = value.substring(0, value.length() - 2);
            if (value.endsWith("cm")) {
                return numberBetweenRange(150, 193).test(heightStr);
            } else if (value.endsWith("in")) {
                return numberBetweenRange(59, 76).test(heightStr);
            } else {
                return false;
            }
        };
    }

    public static Predicate<String> hexColorValidator() {
        final Pattern hexColorPattern = Pattern.compile("#[0-9a-f]{6}");
        return hexColorPattern.asMatchPredicate();
    }


    public static Predicate<String> eyeColorValidator() {
        final Set<String> eyeColorCodes = Set.of("amb", "blu", "brn", "gry", "grn", "hzl", "oth");
        return eyeColorCodes::contains;
    }

    public static Predicate<String> passportIdValidator() {
        final Pattern hexColorPattern = Pattern.compile("\\d{9}");
        return hexColorPattern.asMatchPredicate();
    }

}
