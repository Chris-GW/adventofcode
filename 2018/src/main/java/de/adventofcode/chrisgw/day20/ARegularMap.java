package de.adventofcode.chrisgw.day20;

import lombok.Getter;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


public class ARegularMap {

    @Getter
    private final String roomRouteRegex;

    @Getter
    private final FacilityRoom startRoom;

    private Map<Integer, Map<Integer, FacilityRoom>> facilityRoomMap = new HashMap<>();


    public ARegularMap(String roomRouteRegex) {
        this.roomRouteRegex = roomRouteRegex;
        this.startRoom = new FacilityRoom();
        putFacilityRoom(this.startRoom);
    }


    public Optional<FacilityRoom> facilityRoomAt(int x, int y) {
        Map<Integer, FacilityRoom> row = facilityRoomMap.getOrDefault(y, Collections.emptyMap());
        return Optional.ofNullable(row.get(x));
    }

    private FacilityRoom putFacilityRoom(FacilityRoom facilityRoom) {
        int x = facilityRoom.getX();
        int y = facilityRoom.getY();
        Map<Integer, FacilityRoom> row = facilityRoomMap.computeIfAbsent(y, HashMap::new);
        return row.put(x, facilityRoom);
    }




    public static void main(String[] args) throws Exception {
        if (args.length < 1) {
            System.out.println("Usage <puzzleInputFile>");
            return;
        }
        Path puzzleInputFile = Paths.get(args[0]);

    }

}
