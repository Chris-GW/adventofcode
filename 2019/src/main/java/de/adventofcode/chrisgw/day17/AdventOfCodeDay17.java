package de.adventofcode.chrisgw.day17;

import de.adventofcode.chrisgw.intcode.IntCodeProgram;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Objects.requireNonNull;


/**
 * 2019 Day 17: Set and Forget
 * https://adventofcode.com/2019/day/17
 */
public class AdventOfCodeDay17 {

    private final IntCodeProgram asciiProgram;
    private String cameraImage;


    public AdventOfCodeDay17(IntCodeProgram asciiProgram) {
        this.asciiProgram = requireNonNull(asciiProgram);
    }


    public void run() {
        asciiProgram.run();
        withCameraImage(readCameraImage());
    }


    private String readCameraImage() {
        return asciiProgram.getAllOutput()
                .mapToObj(value -> String.valueOf((char) value))
                .collect(Collectors.joining(""));
    }


    public long sumOfCameraAlignmentParameters() {
        List<ScaffoldIntersection> scaffoldIntersections = scaffoldIntersections().collect(Collectors.toList());
        return scaffoldIntersections.stream().mapToInt(ScaffoldIntersection::alignmentParameter).sum();
    }

    public Stream<ScaffoldIntersection> scaffoldIntersections() {
        return IntStream.range(0, cameraImage.length())
                .filter(this::isScaffoldIntersection)
                .mapToObj(index -> new ScaffoldIntersection(row(index), column(index)));
    }

    private boolean isScaffoldIntersection(int index) {
        int row = row(index);
        int column = column(index);
        boolean rowIsScaffoldPixel = IntStream.rangeClosed(-1, 1)
                .mapToObj(drow -> cameraPixelAt(column, row + drow))
                .allMatch(CameraPixel::isScaffoldPixel);
        boolean columnIsScaffoldPixel = IntStream.rangeClosed(-1, 1)
                .mapToObj(dcolumn -> cameraPixelAt(column + dcolumn, row))
                .allMatch(CameraPixel::isScaffoldPixel);
        return rowIsScaffoldPixel && columnIsScaffoldPixel;
    }


    public AdventOfCodeDay17 withCameraImage(String cameraImageStr) {
        this.cameraImage = cameraImageStr;
        return this;
    }


    public CameraPixel cameraPixelAt(int column, int row) {
        if (column < 0 || column >= cameraImageWidth() || row < 0 || row >= cameraImageHeight()) {
            return CameraPixel.EMPTY;
        }
        int rowStartIndex = row * (cameraImageWidth() + 1);
        int index = rowStartIndex + column;
        char cameraPixelSign = cameraImage.charAt(index);
        return CameraPixel.valueOf(cameraPixelSign);
    }


    public int cameraImageWidth() {
        return cameraImage.indexOf('\n');
    }

    public int cameraImageHeight() {
        return cameraImage.length() / cameraImageWidth();
    }


    public int column(int index) {
        return index % (cameraImageWidth() + 1);
    }

    public int row(int index) {
        return index / (cameraImageWidth() + 1);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(asciiProgram).append("\n");
        for (int row = 0; row < cameraImageHeight(); row++) {
            for (int column = 0; column < cameraImageWidth() - 1; column++) {
                CameraPixel cameraPixel = cameraPixelAt(column, row);
                sb.append(cameraPixel.getCameraPixelSign());
            }
            sb.append('\n');
        }
        return sb.toString();
    }


}
