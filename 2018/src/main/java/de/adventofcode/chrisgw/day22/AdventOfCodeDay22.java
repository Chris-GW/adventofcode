package de.adventofcode.chrisgw.day22;

import java.util.Arrays;
import java.util.stream.Stream;


/**
 * 2018 Day 22: Mode Maze
 * https://adventofcode.com/2018/day/22
 */
public class AdventOfCodeDay22 {

    private final CaveSquareRegion[][] cave;
    private final CaveSquareRegion targetRegion;
    private final int depth;


    public AdventOfCodeDay22(int depth, int[] target) {
        this.depth = depth;
        this.targetRegion = new CaveSquareRegion(target[0], target[1]);

        this.cave = new CaveSquareRegion[caveHeight()][caveWidth()];
        for (int y = 0; y < caveHeight(); y++) {
            for (int x = 0; x < caveWidth(); x++) {
                this.cave[y][x] = new CaveSquareRegion(x, y);
            }
        }
    }


    public int toalRiskLevel() {
        return caveRegions().map(CaveSquareRegion::getCaveReqionType).mapToInt(CaveReqionType::getRiskLevel).sum();
    }


    public Stream<CaveSquareRegion> caveRegions() {
        return Arrays.stream(cave).flatMap(Arrays::stream);
    }


    public CaveSquareRegion caveRegionAt(int x, int y) {
        return cave[y][x];
    }


    public int caveWidth() {
        return targetRegion.getX();
    }

    public int caveHeight() {
        return targetRegion.getY();
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < caveHeight(); y++) {
            for (int x = 0; x < caveWidth(); x++) {
                CaveSquareRegion caveRegion = caveRegionAt(x, y);
                CaveReqionType caveReqionType = caveRegion.getCaveReqionType();
                sb.append(caveReqionType.getTypeSign());
            }
            sb.append("\n");
        }
        sb.setCharAt(0, 'M');
        sb.setCharAt(sb.length() - 2, 'T');
        return sb.toString();
    }


    public class CaveSquareRegion {

        private final int x;
        private final int y;
        private int geologicIndex = -1;
        private int erosionLevel = -1;


        public CaveSquareRegion(int x, int y) {
            this.x = x;
            this.y = y;
        }


        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }


        public int geologicIndex() {
            if (geologicIndex == -1) {
                if (isMouthOfCave() || isTargetRegion()) {
                    return 0;
                } else if (y == 0) {
                    return x * 16807;
                } else if (x == 0) {
                    return y * 48271;
                } else {
                    int erosionLevelX = caveRegionAt(x - 1, y).erosionLevel();
                    int erosionLevelY = caveRegionAt(x, y - 1).erosionLevel();
                    geologicIndex = erosionLevelX * erosionLevelY;
                }
            }
            return geologicIndex;
        }

        public int erosionLevel() {
            if (erosionLevel == -1) {
                erosionLevel = (geologicIndex() + depth) % 20183;
            }
            return erosionLevel;
        }


        private boolean isTargetRegion() {
            return this.x == targetRegion.x && this.y == targetRegion.y;
        }


        public boolean isMouthOfCave() {
            return x == 0 && y == 0;
        }


        public CaveReqionType getCaveReqionType() {
            int erosionLevel = erosionLevel() % 3;
            return CaveReqionType.fromRiskLevel(erosionLevel);
        }

    }

}
