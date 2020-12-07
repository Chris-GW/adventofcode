package chrisgw.day07;

import lombok.Data;

import java.util.Collections;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


@Data
public class BagColorCodeRule {

    public static final Pattern RULE_BEGIN_PATTERN = Pattern.compile("^(.+?) bags contain");
    public static final Pattern RULE_CONTAINS_PATTERN = Pattern.compile("(\\d+) (.+?) bags?");

    private final Bag bag;
    private final List<BagQuantity> containedBags;


    public static BagColorCodeRule parseBagColorCodeRule(String ruleLine) {
        Matcher ruleBeginMatcher = RULE_BEGIN_PATTERN.matcher(ruleLine);
        if (!ruleBeginMatcher.find()) {
            throw new IllegalArgumentException(
                    String.format("expect BagColorCodeRule start with: '%s' but was: '%s'", RULE_BEGIN_PATTERN,
                            ruleLine));
        }
        Bag bag = new Bag(ruleBeginMatcher.group(1));
        if (ruleLine.endsWith("no other bags.")) {
            return new BagColorCodeRule(bag, Collections.emptyList());
        }
        Matcher containedBagsMatcher = RULE_CONTAINS_PATTERN.matcher(ruleLine);
        List<BagQuantity> containedBags = containedBagsMatcher.results()
                .map(BagColorCodeRule::asBagQuantity)
                .collect(Collectors.toList());
        return new BagColorCodeRule(bag, containedBags);
    }

    private static BagQuantity asBagQuantity(MatchResult matchResult) {
        int quantity = Integer.parseInt(matchResult.group(1));
        Bag bag = new Bag(matchResult.group(2));
        return new BagQuantity(quantity, bag);
    }


    public boolean containsNoOtherBags() {
        return containedBags.isEmpty();
    }


    public boolean isApplicableFor(Bag bag) {
        return this.bag.equals(bag);
    }

    public boolean canContain(Bag bag) {
        return containedBags.stream().map(BagQuantity::getBag).anyMatch(bag::equals);
    }


    public int countContainedBags() {
        return containedBags.stream().mapToInt(BagQuantity::getQuantity).sum();
    }


    @Override
    public String toString() {
        return bag + " -> " + containedBags;
    }

}
