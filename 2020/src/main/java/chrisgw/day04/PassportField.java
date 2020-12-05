package chrisgw.day04;

import lombok.Getter;
import org.apache.commons.lang3.builder.ToStringBuilder;


public enum PassportField {
    Birth_Year("Birth Year", "byr"), //
    Issue_Year("Issue Year", "iyr"), //
    Expiration_Year("Expiration Year", "eyr"), //
    Height("Height", "hgt"), //
    Hair_Color("Hair Color", "hcl"), //
    Eye_Color("Eye Color", "ecl"), //
    Passport_ID("Passport ID", "pid"), //
    Country_ID("Country ID", "cid"), //
    ;


    @Getter
    private final String fieldName;

    @Getter
    private final String fieldShortName;


    PassportField(String fieldName, String fieldShortName) {
        this.fieldName = fieldName;
        this.fieldShortName = fieldShortName;
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

}
