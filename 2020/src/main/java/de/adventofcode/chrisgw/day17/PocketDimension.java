package de.adventofcode.chrisgw.day17;

import de.adventofcode.chrisgw.AdventOfCodePuzzle;

import java.time.Year;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * https://adventofcode.com/2020/day/17
 */
public class PocketDimension extends AdventOfCodePuzzle {

    private final Map<CubeCoordinate, Boolean> cubeMap = new HashMap<>();
    private int cycle = 0;

    public PocketDimension(List<String> inputLines) {
        super(Year.of(2020), 17, inputLines);
        for (int y = 0; y < inputLines.size(); y++) {
            String inputLine = inputLines.get(y);
            for (int x = 0; x < inputLines.size(); x++) {
                char cubeStateSign = inputLine.charAt(x);
                if (cubeStateSign == '#') {
                    CubeCoordinate coordinate = new CubeCoordinate(x, y, 0);
                    activateCubeAt(coordinate);
                } else if (cubeStateSign != '.') {
                    throw new IllegalArgumentException("expect cubeStateSign to be [#.], but was " + cubeStateSign);
                }
            }
        }
    }


    private void activateCubeAt(CubeCoordinate coordinate) {
        cubeMap.put(coordinate, true);
        for (CubeCoordinate neighborCoordinate : coordinate.neighborCoordinates()) {
            cubeMap.merge(neighborCoordinate, false, (cubeIsActive, otherCubeIsActive) -> cubeIsActive);
        }
    }

    private void deactivateCubeAt(CubeCoordinate coordinate) {
        cubeMap.put(coordinate, false);
    }


    private boolean isActiveCubeAt(CubeCoordinate coordinate) {
        return cubeMap.getOrDefault(coordinate, false);
    }


    private Stream<CubeCoordinate> activeCubeCoordinates() {
        return cubeMap.entrySet().stream().filter(Entry::getValue).map(Entry::getKey);
    }

    private Stream<CubeCoordinate> inactiveCubeCoordinates() {
        return cubeMap.entrySet().stream().filter(Predicate.not(Entry::getValue)).map(Entry::getKey);
    }


    /**
     * If a cube is active and exactly 2 or 3 of its neighbors are also active, the cube remains active.
     * Otherwise, the cube becomes inactive.
     *
     * @return active {@link CubeCoordinate} which become in next simulated cycle inactive
     */
    private Collection<CubeCoordinate> activeCubesWhichBecomeInactive() {
        return activeCubeCoordinates().filter(coordinate -> {
            long activeNeighborCubeCount = countActiveNeighborCubes(coordinate);
            return activeNeighborCubeCount != 2 && activeNeighborCubeCount != 3;
        }).collect(Collectors.toList());
    }


    /**
     * If a cube is inactive but exactly 3 of its neighbors are active, the cube becomes active.
     * Otherwise, the cube remains inactive.
     *
     * @return inactive {@link CubeCoordinate} which become in next simulated cycle active
     */
    private Collection<CubeCoordinate> inactiveCubesWhichBecomeActive() {
        return inactiveCubeCoordinates().filter(coordinate -> countActiveNeighborCubes(coordinate) == 3)
                .collect(Collectors.toList());
    }


    private long countActiveNeighborCubes(CubeCoordinate coordinate) {
        return coordinate.neighborCoordinates().stream().filter(this::isActiveCubeAt).count();
    }


    @Override
    public Long solveFirstPart() {
        while (cycle < 6) {
            nextCycle();
        }
        return activeCubeCoordinates().count();
    }

    @Override
    public Long solveSecondPart() {
        while (cycle < 6) {
            nextCycle();
        }
        return activeCubeCoordinates().count();
    }


    private void nextCycle() {
        Collection<CubeCoordinate> becomeInactiveCubes = activeCubesWhichBecomeInactive();
        Collection<CubeCoordinate> becomeActiveCubes = inactiveCubesWhichBecomeActive();
        becomeInactiveCubes.forEach(this::deactivateCubeAt);
        becomeActiveCubes.forEach(this::activateCubeAt);
        cycle++;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof PocketDimension))
            return false;
        if (!super.equals(o))
            return false;

        PocketDimension that = (PocketDimension) o;

        if (cycle != that.cycle)
            return false;
        return cubeMap.equals(that.cubeMap);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + cubeMap.hashCode();
        result = 31 * result + cycle;
        return result;
    }

}
