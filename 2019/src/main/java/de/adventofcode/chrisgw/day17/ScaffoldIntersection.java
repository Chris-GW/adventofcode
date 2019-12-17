package de.adventofcode.chrisgw.day17;

import lombok.Data;


@Data
public class ScaffoldIntersection {

    private final int row;
    private final int column;


    public int alignmentParameter() {
        return row * column;
    }


}
