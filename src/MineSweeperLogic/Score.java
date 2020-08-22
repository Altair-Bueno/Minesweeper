package MineSweeperLogic;

import java.util.StringJoiner;

public class Score implements Comparable<Score> {

    private final String size;
    private final int hours;
    private final int min;
    private final int sec;

    public Score(int secElapsed){
        this(null,secElapsed);
    }

    public Score(String size, int secElapsed) {
        sec = secElapsed % 60;
        int temp = secElapsed / 60;
        min = temp % 60;
        hours = temp / 60;
        this.size = size;
    }

    public Score(String size, int hours, int min, int sec) {
        this.hours = hours;
        this.min = min;
        this.sec = sec;
        this.size = size;
    }

    public static Score parseScore(String size, String s) {
        try {
            String[] temp = s.split("[:]");
            return new Score(size, Integer.parseInt(temp[0]), Integer.parseInt(temp[1]), Integer.parseInt(temp[3]));
        } catch (Exception e) {
            throw new RuntimeException("Coulden't parse Score " + s);
        }
    }

    @Override
    public String toString() {
        StringJoiner stringJoiner = new StringJoiner(":");
        if (hours != 0) stringJoiner.add(hours + "");
        if (min != 0) stringJoiner.add(min + "");
        stringJoiner.add(sec + "");
        return (size==null? "":(size+ " in ")) + stringJoiner.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Score score = (Score) o;

        if (!size.equals(size)) return false;
        if (hours != score.hours) return false;
        if (min != score.min) return false;
        return sec == score.sec;
    }

    @Override
    public int hashCode() {
        int result = size.hashCode();
        result = 31 * result + hours;
        result = 31 * result + min;
        result = 31 * result + sec;
        return result;
    }

    @Override
    public int compareTo(Score o) {
        int temp = size.compareTo(o.size);
        if (temp == 0) temp = hours - o.hours;
        if (temp == 0) temp = min - o.min;
        return temp == 0 ? sec - o.sec : temp;
    }
}
