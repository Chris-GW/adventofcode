package de.adventofcode.chrisgw.day16;

import lombok.Data;

import java.util.function.IntPredicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Data
public class TrainTicketFieldRule implements IntPredicate {

    public static final Pattern TICKET_FIELD_RULE_PATTERN = Pattern.compile(
            "(\\w+( \\w+)*): (\\d+)-(\\d+) or (\\d+)-(\\d+)");

    private final String fieldName;

    private final int beginScopeA;
    private final int endScopeA;

    private final int beginScopeB;
    private final int endScopeB;


    public static TrainTicketFieldRule parseTicketFieldRule(String ticketFieldRuleLine) {
        Matcher matcher = TICKET_FIELD_RULE_PATTERN.matcher(ticketFieldRuleLine);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("expect ticketFieldRuleLine with pattern " + TICKET_FIELD_RULE_PATTERN);
        }
        String fieldName = matcher.group(1);
        int beginScopeA = Integer.parseInt(matcher.group(3));
        int endScopeA = Integer.parseInt(matcher.group(4));
        int beginScopeB = Integer.parseInt(matcher.group(5));
        int endScopeB = Integer.parseInt(matcher.group(6));
        return new TrainTicketFieldRule(fieldName, beginScopeA, endScopeA, beginScopeB, endScopeB);
    }


    @Override
    public boolean test(int value) {
        boolean inScopeA = beginScopeA <= value && value <= endScopeA;
        return inScopeA || beginScopeB <= value && value <= endScopeB;
    }


    public boolean isDepartureField() {
        return fieldName.startsWith("departure ");
    }

}
