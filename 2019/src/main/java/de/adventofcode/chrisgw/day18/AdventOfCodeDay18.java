package de.adventofcode.chrisgw.day18;

import de.adventofcode.chrisgw.day03.Direction;
import org.apache.commons.math3.util.Pair;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static de.adventofcode.chrisgw.day18.ValutTunnelType.ENTRANCE;
import static de.adventofcode.chrisgw.day18.ValutTunnelType.KEY;


/**
 * 2019 Day 18: Many-Worlds Interpretation
 * https://adventofcode.com/2019/day/18
 */
public class AdventOfCodeDay18 implements Comparable<AdventOfCodeDay18> {

    private final VaultTunnel[][] tunnelSystem;
    private final int neededKeys;

    private Set<VaultTunnel> collectedKeys = new LinkedHashSet<>();
    private VaultTunnel position;
    private int steps = 0;


    public AdventOfCodeDay18(AdventOfCodeDay18 other) {
        this.tunnelSystem = other.tunnelSystem;
        this.neededKeys = other.neededKeys;
        this.collectedKeys = new HashSet<>(other.collectedKeys);
        this.position = other.position;
        this.steps = other.steps;
    }

    public AdventOfCodeDay18(List<String> vaultTunnelMapLines) {
        int neededKeys = 0;
        tunnelSystem = new VaultTunnel[vaultTunnelMapLines.size()][];
        for (int y = 0; y < vaultTunnelMapLines.size(); y++) {
            String line = vaultTunnelMapLines.get(y);
            tunnelSystem[y] = new VaultTunnel[line.length()];
            for (int x = 0; x < line.length(); x++) {
                char mapSign = line.charAt(x);
                VaultTunnel vaultTunnel = parseVaultTunnel(x, y, mapSign);
                ValutTunnelType tunnelType = vaultTunnel.getType();
                tunnelSystem[y][x] = vaultTunnel;

                if (ENTRANCE.equals(tunnelType)) {
                    position = vaultTunnel;
                } else if (KEY.equals(tunnelType)) {
                    neededKeys++;
                }
            }
        }
        this.neededKeys = neededKeys;
    }


    private VaultTunnel parseVaultTunnel(int x, int y, char mapSign) {
        String key = String.valueOf(mapSign);
        if (mapSign == '.') {
            return new VaultTunnel(x, y, ValutTunnelType.EMPTY);
        } else if (mapSign == '#') {
            return new VaultTunnel(x, y, ValutTunnelType.WALL);
        } else if (mapSign == '@') {
            return new VaultTunnel(x, y, ENTRANCE);
        } else if (Character.isUpperCase(mapSign)) {
            return new VaultTunnel(x, y, ValutTunnelType.DOOR, key);
        } else if (Character.isLowerCase(mapSign)) {
            return new VaultTunnel(x, y, KEY, key);
        } else {
            throw new IllegalArgumentException("unknown mapSign: " + mapSign);
        }
    }


    public AdventOfCodeDay18 findShortestPath() {
        AtomicInteger bestSteps = new AtomicInteger(Integer.MAX_VALUE);
        AtomicInteger unchanged = new AtomicInteger(500000);
        List<AdventOfCodeDay18> moves = possibleNextMoves(bestSteps, unchanged).collect(Collectors.toList());
        return moves.stream().min(Comparator.comparingInt(AdventOfCodeDay18::getSteps)).orElseThrow();
    }

    private Stream<AdventOfCodeDay18> possibleNextMoves(AtomicInteger bestSteps, AtomicInteger unchanged) {
        if (unchanged.get() < 0 || steps > bestSteps.get()) {
            return Stream.empty();
        }
        if (collectedAllKeys() && steps < bestSteps.get()) {
            bestSteps.set(steps);
            unchanged.set(500000);
            return Stream.of(this);
        } else {
            unchanged.getAndDecrement();
        }
        List<AdventOfCodeDay18> possibleMoves = reachableTunnels().map(this::moveTo).collect(Collectors.toList());
        return possibleMoves.stream()
                .flatMap(adventOfCodeDay18 -> adventOfCodeDay18.possibleNextMoves(bestSteps, unchanged));
    }

