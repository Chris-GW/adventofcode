package de.adventofcode.chrisgw.day14;

import lombok.Data;

import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Data
public class ChemicalQuantity {

    public static final Pattern CHEMICAL_QUANTITY_PATTERN = Pattern.compile("(\\d+)\\s+(\\w+)");

    private final String chemical;
    private final int quantity;


    public static ChemicalQuantity parse(String chemicalQuantityStr) {
        Matcher matcher = CHEMICAL_QUANTITY_PATTERN.matcher(chemicalQuantityStr);
        if (matcher.matches()) {
            int quantity1 = Integer.parseInt(matcher.group(1));
            String chemical1 = matcher.group(2);
            return new ChemicalQuantity(chemical1, quantity1);
        } else {
            throw new IllegalArgumentException(
                    String.format("Expect chemicalQuantity matching pattern '%s', but was: %s",
                            CHEMICAL_QUANTITY_PATTERN, chemicalQuantityStr));
        }
    }


    public static ChemicalQuantity zero() {
        return new ChemicalQuantity(null, 0);
    }


    public ChemicalQuantity add(ChemicalQuantity chemicalQuantity) {
        if (this.chemical != null) {
            return add(chemicalQuantity.getQuantity());
        } else {
            return chemicalQuantity.add(this.getQuantity());
        }
    }

    public ChemicalQuantity add(int quantity) {
        return new ChemicalQuantity(chemical, this.quantity + quantity);
    }

    public ChemicalQuantity multiply(int mul) {
        return new ChemicalQuantity(chemical, this.quantity * mul);
    }


    public boolean isOre() {
        return isChemical("ORE");
    }

    public boolean isFuel() {
        return isChemical("FUEL");
    }

    public boolean isChemical(String fuel) {
        return fuel.equals(chemical);
    }


    @Override
    public String toString() {
        return quantity + " " + chemical;
    }

}
