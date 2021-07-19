package board;

/**
 * Represents a coordinate on a game board
 */
public class Coordinate {

    private final int row;
    private final int column;

    public Coordinate(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public Coordinate() {
        this(0, 0);
    }

    /**
     * Transforms the given string into a coordinate, if possible
     * @param s string
     * @return Coordinate
     * @throws RuntimeException if the given string couldn't be parsed
     */
    public static Coordinate parseString(String s) {
        try {
            String[] temp = s.split("[:]");
            return new Coordinate(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]));
        } catch (Exception e) {
            throw new RuntimeException("Impossible to parse String " + s);
        }
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    @Override
    public String toString() {
        return row + ":" + column;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coordinate that = (Coordinate) o;

        if (row != that.row) return false;
        return column == that.column;
    }

    @Override
    public int hashCode() {
        int result = row;
        result = 31 * result + column;
        return result;
    }
}
