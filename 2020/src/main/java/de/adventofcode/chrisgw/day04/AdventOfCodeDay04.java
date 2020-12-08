package de.adventofcode.chrisgw.day04;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * https://adventofcode.com/2020/day/4
 */
public class AdventOfCodeDay04 {

    public static final Pattern PASSPORT_FIELD_WITH_VALUE_PATTERN = Pattern.compile("(\\w{3}):(\\S+)");


    private AdventOfCodeDay04() {
    }


    public static long countPassportsWithAllNeededFields(Readable passportBatchInputStream) {
        List<Passport> passports = parsePassportBatch(passportBatchInputStream);
        return passports.stream().
                filter(Passport::hasAllNeededFields).
                count();
    }

    private static List<Passport> parsePassportBatch(Readable passportBatchInputStream) {
        List<Passport> passports = new ArrayList<>();

        Passport currentPassport = new Passport();
        try (Scanner sc = new Scanner(passportBatchInputStream)) {
            while (sc.hasNextLine()) {
                String currentLine = sc.nextLine();
                Map<PassportField, String> passportFieldValueMap = parseAllPassportFieldsWithValue(currentLine);
                currentPassport.putAll(passportFieldValueMap);
                if (passportFieldValueMap.isEmpty()) {
                    passports.add(currentPassport);
                    currentPassport = new Passport();
                }
            }
            passports.add(currentPassport);
        }
        return passports;
    }

    private static Map<PassportField, String> parseAllPassportFieldsWithValue(String currentLine) {
        Map<PassportField, String> passportFieldValueMap = new LinkedHashMap<>(PassportField.values().length);
        Matcher passportFieldMatch = PASSPORT_FIELD_WITH_VALUE_PATTERN.matcher(currentLine);
        while (passportFieldMatch.find()) {
            PassportField passportField = PassportField.ofFieldShortName(passportFieldMatch.group(1));
            String value = passportFieldMatch.group(2);
            passportFieldValueMap.put(passportField, value);
        }
        return passportFieldValueMap;
    }


    // part 02

    public static long countValidPassports(Readable passportBatchInputStream) {
        List<Passport> passports = parsePassportBatch(passportBatchInputStream);
        return passports.stream().
                filter(Passport::isValid).
                count();
    }

}
