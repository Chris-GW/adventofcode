package de.adventofcode.chrisgw.day17;

import lombok.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.IntBinaryOperator;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static de.adventofcode.chrisgw.day17.ReservoirResearch.WaterSquareFlowDirection.*;


public class ReservoirResearch {

    public static final Pattern CLAY_VEIN_LINE_PATTERN = Pattern.compile("([yx])=(\\d+),\\s+[yx]=(\\d+)\\.\\.(\\d+)");
    public static final GroundSquare WATER_SPRING_SQUARE = new GroundSquare(500, 0);


    private final Map<Integer, Map<Integer, GroundSquare>> clayGroundSquareMap = new HashMap<>();
    private final Map<Integer, Map<Integer, WaterSquare>> waterSquareMap = new HashMap<>();
    private final GroundSquare topLeftGroundSquare;
    private final GroundSquare bottomRightGroundSquare;

    @Getter
    private int rounds = 0;


    public ReservoirResearch(List<String> groundSliceLines) {
        putClayGroundSquare(WATER_SPRING_SQUARE);
        groundSliceLines.stream()
                .map(ReservoirResearch::parseClayVeinLine)
                .flatMap(List::stream)
                .forEach(this::putClayGroundSquare);
        this.topLeftGroundSquare = cornerGroundSquare(Math::min);
        this.bottomRightGroundSquare = cornerGroundSquare(Math::max);
    }

    private static List<GroundSquare> parseClayVeinLine(String clayVeinLine) {
        Matcher groundSliceSquareMatcher = CLAY_VEIN_LINE_PATTERN.matcher(clayVeinLine);
        if (!groundSliceSquareMatcher.matches()) {
            throw new IllegalArgumentException("Expect matching clayVeinLine for pattern: " + CLAY_VEIN_LINE_PATTERN);
        }

        boolean isFixedXCoordinate = "x".equalsIgnoreCase(groundSliceSquareMatcher.group(1));
        int a = Integer.parseInt(groundSliceSquareMatcher.group(2));
        int from = Integer.parseInt(groundSliceSquareMatcher.group(3));
        int to = Integer.parseInt(groundSliceSquareMatcher.group(4));

        List<GroundSquare> clayGroundSquares = new ArrayList<>(to - from + 1);
        for (int b = from; b <= to; b++) {
            GroundSquare clayGroundSquare;
            if (isFixedXCoordinate) {
                clayGroundSquare = new GroundSquare(a, b);
            } else {
                clayGroundSquare = new GroundSquare(b, a);
            }
            clayGroundSquares.add(clayGroundSquare);
        }
        return clayGroundSquares;
    }

    private GroundSquare cornerGroundSquare(IntBinaryOperator minOrMaxOperation) {
        int x = clayGroundSquares().mapToInt(GroundSquare::getX).reduce(minOrMaxOperation).orElse(0);
        x = minOrMaxOperation.applyAsInt(x - 1, x + 1);
        int y = clayGroundSquares().mapToInt(GroundSquare::getY).reduce(minOrMaxOperation).orElse(0);
        return new GroundSquare(x, y);
    }


    public boolean nextWaterSquare() {
        int restingWaterCount = countRestingWater();

        List<WaterSquare> flowingWaterSquares = new ArrayList<>();
        flowingWaterSquares.add(new WaterSquare(WATER_SPRING_SQUARE));

        while (flowingWaterSquares.size() > 0) {
            flowingWaterSquares = flowingWaterSquares.stream()
                    .flatMap(WaterSquare::flowToNextSquare)
                    .distinct()
                    .collect(Collectors.toList());

            Predicate<WaterSquare> isRestingWater = WaterSquare::isRestingWater;
            flowingWaterSquares.removeIf(isRestingWater.or(WaterSquare::isFlowingEndless));
        }
        printToFile(Collections.emptyList());
        rounds++;
        return restingWaterCount < countRestingWater();
    }


    public boolean isClayGroundSquare(GroundSquare groundSquare) {
        return isClayGroundSquare(groundSquare.getX(), groundSquare.getY());
    }

    public boolean isClayGroundSquare(int x, int y) {
        Map<Integer, GroundSquare> row = clayGroundSquareMap.getOrDefault(y, Collections.emptyMap());
        return row.containsKey(x);
    }

    private void putClayGroundSquare(GroundSquare groundSquare) {
        int y = groundSquare.getY();
        Map<Integer, GroundSquare> row = clayGroundSquareMap.computeIfAbsent(y, HashMap::new);
        row.put(groundSquare.getX(), groundSquare);
    }


