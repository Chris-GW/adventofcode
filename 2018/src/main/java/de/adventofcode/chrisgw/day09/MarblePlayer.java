package de.adventofcode.chrisgw.day09;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


@ToString
@EqualsAndHashCode
public class MarblePlayer {

    @Getter
    private final int playerId;

    @Getter
    @Setter
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private MarblePlayer nextPlayer;

    private List<Marble> takenMarbles = new ArrayList<>();

    @Getter
    @Setter
    private BigInteger score;


    public MarblePlayer(int playerId) {
        this.playerId = playerId;
        this.nextPlayer = this;
    }


    public void addMarbel(Marble marble) {
        takenMarbles.add(marble);
    }


    public Stream<Marble> takenMarbels() {
        return takenMarbles.stream();
    }

    public Marble lastTakenMarbel() {
        return takenMarbles.get(takenMarbles.size() - 1);
    }

}
