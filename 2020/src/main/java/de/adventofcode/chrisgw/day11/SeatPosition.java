package de.adventofcode.chrisgw.day11;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class SeatPosition {


    private final int x;
    private final int y;
    private SeatPositionContent content;


    public void toggleSeatOccupancy() {
        if (isEmptySeat()) {
            content = SeatPositionContent.OCCUPIED_SEAT;
        } else if (isOccupiedSeat()) {
            content = SeatPositionContent.EMPTY_SEAT;
        }
    }


    public boolean is(SeatPositionContent content) {
        return this.content.equals(content);
    }

    public boolean isFloor() {
        return is(SeatPositionContent.FLOOR);
    }

    public boolean isEmptySeat() {
        return is(SeatPositionContent.EMPTY_SEAT);
    }

    public boolean isOccupiedSeat() {
        return is(SeatPositionContent.OCCUPIED_SEAT);
    }

    public boolean isSeat() {
        return !isFloor();
    }

}