    public Optional<WaterSquare> getWaterSquare(int x, int y) {
        Map<Integer, WaterSquare> row = waterSquareMap.getOrDefault(y, Collections.emptyMap());
        return Optional.ofNullable(row.get(x));
    }

    private void putWaterSquare(WaterSquare waterSquare) {
        if (waterSquare.isFlowingEndless()) {
            return;
        }
        GroundSquare currentSquare = waterSquare.getCurrentSquare();
        Map<Integer, WaterSquare> row = waterSquareMap.computeIfAbsent(currentSquare.getY(), HashMap::new);
        row.put(currentSquare.getX(), waterSquare);
    }


    public boolean isRestingWaterAt(GroundSquare groundSquare) {
        return isRestingWaterAt(groundSquare.getX(), groundSquare.getY());
    }

    public boolean isRestingWaterAt(int x, int y) {
        return getWaterSquare(x, y).map(WaterSquare::isRestingWater).orElse(false);
    }


    public Stream<GroundSquare> clayGroundSquares() {
        return clayGroundSquareMap.values().stream().map(Map::values).flatMap(Collection::stream);
    }

    public Stream<WaterSquare> waterSquares() {
        return waterSquareMap.values().stream().map(Map::values).flatMap(Collection::stream);
    }


    public int clayGroundSquareCount() {
        return clayGroundSquareMap.values().stream().mapToInt(Map::size).sum();
    }

    public int waterSquareCount() {
        return waterSquareMap.values().stream().mapToInt(Map::size).sum();
    }


    public int countRestingWater() {
        return (int) waterSquares().filter(WaterSquare::isRestingWater).count();
    }


    @Data
    @Setter(AccessLevel.PRIVATE)
    public class WaterSquare {

        private GroundSquare currentSquare;
        private WaterSquareFlowDirection flowDirection;


        public WaterSquare(GroundSquare currentSquare) {
            this.currentSquare = currentSquare;
            this.flowDirection = DOWN;
        }

        private WaterSquare(WaterSquare waterSquare) {
            this.currentSquare = waterSquare.getCurrentSquare();
            this.flowDirection = waterSquare.getFlowDirection();
        }


        private Stream<WaterSquare> flowToNextSquare() {
            if (canFlow(DOWN)) {
                return Stream.of(spreadWaterSquares(DOWN).getLast());
            } else if (DOWN.equals(flowDirection)) {
                Deque<WaterSquare> waterSquares = spreadWaterSquares();
                WaterSquare leftWaterSquare = waterSquares.getFirst();
                WaterSquare rightWaterSquare = waterSquares.getLast();
                if (leftWaterSquare.canRest() && rightWaterSquare.canRest()) {
                    waterSquares.forEach(waterSquare -> waterSquare.flowDirection = RESTING);
                    int y = currentSquare.getY() - 1;
                    return getWaterSquare(currentSquare.getX(), y).map(Stream::of).orElse(Stream.empty());
                } else {
                    return Stream.of(leftWaterSquare, rightWaterSquare)
                            .filter(waterSquare -> !this.equals(waterSquare))
                            .distinct();
                }
            } else {
                return Stream.empty();
            }
        }


        private Deque<WaterSquare> spreadWaterSquares(WaterSquareFlowDirection flowDirection) {
            Deque<WaterSquare> waterSquares = new ArrayDeque<>();
            WaterSquare currentWaterSquare = this;
            waterSquares.add(this);

            while (currentWaterSquare.canFlow(flowDirection)) {
                currentWaterSquare = currentWaterSquare.flow(flowDirection);
                waterSquares.add(currentWaterSquare);
                boolean canFlowDown = !DOWN.equals(flowDirection) && currentWaterSquare.canFlow(DOWN);
                if (canFlowDown || currentWaterSquare.isFlowingEndless()) {
                    break;
                }
            }
            return waterSquares;
        }


        private Deque<WaterSquare> spreadWaterSquares() {
            Deque<WaterSquare> leftWaterSquares = spreadWaterSquares(LEFT);
            Deque<WaterSquare> rightWaterSquares = spreadWaterSquares(RIGHT);
            leftWaterSquares.removeFirst();
            leftWaterSquares.forEach(rightWaterSquares::addFirst);
            return rightWaterSquares;
        }


        private boolean canFlow(WaterSquareFlowDirection flowDirection) {
            int x = currentSquare.getX() + flowDirection.getDx();
            int y = currentSquare.getY() + flowDirection.getDy();
            return !(isClayGroundSquare(x, y) || isRestingWaterAt(x, y));
        }

