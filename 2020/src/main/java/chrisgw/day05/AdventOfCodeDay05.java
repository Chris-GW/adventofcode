package chrisgw.day05;

import java.util.Comparator;
import java.util.List;


/**
 * https://adventofcode.com/2020/day/5
 */
public class AdventOfCodeDay05 {

    private AdventOfCodeDay05() {
    }


    public static BoardingPass findMaxBoardingPassId(List<String> scannedBoardingPasses) {
        return scannedBoardingPasses.stream()
                .map(BoardingPass::new)
                .max(Comparator.comparing(BoardingPass::getId))
                .orElse(null);
    }

}
