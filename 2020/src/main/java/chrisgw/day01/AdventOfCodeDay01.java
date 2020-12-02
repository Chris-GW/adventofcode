package chrisgw.day01;


import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;

import java.util.List;


/**
 * https://adventofcode.com/2020/day/1
 */
public class AdventOfCodeDay01 {

    private AdventOfCodeDay01() {
    }


    public static Pair<Integer, Integer> findSum2020PairEntries(List<Integer> expenseReport) {
        int requestedSum = 2020;

        for (int i = 0; i < expenseReport.size(); i++) {
            int firstExpenseReportEntry = expenseReport.get(i);

            for (int j = i + 1; j < expenseReport.size(); j++) {
                int secondExpenseReportEntry = expenseReport.get(j);
                if (firstExpenseReportEntry + secondExpenseReportEntry == requestedSum) {
                    return new ImmutablePair<>(firstExpenseReportEntry, secondExpenseReportEntry);
                }
            }
        }
        throw new IllegalArgumentException("Expense Report doesn't contain any sum for " + requestedSum);
    }


    public static Triple<Integer, Integer, Integer> findSum2020TripleEntries(List<Integer> expenseReport) {
        int requestedSum = 2020;

        for (int i = 0; i < expenseReport.size(); i++) {
            int firstEntry = expenseReport.get(i);

            for (int j = i + 1; j < expenseReport.size(); j++) {
                int secondEntry = expenseReport.get(j);

                for (int k = j + 1; k < expenseReport.size(); k++) {
                    int thirdEntry = expenseReport.get(k);

                    int tripletEntrySum = firstEntry + secondEntry + thirdEntry;
                    if (tripletEntrySum == requestedSum) {
                        return new ImmutableTriple<>(firstEntry, secondEntry, thirdEntry);
                    }
                }
            }
        }
        throw new IllegalArgumentException("Expense Report doesn't contain any sum for " + requestedSum);
    }

}
