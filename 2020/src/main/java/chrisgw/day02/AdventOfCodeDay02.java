package chrisgw.day02;

import lombok.Data;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * https://adventofcode.com/2020/day/2
 */
public class AdventOfCodeDay02 {


    public static long countValidPasswords(List<String> passwords) {
        return passwords.stream()
                .map(PasswordWithPolicy::parsePasswordWithPolicy)
                .filter(PasswordWithPolicy::matchesPolicy)
                .count();
    }


    @Data
    public static class PasswordWithPolicy {

        public static final Pattern PASSWORD_WITH_POLICY_PATTERN = Pattern.compile("(\\d+)-(\\d+) (\\w): (\\w+)");

        private final PasswordPolicy passwordPolicy;
        private final String password;


        public static PasswordWithPolicy parsePasswordWithPolicy(String passwordWithPolicy) {
            Matcher matcher = PASSWORD_WITH_POLICY_PATTERN.matcher(passwordWithPolicy);
            if (!matcher.matches()) {
                throw new IllegalArgumentException(
                        "Expect passwordWithPolicy matching: " + PASSWORD_WITH_POLICY_PATTERN);
            }
            int lowerLimit = Integer.parseInt(matcher.group(1));
            int upperLimit = Integer.parseInt(matcher.group(2));
            char letter = matcher.group(3).charAt(0);
            String password = matcher.group(4);
            PasswordPolicy passwordPolicy = new PasswordPolicy(lowerLimit, upperLimit, letter);
            return new PasswordWithPolicy(passwordPolicy, password);
        }


        public boolean matchesPolicy() {
            return passwordPolicy.matchesPolicy(password);
        }

    }


    @Data
    public static class PasswordPolicy {

        private final int lowerLimit;
        private final int upperLimit;
        private final char letter;


        public boolean matchesPolicy(String password) {
            int letterOccurrence = countLetterOccurrence(password);
            return lowerLimit <= letterOccurrence && letterOccurrence <= upperLimit;
        }

        private int countLetterOccurrence(String password) {
            int count = 0;
            for (int i = 0; i < password.length(); i++) {
                if (password.charAt(i) == letter) {
                    count++;
                }
            }
            return count;
        }

    }

}
