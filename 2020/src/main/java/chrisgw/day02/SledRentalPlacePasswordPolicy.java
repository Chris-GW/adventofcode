package chrisgw.day02;

import lombok.Data;

import java.util.regex.MatchResult;
import java.util.regex.Matcher;


@Data
public class SledRentalPlacePasswordPolicy implements PasswordPolicy {

    private final int lowerLimit;
    private final int upperLimit;
    private final char letter;


    public static SledRentalPlacePasswordPolicy fromMatchResult(MatchResult matchResult) {
        int lowerLimit = Integer.parseInt(matchResult.group(1));
        int upperLimit = Integer.parseInt(matchResult.group(2));
        char letter = matchResult.group(3).charAt(0);
        return new SledRentalPlacePasswordPolicy(lowerLimit, upperLimit, letter);
    }

    @Override
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
