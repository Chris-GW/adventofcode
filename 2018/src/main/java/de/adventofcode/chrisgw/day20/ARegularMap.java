package de.adventofcode.chrisgw.day20;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Value;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class ARegularMap {

    @Getter
    private final String roomRouteRegex;

    @Getter
    private final FacilityRoom startRoom;

    private Map<Integer, Map<Integer, FacilityRoom>> facilityRoomMap = new HashMap<>();


    public ARegularMap(String roomRouteRegex) {
        this.roomRouteRegex = roomRouteRegex;
        this.startRoom = new FacilityRoom(0, 0);
        putFacilityRoom(this.startRoom);
        followRouteRegex();
    }

    private FacilityRoute followRouteRegex() {
        Stack<FacilityRoute> branchingRouteStartStack = new Stack<>();
        Stack<FacilityRoute> routeStack = new Stack<>();
        routeStack.push(new FacilityRoute());

        for (int i = 1; i < roomRouteRegex.length() - 1; i++) {
            char directionLetter = roomRouteRegex.charAt(i);
            if (directionLetter == '(') {
                FacilityRoute branchingRoute = routeStack.peek();
                branchingRouteStartStack.push(branchingRoute);
                routeStack.push(new FacilityRoute(branchingRoute));

            } else if (directionLetter == '|') {
                FacilityRoute newFacilityRoute = new FacilityRoute(branchingRouteStartStack.peek());
                routeStack.push(newFacilityRoute);

            } else if (directionLetter == ')') {
                FacilityRoute branchingRoutePoint = branchingRouteStartStack.pop();
                while (branchingRoutePoint != routeStack.peek()) {
                    FacilityRoute branchingRoute = routeStack.pop();
                    branchingRoutePoint.branchingRoutes.add(branchingRoute);
                }

            } else {
                FacilityRouteDirection direction = FacilityRouteDirection.valueOf(directionLetter);
                routeStack.peek().addDirection(direction);
            }
        }
        return routeStack.pop();
    }


    public Optional<FacilityRoom> roomAt(int x, int y) {
        Map<Integer, FacilityRoom> row = facilityRoomMap.getOrDefault(y, Collections.emptyMap());
        return Optional.ofNullable(row.get(x));
    }

    private FacilityRoom putFacilityRoom(FacilityRoom facilityRoom) {
        int x = facilityRoom.getX();
        int y = facilityRoom.getY();
        Map<Integer, FacilityRoom> row = facilityRoomMap.computeIfAbsent(y, (yVal) -> new HashMap<>());
        return row.put(x, facilityRoom);
    }

    public int longestRouteLength() {
        return 0;
    }


    @EqualsAndHashCode
    public class FacilityRoute {

        private List<FacilityRouteDirection> walkedDirections = new ArrayList<>();
        private List<FacilityRoom> walkedRooms = new ArrayList<>();
        private List<FacilityRoute> branchingRoutes = new ArrayList<>();


        private FacilityRoute() {
            this.walkedRooms.add(startRoom);
        }

        private FacilityRoute(FacilityRoute otherRoute) {
            this.walkedDirections.addAll(otherRoute.walkedDirections);
            this.walkedRooms.addAll(otherRoute.walkedRooms);
        }


        private void addDirection(FacilityRouteDirection direction) {
            walkedDirections.add(direction);
            FacilityRoom destinationRoom = getDestinationRoom();
            int x = destinationRoom.getX() + direction.getDx();
            int y = destinationRoom.getY() + direction.getDy();
            walkedRooms.add(roomAt(x, y).orElseGet(() -> new FacilityRoom(x, y)));
        }


        public Stream<FacilityRoom> facilityRooms() {
            return walkedRooms.stream();
        }

        public FacilityRoom getStartRoom() {
            return walkedRooms.get(0);
        }

        public FacilityRoom getDestinationRoom() {
            return walkedRooms.get(walkedRooms.size() - 1);
        }


        public Stream<FacilityRouteDirection> directions() {
            return walkedDirections.stream();
        }


        public int length() {
            return walkedDirections.size();
        }


        @Override
        public String toString() {
            return walkedDirections.stream().map(FacilityRouteDirection::letter).collect(Collectors.joining(""));
        }

    }


    @Value
    public class FacilityRoom {

        private final int x;
        private final int y;


        private FacilityRoom(int x, int y) {
            this.x = x;
            this.y = y;
            putFacilityRoom(this);
        }


        public boolean hasRoomTo(FacilityRouteDirection direction) {
            int x = getX() + direction.getDx();
            int y = getX() + direction.getDy();
            return roomAt(x, y).isPresent();
        }

        public FacilityRoom getRoomTo(FacilityRouteDirection direction) {
            int x = getX() + direction.getDx();
            int y = getX() + direction.getDy();
            return roomAt(x, y).orElse(null);
        }


        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("(").append(x).append(",").append(y).append("): ");
            for (FacilityRouteDirection direction : FacilityRouteDirection.values()) {
                if (hasRoomTo(direction)) {
                    sb.append(direction.letter());
                }
            }
            return sb.toString();
        }

    }


    public static void main(String[] args) throws Exception {
        if (args.length < 1) {
            System.out.println("Usage <puzzleInputFile>");
            return;
        }
        Path puzzleInputFile = Paths.get(args[0]);

    }

}
