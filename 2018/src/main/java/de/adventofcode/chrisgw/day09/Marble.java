package de.adventofcode.chrisgw.day09;

import lombok.Getter;

import java.util.stream.Stream;


public class Marble {

    @Getter
    private final int value;

    @Getter
    private Marble clockwiseMarble;

    @Getter
    private Marble counterClockwiseMarble;


    public Marble(int value) {
        this.value = value;
        this.clockwiseMarble = this;
        this.counterClockwiseMarble = this;
    }


    public Marble removeMarbel(int marbelStep) {
        return marbelAt(marbelStep).removeMarbel();
    }

    public void placeMarbelBetween(Marble marble, int marbelStepA, int marbelStepB) {
        Marble marbleA = marbelAt(marbelStepA);
        Marble marbleB = marbelAt(marbelStepB);
        marble.setCounterClockwiseMarble(marbleA);
        marble.setClockwiseMarble(marbleB);
    }

    public Marble marbelAt(int marbelStep) {
        boolean clockwise = marbelStep >= 0;
        marbelStep = Math.abs(marbelStep);
        if (clockwise) {
            return clockwiseMarbels().skip(marbelStep).findFirst().orElseThrow(IllegalStateException::new);
        } else {
            return counterClockwiseMarbels().skip(marbelStep).findFirst().orElseThrow(IllegalStateException::new);
        }
    }


    public boolean isDivisibleBy(int number) {
        return value % number == 0;
    }

    public Marble removeMarbel() {
        Marble previousMarble = marbelAt(-1);
        Marble nextMarble = marbelAt(1);
        previousMarble.setClockwiseMarble(nextMarble);
        return this;
    }


    public void setClockwiseMarble(Marble marble) {
        this.clockwiseMarble = marble;
        marble.counterClockwiseMarble = this;
    }

    public void setCounterClockwiseMarble(Marble marble) {
        this.counterClockwiseMarble = marble;
        marble.clockwiseMarble = this;
    }


    public Stream<Marble> clockwiseMarbels() {
        return Stream.iterate(this, Marble::getClockwiseMarble);
    }

    public Stream<Marble> counterClockwiseMarbels() {
        return Stream.iterate(this, Marble::getCounterClockwiseMarble);
    }


    @Override
    public String toString() {
        return String.valueOf(value);
    }

}
