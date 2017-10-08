import java.util.ArrayList;

public class Knight extends Figure{
    public Knight(Color NewColor, ArrayList<Figure> FigList, Pair P){
        super(NewColor, FigList, P);
    }
    @Override
    public Type getType(){
        return Type.KNIGHT;
    }
    @Override
    public void CreateMoveList(Board CurrBoard){
        MoveList.clear();
        if(this.Position.OnBoard(1, 2) && CurrBoard.SquareCanMove(this.FigColor,this.Position.getX()+1, this.Position.getY()+2)) {
            MoveList.add(new Pair(this.Position, 1, 2));
            CurrBoard.CoverSquare(this.FigColor, this.Position.getX() + 1, this.Position.getY() + 2);
        }
        if(this.Position.OnBoard(-1, 2) && CurrBoard.SquareCanMove(this.FigColor,this.Position.getX()-1, this.Position.getY()+2)){
            MoveList.add(new Pair(this.Position, -1, 2));
            CurrBoard.CoverSquare(this.FigColor, this.Position.getX() - 1, this.Position.getY() + 2);
        }
        if(this.Position.OnBoard(2, 1) && CurrBoard.SquareCanMove(this.FigColor,this.Position.getX()+2, this.Position.getY()+1)){
            MoveList.add(new Pair(this.Position, 2, 1));
            CurrBoard.CoverSquare(this.FigColor, this.Position.getX() + 2, this.Position.getY() + 1);
        }
        if(this.Position.OnBoard(-2, 1) && CurrBoard.SquareCanMove(this.FigColor,this.Position.getX()-2, this.Position.getY()+1)){
            MoveList.add(new Pair(this.Position, 1, 2));
            CurrBoard.CoverSquare(this.FigColor, this.Position.getX() + 1, this.Position.getY() + 2);
        }
        if(this.Position.OnBoard(1, -2) && CurrBoard.SquareCanMove(this.FigColor,this.Position.getX()+1, this.Position.getY()-2)){
            MoveList.add(new Pair(this.Position, 1, -2));
            CurrBoard.CoverSquare(this.FigColor, this.Position.getX() + 1, this.Position.getY() - 2);
        }
        if(this.Position.OnBoard(-1, -2) && CurrBoard.SquareCanMove(this.FigColor,this.Position.getX()-1, this.Position.getY()-2)){
            MoveList.add(new Pair(this.Position, -1, -2));
            CurrBoard.CoverSquare(this.FigColor, this.Position.getX() - 1, this.Position.getY() - 2);
        }
        if(this.Position.OnBoard(2, -1) && CurrBoard.SquareCanMove(this.FigColor,this.Position.getX()+2, this.Position.getY()-1)){
            MoveList.add(new Pair(this.Position, 2, -1));
            CurrBoard.CoverSquare(this.FigColor, this.Position.getX() + 2, this.Position.getY() - 1);
        }
        if(this.Position.OnBoard(-2, -1) && CurrBoard.SquareCanMove(this.FigColor,this.Position.getX()-2, this.Position.getY()-1)){
            MoveList.add(new Pair(this.Position, -2, -2));
            CurrBoard.CoverSquare(this.FigColor, this.Position.getX() - 2, this.Position.getY() - 1);
        }
    }
}
