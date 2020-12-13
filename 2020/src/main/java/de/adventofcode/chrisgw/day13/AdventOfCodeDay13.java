package de.adventofcode.chrisgw.day13;

import de.adventofcode.chrisgw.AdventOfCodePuzzle;

import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


/**
 * https://adventofcode.com/2020/day/13
 */
public class AdventOfCodeDay13 extends AdventOfCodePuzzle {

    private final int earliestTime;
    private final List<Bus> busPlan;


    public AdventOfCodeDay13(List<String> inputLines) {
        super(Year.of(2020), 13, inputLines);
        earliestTime = Integer.parseInt(inputLines.get(0));
        String[] splittedBusPlan = inputLines.get(1).split(",");
        busPlan = new ArrayList<>(splittedBusPlan.length);
        for (String busIdStr : splittedBusPlan) {
            if (!"x".equals(busIdStr)) {
                int busId = Integer.parseInt(busIdStr);
                Bus bus = new Bus(busId);
                busPlan.add(bus);
            }
        }
    }


    @Override
    public Integer solveFirstPart() {
        for (int timestamp = earliestTime; true; timestamp++) {
            Optional<Bus> earliestBus = findBusForDepartAt(timestamp);
            if (earliestBus.isPresent()) {
                int waitTime = timestamp - earliestTime;
                return earliestBus.get().getId() * waitTime;
            }
        }
    }

    private Optional<Bus> findBusForDepartAt(int timestamp) {
        return busPlan.stream().filter(bus -> bus.isAirportDepartureTime(timestamp)).findAny();
    }


    // part 02

    @Override
    public Number solveSecondPart() {
        return 0;
    }

}
