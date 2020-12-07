package chrisgw.day07;

import lombok.Data;


@Data
public class Bag {

    private final String colorCode;


    @Override
    public String toString() {
        return colorCode;
    }

}
