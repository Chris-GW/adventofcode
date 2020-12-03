package chrisgw.day01;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

import static chrisgw.TestUtils.readAllLinesOfClassPathResource;
import static chrisgw.day01.AdventOfCodeDay01.findSum2020PairEntries;
import static chrisgw.day01.AdventOfCodeDay01.findSum2020TripleEntries;
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
    public void findSum2020PairEntries_example() {
        Pair<Integer, Integer> sum2020Pair = findSum2020PairEntries(exampleExpenseReportLines);
        int pairProduct = calculatePairProduct(sum2020Pair);
        assertEquals("entriesProduct", 1721 * 299, pairProduct);
    }


    @Test
    public void findSum2020PairEntries_myPuzzleInput() {
        List<Integer> expenseReportLines = readAllLinesOfClassPathResource("/puzzleInputDay01.txt").stream()
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toList());
        Pair<Integer, Integer> sum2020Pair = findSum2020PairEntries(expenseReportLines);
        int pairProduct = calculatePairProduct(sum2020Pair);
        assertEquals("entriesProduct", 1165 * 855, pairProduct);
    }


    private int calculatePairProduct(Pair<Integer, Integer> pair) {
        return pair.getLeft() * pair.getRight();
    }


    // part 02


    @Test
    public void findSum2020TripleEntries_example() {
        Triple<Integer, Integer, Integer> sum2020Entries = findSum2020TripleEntries(exampleExpenseReportLines);
        assertEquals("entriesProduct", 979 * 366 * 675, calculateTripleProduct(sum2020Entries));
    }


    @Test
    public void findSum2020TripleEntries_myPuzzleInput() {
        List<Integer> expenseReportLines = readAllLinesOfClassPathResource("/puzzleInputDay01.txt").stream()
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toList());
        Triple<Integer, Integer, Integer> sum2020Triple = findSum2020TripleEntries(expenseReportLines);
        assertEquals("entriesProduct", 168 * 185 * 1667, calculateTripleProduct(sum2020Triple));
    }


    private int calculateTripleProduct(Triple<Integer, Integer, Integer> triple) {
        return triple.getLeft() * triple.getMiddle() * triple.getRight();
    }

}
