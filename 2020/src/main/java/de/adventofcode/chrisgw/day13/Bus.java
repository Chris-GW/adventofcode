package de.adventofcode.chrisgw.day13;

import lombok.Data;


@Data
public class Bus {

    private final int id;


    public boolean isAirportDepartureTime(int timestamp) {
        return timestamp % id == 0;
    }

}
