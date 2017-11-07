package Game;

import  java.util.ArrayList;

public abstract class Figure {
    protected Color FigColor;

    protected ArrayList<Pair> MoveList;

    protected Pair Position;

    Figure(Color NewColor, Pair p){
        FigColor = NewColor;
        MoveList = new ArrayList<>();
        Position = p;
    }
    Figure(Color NewColor, ArrayList<Figure> FigList , Pair P){
        FigColor = NewColor;
        MoveList = new ArrayList<>();
        Position = P;
        FigList.add(this);
    }

    Color getFigColor(){
        return FigColor;
    }

    public void setPosition(Pair position) {
        Position = position;
    }

    //Cоздаёт список клеток, которые может занять фигура противоположенного цвета, чтобы спасти своего короля
    abstract ArrayList<Pair>  SaveKingList(Board CurrBoard, Pair KingPos);

    abstract Type getType();
    abstract void CreateMoveList(Board CurrBoard);
    abstract void CoverSquares(Board CurrBoard);

    public ArrayList<Pair> getMoveList(){
        return  MoveList;
    }
}

enum Color{
    BLACK, WHITE;
}
enum Type{
    KING, QUEEN, ROOK, BISHOP, KNIGHT, PAWN;
}
