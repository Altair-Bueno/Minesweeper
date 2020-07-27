package MineSweeperLogic;

public class Coordenada {
    private int x;
    private int y;

    public Coordenada(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public Coordenada(){
        this(0,0);
    }

    public int getFila() {
        return x;
    }

    public int getColumna() {
        return y;
    }

    @Override
    public String toString() {
        return x+":"+y;
    }
}
