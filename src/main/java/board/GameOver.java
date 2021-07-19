package board;

public class GameOver extends RuntimeException {
    public final static int MINEFOUND = 1;
    public final static int GAMEWON = 2;
    /*
    Stops the code execution when the game is over
     */
    private final static int NOCODE = 0;
    private final int errorCode;

    public GameOver() {
        super();
        errorCode = NOCODE;
    }

    public GameOver(int errorCode) {
        super();
        this.errorCode = errorCode;
    }

    public GameOver(String s) {
        this(s, NOCODE);
    }

    public GameOver(String s, int code) {
        super(s);
        errorCode = code;
    }

    public int getGameOverCode() {
        return errorCode;
    }

    @Override
    public String toString() {
        return super.toString() + " CODE:" + errorCode;
    }
}
