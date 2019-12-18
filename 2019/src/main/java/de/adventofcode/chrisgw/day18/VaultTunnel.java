package de.adventofcode.chrisgw.day18;

import lombok.Data;

import static de.adventofcode.chrisgw.day18.ValutTunnelType.*;


@Data
public class VaultTunnel {

    private final int x;
    private final int y;
    private final ValutTunnelType type;
    private final String key;


    public VaultTunnel(int x, int y, ValutTunnelType type) {
        this(x, y, type, null);
    }

    public VaultTunnel(int x, int y, ValutTunnelType type, String key) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.key = key;
    }


    public boolean isWall() {
        return WALL.equals(type);
    }

    public boolean isKey() {
        return KEY.equals(type);
    }

    public boolean isDoor() {
        return DOOR.equals(type);
    }


    public boolean canUnlockDoorWith(String key) {
        return this.key.equalsIgnoreCase(key);
    }


    public int compareTo(VaultTunnel other) {
        return Character.compare(this.getKey().charAt(0), other.getKey().charAt(0));
    }


    public String getSign() {
        switch (type) {
        case EMPTY:
            return ".";
        case WALL:
            return "#";
        case ENTRANCE:
            return "@";
        case KEY:
            return key;
        case DOOR:
            return key;
        default:
            throw new IllegalArgumentException("unknown type: " + type);
        }
    }

}
