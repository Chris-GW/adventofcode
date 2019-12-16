package de.adventofcode.chrisgw.day14;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * 2019 Day 14: Space Stoichiometry
 * https://adventofcode.com/2019/day/14
 */
public class AdventOfCodeDay14 {

    private Map<String, ChemicalReaction> chemicalReactions;


    public AdventOfCodeDay14(List<String> chemicalReactionLines) {
        this.chemicalReactions = chemicalReactionLines.stream()
                .map(ChemicalReaction::parseChemicalReaction)
                .collect(Collectors.toMap(ChemicalReaction::getOutputChemicalName, Function.identity()));
    }


    public int produceFuel() {
        ChemicalQuantity oreQuantity = produceFuel(1);
        return oreQuantity.getQuantity();
    }


    public ChemicalQuantity produceFuel(int quantity) {
        ChemicalQuantity fuelQuantity = new ChemicalQuantity("FUEL", quantity);
        List<ChemicalQuantity> neededChemicalList = neededChemicals(fuelQuantity).collect(Collectors.toList());
        return neededChemicalList.stream()
                .flatMap(chemicalQuantity -> findReaction(chemicalQuantity).neededInputChemicals(
                        chemicalQuantity.getQuantity()))
                .reduce(ChemicalQuantity.zero(), ChemicalQuantity::add);
    }

    private Stream<ChemicalQuantity> neededChemicals(ChemicalQuantity chemicalQuantity) {
        ChemicalReaction chemicalReaction = findReaction(chemicalQuantity);
        if (chemicalReaction.isOreInputReaction()) {
            return Stream.of(chemicalQuantity);
        } else if (chemicalQuantity.isOre()) {
            return Stream.of(chemicalQuantity);
        } else {
            return neededChemicalsFor(chemicalQuantity);
        }
    }

    private Stream<ChemicalQuantity> neededChemicalsFor(ChemicalQuantity chemicalQuantity) {
        ChemicalReaction chemicalReaction = findReaction(chemicalQuantity);
        int neededQuantity = chemicalQuantity.getQuantity();
        Map<String, ChemicalQuantity> neededChemicalQuantityMap = chemicalReaction.neededInputChemicals(neededQuantity)
                .flatMap(this::neededChemicals)
                .collect(Collectors.groupingBy(ChemicalQuantity::getChemical,
                        Collectors.reducing(ChemicalQuantity.zero(), ChemicalQuantity::add)));
        return neededChemicalQuantityMap.values().stream();
    }


    private ChemicalReaction findReaction(ChemicalQuantity chemicalQuantity) {
        ChemicalReaction chemicalReaction = chemicalReactions.get(chemicalQuantity.getChemical());
        if (chemicalReaction == null) {
            throw new IllegalArgumentException("Could not find chemical reaction, which produces: " + chemicalQuantity);
        }
        return chemicalReaction;
    }


}
