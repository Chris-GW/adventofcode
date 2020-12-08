package de.adventofcode.chrisgw.day04;

import lombok.Data;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;


@Data
public class Passport {

    private final Map<PassportField, String> passportFieldValueMap = new LinkedHashMap<>(PassportField.values().length);


    public boolean isValid() {
        return hasAllNeededFields() && allFieldsAreValid();
    }


    public boolean hasAllNeededFields() {
        boolean hasAllFields = passportFieldValueMap.size() == PassportField.values().length;
        return hasAllFields || isNorthPoleCredentials();
    }

    public boolean isNorthPoleCredentials() {
        return passportFieldValueMap.size() == PassportField.values().length - 1 && isMissing(PassportField.PASSPORT_FIELD7);
    }


    private boolean allFieldsAreValid() {
        for (Entry<PassportField, String> passportFieldValueEntry : passportFieldValueMap.entrySet()) {
            String value = passportFieldValueEntry.getValue();
            PassportField passportField = passportFieldValueEntry.getKey();
            if (!passportField.getValueValidation().test(value)) {
                return false;
            }
        }
        return true;
    }


    public String getValue(PassportField passportField) {
        return passportFieldValueMap.get(passportField);
    }

    public String setValue(PassportField passportField, String value) {
        return passportFieldValueMap.put(passportField, value);
    }

    public void putAll(Map<PassportField, String> passportFieldStringMap) {
        this.passportFieldValueMap.putAll(passportFieldStringMap);
    }


    public boolean isMissing(PassportField passportField) {
        return !passportFieldValueMap.containsKey(passportField);
    }

}
