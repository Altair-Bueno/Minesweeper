import manager.Platform;
import manager.StartMineSweeper;
import manager.Theme;



public class Main {
    public static void main(String[] args) {
        Theme.setTheme(Theme.getDefinedTheme());
        Platform.setup();

        //Starts the game on a new thread
        Thread game = new Thread(new StartMineSweeper());
        game.start();
    }
}
