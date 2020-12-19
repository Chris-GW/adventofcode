package de.adventofcode.chrisgw.day17;

import lombok.Data;

import java.util.ArrayList;
import java.util.Collection;


@Data
public class CubeCoordinate {

    private final int x;
    private final int y;
    private final int z;
    private final int w;


    public Collection<CubeCoordinate> neighborCoordinates3d() {
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
                    CubeCoordinate coordinate = new CubeCoordinate(x, y, z, this.w);
                    neighborCoordinates.add(coordinate);
                }
            }
        }
        return neighborCoordinates;
    }


    public Collection<CubeCoordinate> neighborCoordinates4d() {
        Collection<CubeCoordinate> neighborCoordinates = new ArrayList<>(3 * 3 * 3 * 3);
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                for (int dz = -1; dz <= 1; dz++) {
                    for (int dw = -1; dw <= 1; dw++) {
                        if (dx == 0 && dy == 0 && dz == 0 && dw == 0) {
                            continue;
                        }
                        int x = this.x + dx;
                        int y = this.y + dy;
                        int z = this.z + dz;
                        int w = this.w + dw;
                        CubeCoordinate coordinate = new CubeCoordinate(x, y, z, w);
                        neighborCoordinates.add(coordinate);
                    }
                }
            }
        }
        return neighborCoordinates;
    }

}
