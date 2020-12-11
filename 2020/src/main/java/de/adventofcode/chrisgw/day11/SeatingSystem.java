package de.adventofcode.chrisgw.day11;

import de.adventofcode.chrisgw.AdventOfCodePuzzle;

import java.time.Year;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.Stream.Builder;


/**
 * https://adventofcode.com/2020/day/11
 */
public class SeatingSystem extends AdventOfCodePuzzle {

    private final SeatPosition[][] positions;


    public SeatingSystem(List<String> inputLines) {
        super(inputLines);
        positions = parseSeatLayout(inputLines);
    }


    private SeatPosition[][] parseSeatLayout(List<String> inputLines) {
        SeatPosition[][] positions = new SeatPosition[inputLines.size()][];
        for (int y = 0; y < inputLines.size(); y++) {
            String line = inputLines.get(y);
            positions[y] = new SeatPosition[line.length()];
            for (int x = 0; x < line.length(); x++) {
                char c = line.charAt(x);
                if (c == 'L') {
                    positions[y][x] = new SeatPosition(x, y);
                } else if (c != '.') {
                    throw new IllegalArgumentException(String.format("Unexpected seat layout at (%d,%d)=%s", x, y, c));
                }
            }
        }
        return positions;
    }


    public SeatPosition at(int x, int y) {
        if (isInsideSeatGrid(x, y)) {
            return positions[y][x];
        } else {
            return null;
        }
    }

    private boolean isInsideSeatGrid(int x, int y) {
        return 0 <= y && y < rowCount() && 0 <= x && x < columnCount();
    }


    public boolean simulatePeopleArriving() {
        Set<SeatPosition> emptySeatsBecomesOccupied = emptySeatsBecomesOccupied().collect(Collectors.toSet());
        emptySeatsBecomesOccupied.forEach(seatPosition -> seatPosition.setEmptySeat(false));

        Set<SeatPosition> occupiedSeatsBecomesEmpty = occupiedSeatsBecomesEmpty().collect(Collectors.toSet());
        occupiedSeatsBecomesEmpty.forEach(seatPosition -> seatPosition.setEmptySeat(true));
        return !(emptySeatsBecomesOccupied.isEmpty() && occupiedSeatsBecomesEmpty.isEmpty());
    }


    /**
     * If a seat is empty (L) and there are no occupied seats adjacent to it, the seat becomes occupied.
     *
     * @return true if any empty seat (L) becomes occupied.
     */
    private Stream<SeatPosition> emptySeatsBecomesOccupied() {
        Stream<SeatPosition> emptySeats = seatPositions().filter(SeatPosition::isEmptySeat);
        return emptySeats.filter(this::hasNoAdjacentOccupiedSeats);
    }

    private boolean hasNoAdjacentOccupiedSeats(SeatPosition emptySeatPosition) {
        return adjacentSeats(emptySeatPosition).allMatch(SeatPosition::isEmptySeat);
    }


    /**
     * If a seat is occupied (#) and four or more seats adjacent to it are also occupied, the seat becomes empty.
     *
     * @return true if any occupied seat (#) becomes empty.
     */
    private Stream<SeatPosition> occupiedSeatsBecomesEmpty() {
        Stream<SeatPosition> occupiedSeats = seatPositions().filter(SeatPosition::isOccupiedSeat);
        return occupiedSeats.filter(this::hasMoreThan4AdjacentOccupiedSeats);
    }

    private boolean hasMoreThan4AdjacentOccupiedSeats(SeatPosition seatPosition) {
        long adjecentOccupiedSeatCount = adjacentSeats(seatPosition).filter(SeatPosition::isOccupiedSeat).count();
        return adjecentOccupiedSeatCount >= 4;
    }


    private Stream<SeatPosition> adjacentSeats(SeatPosition seatPosition) {
        Builder<SeatPosition> adjacentSeats = Stream.builder();
        for (int dy = -1; dy <= 1; dy++) {
            for (int dx = -1; dx <= 1; dx++) {
                if (dy == 0 && dx == 0) {
                    continue;
                }
                int y = seatPosition.getY() + dy;
                int x = seatPosition.getX() + dx;
                SeatPosition adjacentSeat = at(x, y);
                adjacentSeats.add(adjacentSeat);
            }
        }
        return adjacentSeats.build().filter(Objects::nonNull);
    }


    public Stream<SeatPosition> seatPositions() {
        return Arrays.stream(positions).flatMap(Arrays::stream).filter(Objects::nonNull);
    }


    public int rowCount() {
        return positions.length;
    }

    public int columnCount() {
        return positions[0].length;
    }


    @Override
    public Number solveFirstPart() {
        while (true) {
            boolean evenMorePeopleAreArriving = simulatePeopleArriving();
            if (!evenMorePeopleAreArriving) {
                break;
            }
        }
        return seatPositions().filter(SeatPosition::isOccupiedSeat).count();
    }


    @Override
    public Number solveSecondPart() {
        return null;
    }


    @Override
    public Year getYear() {
        return Year.of(2020);
    }

    @Override
    public int getDay() {
        return 11;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(rowCount() * (columnCount() + 2));
        for (int y = 0; y < rowCount(); y++) {
            for (int x = 0; x < columnCount(); x++) {
                SeatPosition seatPosition = at(x, y);
                if (seatPosition == null) {
                    sb.append('.');
                } else if (seatPosition.isEmptySeat()) {
                    sb.append('L');
                } else {
                    sb.append('#');
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

}
