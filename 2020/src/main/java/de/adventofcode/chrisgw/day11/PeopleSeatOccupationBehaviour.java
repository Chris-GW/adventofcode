package de.adventofcode.chrisgw.day11;

public interface PeopleSeatOccupationBehaviour {

    boolean wouldOccupySeat(SeatPosition seatPosition);

    boolean wouldFreeSeat(SeatPosition seatPosition);

}
