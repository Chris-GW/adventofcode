package de.adventofcode.chrisgw;

import lombok.RequiredArgsConstructor;

import java.nio.file.Path;
import java.time.Year;


@RequiredArgsConstructor
public class AdventOfCodePuzzlePreparer {

    private final String aocSessionCookie;
    private final Path projectDirectory;


    public void prepareAdventOfCodePuzzle(int day) {
        prepareAdventOfCodePuzzle(Year.now(), day);
    }

    public void prepareAdventOfCodePuzzle(Year year, int day) {

    }


    public static void main(String[] args) {

    }

}
