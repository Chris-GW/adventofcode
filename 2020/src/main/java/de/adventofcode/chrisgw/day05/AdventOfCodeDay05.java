package de.adventofcode.chrisgw.day05;

import java.util.Comparator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.function.Predicate;
import java.util.stream.IntStream;


/**
 * https://adventofcode.com/2020/day/5
 */
public class AdventOfCodeDay05 {

    private AdventOfCodeDay05() {
    }


    public static BoardingPass findMaxBoardingPassId(List<String> scannedBoardingPasses) {
        return scannedBoardingPasses.stream() //
                .map(BoardingPass::new) //
                .max(Comparator.naturalOrder()) //
                .orElse(null);
    }


    // part 02

    public static BoardingPass findFreeSeat(List<String> scannedBoardingPasses) {
        if (scannedBoardingPasses.isEmpty()) {
            return null;
        }
        SortedSet<BoardingPass> sortedBoardingPasses = scannedBoardingPasses.stream()
                .map(BoardingPass::new)
                .collect(TreeSet::new, TreeSet::add, TreeSet::addAll);
        BoardingPass firstBoardingPass = sortedBoardingPasses.first();
        BoardingPass lastBoardingPass = sortedBoardingPasses.last();
        return IntStream.range(firstBoardingPass.getId(), lastBoardingPass.getId())
                .mapToObj(BoardingPass::new)
                .filter(Predicate.not(sortedBoardingPasses::contains))
                .findAny()
                .orElse(null);
    }

}
