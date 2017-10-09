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

    public  void setPosition(Pair nPos){
        Position = nPos;
    }

    public Figure(Color NewColor){
        FigColor = NewColor;
        MoveList = new ArrayList<>();
    }
    public Figure(Color NewColor, ArrayList<Figure> FigList , Pair P){
        FigColor = NewColor;
        MoveList = new ArrayList<>();
        Position = P;
        FigList.add(this);
    }

    public Color getFigColor(){
        return FigColor;
    }



    public abstract Type getType();
    public abstract void CreateMoveList(Board CurBoard);

    public ArrayList<Pair> getMoveList(){
        return  MoveList;
    }
}
