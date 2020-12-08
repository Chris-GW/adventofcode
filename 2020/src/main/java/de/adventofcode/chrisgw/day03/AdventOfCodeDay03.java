package de.adventofcode.chrisgw.day03;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


/**
 * https://adventofcode.com/2020/day/3
 */
@RequiredArgsConstructor
public class AdventOfCodeDay03 {

    private final TreeMapCoordinate[][] treeMap;


    public static AdventOfCodeDay03 parseTreeMap(List<String> mapLines) {
        int rows = mapLines.size();
        TreeMapCoordinate[][] treeMap = new TreeMapCoordinate[rows][];
        for (int y = 0; y < rows; y++) {
            String mapRow = mapLines.get(y);
            treeMap[y] = new TreeMapCoordinate[mapRow.length()];
            for (int x = 0; x < mapRow.length(); x++) {
                char mapLetter = mapRow.charAt(x);
                if (mapLetter == '#') {
                    treeMap[y][x] = new TreeMapCoordinate(x, y, false);
                } else if (mapLetter == '.') {
                    treeMap[y][x] = new TreeMapCoordinate(x, y, true);
                } else {
                    throw new IllegalArgumentException("unknown map letter at (" + x + ";" + y + ") = " + mapRow);
                }
            }
        }
        return new AdventOfCodeDay03(treeMap);
    }


    public List<TreeMapCoordinate> followSlope(TobogganSlope tobogganSlope) {
        List<TreeMapCoordinate> visitedMapCoordinates = new ArrayList<>();
        TreeMapCoordinate currentCoordinate = get(0, 0);
        visitedMapCoordinates.add(currentCoordinate);

        while (!isBelowMap(currentCoordinate)) {
            int x = currentCoordinate.getX() + tobogganSlope.getDx();
            int y = currentCoordinate.getY() + tobogganSlope.getDy();
            currentCoordinate = get(x, y);
            visitedMapCoordinates.add(currentCoordinate);
        }
        return visitedMapCoordinates;
    }


    /**
     * part 01
     *
     * @param tobogganSlope
     * @return
     */
    public long countTreesAlongSlope(TobogganSlope tobogganSlope) {
        return followSlope(tobogganSlope).stream().filter(TreeMapCoordinate::isTree).count();
    }


    /**
     * part 02
     *
     * @param tobogganSlopes
     * @return
     */
    public long countTreesAlongSlopes(Set<TobogganSlope> tobogganSlopes) {
        return tobogganSlopes.stream().mapToLong(this::countTreesAlongSlope).reduce(1, (left, right) -> left * right);
    }


    public TreeMapCoordinate get(int x, int y) {
        if (0 <= y && y < treeMap.length) {
            x = x % treeMap[y].length;
            if (0 <= x && x < treeMap[y].length) {
                return treeMap[y][x];
            }
        }
        return new TreeMapCoordinate(x, y, true);
    }

    public boolean isBelowMap(TreeMapCoordinate treeMapCoordinate) {
        return treeMapCoordinate.getY() >= treeMap.length;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < treeMap.length; y++) {
            for (int x = 0; x < treeMap[y].length; x++) {
                TreeMapCoordinate mapCoordinate = get(x, y);
                if (mapCoordinate.isTree()) {
                    sb.append('#');
                } else {
                    sb.append('.');
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

}
