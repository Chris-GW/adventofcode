package de.adventofcode.chrisgw.day12;

public interface FerryNavigationInstruction {

    void executeAsShipMoveInstruction(FerryNavigationComputer navigationComputer);

    void executeAsWaypointInstruction(FerryNavigationComputer navigationComputer);

}
