package chrisgw.day03;

import lombok.Data;


@Data
public class TreeMapCoordinate {

    private final int x;
    private final int y;
    private final boolean empty;


    public boolean isTree() {
        return !empty;
    }

}
