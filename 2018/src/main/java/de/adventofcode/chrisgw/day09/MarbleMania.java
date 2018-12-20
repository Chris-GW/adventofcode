package de.adventofcode.chrisgw.day09;

import lombok.Getter;

import java.math.BigInteger;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class MarbleMania {

    @Getter
    private int marbelCount;

    @Getter
    private int playerCount;


    private Iterator<Marble> marbelsToPlace;

    private final Marble firstMarble;

    @Getter
    private Marble currentMarble;

    @Getter
    private MarblePlayer currentPlayer;


    public MarbleMania(int marbelCount, int playerCount) {
        this.marbelCount = marbelCount;
        this.playerCount = playerCount;

        this.firstMarble = new Marble(0);
        this.currentMarble = firstMarble;
        this.currentPlayer = createMarbelPlayers(playerCount);
        this.marbelsToPlace = IntStream.rangeClosed(1, marbelCount).mapToObj(Marble::new).iterator();
    }

    public static MarbleMania fromMarbleManiaDescription(String marbleManiaDescription) {
        Pattern marbelManiaPattern = Pattern.compile("(\\d+) players; last marble is worth (\\d+) points?",
                Pattern.CASE_INSENSITIVE);
        Matcher matcher = marbelManiaPattern.matcher(marbleManiaDescription);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Expect marbleManiaDescription matching pattern: " + marbelManiaPattern);
        }
        int playerCount = Integer.parseInt(matcher.group(1));
        int marbelCount = Integer.parseInt(matcher.group(2));
        return new MarbleMania(marbelCount, playerCount);
    }


    private MarblePlayer createMarbelPlayers(int playerCount) {
        MarblePlayer firstMarblePlayer = new MarblePlayer(1);
        MarblePlayer currentPlayer = firstMarblePlayer;
        for (int player = 2; player <= playerCount; player++) {
            MarblePlayer marblePlayer = new MarblePlayer(player);
            currentPlayer.setNextPlayer(marblePlayer);
            currentPlayer = marblePlayer;
        }
        currentPlayer.setNextPlayer(firstMarblePlayer);
        return firstMarblePlayer;
    }


    public void nextPlayerPlaceNextMarbel() {
        if (!marbelsToPlace.hasNext()) {
            throw new IllegalStateException("This MarbleMania is finished");
        }
        Marble nextMarble = marbelsToPlace.next();
        if (nextMarble.isDivisibleBy(23)) {
            Marble removedMarble = currentMarble.removeMarbel(-7);
            currentPlayer.addMarbel(removedMarble);
            currentPlayer.addMarbel(nextMarble);
            currentMarble = removedMarble.getClockwiseMarble();
        } else {
            currentMarble.placeMarbelBetween(nextMarble, 1, 2);
            currentMarble = nextMarble;
        }
        currentPlayer = currentPlayer.getNextPlayer();
    }


    public boolean isFinished() {
        return !marbelsToPlace.hasNext();
    }

    public MarblePlayer bestPlayer() {
        MarblePlayer bestMarblePlayer = bestPlayer(this::calculateScore);
        bestMarblePlayer.setScore(calculateScore(bestMarblePlayer));
        return bestMarblePlayer;
    }

    private BigInteger calculateScore(MarblePlayer marblePlayer) {
        return marblePlayer.takenMarbels()
                .mapToInt(Marble::getValue)
                .mapToObj(BigInteger::valueOf)
                .reduce(BigInteger.ZERO, BigInteger::add);
    }


    private MarblePlayer bestPlayer(Function<MarblePlayer, BigInteger> marbleScoreFunction) {
        return marbelPlayers().max(Comparator.comparing(marbleScoreFunction)).orElse(null);
    }


    public Stream<MarblePlayer> marbelPlayers() {
        return Stream.iterate(currentPlayer, MarblePlayer::getNextPlayer).limit(playerCount);
    }

    public Stream<Marble> marbels() {
        return Stream.iterate(firstMarble, Marble::getClockwiseMarble)
                .limit(Math.min(currentMarbelValue() + 1, marbelCount));
    }


    private int currentMarbelValue() {
        return currentMarble.getValue();
    }


    @Override
    public String toString() {
        int playerId = currentPlayer.getPlayerId();
        String joinedMarbelStr = marbels().map(marble -> {
            if (marble.equals(currentMarble)) {
                return String.format("(%2d)", marble.getValue());
            } else {
                return String.format(" %2d ", marble.getValue());
            }
        }).collect(Collectors.joining(""));
        return String.format("[%2d] ", playerId) + joinedMarbelStr;
    }


    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            System.out.println("No path in args[]");
            return;
        }
        Path puzzleInputFile = Paths.get(args[0]);

    }

}
