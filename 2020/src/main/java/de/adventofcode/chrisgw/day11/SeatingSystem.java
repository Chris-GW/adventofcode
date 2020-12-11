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


    public SeatPosition at(int x, int y) {
        if (isInsideSeatGrid(x, y)) {
            return positions[y][x];
        } else {
            return new SeatPosition(x, y, SeatPositionContent.FLOOR);
        }
    }

    private boolean isInsideSeatGrid(SeatPosition seatPosition) {
        return isInsideSeatGrid(seatPosition.getX(), seatPosition.getY());
    }

    private boolean isInsideSeatGrid(int x, int y) {
        return 0 <= y && y < rowCount() && 0 <= x && x < columnCount();
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


    private Stream<SeatPosition> adjacentSeats(SeatPosition seatPosition) {
        return Arrays.stream(Direction.values())
                .map(direction -> adjacentPosition(seatPosition, direction))
                .filter(SeatPosition::isSeat);
    }

    private SeatPosition adjacentPosition(SeatPosition seatPosition, Direction direction) {
        int x = seatPosition.getX() + direction.getDx();
        int y = seatPosition.getY() + direction.getDy();
        return at(x, y);
    }


    @Override
    public Number solveFirstPart() {
        PeopleSeatOccupationBehaviour peopleSeatOccupationBehaviour = new FirstPartPeopleSeatOccupationBehaviour();
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


    @Override
    public Number solveSecondPart() {
        PeopleSeatOccupationBehaviour peopleSeatOccupationBehaviour = new SecondPartPeopleSeatOccupationBehaviour();
        while (true) {
            boolean evenMorePeopleAreArriving = simulatePeopleArriving(peopleSeatOccupationBehaviour);
            if (!evenMorePeopleAreArriving) {
                break;
            }
        }
        return countOccupiedSeats();
    }


    private Stream<SeatPosition> firstSeatsInSight(SeatPosition seatPosition) {
        return Arrays.stream(Direction.values())
                .map(direction -> firstSeatsInSight(seatPosition, direction))
                .filter(Optional::isPresent)
                .map(Optional::get);
    }

    public Optional<SeatPosition> firstSeatsInSight(SeatPosition startingSeatPosition, Direction direction) {
        return Stream.iterate(startingSeatPosition, seatPosition -> {
            int x = seatPosition.getX() + direction.getDx();
            int y = seatPosition.getY() + direction.getDy();
            return at(x, y);
        }).skip(1).takeWhile(this::isInsideSeatGrid).filter(SeatPosition::isSeat).findFirst();
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


    public class FirstPartPeopleSeatOccupationBehaviour implements PeopleSeatOccupationBehaviour {

        @Override
        public boolean wouldOccupySeat(SeatPosition seatPosition) {
            return seatPosition.isEmptySeat() && adjacentSeats(seatPosition).allMatch(SeatPosition::isEmptySeat);
        }


        @Override
        public boolean wouldFreeSeat(SeatPosition seatPosition) {
            return seatPosition.isOccupiedSeat() && hasMoreThanToleranceAdjacentOccupiedSeats(seatPosition, 4);
        }

        private boolean hasMoreThanToleranceAdjacentOccupiedSeats(SeatPosition seatPosition,
                int occupiedSeatTolerance) {
            long adjecentOccupiedSeatCount = adjacentSeats(seatPosition).filter(SeatPosition::isOccupiedSeat).count();
            return adjecentOccupiedSeatCount >= occupiedSeatTolerance;
        }

    }


    public class SecondPartPeopleSeatOccupationBehaviour implements PeopleSeatOccupationBehaviour {

        @Override
        public boolean wouldOccupySeat(SeatPosition seatPosition) {
            return seatPosition.isEmptySeat() && firstSeatsInSight(seatPosition).allMatch(SeatPosition::isEmptySeat);
        }


        @Override
        public boolean wouldFreeSeat(SeatPosition seatPosition) {
            return seatPosition.isOccupiedSeat() && hasMoreThanToleranceOccupiedSeatsInSight(seatPosition, 5);
        }

        private boolean hasMoreThanToleranceOccupiedSeatsInSight(SeatPosition seatPosition, int occupiedSeatTolerance) {
            long adjecentOccupiedSeatCount = firstSeatsInSight(seatPosition).filter(SeatPosition::isOccupiedSeat)
                    .count();
            return adjecentOccupiedSeatCount >= occupiedSeatTolerance;
        }

    }

}
