package Game;

import java.util.ArrayList;

class Knight extends Figure{
    Knight(Color NewColor, ArrayList<Figure> FigList, Pair P){
        super(NewColor, FigList, P);
    }
    @Override
    Type getType(){
        return Type.KNIGHT;
    }
    @Override
    void CreateMoveList(Board CurrBoard){
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

    //Единственный способ остановить коня это убить его
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
