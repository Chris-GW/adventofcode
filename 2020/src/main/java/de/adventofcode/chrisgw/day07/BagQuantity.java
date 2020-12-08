package de.adventofcode.chrisgw.day07;

import lombok.Data;


@Data
public class BagQuantity {

    private final int quantity;
    private final Bag bag;


    public boolean isSameBag(Bag bag) {
        return this.bag.equals(bag);
    }


    @Override
    public String toString() {
        return quantity + " " + bag;
    }

}
