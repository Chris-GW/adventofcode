package chrisgw.day02;

import lombok.Data;

import java.util.regex.MatchResult;
import java.util.regex.Matcher;


@Data
public class TobogganCorporatePasswordPolicy implements PasswordPolicy {

    private final int firstPosition;
    private final int secondPosition;
    private final char letter;

    public static TobogganCorporatePasswordPolicy fromMatchResult(MatchResult matchResult) {
        int firstPosition = Integer.parseInt(matchResult.group(1));
        int secondPosition = Integer.parseInt(matchResult.group(2));
        char letter = matchResult.group(3).charAt(0);
        return new TobogganCorporatePasswordPolicy(firstPosition, secondPosition, letter);
    }

    @Override
    public boolean matchesPolicy(String password) {
        boolean occuesInFirstPosition = isOccuesInAtPosition(password, firstPosition);
        return occuesInFirstPosition ^ isOccuesInAtPosition(password, secondPosition);
    }

    private boolean isOccuesInAtPosition(String password, int position1Based) {
        return password.charAt(position1Based - 1) == letter;
    }

}
