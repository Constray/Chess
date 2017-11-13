package game;

import java.util.ArrayList;

class Queen extends Figure {
    Queen(FigColor newColor, ArrayList<Figure> figList, Pair p) {
        super(newColor, figList, p);
    }

    Queen(FigColor newColor, Pair p) {
        super(newColor, p);
    }

    @Override
    Type getType(){
        return Type.QUEEN;
    }

    @Override
    void createMoveList(Board currBoard) {
        moveList.clear();
        coverSquares(currBoard);
        boolean rightDownDiag = super.canMoveRightDownDiagonal(currBoard);
        boolean leftDownDiag = super.canMoveLeftDownDiagonal(currBoard);
        boolean vertMove = super.canMoveVertically(currBoard);
        boolean horMove = super.canMoveHorizontally(currBoard);
        if (vertMove && horMove && leftDownDiag) {
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
        if (vertMove && horMove && rightDownDiag) {
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
        if (rightDownDiag && leftDownDiag && horMove) {
            //Right
            for(int i = this.position.getX() + 1; i < 8 ; i++){
                if(currBoard.getSquare(i, this.position.getY()).currFig == null)
                    moveList.add(new Pair(i, this.position.getY()));
                else if(currBoard.getSquare(i, this.position.getY()).currFig.figColor != this.figColor){
                    moveList.add(new Pair(i, this.position.getY()));
                    break;
                }
                else
                    break;
            }
            //Left
            for(int i = this.position.getX() - 1; i >= 0 ; i--){
                if(currBoard.getSquare(i, this.position.getY()).currFig == null)
                    moveList.add(new Pair(i, this.position.getY()));
                else if(currBoard.getSquare(i, this.position.getY()).currFig.figColor != this.figColor){
                    moveList.add(new Pair(i, this.position.getY()));
                    break;
                }
                else
                    break;
            }
        }
        if (rightDownDiag && leftDownDiag && vertMove) {
            //Up
            for(int i = this.position.getY() + 1; i < 8 ; i++){
                if(currBoard.getSquare(this.position.getX(), i).currFig == null)
                    moveList.add(new Pair(this.position.getX(), i));
                else if(currBoard.getSquare(this.position.getX(), i).currFig.figColor != this.figColor){
                    moveList.add(new Pair(this.position.getX(), i));
                    break;
                }
                else
                    break;
            }
            //Down
            for(int i = this.position.getY() - 1; i >= 0; i--){
                if(currBoard.getSquare(this.position.getX(), i).currFig == null)
                    moveList.add(new Pair(this.position.getX(), i));
                else if(currBoard.getSquare(this.position.getX(), i).currFig.figColor != this.figColor){
                    moveList.add(new Pair(this.position.getX(), i));
                    break;
                }
                else
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
        //Если король справа
        else if (kingPos.getX() > this.position.getX())
            for (int i = this.position.getX() + 1; i < kingPos.getX(); i++)
                A.add(new Pair(i,this.position.getY()));
        //Если король слева
        else if (kingPos.getX() < this.position.getX())
            for (int i = kingPos.getX() - 1; i > this.position.getX(); i--)
                A.add(new Pair(i,this.position.getY()));
        //Если король сверху
        else if (kingPos.getY() > this.position.getY())
            for (int i = this.position.getY() + 1; i < kingPos.getY(); i++)
                A.add(new Pair(this.position.getX(),i));
        //Если король снизу
        else if (kingPos.getY() < this.position.getY())
            for (int i = kingPos.getY() - 1; i > position.getY(); i++)
                A.add(new Pair(this.position.getX(),i));
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
        //Right
        for(int i = this.position.getX() + 1; i < 8 ; i++){
            if(currBoard.getSquare(i, this.position.getY()).currFig == null)
                currBoard.coverSquare(this.figColor, i, this.position.getY());
            else if(currBoard.getSquare(i, this.position.getY()).currFig.figColor != this.figColor){
                currBoard.coverSquare(this.figColor, i, this.position.getY());
                if(currBoard.getSquare(i, this.position.getY()).currFig.getType() != Type.KING)
                    break;
            }
            else if(currBoard.getSquare(i, this.position.getY()).currFig.figColor == this.figColor) {
                currBoard.coverSquare(this.figColor, i, this.position.getY());
                break;
            }
            else
                break;
        }
        //Left
        for(int i = this.position.getX() - 1; i >= 0 ; i--){
            if(currBoard.getSquare(i, this.position.getY()).currFig == null)
                currBoard.coverSquare(this.figColor, i, this.position.getY());
            else if(currBoard.getSquare(i, this.position.getY()).currFig.figColor != this.figColor){
                currBoard.coverSquare(this.figColor, i, this.position.getY());
                if(currBoard.getSquare(i, this.position.getY()).currFig.getType() != Type.KING)
                    break;
            }
            else if(currBoard.getSquare(i, this.position.getY()).currFig.figColor == this.figColor) {
                currBoard.coverSquare(this.figColor, i, this.position.getY());
                break;
            }
            else
                break;
        }
        //Up
        for(int i = this.position.getY() + 1; i < 8 ; i++){
            if(currBoard.getSquare(this.position.getX(), i).currFig == null)
                currBoard.coverSquare(this.figColor, this.position.getX(), i);
            else if(currBoard.getSquare(this.position.getX(), i).currFig.figColor != this.figColor){
                currBoard.coverSquare(this.figColor, this.position.getX(), i);
                if(currBoard.getSquare(this.position.getX(), i).currFig.getType() != Type.KING)
                    break;
            }
            else if(currBoard.getSquare(this.position.getX(), i).currFig.figColor == this.figColor) {
                currBoard.coverSquare(this.figColor, this.position.getX(), i);
                break;
            }
            else
                break;
        }
        //Down
        for(int i = this.position.getY() - 1; i >= 0; i--){
            if(currBoard.getSquare(this.position.getX(), i).currFig == null)
                currBoard.coverSquare(this.figColor, this.position.getX(), i);
            else if(currBoard.getSquare(this.position.getX(), i).currFig.figColor != this.figColor){
                currBoard.coverSquare(this.figColor, this.position.getX(), i);
                if(currBoard.getSquare(this.position.getX(), i).currFig.getType() != Type.KING)
                    break;
            }
            else if(currBoard.getSquare(this.position.getX(), i).currFig.figColor == this.figColor) {
                currBoard.coverSquare(this.figColor, this.position.getX(), i);
                break;
            }
            else
                break;
        }
    }
}
