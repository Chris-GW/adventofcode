package de.adventofcode.chrisgw.day18;

import lombok.Data;

import java.util.Set;


@Data
public class VaultPath {

    private final VaultTunnel from;
    private final VaultTunnel to;
    private final int distance;
    private final Set<String> neededKeys;


    public String collectedKey() {
        return to.getKey();
    }


    @Override
    public String toString() {
        return String.format("%s -(%2d)> %s: %s", from.getKey(), distance, to.getKey(), neededKeys);
    }

}
