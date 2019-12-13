package de.adventofcode.chrisgw.day22;

import java.util.List;


/**
 * 2018 Day 22: Mode Maze
 * https://adventofcode.com/2018/day/22
 */
public class AdventOfCodeDay22 {


    private CaveSquareRegion[][] caveRegion;
    private CaveSquareRegion targetRegion;


    public AdventOfCodeDay22(CaveSquareRegion[][] caveRegion, CaveSquareRegion targetRegion) {
        this.caveRegion = caveRegion;
        this.targetRegion = targetRegion;
    }

    public static AdventOfCodeDay22 parseCaveScan(List<String> caveScan) {
        CaveSquareRegion[][] caveRegion = null;
        CaveSquareRegion targetRegion = null;
        return new AdventOfCodeDay22(caveRegion, targetRegion);
    }


    public CaveSquareRegion caveRegionAt(int x, int y) {
        return caveRegion[y][x];
    }


    public class CaveSquareRegion {

        private final int x;
        private final int y;
        private final int depth;


        public CaveSquareRegion(int x, int y, int depth) {
            this.x = x;
            this.y = y;
            this.depth = depth;
        }


        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getDepth() {
            return depth;
        }


        public int geologicIndex() {
            if (isMouthOfCave() || isTargetRegion()) {
                return 0;
            } else if (y == 0) {
                return x * 16807;
            } else if (x == 0) {
                return y * 48271;
            } else {
                int erosionLevelX = caveRegionAt(x - 1, y).erosionLevel();
                int erosionLevelY = caveRegionAt(x, y - 1).erosionLevel();
                return erosionLevelX * erosionLevelY;
            }
        }

        public int erosionLevel() {
            return (geologicIndex() + depth) % 20183;
        }


        private boolean isTargetRegion() {
            return equals(targetRegion);
        }


        public boolean isMouthOfCave() {
            return x == 0 && y == 0;
        }


        public CaveReqionType getCaveReqionType() {
            int erosionLevel = erosionLevel() % 3;
            switch (erosionLevel) {
            case 0:
                return CaveReqionType.ROCKY;
            case 1:
                return CaveReqionType.WET;
            case 2:
            default:
                return CaveReqionType.NARROW;
            }
        }


    }


    public enum CaveReqionType {
        ROCKY, NARROW, WET;
    }

}
