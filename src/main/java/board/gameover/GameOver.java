package board.gameover;

public class GameOver extends RuntimeException {
    /*
    Stops the code execution when the game is over
     */
    public GameOver() {
        super();
    }

    @Override
    public String toString() {
        return "Game ended";
    }
}
