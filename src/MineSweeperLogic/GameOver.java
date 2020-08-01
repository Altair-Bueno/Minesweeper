package MineSweeperLogic;

public class GameOver extends RuntimeException{

    private final static int NOCODE=0;
    public final static int MINEFOUND=1;
    public final static int GAMEWON=2;

    private final int errorCode;

    public GameOver(){
        super();
        errorCode = NOCODE;
    }
    public GameOver(int errorCode){
        super();
        this.errorCode=errorCode;
    }
    public GameOver(String s){
        this(s,NOCODE);
    }
    public GameOver(String s,int code){
        super(s);
        errorCode =code;
    }
    public int getGameOverCode(){
        return errorCode;
    }

    @Override
    public String toString() {
        return super.toString() + " CODE:"+ errorCode;
    }
}
