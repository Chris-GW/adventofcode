package de.adventofcode.chrisgw.day18;

import de.adventofcode.chrisgw.day03.Direction;

import java.util.*;
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
public class AdventOfCodeDay18 {

    private final VaultTunnel[][] tunnelSystem;
    private VaultTunnel entrance;
    private Set<VaultTunnel> keys = new HashSet<>();
    private List<VaultPath> vaultTunnelPaths = new ArrayList<>();

    private List<VaultPath> takenPaths = new ArrayList<>();


    public AdventOfCodeDay18(AdventOfCodeDay18 other) {
        this.tunnelSystem = other.tunnelSystem;
        this.entrance = other.entrance;
        this.keys = other.keys;
        this.vaultTunnelPaths = other.vaultTunnelPaths;
        this.takenPaths = new ArrayList<>(other.takenPaths);
    }

    public AdventOfCodeDay18(List<String> vaultTunnelMapLines) {
        VaultTunnel entrance = null;
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
                    entrance = vaultTunnel;
                } else if (KEY.equals(tunnelType)) {
                    keys.add(vaultTunnel);
                }
            }
        }
        this.entrance = entrance;
    }


    private VaultTunnel parseVaultTunnel(int x, int y, char mapSign) {
        String key = String.valueOf(mapSign);
        if (mapSign == '.') {
            return new VaultTunnel(x, y, ValutTunnelType.EMPTY);
        } else if (mapSign == '#') {
            return new VaultTunnel(x, y, ValutTunnelType.WALL);
        } else if (mapSign == '@') {
            return new VaultTunnel(x, y, ENTRANCE, "0");
        } else if (Character.isUpperCase(mapSign)) {
            return new VaultTunnel(x, y, ValutTunnelType.DOOR, key);
        } else if (Character.isLowerCase(mapSign)) {
            return new VaultTunnel(x, y, KEY, key);
        } else {
            throw new IllegalArgumentException("unknown mapSign: " + mapSign);
        }
    }


    public AdventOfCodeDay18 findShortestPath() {
        this.vaultTunnelPaths = paths().collect(Collectors.toList());
        this.vaultTunnelPaths.forEach(System.out::println);
        return possibleNextMoves().min(Comparator.comparingInt(AdventOfCodeDay18::getSteps)).orElseThrow();
    }


    private Stream<AdventOfCodeDay18> possibleNextMoves() {
        if (collectedAllKeys()) {
            return Stream.of(this);
        }
        List<VaultPath> nextPaths = possibleNextPaths().collect(Collectors.toList());
        List<AdventOfCodeDay18> possibleMoves = nextPaths.stream().map(this::followPath).collect(Collectors.toList());
        return possibleMoves.stream().flatMap(AdventOfCodeDay18::possibleNextMoves);
    }

    private Stream<VaultPath> possibleNextPaths() {
        List<VaultPath> connectedPaths = vaultTunnelPaths.stream()
                .filter(vaultPath -> currentPosition().equals(vaultPath.getFrom()))
                .collect(Collectors.toList());
        return connectedPaths.stream().filter(this::canFollowPath);

    }

    private boolean canFollowPath(VaultPath path) {
        boolean isConnectablePath = currentPosition().equals(path.getFrom());
        return isConnectablePath && collectsNewNeededKey(path) && canUnlockAllDoorAlongPath(path);
    }

    private boolean collectsNewNeededKey(VaultPath path) {
        return collectedKeys().noneMatch(path.collectedKey()::equalsIgnoreCase);
    }

    private boolean canUnlockAllDoorAlongPath(VaultPath path) {
        return path.getNeededKeys().stream().allMatch(key -> collectedKeys().anyMatch(key::equalsIgnoreCase));
    }

    public VaultTunnel currentPosition() {
        if (takenPaths.isEmpty()) {
            return entrance;
        }
        VaultPath lastPath = takenPaths.get(takenPaths.size() - 1);
        return lastPath.getTo();
    }


    private AdventOfCodeDay18 followPath(VaultPath path) {
        AdventOfCodeDay18 newAoc18 = new AdventOfCodeDay18(this);
        newAoc18.takenPaths.add(path);
        return newAoc18;
    }


    public Stream<VaultPath> paths() {
        return Stream.concat(Stream.of(entrance), keys.stream())
                .map(vaultTunnel -> new VaultPath(vaultTunnel, vaultTunnel, 0, Collections.emptySet()))
                .flatMap(vaultPath -> {
                    Set<VaultTunnel> visitedTunnels = new HashSet<>();
                    visitedTunnels.add(vaultPath.getTo());
                    return vaultPaths(vaultPath, visitedTunnels);
                })
                .sorted(Comparator.comparingInt(VaultPath::getDistance));
    }

    public Stream<VaultPath> vaultPaths(VaultPath currentPath, Set<VaultTunnel> visitedTunnels) {
        VaultTunnel from = currentPath.getFrom();
        VaultTunnel to = currentPath.getTo();
        if (to.isDoor()) {
            currentPath.getNeededKeys().add(to.getKey());
        }
        int newDistance = currentPath.getDistance() + 1;

        List<VaultPath> nextPaths = Arrays.stream(Direction.values())
                .map(direction -> tunnelInDirection(to, direction))
                .filter(Predicate.not(VaultTunnel::isWall))
                .filter(Predicate.not(visitedTunnels::contains))
                .map(newPosition -> new VaultPath(from, newPosition, newDistance,
                        new HashSet<>(currentPath.getNeededKeys())))
                .collect(Collectors.toList());
        return nextPaths.stream().flatMap(newPath -> {
            Set<VaultTunnel> newVisitedTunnels = new HashSet<>(visitedTunnels);
            newVisitedTunnels.add(newPath.getTo());
            if (to.isKey() && !from.equals(to)) {
                return Stream.concat(Stream.of(currentPath), vaultPaths(newPath, newVisitedTunnels));
            } else {
                return vaultPaths(newPath, newVisitedTunnels);
            }
        });
    }


    private VaultTunnel tunnelInDirection(VaultTunnel position, Direction direction) {
        int x = position.getX() + direction.getDx();
        int y = position.getY() + direction.getDy();
        return tunnelAt(x, y);
    }


    private Stream<String> collectedKeys() {
        return takenPaths.stream().map(VaultPath::collectedKey);
    }

    private boolean collectedAllKeys() {
        return takenPaths.size() == keys.size();
    }


    public int getSteps() {
        return takenPaths.stream().mapToInt(VaultPath::getDistance).sum();
    }


    public VaultTunnel tunnelAt(int x, int y) {
        return tunnelSystem[y][x];
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
