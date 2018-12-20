package de.adventofcode.chrisgw.day20;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Value;

import java.util.EnumMap;
import java.util.Map;


@Value
public class FacilityRoom {

    private final int x;
    private final int y;

    @Getter(AccessLevel.PRIVATE)
    @EqualsAndHashCode.Exclude
    private final Map<FacilityRouteDirection, FacilityRoom> connectedRooms;


    public FacilityRoom() {
        this(0, 0);
    }

    public FacilityRoom(int x, int y) {
        this.x = x;
        this.y = y;
        this.connectedRooms = new EnumMap<>(FacilityRouteDirection.class);
    }


    public boolean hasRoomTo(FacilityRouteDirection direction) {
        return connectedRooms.containsKey(direction);
    }

    public FacilityRoom getRoomTo(FacilityRouteDirection direction) {
        return connectedRooms.get(direction);
    }

    public FacilityRoom putConnectedRoom(FacilityRoom connectedRoom, FacilityRouteDirection direction) {
        connectedRoom.connectedRooms.put(direction.oppositeDirection(), this);
        return connectedRooms.put(direction, connectedRoom);
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
