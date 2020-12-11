package de.adventofcode.chrisgw.day11;

import de.adventofcode.chrisgw.AdventOfCodePuzzle;

import java.time.Year;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


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
                char symbol = line.charAt(x);
                SeatPositionContent seatPositionContent = SeatPositionContent.fromSymbol(symbol);
                positions[y][x] = new SeatPosition(x, y, seatPositionContent);
            }
        }
        return positions;
    }


    @Override
    public Number solveFirstPart() {
        return countOccupiedSeatsInTheEnd(new FirstPartPeopleSeatOccupationBehaviour(this));
    }

    private boolean simulatePeopleArriving(PeopleSeatOccupationBehaviour peopleSeatOccupationBehaviour) {
        Set<SeatPosition> emptySeatsBecomesOccupied = seatPositions().filter(
                peopleSeatOccupationBehaviour::wouldFreeSeat).collect(Collectors.toSet());
        emptySeatsBecomesOccupied.forEach(SeatPosition::toggleSeatOccupancy);

        Set<SeatPosition> occupiedSeatsBecomesEmpty = seatPositions().filter(
                peopleSeatOccupationBehaviour::wouldOccupySeat).collect(Collectors.toSet());
        occupiedSeatsBecomesEmpty.forEach(SeatPosition::toggleSeatOccupancy);
        return !(emptySeatsBecomesOccupied.isEmpty() && occupiedSeatsBecomesEmpty.isEmpty());
    }

    private long countOccupiedSeatsInTheEnd(PeopleSeatOccupationBehaviour peopleSeatOccupationBehaviour) {
        while (true) {
            boolean evenMorePeopleAreArriving = simulatePeopleArriving(peopleSeatOccupationBehaviour);
            if (!evenMorePeopleAreArriving) {
                break;
            }
        }
        return countOccupiedSeats();
    }

    private long countOccupiedSeats() {
        return seatPositions().filter(SeatPosition::isOccupiedSeat).count();
    }


    public Stream<SeatPosition> adjacentSeats(SeatPosition seatPosition) {
        return Arrays.stream(Direction.values())
                .map(direction -> adjacentPosition(seatPosition, direction))
                .filter(SeatPosition::isSeat);
    }

    private SeatPosition adjacentPosition(SeatPosition seatPosition, Direction direction) {
        int x = seatPosition.getX() + direction.getDx();
        int y = seatPosition.getY() + direction.getDy();
        return at(x, y);
    }


    // part 02

    @Override
    public Number solveSecondPart() {
        return countOccupiedSeatsInTheEnd(new SecondPartPeopleSeatOccupationBehaviour(this));
    }

    public Stream<SeatPosition> firstSeatsInSight(SeatPosition seatPosition) {
        return Arrays.stream(Direction.values())
                .map(direction -> firstSeatInSight(seatPosition, direction))
                .filter(Optional::isPresent)
                .map(Optional::get);
    }

    private Optional<SeatPosition> firstSeatInSight(SeatPosition startingSeatPosition, Direction direction) {
        return Stream.iterate(startingSeatPosition, seatPosition -> {
            int x = seatPosition.getX() + direction.getDx();
            int y = seatPosition.getY() + direction.getDy();
            return at(x, y);
        }).skip(1).takeWhile(this::isInsideSeatGrid).filter(SeatPosition::isSeat).findFirst();
    }


    public SeatPosition at(int x, int y) {
        if (isInsideSeatGrid(x, y)) {
            return positions[y][x];
        } else {
            return new SeatPosition(x, y, SeatPositionContent.FLOOR);
        }
    }

    public boolean isInsideSeatGrid(SeatPosition seatPosition) {
        return isInsideSeatGrid(seatPosition.getX(), seatPosition.getY());
    }

    public boolean isInsideSeatGrid(int x, int y) {
        return 0 <= y && y < rowCount() && 0 <= x && x < columnCount();
    }


    public Stream<SeatPosition> seatPositions() {
        return Arrays.stream(positions).flatMap(Arrays::stream);
    }


    public int rowCount() {
        return positions.length;
    }

    public int columnCount() {
        return positions[0].length;
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
