package game;

import java.util.ArrayList;

class Bishop extends Figure {
    Bishop(FigColor NewColor, ArrayList<Figure> FigList, Pair P){
        super(NewColor, FigList, P);
    }
    @Override
    Type getType(){
        return Type.BISHOP;
    }
    @Override
    void createMoveList(Board currBoard) {
        moveList.clear();
        coverSquares(currBoard);
        boolean vertMove = super.canMoveVertically(currBoard);
        boolean horMove = super.canMoveHorizontally(currBoard);
        if (vertMove && horMove && super.canMoveLeftDownDiagonal(currBoard)) {
            //Up-Right
            for (int i = this.position.getX() + 1, j = this.position.getY() + 1; Pair.OnBoard(i, j, false); i++, j++) {
                if (currBoard.getSquare(i, j).currFig == null)
                    moveList.add(new Pair(i, j));
                else if (currBoard.getSquare(i, j).currFig.figColor != this.figColor) {
                    moveList.add(new Pair(i, j));
                    currBoard.coverSquare(this.figColor, i, j);
                    break;
                } else
                    break;
            }
            //Down-Left
            for (int i = this.position.getX() - 1, j = this.position.getY() - 1; Pair.OnBoard(i, j, false); i--, j--) {
                if (currBoard.getSquare(i, j).currFig == null)
                    moveList.add(new Pair(i, j));
                else if (currBoard.getSquare(i, j).currFig.figColor != this.figColor) {
                    moveList.add(new Pair(i, j));
                    break;
                } else
                    break;
            }
        }
        if (vertMove && horMove && super.canMoveRightDownDiagonal(currBoard)) {
            //Up-Left
            for (int i = this.position.getX() - 1, j = this.position.getY() + 1; Pair.OnBoard(i, j, false); i--, j++) {
                if (currBoard.getSquare(i, j).currFig == null)
                    moveList.add(new Pair(i, j));
                else if (currBoard.getSquare(i, j).currFig.figColor != this.figColor) {
                    moveList.add(new Pair(i, j));
                    break;
                } else
                    break;
            }
            //Down-Right
            for (int i = this.position.getX() + 1, j = this.position.getY() - 1; Pair.OnBoard(i, j, false); i++, j--) {
                if (currBoard.getSquare(i, j).currFig == null)
                    moveList.add(new Pair(i, j));
                else if (currBoard.getSquare(i, j).currFig.figColor != this.figColor) {
                    moveList.add(new Pair(i, j));
                    break;
                } else
                    break;
            }
        }
    }
    @Override
    ArrayList<Pair> saveKingList(Board currBoard, Pair kingPos) {
        ArrayList<Pair> A = new ArrayList<>();
        A.add(new Pair(this.position.getX(), this.position.getY()));
        //Если король сверху-справа
        if (kingPos.getX() > this.position.getX() && kingPos.getY() > this.position.getY())
            for (int i = this.position.getX() + 1, j = this.position.getY() + 1; i < kingPos.getX() && j < kingPos.getY(); i++, j++)
                A.add(new Pair(i,j));
        //Есди король сверху-слева
        else if (kingPos.getX() < this.position.getX() && kingPos.getY() > this.position.getY())
            for (int i = this.position.getX() - 1, j = this.position.getY() + 1; i > kingPos.getX() && j < kingPos.getY(); i--, j++)
                A.add(new Pair(i,j));
        //Если король снизу-справа
        else if (kingPos.getX() > this.position.getX() && kingPos.getY() < this.position.getY())
            for (int i = this.position.getX() + 1, j = this.position.getY() - 1; i < kingPos.getX() && j > kingPos.getY(); i++, j--)
                A.add(new Pair(i,j));
        //Если король снизу-слева
        else if (kingPos.getX() < this.position.getX() && kingPos.getY() < this.position.getY())
            for (int i = this.position.getX() - 1, j = this.position.getY() - 1; i > kingPos.getX() && j > kingPos.getY(); i--, j--)
                A.add(new Pair(i,j));
        return A;
    }

    @Override
    void coverSquares(Board currBoard) {
        //Up-Right
        for (int i = this.position.getX() + 1, j = this.position.getY() + 1; Pair.OnBoard(i, j, false); i++, j++) {
            if (currBoard.getSquare(i, j).currFig == null)
                currBoard.coverSquare(this.figColor, i, j);
            else if (currBoard.getSquare(i, j).currFig.figColor != this.figColor) {
                currBoard.coverSquare(this.figColor, i, j);
                if (currBoard.getSquare(i, j).currFig.getType() != Type.KING)
                    break;
            } else if (currBoard.getSquare(i, j).currFig.figColor == this.figColor) {
                currBoard.coverSquare(this.figColor, i, j);
                break;
            } else
                break;
        }
        //Up-Left
        for (int i = this.position.getX() - 1, j = this.position.getY() + 1; Pair.OnBoard(i, j, false); i--, j++) {
            if (currBoard.getSquare(i, j).currFig == null)
                currBoard.coverSquare(this.figColor, i, j);
            else if (currBoard.getSquare(i, j).currFig.figColor != this.figColor) {
                currBoard.coverSquare(this.figColor, i, j);
                if (currBoard.getSquare(i, j).currFig.getType() != Type.KING)
                    break;
            } else if (currBoard.getSquare(i, j).currFig.figColor == this.figColor) {
                currBoard.coverSquare(this.figColor, i, j);
                break;
            } else
                break;
        }
        //Down-Right
        for (int i = this.position.getX() + 1, j = this.position.getY() - 1; Pair.OnBoard(i, j, false); i++, j--) {
            if (currBoard.getSquare(i, j).currFig == null)
                currBoard.coverSquare(this.figColor, i, j);
            else if (currBoard.getSquare(i, j).currFig.figColor != this.figColor) {
                currBoard.coverSquare(this.figColor, i, j);
                if (currBoard.getSquare(i, j).currFig.getType() != Type.KING)
                    break;
            } else if (currBoard.getSquare(i, j).currFig.figColor == this.figColor) {
                currBoard.coverSquare(this.figColor, i, j);
                break;
            } else
                break;
        }
        //Down-Left
        for (int i = this.position.getX() - 1, j = this.position.getY() - 1; Pair.OnBoard(i, j, false); i--, j--) {
            if (currBoard.getSquare(i, j).currFig == null)
                currBoard.coverSquare(this.figColor, i, j);
            else if (currBoard.getSquare(i, j).currFig.figColor != this.figColor) {
                currBoard.coverSquare(this.figColor, i, j);
                if (currBoard.getSquare(i, j).currFig.getType() != Type.KING)
                    break;
            } else if (currBoard.getSquare(i, j).currFig.figColor == this.figColor) {
                currBoard.coverSquare(this.figColor, i, j);
                break;
            } else
                break;
        }
    }
}
