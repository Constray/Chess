package game;

import java.util.ArrayList;

class Knight extends Figure{
    Knight(FigColor NewColor, ArrayList<Figure> FigList, Pair P){
        super(NewColor, FigList, P);
    }
    @Override
    Type getType(){
        return Type.KNIGHT;
    }
    @Override
    void createMoveList(Board currBoard) {
        moveList.clear();
        if (!super.canMoveRightDownDiagonal(currBoard) || !super.canMoveHorizontally(currBoard)
                                                       || !super.canMoveVertically(currBoard) || !super.canMoveLeftDownDiagonal(currBoard)) {
            currBoard.coverSquare(this.figColor, this.position.getX() + 1, this.position.getY() + 2);
            currBoard.coverSquare(this.figColor, this.position.getX() - 1, this.position.getY() + 2);
            currBoard.coverSquare(this.figColor, this.position.getX() - 2, this.position.getY() + 1);
            currBoard.coverSquare(this.figColor, this.position.getX() - 2, this.position.getY() + 1);
            currBoard.coverSquare(this.figColor, this.position.getX() + 1, this.position.getY() - 2);
            currBoard.coverSquare(this.figColor, this.position.getX() - 1, this.position.getY() - 2);
            currBoard.coverSquare(this.figColor, this.position.getX() + 2, this.position.getY() - 1);
            currBoard.coverSquare(this.figColor, this.position.getX() - 2, this.position.getY() - 1);
            return;
        }
        if (this.position.OnBoard(1, 2) && currBoard.squareCanMove(this.figColor,this.position.getX()+1, this.position.getY()+2)) {
            moveList.add(new Pair(this.position, 1, 2));
            currBoard.coverSquare(this.figColor, this.position.getX() + 1, this.position.getY() + 2);
        }
        if (this.position.OnBoard(-1, 2) && currBoard.squareCanMove(this.figColor,this.position.getX()-1, this.position.getY()+2)) {
            moveList.add(new Pair(this.position, -1, 2));
            currBoard.coverSquare(this.figColor, this.position.getX() - 1, this.position.getY() + 2);
        }
        if (this.position.OnBoard(2, 1) && currBoard.squareCanMove(this.figColor,this.position.getX()+2, this.position.getY()+1)) {
            moveList.add(new Pair(this.position, 2, 1));
            currBoard.coverSquare(this.figColor, this.position.getX() + 2, this.position.getY() + 1);
        }
        if (this.position.OnBoard(-2, 1) && currBoard.squareCanMove(this.figColor,this.position.getX()-2, this.position.getY()+1)) {
            moveList.add(new Pair(this.position, -2, 1));
            currBoard.coverSquare(this.figColor, this.position.getX() - 2, this.position.getY() + 1);
        }
        if (this.position.OnBoard(1, -2) && currBoard.squareCanMove(this.figColor,this.position.getX()+1, this.position.getY()-2)) {
            moveList.add(new Pair(this.position, 1, -2));
            currBoard.coverSquare(this.figColor, this.position.getX() + 1, this.position.getY() - 2);
        }
        if (this.position.OnBoard(-1, -2) && currBoard.squareCanMove(this.figColor,this.position.getX()-1, this.position.getY()-2)) {
            moveList.add(new Pair(this.position, -1, -2));
            currBoard.coverSquare(this.figColor, this.position.getX() - 1, this.position.getY() - 2);
        }
        if (this.position.OnBoard(2, -1) && currBoard.squareCanMove(this.figColor,this.position.getX()+2, this.position.getY()-1)) {
            moveList.add(new Pair(this.position, 2, -1));
            currBoard.coverSquare(this.figColor, this.position.getX() + 2, this.position.getY() - 1);
        }
        if (this.position.OnBoard(-2, -1) && currBoard.squareCanMove(this.figColor,this.position.getX()-2, this.position.getY()-1)) {
            moveList.add(new Pair(this.position, -2, -1));
            currBoard.coverSquare(this.figColor, this.position.getX() - 2, this.position.getY() - 1);
        }
    }

    //Единственный способ остановить коня это убить его
    @Override
    ArrayList<Pair> saveKingList(Board currBoard, Pair kingPos) {
        ArrayList<Pair> A = new ArrayList<>();
        A.add(new Pair(this.position.getX(), this.position.getY()));
        return A;
    }
    //Заглушка для наследование
    @Override
    void coverSquares(Board currBoard) {
    }
}
