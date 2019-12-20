package de.adventofcode.chrisgw.day15;

public enum RepairDroidStatusCode {

    HIT_WALL(0), //
    MOVED(1), //
    REACHED_LOCATION(2);


    private final int statusCode;


    RepairDroidStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public static RepairDroidStatusCode valueOf(int statusCode) {
        for (RepairDroidStatusCode repairDroidStatusCode : RepairDroidStatusCode.values()) {
            if (repairDroidStatusCode.getStatusCode() == statusCode) {
                return repairDroidStatusCode;
            }
        }
        throw new IllegalArgumentException("Unknown status code: " + statusCode);
    }


    public int getStatusCode() {
        return statusCode;
    }

}
