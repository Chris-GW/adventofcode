package de.adventofcode.chrisgw.day16;

import de.adventofcode.chrisgw.AdventOfCodePuzzle;

import java.time.Year;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;


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
    public Integer solveSecondPart() {
        return 0;
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
