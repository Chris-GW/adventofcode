package de.adventofcode.chrisgw.day11;

import lombok.Data;


@Data
public class SeatPosition {

    private final int x;
    private final int y;
    private boolean emptySeat;


    public boolean isOccupiedSeat() {
        return !isEmptySeat();
    }

}
