package chrisgw.day05;

import lombok.Data;

import java.math.BigInteger;


@Data
public class BoardingPass implements Comparable<BoardingPass> {

    public static final int SEAT_PARTITIONING_ROW_POSITIONS = 7;

    private final int row;
    private final int column;


    public BoardingPass(int seatId) {
        column = seatId % 8;
        row = seatId / 8;
    }

    public BoardingPass(String seatPartitioning) {
        this.row = binarySpacePartitioning(seatPartitioning.substring(0, SEAT_PARTITIONING_ROW_POSITIONS), 'F', 'B');
        this.column = binarySpacePartitioning(seatPartitioning.substring(SEAT_PARTITIONING_ROW_POSITIONS), 'L', 'R');
    }

    public static int binarySpacePartitioning(String partitioning, char lowerCode, char upperCode) {
        int maxIndex = BigInteger.TWO.pow(partitioning.length()).intValue() - 1;
        int lowerIndex = 0;
        int upperIndex = maxIndex;
        for (int i = 0; i < partitioning.length(); i++) {
            char code = partitioning.charAt(i);
            int middleIndex = (lowerIndex + upperIndex) / 2;
            if (code == lowerCode) {
                upperIndex = middleIndex;
            } else if (code == upperCode) {
                lowerIndex = middleIndex + 1;
            } else {
                throw new IllegalArgumentException("unknown partitioning code at " + i + ": " + partitioning);
            }
        }
        return lowerIndex;
    }


    public int getId() {
        return row * 8 + column;
    }


    @Override
    public int compareTo(BoardingPass other) {
        return Integer.compare(this.getId(), other.getId());
    }

}
