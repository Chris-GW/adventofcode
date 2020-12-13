package de.adventofcode.chrisgw.day13;

import de.adventofcode.chrisgw.AdventOfCodePuzzle;

import java.time.Year;
import java.util.ArrayList;
import java.util.Comparator;
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
        for (int i = 0; i < splittedBusPlan.length; i++) {
            String busIdStr = splittedBusPlan[i];
            if (!"x".equals(busIdStr)) {
                int busId = Integer.parseInt(busIdStr);
                Bus bus = new Bus(busId, i);
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

    private Optional<Bus> findBusForDepartAt(long timestamp) {
        return busPlan.stream().filter(bus -> bus.isAirportDepartureTime(timestamp)).findAny();
    }


    // part 02

    @Override
    public Long solveSecondPart() {
        long[] moduloN = busPlan.stream()
                .sorted(Comparator.comparing(Bus::getId).reversed())
                .mapToLong(Bus::getId)
                .toArray();
        long[] restA = busPlan.stream()
                .sorted(Comparator.comparing(Bus::getId).reversed())
                .mapToLong(Bus::getDepartureIndex)
                .toArray();

        long timestamp = restA[0];
        long stepSize = moduloN[0];
        for (int i = 1; i < moduloN.length; i++) {
            long modulo = moduloN[i];
            long rest = restA[i];

            while (timestamp % modulo != rest) {
                System.out.printf("%15d mod %3d -> %3d != %3d Continue%n", timestamp, modulo, timestamp % modulo, rest);
                timestamp += stepSize;
            }
            stepSize *= modulo;
            System.out.printf("%15d mod %3d -> %3d OK with new StepSize %d%n", timestamp, modulo, rest, stepSize);
        }
        return timestamp;
    }

}