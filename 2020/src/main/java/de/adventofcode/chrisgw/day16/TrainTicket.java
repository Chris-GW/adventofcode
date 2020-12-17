package de.adventofcode.chrisgw.day16;

import lombok.Data;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Data
public class TrainTicket {

    private final List<Integer> values;


    public static TrainTicket parseTrainTicketValues(String trainTicketValueLine) {
        List<Integer> values = Arrays.stream(trainTicketValueLine.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        return new TrainTicket(values);
    }


    public boolean isValidTicket(List<TrainTicketFieldRule> ticketFieldRules) {
        return values.stream()
                .allMatch(value -> ticketFieldRules.stream()
                        .anyMatch(trainTicketFieldRule -> trainTicketFieldRule.test(value)));
    }


    public int getField(int fieldIndex) {
        return values.get(fieldIndex);
    }


    public int fieldSize() {
        return values.size();
    }

}
