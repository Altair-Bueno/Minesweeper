package MineSweeperLogic;

public class GameOver extends RuntimeException{

    private final static int NOCODE=0;
    public final static int MINEFOUND=1;
    public final static int GAMEWIN=2;

    private final int errorCode;

    public GameOver(){
        super();
        errorCode =NOCODE;
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
}
