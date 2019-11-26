package de.adventofcode.chrisgw.day18;

import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;
import java.util.stream.Stream.Builder;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;


public class SettlersOfTheNorthPole {

    private LandAcre[][] area;
    private int elapsedMinutes;


    private SettlersOfTheNorthPole(int size) {
        area = new LandAcre[size][size];
        elapsedMinutes = 0;
    }


    public static SettlersOfTheNorthPole parseNorthPoleAcres(List<String> northPoleAcresString) {
        SettlersOfTheNorthPole northPole = new SettlersOfTheNorthPole(northPoleAcresString.size());
        for (int y = 0; y < northPoleAcresString.size(); y++) {
            String row = northPoleAcresString.get(y);
            for (int x = 0; x < row.length(); x++) {
                char symbol = row.charAt(x);
                LandAcreType type = LandAcreType.fromSymbol(symbol);
                LandAcre landAcre = northPole.new LandAcre(x, y, type);
                northPole.setLandArce(landAcre);
            }
        }
        return northPole;
    }


    public SettlersOfTheNorthPole nextMinute() {
        SettlersOfTheNorthPole next = new SettlersOfTheNorthPole(getSize());
        next.elapsedMinutes = this.elapsedMinutes + 1;
        landArces().map(LandAcre::nextMinute).forEach(next::setLandArce);
        return next;
    }


    public long resourceValue() {
        Map<LandAcreType, Long> acreTypeCount = countLandArcesByType();
        long treeCount = acreTypeCount.getOrDefault(LandAcreType.TREES, 0L);
        long lumberyardCount = acreTypeCount.getOrDefault(LandAcreType.LUMBERYARD, 0L);
        return treeCount * lumberyardCount;
    }

    public Stream<LandAcre> landArces() {
        return Arrays.stream(area).flatMap(Arrays::stream);
    }

    public Map<LandAcreType, Long> countLandArcesByType() {
        return landArces().collect(groupingBy(LandAcre::getType, counting()));
    }


    public LandAcre landAcreAt(int x, int y) {
        if (isValidLanAcreLocation(x, y)) {
            return area[y][x];
        }
        return null;
    }

    private void setLandArce(LandAcre landArce) {
        if (landArce == null || !isValidLanAcreLocation(landArce.getX(), landArce.getY())) {
            throw new IllegalArgumentException(
                    "Given LandArce doesn't fit in this area with an size = " + getSize() + ": " + landArce);
        }
        area[landArce.getY()][landArce.getX()] = landArce;
    }

    public boolean isValidLanAcreLocation(int x, int y) {
        return 0 <= x && x < getSize() && 0 <= y && y < getSize();
    }


    public int getElapsedMinutes() {
        return elapsedMinutes;
    }

    public int getSize() {
        return area.length;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder((getSize() + 1) * getSize());
        if (elapsedMinutes == 0) {
            sb.append("Initial state:\n");
        } else {
            sb.append("After ").append(elapsedMinutes).append(" minutes:\n");
        }

        for (int y = 0; y < getSize(); y++) {
            for (int x = 0; x < getSize(); x++) {
                LandAcre landAcre = landAcreAt(x, y);
                LandAcreType type = landAcre.getType();
                sb.append(type.getSymbol());
            }
            sb.append("\n");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }


    @Data
    public class LandAcre {

        private final int x;
        private final int y;
        private final LandAcreType type;


        public LandAcre withType(LandAcreType type) {
            return new LandAcre(this.x, this.y, type);
        }


        public LandAcre nextMinute() {
            switch (type) {
            case OPEN_GROUND:
                return openGroundChangeRule();
            case TREES:
                return treeChangeRule();
            case LUMBERYARD:
                return lumberyardChangeRule();
            default:
                throw new IllegalStateException("unexpected LandAcreType: " + type);
            }
        }


        public Stream<LandAcre> adjacentLandAcres() {
            Builder<LandAcre> adjacentLandArces = Stream.builder();
            for (int dy = -1; dy <= 1; dy++) {
                for (int dx = -1; dx <= 1; dx++) {
                    if (dy == 0 && dx == 0) {
                        continue;
                    }
                    LandAcre adjacentLandAcre = landAcreAt(x + dx, y + dy);
                    adjacentLandArces.add(adjacentLandAcre);
                }
            }
            return adjacentLandArces.build().filter(Objects::nonNull);
        }

        public Map<LandAcreType, Long> countAdjacentLandArcesByType() {
            return adjacentLandAcres().collect(groupingBy(LandAcre::getType, counting()));
        }


        private LandAcre openGroundChangeRule() {
            Map<LandAcreType, Long> adjacentlandAcreTypes = countAdjacentLandArcesByType();
            long adjacentTrees = adjacentlandAcreTypes.getOrDefault(LandAcreType.TREES, 0L);
            if (adjacentTrees >= 3) {
                return withType(LandAcreType.TREES);
            } else {
                return this;
            }
        }

        private LandAcre treeChangeRule() {
            Map<LandAcreType, Long> adjacentlandAcreTypes = countAdjacentLandArcesByType();
            long adjacentLumberYards = adjacentlandAcreTypes.getOrDefault(LandAcreType.LUMBERYARD, 0L);
            if (adjacentLumberYards >= 3) {
                return withType(LandAcreType.LUMBERYARD);
            } else {
                return this;
            }
        }

        private LandAcre lumberyardChangeRule() {
            Map<LandAcreType, Long> adjacentlandAcreTypes = countAdjacentLandArcesByType();
            long adjacentLumberYards = adjacentlandAcreTypes.getOrDefault(LandAcreType.LUMBERYARD, 0L);
            long adjacentTrees = adjacentlandAcreTypes.getOrDefault(LandAcreType.TREES, 0L);
            if (adjacentLumberYards >= 1 && adjacentTrees >= 1) {
                return withType(LandAcreType.LUMBERYARD);
            } else {
                return withType(LandAcreType.OPEN_GROUND);
            }
        }

    }


    public enum LandAcreType {
        OPEN_GROUND('.'), TREES('|'), LUMBERYARD('#');

        private char symbol;


        LandAcreType(char symbol) {
            this.symbol = symbol;
        }

        public static LandAcreType fromSymbol(char symbol) {
            for (LandAcreType type : LandAcreType.values()) {
                if (type.getSymbol() == symbol) {
                    return type;
                }
            }
            return null;
        }


        public char getSymbol() {
            return symbol;
        }

    }

}
