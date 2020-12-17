package de.adventofcode.chrisgw.day16;

import de.adventofcode.chrisgw.AdventOfCodePuzzle;

import java.time.Year;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


/**
 * https://adventofcode.com/2020/day/16
 */
public class AdventOfCodeDay16 extends AdventOfCodePuzzle {

    private final List<TrainTicketFieldRule> fieldRules;
    private final TrainTicket yourTicket;
    private final List<TrainTicket> tickets;


    public AdventOfCodeDay16(List<String> inputLines) {
        super(Year.of(2020), 16, inputLines);

        int yourTicketsHeader = inputLines.indexOf("your ticket:");
        int nearbyTicketsHeader = inputLines.indexOf("nearby tickets:");

        fieldRules = parseTicketFieldRules(inputLines.subList(0, yourTicketsHeader - 1));
        yourTicket = parseYourTicket(inputLines.get(yourTicketsHeader + 1));
        tickets = parseNearbyTickets(inputLines.subList(nearbyTicketsHeader + 1, inputLines.size()));
    }

    private List<TrainTicketFieldRule> parseTicketFieldRules(List<String> ticketFieldRuleLines) {
        return ticketFieldRuleLines.stream()
                .map(TrainTicketFieldRule::parseTicketFieldRule)
                .collect(Collectors.toList());
    }

    private TrainTicket parseYourTicket(String yourTicketValueLine) {
        return TrainTicket.parseTrainTicketValues(yourTicketValueLine);
    }

    private List<TrainTicket> parseNearbyTickets(List<String> trainTicketLines) {
        return trainTicketLines.stream().map(TrainTicket::parseTrainTicketValues).collect(Collectors.toList());
    }


    @Override
    public Integer solveFirstPart() {
        return calculateTicketScanningErrorRate();
    }

    private int calculateTicketScanningErrorRate() {
        return tickets.stream()
                .map(TrainTicket::getValues)
                .flatMap(Collection::stream)
                .filter(Predicate.not(this::isValidValue))
                .reduce(0, Integer::sum);
    }

    private boolean isValidValue(int value) {
        return fieldRules.stream().anyMatch(trainTicketFieldRule -> trainTicketFieldRule.test(value));
    }


    @Override
    public Long solveSecondPart() {
        return calculateDepartureProduct();
    }

    private long calculateDepartureProduct() {
        List<List<TrainTicketFieldRule>> suitableFieldRulesForField = new ArrayList<>(yourTicket.fieldSize());

        List<TrainTicket> validTickets = tickets.stream().filter(this::isValidTicket).collect(Collectors.toList());
        for (int i = 0; i < yourTicket.fieldSize(); i++) {
            final int fieldIndex = i;
            Set<Integer> values = validTickets.stream()
                    .map(ticket -> ticket.getField(fieldIndex))
                    .collect(Collectors.toSet());
            List<TrainTicketFieldRule> matchingRules = fieldRules.stream()
                    .filter(ticketFieldRule -> values.stream().allMatch(ticketFieldRule::test))
                    .collect(Collectors.toList());
            suitableFieldRulesForField.add(matchingRules);
        }

        while (!suitableFieldRulesForField.stream().mapToInt(List::size).allMatch(size -> size == 1)) {
            List<TrainTicketFieldRule> unambiguousFieldRules = suitableFieldRulesForField.stream()
                    .filter(fieldRules -> fieldRules.size() == 1)
                    .map(ticketFieldRules -> ticketFieldRules.get(0))
                    .collect(Collectors.toList());

            for (int fieldIndex = 0; fieldIndex < yourTicket.fieldSize(); fieldIndex++) {
                List<TrainTicketFieldRule> possibleRules = suitableFieldRulesForField.get(fieldIndex);
                if (possibleRules.size() == 1) {
                    TrainTicketFieldRule foundRule = possibleRules.get(0);
                    for (int k = 0; k < yourTicket.fieldSize(); k++) {
                        if (k == fieldIndex) {
                            continue;
                        }
                        List<TrainTicketFieldRule> fieldRules = suitableFieldRulesForField.get(k);
                        fieldRules.remove(foundRule);
                    }
                }
            }
        }

        long departureProduct = 1;
        for (int fieldIndex = 0; fieldIndex < suitableFieldRulesForField.size(); fieldIndex++) {
            TrainTicketFieldRule fieldRule = suitableFieldRulesForField.get(fieldIndex).get(0);
            if (fieldRule.isDepartureField()) {
                int value = yourTicket.getField(fieldIndex);
                departureProduct *= value;
            }
        }
        return departureProduct;
    }

    private boolean isValidTicket(TrainTicket trainTicket) {
        return trainTicket.getValues()
                .stream()
                .allMatch(value -> fieldRules.stream().anyMatch(ticketFieldRule -> ticketFieldRule.test(value)));
    }


    public TrainTicket getYourTicket() {
        return yourTicket;
    }

    public List<TrainTicket> getTickets() {
        return tickets;
    }

    public List<TrainTicketFieldRule> getFieldRules() {
        return fieldRules;
    }

}
