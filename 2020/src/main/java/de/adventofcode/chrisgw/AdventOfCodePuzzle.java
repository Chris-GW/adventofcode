package de.adventofcode.chrisgw;

import lombok.Data;

import java.net.URI;
import java.net.http.HttpRequest;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Year;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.TimeZone;

import static java.time.Month.DECEMBER;


@Data
public abstract class AdventOfCodePuzzle {

    public static final Year FIRST_ADVENT_OF_CODE_YEAR = Year.of(2015);
    public static final int ADVENT_OF_CODE_PUZZLE_COUNT = 25;
    public static final URI ADVENT_OF_CODE_BASE_URI = URI.create("https://adventofcode.com/");

    private final List<String> inputLines;


    public abstract Object solveFirstPart();

    public abstract Object solveSecondPart();


    public abstract Year getYear();

    public abstract int getDay();

    public LocalDate getDate() {
        return getYear().atMonth(DECEMBER).atDay(getDay());
    }

    public ZonedDateTime puzzleOpeningDateTime() {
        TimeZone utc = TimeZone.getTimeZone("UTC");
        return ZonedDateTime.of(getDate(), LocalTime.of(5, 0), utc.toZoneId());
    }


    public HttpRequest getAdventOfCodePuzzleHtml() {
        return HttpRequest.newBuilder().GET().uri(adventOfCodePuzzleUri()).header("accept", "text/html").build();
    }

    public HttpRequest getAdventOfCodePuzzleInput() {
        return HttpRequest.newBuilder()
                .GET()
                .uri(adventOfCodePuzzleUri().resolve("input"))
                .header("accept", "text/html")
                .build();
    }

    private URI adventOfCodePuzzleUri() {
        return ADVENT_OF_CODE_BASE_URI.resolve(String.valueOf(getYear()))
                .resolve("day")
                .resolve(String.valueOf(getDay()));
    }


    @Override
    public String toString() {
        return "Advent of Code " + getYear() + " day " + getDay();
    }

}
