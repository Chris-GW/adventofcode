package chrisgw.day04;

import lombok.Getter;

import java.util.Set;
import java.util.function.Predicate;
import java.util.regex.Pattern;


public enum PassportField {
    Birth_Year("Birth Year", "byr", numberBetweenRange(1920, 2002)), //
    Issue_Year("Issue Year", "iyr", numberBetweenRange(2010, 2020)), //
    Expiration_Year("Expiration Year", "eyr", numberBetweenRange(2020, 2030)), //
    Height("Height", "hgt", heightValidator()), //
    Hair_Color("Hair Color", "hcl", hexColorValidator()), //
    Eye_Color("Eye Color", "ecl", eyeColorValidator()), //
    Passport_ID("Passport ID", "pid", passportIdValidator()), //
    Country_ID("Country ID", "cid", value -> true), //
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
            if (value.endsWith("cm")) {
                String heightStr = value.substring(0, value.length() - 2);
                return numberBetweenRange(150, 193).test(heightStr);
            } else if (value.endsWith("in")) {
                String heightStr = value.substring(0, value.length() - 2);
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
