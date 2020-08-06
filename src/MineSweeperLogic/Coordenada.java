package MineSweeperLogic;

public class Coordenada {

    private final int fila;
    private final int columna;

    public Coordenada(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
    }

    public Coordenada() {
        this(0, 0);
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }

    public static Coordenada getOriginInstance() {
        return new Coordenada();
    }

    public static Coordenada parseString(String s){
        String[] temp = s.split("[:]");
        return new Coordenada(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]));
    }

    @Override
    public String toString() {
        return fila + ":" + columna;
    }
}
