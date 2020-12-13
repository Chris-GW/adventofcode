package de.adventofcode.chrisgw.day13;

import lombok.Data;


@Data
public class Bus {

    private final int id;
    private final int departureIndex;


    public boolean isAirportDepartureTime(long timestamp) {
        return timestamp % id == 0;
    }

}
