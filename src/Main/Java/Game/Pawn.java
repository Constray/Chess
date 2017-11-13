package game;

import java.util.ArrayList;

public class Pawn extends Figure {
    boolean FirsStep;

    Pawn(FigColor NewColor, ArrayList<Figure> FigList, Pair P) {
        super(NewColor, FigList, P);
        FirsStep = true;
    }
    @Override
    Type getType(){
        return Type.PAWN;
    }
    @Override
    void createMoveList(Board currBoard) {
        moveList.clear();
        boolean vertMove = super.canMoveVertically(currBoard);
        boolean horMove = super.canMoveHorizontally(currBoard);
        boolean rightDownDiag = super.canMoveRightDownDiagonal(currBoard);
        boolean leftDownDiag = super.canMoveLeftDownDiagonal(currBoard);
        switch (this.figColor) {
            case BLACK:
                if (rightDownDiag && leftDownDiag && vertMove) {
                    if (this.position.OnBoard(0, -1) && currBoard.getSquare(this.position.getX(), this.position.getY() - 1 ).currFig == null)
                        moveList.add(new Pair(this.position, 0, -1));
                    if (FirsStep && this.position.OnBoard(0, -2) && currBoard.getSquare(this.position.getX(), this.position.getY() - 2).currFig == null)
                        moveList.add(new Pair(this.position, 0, -2));
                }
                if (rightDownDiag && vertMove && horMove) {
                    if(this.position.OnBoard(1, -1) && currBoard.getSquare(this.position.getX() + 1, this.position.getY() - 1).currFig != null && currBoard.getSquare(this.position.getX() + 1, this.position.getY() - 1).currFig.getFigColor() != this.figColor) {
                        moveList.add(new Pair(this.position, 1, -1));
                    }
                }
                if (leftDownDiag && vertMove && horMove) {
                    if(this.position.OnBoard(-1, -1) && currBoard.getSquare(this.position.getX() - 1, this.position.getY() - 1).currFig != null && currBoard.getSquare(this.position.getX() - 1, this.position.getY() - 1).currFig.getFigColor() != this.figColor) {
                        moveList.add(new Pair(this.position, -1, -1));
                    }
                }
                currBoard.coverSquare(this.figColor, this.position.getX() + 1, this.position.getY() - 1);
                currBoard.coverSquare(this.figColor, this.position.getX() - 1, this.position.getY() - 1);
                break;
            case WHITE:
                if (vertMove && rightDownDiag && leftDownDiag) {
                    if(this.position.OnBoard(0, 1) && currBoard.getSquare(this.position.getX(), this.position.getY() + 1).currFig == null)
                        moveList.add(new Pair(this.position, 0, 1));
                    if(FirsStep && this.position.OnBoard(0, 2) && currBoard.getSquare(this.position.getX(), this.position.getY() + 2).currFig == null)
                        moveList.add(new Pair(this.position, 0, 2));
                }
                if (leftDownDiag && vertMove && horMove) {
                    if (this.position.OnBoard(1, 1) && currBoard.getSquare(this.position.getX() + 1, this.position.getY() + 1).currFig != null && currBoard.getSquare(this.position.getX() + 1, this.position.getY() +1).currFig.getFigColor() != this.figColor) {
                        moveList.add(new Pair(this.position, 1, 1));
                    }
                }
                if (rightDownDiag && vertMove && horMove) {
                    if (this.position.OnBoard(-1, 1) && currBoard.getSquare(this.position.getX() - 1, this.position.getY() + 1).currFig != null && currBoard.getSquare(this.position.getX() - 1, this.position.getY() +1).currFig.getFigColor() != this.figColor) {
                        moveList.add(new Pair(this.position, -1, 1));
                    }
                }
                currBoard.coverSquare(this.figColor, this.position.getX() - 1, this.position.getY() + 1);
                currBoard.coverSquare(this.figColor, this.position.getX() + 1, this.position.getY() + 1);
        }
    }

    //Единственный способ остановить пешку это убить её
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
