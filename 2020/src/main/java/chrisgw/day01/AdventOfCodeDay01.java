package chrisgw.day01;

/**
 * https://adventofcode.com/2019/day/1
 */
public class AdventOfCodeDay01 {

    public static long calculateModuleFuelRequirement(long moduleMass) {
        long fuel = moduleMass / 3;
        return Math.max(fuel - 2, 0);
    }

    public static long calculateCorrectFuelRequirement(long mass) {
        if (mass <= 0) {
            return 0;
        }
        long fuel = calculateModuleFuelRequirement(mass);
        long fuelForFuelMass = calculateCorrectFuelRequirement(fuel);
        return fuel + fuelForFuelMass;
    }

}
