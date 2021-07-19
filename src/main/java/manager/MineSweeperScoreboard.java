package manager;

import board.Score;

import java.util.SortedSet;
import java.util.TreeSet;
import java.util.Vector;

public class MineSweeperScoreboard {
    private static final SortedSet<Score> scoreList = new TreeSet<>();

    public static void addScore(String size, Integer i) {
        scoreList.add(new Score(size, i));
    }

    public static Vector<Score> getScoreboard() {
        return new Vector<>(scoreList);
    }
}