    private AdventOfCodeDay18 moveTo(Pair<VaultTunnel, Integer> newPositionWithDistance) {
        VaultTunnel newPosition = newPositionWithDistance.getFirst();
        int neededSteps = newPositionWithDistance.getSecond();
        AdventOfCodeDay18 newAoc18 = new AdventOfCodeDay18(this);
        newAoc18.position = newPosition;
        newAoc18.steps += neededSteps;
        if (isCollectableKey(newPosition)) {
            newAoc18.collectedKeys.add(newPosition);
        } else {
            throw new IllegalArgumentException("Given move doesn't pickup key");
        }
        return newAoc18;
    }


    public boolean collectedAllKeys() {
        return collectedKeys.size() == neededKeys;
    }


    public Stream<Pair<VaultTunnel, Integer>> reachableTunnels() {
        Pair<VaultTunnel, Integer> startPositionWithDistance = Pair.create(this.position, 0);
        Set<VaultTunnel> visitedTunnels = new HashSet<>();
        visitedTunnels.add(this.position);
        return reachableTunnels(startPositionWithDistance, visitedTunnels);
    }

    public Stream<Pair<VaultTunnel, Integer>> reachableTunnels(Pair<VaultTunnel, Integer> positionWithDistance,
            Set<VaultTunnel> visitedTunnels) {
        VaultTunnel currentPosition = positionWithDistance.getFirst();
        int nextStepDistance = positionWithDistance.getSecond() + 1;
        if (isCollectableKey(currentPosition)) {
            return Stream.of(positionWithDistance);
        } else if (currentPosition.isDoor() && !isOpenDoor(currentPosition)) {
            return Stream.empty();
        }

        return Arrays.stream(Direction.values())
                .map(direction -> tunnelInDirection(currentPosition, direction))
                .filter(Predicate.not(VaultTunnel::isWall))
                .filter(visitedTunnels::add)
                .map(newPosition -> Pair.create(newPosition, nextStepDistance))
                .flatMap(newPositionWithDistance -> reachableTunnels(newPositionWithDistance, visitedTunnels))
                .sorted(Comparator.comparingInt(Pair::getSecond));
    }


    private VaultTunnel tunnelInDirection(VaultTunnel position, Direction direction) {
        int x = position.getX() + direction.getDx();
        int y = position.getY() + direction.getDy();
        return tunnelAt(x, y);
    }


    private boolean isCollectedKey(VaultTunnel tunnelWithKey) {
        return collectedKeys.contains(tunnelWithKey);
    }

    private boolean isCollectableKey(VaultTunnel tunnelWithKey) {
        return tunnelWithKey.isKey() && !isCollectedKey(tunnelWithKey);
    }


    private boolean isOpenDoor(VaultTunnel tunnelWithDoor) {
        return hasCollectedKeyForDoor(tunnelWithDoor);
    }

    private boolean hasCollectedKeyForDoor(VaultTunnel tunnelWithDoor) {
        return collectedKeys.stream().map(VaultTunnel::getKey).anyMatch(tunnelWithDoor::canUnlockDoorWith);
    }


    public VaultTunnel tunnelAt(int x, int y) {
        return tunnelSystem[y][x];
    }


    public int getSteps() {
        return steps;
    }


    public Set<VaultTunnel> getCollectedKeys() {
        return collectedKeys;
    }


    @Override
    public int compareTo(AdventOfCodeDay18 other) {
        return Integer.compare(this.getSteps(), other.getSteps());
    }


    public int width() {
        return tunnelSystem[0].length;
    }

    public int height() {
        return tunnelSystem.length;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("  ");
        IntStream.range(0, width()).mapToObj(String::valueOf).forEachOrdered(sb::append);
        sb.append("\n");
        for (int y = 0; y < height(); y++) {
            sb.append(y % 10).append(" ");
            for (int x = 0; x < width(); x++) {
                VaultTunnel vaultTunnel = tunnelAt(x, y);
                sb.append(vaultTunnel.getSign());
            }
            sb.append("\n");
        }
        sb.append("  ");
        IntStream.range(0, width()).mapToObj(String::valueOf).forEachOrdered(sb::append);
        return sb.toString();
    }

}