        private WaterSquare flow(WaterSquareFlowDirection flowDirection) {
            int x = currentSquare.getX() + flowDirection.getDx();
            int y = currentSquare.getY() + flowDirection.getDy();
            WaterSquare waterSquare = new WaterSquare(this);
            waterSquare.currentSquare = new GroundSquare(x, y);
            waterSquare.flowDirection = flowDirection;
            if (!waterSquare.isFlowingEndless()) {
                putWaterSquare(waterSquare);
            }
            return waterSquare;
        }


        public boolean canRest() {
            return !(canFlow(flowDirection) || canFlow(DOWN));
        }

        public boolean isRestingWater() {
            return RESTING.equals(flowDirection);
        }

        public boolean isFlowingEndless() {
            return currentSquare.getY() <= topLeftGroundSquare.getY()
                    || bottomRightGroundSquare.getY() < currentSquare.getY();
        }

    }


    public enum WaterSquareFlowDirection {

        RESTING(0, 0), DOWN(0, 1), LEFT(-1, 0), RIGHT(1, 0);

        private int dx;
        private int dy;

        WaterSquareFlowDirection(int dx, int dy) {
            this.dx = dx;
            this.dy = dy;
        }

        public int getDx() {
            return dx;
        }

        public int getDy() {
            return dy;
        }

    }


    @Value
    public static class GroundSquare {

        private final int x;
        private final int y;


        public boolean isAt(int x, int y) {
            return this.x == x && this.y == y;
        }


        @Override
        public String toString() {
            return String.format("(%3d;%3d)", x, y);
        }

    }


    @Override
    public String toString() {
        return toString(Collections.emptyList());
    }

    public String toString(List<WaterSquare> flowingWaterSquares) {
        StringBuilder sb = new StringBuilder();
        appendColumnNumbering(sb);
        for (int y = topLeftGroundSquare.getY(); y <= bottomRightGroundSquare.getY(); y++) {
            sb.append(String.format("%4d ", y));
            for (int x = topLeftGroundSquare.getX(); x <= bottomRightGroundSquare.getX(); x++) {
                final int currentX = x;
                final int currentY = y;
                Optional<WaterSquare> flowingWaterSquare = flowingWaterSquares.stream()
                        .filter(waterSquare -> waterSquare.getCurrentSquare().isAt(currentX, currentY))
                        .findFirst();
                if (flowingWaterSquare.isPresent()) {
                    switch (flowingWaterSquare.get().getFlowDirection()) {
                    case DOWN:
                        sb.append('v');
                        break;
                    case LEFT:
                        sb.append('<');
                        break;
                    case RIGHT:
                        sb.append('>');
                        break;
                    default:
                        sb.append('X');
                    }
                } else {
                    appendSquare(sb, x, y);
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    private void appendColumnNumbering(StringBuilder sb) {
        for (int i = 100; i >= 1; i /= 10) {
            sb.append(String.format("%-3d  ", i));
            for (int x = topLeftGroundSquare.getX(); x <= bottomRightGroundSquare.getX(); x++) {
                sb.append((x / i) % 10);
            }
            sb.append("\n");
        }
    }

    private void appendSquare(StringBuilder sb, int x, int y) {
        Optional<WaterSquare> waterSquare = getWaterSquare(x, y);
        if (WATER_SPRING_SQUARE.getX() == x && WATER_SPRING_SQUARE.getY() == y) {
            sb.append('+');
        } else if (isClayGroundSquare(x, y)) {
            sb.append('#');
        } else if (waterSquare.map(WaterSquare::isRestingWater).orElse(false)) {
            sb.append('~');
        } else if (waterSquare.isPresent()) {
            sb.append('|');
        } else {
            sb.append('.');
        }
    }


    private void printToFile(List<WaterSquare> flowingWaterSquares) {
        try {
            Path path = Paths.get("target/day16/waterSquares_" + rounds + ".txt");
            Files.createDirectories(path.getParent());
            Files.write(path, Collections.singletonList(toString(flowingWaterSquares)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws Exception {
        if (args.length < 1) {
            System.out.println("Usage <puzzleInputFile>");
            return;
        }
        Path puzzleInputFile = Paths.get(args[0]);
        ReservoirResearch reservoirResearch = new ReservoirResearch(Files.readAllLines(puzzleInputFile));
        System.out.println(reservoirResearch);
        while (reservoirResearch.nextWaterSquare()) {

        }
        System.out.println(reservoirResearch);
        int waterSquareCount = reservoirResearch.waterSquareCount();
        System.out.println("waterSquareCount: " + waterSquareCount);
        int restingWaterSquareCount = reservoirResearch.countRestingWater();
        System.out.println("restingWaterSquareCount: " + restingWaterSquareCount);
    }

}
