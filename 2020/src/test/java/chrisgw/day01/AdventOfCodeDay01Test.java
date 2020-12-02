package chrisgw.day01;

import chrisgw.TestUtils;
import org.apache.commons.math3.util.Pair;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

import static chrisgw.day01.AdventOfCodeDay01.findSum2020Entries;
import static org.junit.Assert.*;


public class AdventOfCodeDay01Test {

    private final List<Integer> exampleExpenseReportLines = List.of( //
            1721, //
            979, //
            366, //
            299, //
            675, //
            1456);


    @Test
    public void findSum2020Entries_example() {
        int expectedPairProduct = 1721 * 299;
        Pair<Integer, Integer> sum2020Entries = findSum2020Entries(exampleExpenseReportLines);
        assertEquals("entriesProduct", expectedPairProduct, calculatePairProduct(sum2020Entries));
    }


    @Test
    public void findSum2020Entries_myPuzzleInput() {
        int expectedPairProduct = 1165 * 855; // = 996075
        List<Integer> expenseReportLines = TestUtils.readAllLinesOfClassPathResource("/puzzleInputDay01.txt")
                .stream()
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toList());
        Pair<Integer, Integer> sum2020Entries = findSum2020Entries(expenseReportLines);
        int pairProduct = calculatePairProduct(sum2020Entries);
        assertEquals("entriesProduct", expectedPairProduct, pairProduct);
    }


    private int calculatePairProduct(Pair<Integer, Integer> sum2020Entries) {
        return sum2020Entries.getFirst() * sum2020Entries.getSecond();
    }

}
