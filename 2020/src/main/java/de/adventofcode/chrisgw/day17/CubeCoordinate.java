package de.adventofcode.chrisgw.day17;

import lombok.Data;

import java.util.ArrayList;
import java.util.Collection;


@Data
public class CubeCoordinate {

    private final int x;
    private final int y;
    private final int z;


    public Collection<CubeCoordinate> neighborCoordinates() {
        Collection<CubeCoordinate> neighborCoordinates = new ArrayList<>(3 * 3 * 3);
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                for (int dz = -1; dz <= 1; dz++) {
                    if (dx == 0 && dy == 0 && dz == 0) {
                        continue;
                    }
                    int x = this.x + dx;
                    int y = this.y + dy;
                    int z = this.z + dz;
                    CubeCoordinate coordinate = new CubeCoordinate(x, y, z);
                    neighborCoordinates.add(coordinate);
                }
            }
        }
        return neighborCoordinates;
    }

}
