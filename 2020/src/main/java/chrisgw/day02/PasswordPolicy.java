package chrisgw.day02;

import java.util.regex.Pattern;


public interface PasswordPolicy {

    Pattern PASSWORD_POLICY_PATTERN = Pattern.compile("(\\d+)-(\\d+) (\\w)");
    Pattern PASSWORD_WITH_POLICY_PATTERN = Pattern.compile(PASSWORD_POLICY_PATTERN + ": (\\w+)");

    boolean matchesPolicy(String password);

}
