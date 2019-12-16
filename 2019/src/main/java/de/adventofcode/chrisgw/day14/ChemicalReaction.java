package de.adventofcode.chrisgw.day14;

import lombok.Data;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Data
public class ChemicalReaction {

    private final List<ChemicalQuantity> inputChemicals;
    private final ChemicalQuantity outputChemical;


    public static ChemicalReaction parseChemicalReaction(String chemicalRectionStr) {
        String[] splitInputAndOutputStr = chemicalRectionStr.split("\\s*=>\\s*");
        List<ChemicalQuantity> inputChemicals = Pattern.compile(",\\s+")
                .splitAsStream(splitInputAndOutputStr[0])
                .map(ChemicalQuantity::parse)
                .collect(Collectors.toList());
        ChemicalQuantity outputChemical = ChemicalQuantity.parse(splitInputAndOutputStr[1]);
        return new ChemicalReaction(inputChemicals, outputChemical);
    }


    public Stream<ChemicalQuantity> neededInputChemicals(int neededQuantity) {
        // x * produce = needed
        int produceQuantity = outputChemical.getQuantity();
        int neededReactions = (neededQuantity + produceQuantity - 1) / produceQuantity;
        List<ChemicalQuantity> neededChemicals = inputChemicals.stream()
                .map(chemicalQuantity -> chemicalQuantity.multiply(neededReactions))
                .collect(Collectors.toList());
        System.out.println(this.multiply(neededReactions));
        return neededChemicals.stream();
    }

    public ChemicalReaction multiply(int quantity) {
        List<ChemicalQuantity> newInputChemicals = this.inputChemicals.stream()
                .map(chemicalQuantity -> chemicalQuantity.multiply(quantity))
                .collect(Collectors.toList());
        ChemicalQuantity newOutputQuantity = outputChemical.multiply(quantity);
        return new ChemicalReaction(newInputChemicals, newOutputQuantity);
    }


    public boolean isOreInputReaction() {
        return inputChemicals.stream().allMatch(ChemicalQuantity::isOre);
    }

    public String getOutputChemicalName() {
        return getOutputChemical().getChemical();
    }


    @Override
    public String toString() {
        String inputChemicalsStr = inputChemicals.stream()
                .map(ChemicalQuantity::toString)
                .collect(Collectors.joining(", "));
        return inputChemicalsStr + " => " + outputChemical;
    }

}
