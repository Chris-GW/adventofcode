package de.adventofcode.chrisgw.day16;

import lombok.Data;

import java.util.Arrays;
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

}
