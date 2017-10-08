import java.util.ArrayList;

public class Pawn extends Figure{
    public  boolean FirsStep;

    public Pawn(Color NewColor, ArrayList<Figure> FigList, Pair P){
        super(NewColor, FigList, P);
        FirsStep = true;
    }
    @Override
    public Type getType(){
        return Type.PAWN;
    }
    @Override
    public void CreateMoveList(Board CurrBoard){
        MoveList.clear();
        if(this.Position.OnBoard(0, 1) && CurrBoard.getSquare(this.Position.getX(), this.Position.getY() + 1 ).currFig == null)
            MoveList.add(new Pair(this.Position, 0, 1));
        if(FirsStep && this.Position.OnBoard(0, 2) && CurrBoard.getSquare(this.Position.getX(), this.Position.getY() + 2 ).currFig == null)
            MoveList.add(new Pair(this.Position, 0, 2));
        if(this.Position.OnBoard(1, 1) && CurrBoard.SquareCanMove(this.FigColor,this.Position.getX()+1, this.Position.getY()+1)){
            MoveList.add(new Pair(this.Position, 1, 1));
            CurrBoard.CoverSquare(this.FigColor, this.Position.getX() + 1, this.Position.getY() + 1);
        }
        if(this.Position.OnBoard(-1, 1) && CurrBoard.SquareCanMove(this.FigColor,this.Position.getX()-1, this.Position.getY()+1)){
            MoveList.add(new Pair(this.Position, 1, 1));
            CurrBoard.CoverSquare(this.FigColor, this.Position.getX() - 1, this.Position.getY() + 1);
        }
    }
}
