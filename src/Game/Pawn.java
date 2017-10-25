package Game;

import java.util.ArrayList;

public class Pawn extends Figure{
    private boolean FirsStep;

    Pawn(Color NewColor, ArrayList<Figure> FigList, Pair P){
        super(NewColor, FigList, P);
        FirsStep = true;
    }
    @Override
    Type getType(){
        return Type.PAWN;
    }
    @Override
    void CreateMoveList(Board CurrBoard){
        MoveList.clear();
        if(this.Position.OnBoard(0, 1) && CurrBoard.getSquare(this.Position.getX(), this.Position.getY() + 1 ).currFig == null)
            MoveList.add(new Pair(this.Position, 0, 1));
        if(FirsStep && this.Position.OnBoard(0, 2) && CurrBoard.getSquare(this.Position.getX(), this.Position.getY() + 2 ).currFig == null)
            MoveList.add(new Pair(this.Position, 0, 2));
        if(this.Position.OnBoard(1, 1) && CurrBoard.getSquare(this.Position.getX() + 1, this.Position.getY() +1).currFig != null && CurrBoard.getSquare(this.Position.getX() + 1, this.Position.getY() +1).currFig.getFigColor() != this.FigColor){
            MoveList.add(new Pair(this.Position, 1, 1));
            CurrBoard.CoverSquare(this.FigColor, this.Position.getX() + 1, this.Position.getY() + 1);
        }
        if(this.Position.OnBoard(-1, 1) && CurrBoard.getSquare(this.Position.getX() - 1, this.Position.getY() +1).currFig != null && CurrBoard.getSquare(this.Position.getX() - 1, this.Position.getY() +1).currFig.getFigColor() != this.FigColor){
            MoveList.add(new Pair(this.Position, -1, 1));
            CurrBoard.CoverSquare(this.FigColor, this.Position.getX() - 1, this.Position.getY() + 1);
        }
    }

    //Единственный способ остановить пешку это убить её
    @Override
    ArrayList<Pair> SaveKingList(Board CurrBoard, Pair KingPos) {
        ArrayList<Pair> A = new ArrayList<>();
        A.add(new Pair(this.Position.getX(), this.Position.getY()));
        return A;
    }

    //Заглушка для наследование
    @Override
    void CoverSquares(Board CurrBoard) {
    }
}
