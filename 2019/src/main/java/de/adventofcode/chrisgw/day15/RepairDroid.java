package de.adventofcode.chrisgw.day15;

import de.adventofcode.chrisgw.day03.Direction;
import de.adventofcode.chrisgw.intcode.IntCodeProgram;
import lombok.Data;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import java.util.*;
import java.util.stream.Stream;

import static de.adventofcode.chrisgw.day15.OxygenSystemMapTile.*;
import static de.adventofcode.chrisgw.day15.RepairDroidStatusCode.*;


@Data
public class RepairDroid {

    private final IntCodeProgram droidProgram;

    private Vector2D position = Vector2D.ZERO;
    private Direction direction = Direction.UP;

    private Vector2D oxygenSystemLocation = null;
    private Map<Vector2D, OxygenSystemMapTile> map = new HashMap<>();


    public void escapeMazeUsingRightHandRule() {
        Iterator<Direction> directionIterator = Stream.iterate(direction.rotateRight(), Direction::rotateLeft)
                .limit(4)
                .iterator();

        while (directionIterator.hasNext()) {
            Direction nextDirection = directionIterator.next();
            RepairDroidStatusCode droidStatusCode = move(nextDirection);
            if (REACHED_LOCATION.equals(droidStatusCode)) {
                if (oxygenSystemLocation != null) {
                    return;
                }
                oxygenSystemLocation = position;
                escapeMazeUsingRightHandRule();
                return;
            } else if (MOVED.equals(droidStatusCode)) {
                escapeMazeUsingRightHandRule();
                return;
            }
        }
    }


    public int shortestDistanceToOxygenSystem() {
        Vector2D startPosition = Vector2D.ZERO;
        Set<Vector2D> visitedNodes = new HashSet<>();
        return distanceShortestPathBreadthFirstSearch(startPosition, visitedNodes, Integer.MAX_VALUE, 0);
    }

    private int distanceShortestPathBreadthFirstSearch(Vector2D currentPosition, Set<Vector2D> visitedNodes,
            int minDistance, int distance) {
        if (oxygenSystemLocation.equals(currentPosition)) {
            return Integer.min(distance, minDistance);
        }

        visitedNodes.add(currentPosition);
        for (Direction direction : Direction.values()) {
            double x = currentPosition.getX() + direction.getDx();
            double y = currentPosition.getY() + direction.getDy();
            Vector2D nextPosition = new Vector2D(x, y);
            if (isEmptySpaceAt(nextPosition) && !visitedNodes.contains(nextPosition)) {
                minDistance = Integer.min(
                        distanceShortestPathBreadthFirstSearch(nextPosition, visitedNodes, minDistance, distance + 1),
                        minDistance);
            }
        }
        visitedNodes.remove(currentPosition);
        return minDistance;
    }


    public RepairDroidStatusCode move(Direction direction) {
        droidProgram.addInput(moveCommandCode(direction));
        droidProgram.run();
        RepairDroidStatusCode statusCode = readDroidStatusCode();
        if (HIT_WALL.equals(statusCode)) {
            putWallIn(direction);
            return statusCode;
        } else if (MOVED.equals(statusCode)) {
            double x = position.getX() + direction.getDx();
            double y = position.getY() + direction.getDy();
            this.position = new Vector2D(x, y);
            this.direction = direction;
            putSpace();
            return statusCode;
        } else if (REACHED_LOCATION.equals(statusCode)) {
            return statusCode;
        } else {
            throw new IllegalArgumentException("Unknown statuscode from repair droid: " + statusCode);
        }
    }

    private int moveCommandCode(Direction direction) {
        switch (direction) {
        case UP:
            return 1;
        case DOWN:
            return 2;
        case LEFT:
            return 3;
        case RIGHT:
            return 4;
        default:
            throw new IllegalArgumentException("Unknown direction: " + direction);
        }
    }

    private RepairDroidStatusCode readDroidStatusCode() {
        int nextOutput = (int) droidProgram.nextOutput();
        return RepairDroidStatusCode.valueOf(nextOutput);
    }


    private void putWallIn(Direction direction) {
        double x = position.getX() + direction.getDx();
        double y = position.getY() + direction.getDy();
        Vector2D wallPosition = new Vector2D(x, y);
        map.put(wallPosition, WALL);
    }

    private void putSpace() {
        map.put(position, EMPTY);
    }


    private boolean isWallAt(Vector2D location) {
        return WALL.equals(mapTileAt(location));
    }

    private boolean isEmptySpaceAt(Vector2D location) {
        return EMPTY.equals(mapTileAt(location));
    }

    private OxygenSystemMapTile mapTileAt(Vector2D location) {
        return map.getOrDefault(location, UNKNOWN);
    }


    @Override
    public String toString() {
        double fromY = map.keySet().stream().mapToDouble(Vector2D::getY).min().orElse(0.0);
        double toY = map.keySet().stream().mapToDouble(Vector2D::getY).max().orElse(0.0);
        double fromX = map.keySet().stream().mapToDouble(Vector2D::getX).min().orElse(0.0);
        double toX = map.keySet().stream().mapToDouble(Vector2D::getX).max().orElse(0.0);

        StringBuilder sb = new StringBuilder();
        for (double y = fromY; y <= toY; y++) {
            for (double x = fromX; x <= toX; x++) {
                Vector2D location = new Vector2D(x, y);
                sb.append(mapSignAt(location));
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    private char mapSignAt(Vector2D location) {
        if (position.equals(location)) {
            return 'D';
        } else if (location.equals(location.getZero())) {
            return 'E';
        } else if (location.equals(oxygenSystemLocation)) {
            return 'O';
        }

        OxygenSystemMapTile mapTile = mapTileAt(location);
        switch (mapTile) {
        case UNKNOWN:
            return ' ';
        case EMPTY:
            return '.';
        case WALL:
            return '#';
        default:
            throw new IllegalStateException("Unexpected value: " + mapTile);
        }
    }


}
