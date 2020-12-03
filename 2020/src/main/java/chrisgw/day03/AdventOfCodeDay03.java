package chrisgw.day03;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;


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


    public List<TreeMapCoordinate> followSlope(int dx, int dy) {
        List<TreeMapCoordinate> visitedMapCoordinates = new ArrayList<>();
        TreeMapCoordinate currentCoordinate = get(0, 0);
        visitedMapCoordinates.add(currentCoordinate);

        while (!isBelowMap(currentCoordinate)) {
            int x = currentCoordinate.getX() + dx;
            int y = currentCoordinate.getY() + dy;
            currentCoordinate = get(x, y);
            visitedMapCoordinates.add(currentCoordinate);
        }
        return visitedMapCoordinates;
    }

    public long countTreesAlongSlope(int dx, int dy) {
        return followSlope(dx, dy).stream().filter(TreeMapCoordinate::isTree).count();

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

}
