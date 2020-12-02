package chrisgw.day01;


import org.apache.commons.math3.util.Pair;

import java.util.List;


/**
 * https://adventofcode.com/2020/day/1
 */
public class AdventOfCodeDay01 {

    
    public static Pair<Integer, Integer> findSum2020Entries(List<Integer> expenseReport) {
        int requestedSum = 2020;

        for (int i = 0; i < expenseReport.size(); i++) {
            int firstExpenseReportEntry = expenseReport.get(i);

            for (int j = i + 1; j < expenseReport.size(); j++) {
                int secondExpenseReportEntry = expenseReport.get(j);
                if (firstExpenseReportEntry + secondExpenseReportEntry == requestedSum) {
                    return Pair.create(firstExpenseReportEntry, secondExpenseReportEntry);
                }
            }
        }
        throw new IllegalArgumentException("Expense Report doesn't contain any sum for " + requestedSum);
    }

}
