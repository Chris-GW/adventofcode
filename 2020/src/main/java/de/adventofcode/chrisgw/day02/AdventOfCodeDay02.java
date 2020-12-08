package de.adventofcode.chrisgw.day02;

import java.util.List;
import java.util.regex.Matcher;


/**
 * https://adventofcode.com/2020/day/2
 */
public class AdventOfCodeDay02 {


    private AdventOfCodeDay02() {
    }


    public static long countValidPasswordsWithSledRentalPlacePasswordPolicy(List<String> passwords) {
        return passwords.stream()
                .map(PasswordPolicy.PASSWORD_WITH_POLICY_PATTERN::matcher)
                .filter(Matcher::matches)
                .map(Matcher::toMatchResult)
                .filter(matchResult -> {
                    PasswordPolicy passwordPolicy = SledRentalPlacePasswordPolicy.fromMatchResult(matchResult);
                    String password = matchResult.group(4);
                    return passwordPolicy.matchesPolicy(password);
                })
                .count();
    }

    public static long countValidPasswordsWithTobogganCorporatePasswordPolicy(List<String> passwords) {
        return passwords.stream()
                .map(PasswordPolicy.PASSWORD_WITH_POLICY_PATTERN::matcher)
                .filter(Matcher::matches)
                .map(Matcher::toMatchResult)
                .filter(matchResult -> {
                    PasswordPolicy passwordPolicy = TobogganCorporatePasswordPolicy.fromMatchResult(matchResult);
                    String password = matchResult.group(4);
                    return passwordPolicy.matchesPolicy(password);
                })
                .count();
    }


}
