package de.adventofcode.chrisgw.day11;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class SecondPartPeopleSeatOccupationBehaviour implements PeopleSeatOccupationBehaviour {

    public static final int OCCUPIED_SEAT_TOLERANCE = 5;

    private final SeatingSystem seatingSystem;


    @Override
    public boolean wouldOccupySeat(SeatPosition seatPosition) {
        return seatPosition.isEmptySeat() && seatingSystem.firstSeatsInSight(seatPosition)
                .allMatch(SeatPosition::isEmptySeat);
    }


    @Override
    public boolean wouldFreeSeat(SeatPosition seatPosition) {
        return seatPosition.isOccupiedSeat() && hasMoreThanToleranceOccupiedSeatsInSight(seatPosition);
    }

    private boolean hasMoreThanToleranceOccupiedSeatsInSight(SeatPosition seatPosition) {
        long adjecentOccupiedSeatCount = seatingSystem.firstSeatsInSight(seatPosition)
                .filter(SeatPosition::isOccupiedSeat)
                .count();
        return adjecentOccupiedSeatCount >= OCCUPIED_SEAT_TOLERANCE;
    }

}
