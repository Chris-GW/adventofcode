package de.adventofcode.chrisgw.day11;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class FirstPartPeopleSeatOccupationBehaviour implements PeopleSeatOccupationBehaviour {

    public static final int OCCUPIED_SEAT_TOLERANCE = 4;

    private final SeatingSystem seatingSystem;


    @Override
    public boolean wouldOccupySeat(SeatPosition seatPosition) {
        return seatPosition.isEmptySeat() && seatingSystem.adjacentSeats(seatPosition)
                .allMatch(SeatPosition::isEmptySeat);
    }


    @Override
    public boolean wouldFreeSeat(SeatPosition seatPosition) {
        return seatPosition.isOccupiedSeat() && hasMoreThanToleranceAdjacentOccupiedSeats(seatPosition);
    }

    private boolean hasMoreThanToleranceAdjacentOccupiedSeats(SeatPosition seatPosition) {
        long adjecentOccupiedSeatCount = seatingSystem.adjacentSeats(seatPosition)
                .filter(SeatPosition::isOccupiedSeat)
                .count();
        return adjecentOccupiedSeatCount >= OCCUPIED_SEAT_TOLERANCE;
    }

}
