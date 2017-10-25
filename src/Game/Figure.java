package Game;

import  java.util.ArrayList;

enum Color{
    BLACK, WHITE;
}
enum Type{
    KING, QUEEN, ROOK, BISHOP, KNIGHT, PAWN;
}

public abstract class Figure {
    protected Color FigColor;

    protected ArrayList<Pair> MoveList;

    protected Pair Position;

    public Figure(Color NewColor){
        FigColor = NewColor;
        MoveList = new ArrayList<>();
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

    //Cоздаёт список клеток, которые может занять фигура противоположенного цвета, чтобы спасти своего короля
    abstract ArrayList<Pair>  SaveKingList(Board CurrBoard, Pair KingPos);

    abstract Type getType();
    abstract void CreateMoveList(Board CurrBoard);
    abstract void CoverSquares(Board CurrBoard);

    public ArrayList<Pair> getMoveList(){
        return  MoveList;
    }
}
