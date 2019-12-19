package de.adventofcode.chrisgw.day15;

import de.adventofcode.chrisgw.day03.Direction;
import de.adventofcode.chrisgw.intcode.IntCodeProgram;
import lombok.Data;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


@Data
public class RepairDroid {

    public static final long HIT_WALL_STATUS_CODE = 0;
    public static final long MOVED_STATUS_CODE = 1;
    public static final long REACHED_LOCATION_STATUS_CODE = 2;


    private final IntCodeProgram droidProgram;

    private int x = 0;
    private int y = 0;
    private Map<Integer, Map<Integer, Integer>> map = new HashMap();


    public long move(Direction direction) {
        droidProgram.addInput(moveCommandCode(direction));
        droidProgram.run();
        long statusCode = droidProgram.nextOutput();

        if (statusCode == HIT_WALL_STATUS_CODE) {
            putWallIn(direction);
            return statusCode;
        } else if (statusCode == MOVED_STATUS_CODE) {
            this.x += direction.getDx();
            this.y += direction.getDy();
            putSpace();
            return statusCode;
        } else if (statusCode == REACHED_LOCATION_STATUS_CODE) {
            return statusCode;
        } else {
            throw new IllegalArgumentException("Unknown statuscode from repair droid: " + statusCode);
        }
    }

    private void putWallIn(Direction direction) {
        int x = this.x + direction.getDx();
        int y = this.y + direction.getDy();
        map.computeIfAbsent(y, i -> new HashMap<>()).put(x, 0);
    }

    private void putSpace() {
        map.computeIfAbsent(y, i -> new HashMap<>()).put(x, 1);
    }

    private int moveCommandCode(Direction direction) {
        switch (direction) {
        case UP:
            return 1;
        case DOWN:
            return 2;
        case LEFT:
            return 3;
        case RIGHT:
            return 4;
        default:
            throw new IllegalArgumentException("Unknown direction: " + direction);
        }
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int y = -200; y <= 200; y++) {
            for (int x = -200; x <= 200; x++) {
                sb.append(mapSignAt(x, y));
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    private char mapSignAt(int x, int y) {
        if (this.x == x && this.y == y) {
            return 'D';
        }
        int mapCode = map.getOrDefault(y, Collections.emptyMap()).getOrDefault(x, -1);
        switch (mapCode) {
        case -1:
            return ' ';
        case 0:
            return '#';
        case 1:
            return '.';
        default:
            throw new IllegalStateException("Unexpected value: " + mapCode);
        }
    }


}
