package de.adventofcode.chrisgw.day15;

import de.adventofcode.chrisgw.AdventOfCodePuzzle;

import java.time.Year;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * https://adventofcode.com/2020/day/15
 */
public class AdventOfCodeDay15 extends AdventOfCodePuzzle {

    private int currentTurn;
    private int lastNumber;
    private Map<Integer, Integer> spokenNumbers = new HashMap<>();


    public AdventOfCodeDay15(List<Integer> startingNumbers) {
        super(Year.of(2020), 15,
                List.of(startingNumbers.stream().map(String::valueOf).collect(Collectors.joining(","))));
        for (int i = 0; i < startingNumbers.size() - 1; i++) {
            spokenNumbers.put(startingNumbers.get(i), i + 1);
        }
        currentTurn = startingNumbers.size();
        lastNumber = startingNumbers.get(startingNumbers.size() - 1);
    }


    @Override
    public Integer solveFirstPart() {
        return solveTill(2020);
    }

    @Override
    public Integer solveSecondPart() {
        return solveTill(30_000_000);
    }


    private int solveTill(int playedTurns) {
        while (this.currentTurn < playedTurns) {
            int lastSpokenNumber = spokenNumbers.getOrDefault(lastNumber, 0);
            if (lastSpokenNumber > 0) {
                lastSpokenNumber = currentTurn - lastSpokenNumber;
            }
            spokenNumbers.put(lastNumber, currentTurn++);
            lastNumber = lastSpokenNumber;
        }
        return lastNumber;
    }

}
