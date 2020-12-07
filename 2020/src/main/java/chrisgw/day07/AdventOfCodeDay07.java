package chrisgw.day07;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Objects.requireNonNull;


/**
 * https://adventofcode.com/2020/day/7
 */
public class AdventOfCodeDay07 {

    private final Map<Bag, BagColorCodeRule> rules;


    private AdventOfCodeDay07(Map<Bag, BagColorCodeRule> rules) {
        this.rules = requireNonNull(rules);
    }

    public static AdventOfCodeDay07 parseBagColorCodeRules(List<String> ruleLines) {
        Map<Bag, BagColorCodeRule> rules = ruleLines.stream()
                .map(BagColorCodeRule::parseBagColorCodeRule)
                .collect(Collectors.toMap(BagColorCodeRule::getBag, Function.identity()));
        return new AdventOfCodeDay07(rules);
    }


    public int countBagsWichCanContain(Bag bag) {
        return countBagsWichCanContain(Set.of(bag));
    }

    private int countBagsWichCanContain(Set<Bag> bags) {
        Set<Bag> possibleBags = bags.stream()
                .flatMap(this::rulesWhichCanContain)
                .map(BagColorCodeRule::getBag)
                .collect(Collectors.toSet());
        possibleBags.addAll(bags);
        if (possibleBags.size() == bags.size()) {
            return possibleBags.size() - 1;
        } else {
            return countBagsWichCanContain(possibleBags);
        }
    }


    private Stream<BagColorCodeRule> rulesWhichCanContain(Bag bag) {
        return rules().filter(rule -> rule.canContain(bag));
    }

    private Stream<BagColorCodeRule> rules() {
        return rules.values().stream();
    }


    // part 02

    public int countContainedBagsFor(Bag bag) {
        BagColorCodeRule rule = rules.get(bag);
        int bagSum = 1;
        for (BagQuantity containedBag : rule.getContainedBags()) {
            int quantity = containedBag.getQuantity();
            int withinThat = countContainedBagsFor(containedBag.getBag());
            bagSum += quantity * withinThat;
        }
        return bagSum;
    }

}
