package de.adventofcode.chrisgw.day18;

import lombok.Data;
import lombok.Setter;

import java.util.*;
import java.util.stream.Stream;
import java.util.stream.Stream.Builder;

import static de.adventofcode.chrisgw.day18.SettlersOfTheNorthPole.LandAcreType.*;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static lombok.AccessLevel.PRIVATE;


public class SettlersOfTheNorthPole {

    private LandAcre[][] area;
    private int elapsedMinutes;
    private LinkedHashSet<SettlersOfTheNorthPole> seenLandAcres = new LinkedHashSet<>();


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
                LandAcre landAcre = northPole.new LandAcre(x, y);
                landAcre.setType(LandAcreType.fromSymbol(symbol));
                northPole.setLandAcre(landAcre);
            }
        }
        return northPole;
    }


    public SettlersOfTheNorthPole toMinute(int minute) {
        SettlersOfTheNorthPole northPole = this;
        for (int min = elapsedMinutes; min < minute; min++) {
            northPole = nextMinute();
        }
        return northPole;
    }


    public SettlersOfTheNorthPole nextMinute() {
        if (seenLandAcres.contains(this)) {
            Iterator<SettlersOfTheNorthPole> iterator = seenLandAcres.iterator();
            while (iterator.hasNext()) {
                if (this.equals(iterator.next())) {
                    return iterator.next();
                }
            }
        }

        SettlersOfTheNorthPole newNorthPole = new SettlersOfTheNorthPole(getSize());
        newNorthPole.elapsedMinutes = this.elapsedMinutes + 1;
        newNorthPole.seenLandAcres = this.seenLandAcres;
        Iterator<LandAcre> landAcreIterator = landAcres().iterator();
        while (landAcreIterator.hasNext()) {
            LandAcre landAcre = landAcreIterator.next();
            LandAcreType mextLandAcreType = landAcre.nextMinute();
            LandAcre newLandAcre = newNorthPole.new LandAcre(landAcre.getX(), landAcre.getY());
            newLandAcre.setType(mextLandAcreType);
            newNorthPole.setLandAcre(newLandAcre);
        }
        seenLandAcres.add(this);
        return newNorthPole;
    }


    public Stream<LandAcre> landAcres() {
        return Arrays.stream(area).flatMap(Arrays::stream);
    }

    public Map<LandAcreType, Long> countLandAcresByType() {
        return landAcres().collect(groupingBy(LandAcre::getType, counting()));
    }

    public long resourceValue() {
        Map<LandAcreType, Long> acreTypeCount = countLandAcresByType();
        long treeCount = acreTypeCount.getOrDefault(TREES, 0L);
        long lumberyardCount = acreTypeCount.getOrDefault(LUMBERYARD, 0L);
        return treeCount * lumberyardCount;
    }


    public LandAcre landAcreAt(int x, int y) {
        if (!isValidLanAcreLocation(x, y)) {
            throw new IndexOutOfBoundsException(
                    String.format("LandAcre (x=%d; y=%d) is out of bounds with area size=%d", x, y, getSize()));
        }
        return area[y][x];
    }

    public boolean isValidLanAcreLocation(int x, int y) {
        return 0 <= x && x < getSize() && 0 <= y && y < getSize();
    }

    private void setLandAcre(LandAcre landAcre) {
        int x = landAcre.getX();
        int y = landAcre.getY();
        if (!isValidLanAcreLocation(x, y)) {
            throw new IllegalArgumentException(
                    "Given LandAcre doesn't fit in this area with an size = " + getSize() + ": " + landAcre);
        }
        area[y][x] = landAcre;
    }


    public int getElapsedMinutes() {
        return elapsedMinutes;
    }

    public int getSize() {
        return area.length;
    }


    public boolean hasSameLandAcre(SettlersOfTheNorthPole otherLandAcre) {
        return Arrays.deepEquals(this.area, otherLandAcre.area);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(area);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SettlersOfTheNorthPole)) {
            return false;
        }
        SettlersOfTheNorthPole that = (SettlersOfTheNorthPole) o;
        return Arrays.deepEquals(this.area, that.area);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder((getSize() + 1) * getSize());
        appendElapsedMinutesHeader(sb);

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

    private void appendElapsedMinutesHeader(StringBuilder sb) {
        if (elapsedMinutes == 0) {
            sb.append("Initial state:\n");
        } else if (elapsedMinutes == 1) {
            sb.append("After ").append(elapsedMinutes).append(" minute:\n");
        } else {
            sb.append("After ").append(elapsedMinutes).append(" minutes:\n");
        }
    }


    @Data
    public class LandAcre {

        private final int x;
        private final int y;

        @Setter(PRIVATE)
        private LandAcreType type;


        public LandAcreType nextMinute() {
            switch (type) {
            case OPEN_GROUND:
                return openGroundChangeRule();
            case TREES:
                return treeChangeRule();
            case LUMBERYARD:
                return lumberyardChangeRule();
            default:
                throw new IllegalStateException("unexpected type: " + type);
            }
        }


        private LandAcreType openGroundChangeRule() {
            Map<LandAcreType, Long> adjacentlandAcreTypes = countAdjacentLandAcresByType();
            long adjacentTrees = adjacentlandAcreTypes.getOrDefault(TREES, 0L);
            if (adjacentTrees >= 3) {
                return TREES;
            }
            return this.getType();
        }

        private LandAcreType treeChangeRule() {
            Map<LandAcreType, Long> adjacentlandAcreTypes = countAdjacentLandAcresByType();
            long adjacentLumberYards = adjacentlandAcreTypes.getOrDefault(LUMBERYARD, 0L);
            if (adjacentLumberYards >= 3) {
                return LUMBERYARD;
            }
            return this.getType();
        }

        private LandAcreType lumberyardChangeRule() {
            Map<LandAcreType, Long> adjacentlandAcreTypes = countAdjacentLandAcresByType();
            long adjacentLumberYards = adjacentlandAcreTypes.getOrDefault(LUMBERYARD, 0L);
            long adjacentTrees = adjacentlandAcreTypes.getOrDefault(TREES, 0L);
            if (adjacentLumberYards >= 1 && adjacentTrees >= 1) {
                return LUMBERYARD;
            } else {
                return OPEN_GROUND;
            }
        }


        public Stream<LandAcre> adjacentLandAcres() {
            Builder<LandAcre> adjacentLandAcres = Stream.builder();
            for (int dy = -1; dy <= 1; dy++) {
                for (int dx = -1; dx <= 1; dx++) {
                    if (dy == 0 && dx == 0) {
                        continue;
                    }
                    int x = getX() + dx;
                    int y = getY() + dy;
                    if (isValidLanAcreLocation(x, y)) {
                        LandAcre adjacentLandAcre = landAcreAt(x, y);
                        adjacentLandAcres.add(adjacentLandAcre);
                    }
                }
            }
            return adjacentLandAcres.build();
        }

        public Map<LandAcreType, Long> countAdjacentLandAcresByType() {
            return adjacentLandAcres().collect(groupingBy(LandAcre::getType, counting()));
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
