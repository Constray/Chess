package game;

import java.util.ArrayList;

class Rook extends Figure {
    boolean Castling;

    Rook(FigColor NewColor, boolean cast, ArrayList<Figure> FigList, Pair P){
        super(NewColor, FigList, P);
        Castling = cast;
    }
    @Override
    public Type getType(){
        return Type.ROOK;
    }
    @Override
    public void createMoveList(Board currBoard){
        moveList.clear();
        coverSquares(currBoard);
        boolean rightDownDiag = super.canMoveRightDownDiagonal(currBoard);
        boolean leftDownDiag = super.canMoveLeftDownDiagonal(currBoard);
        if (rightDownDiag && leftDownDiag && super.canMoveHorizontally(currBoard)) {
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
        if (rightDownDiag && leftDownDiag && super.canMoveVertically(currBoard)) {
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
        //Если король справа
        if (kingPos.getX() > this.position.getX())
            for (int i = this.position.getX() + 1; i < kingPos.getX(); i++)
                A.add(new Pair(i,this.position.getY()));
            //Если король слева
        else if (kingPos.getX() < this.position.getX())
            for (int i = kingPos.getX() + 1; i < this.position.getX(); i++)
                A.add(new Pair(i,this.position.getY()));
            //Если король сверху
        else if (kingPos.getY() > this.position.getY())
            for (int i = this.position.getY() + 1; i < kingPos.getY(); i++)
                A.add(new Pair(this.position.getX(),i));
            //Если король снизу
        else if (kingPos.getY() < this.position.getY())
            for (int i = kingPos.getY() + 1; i < this.position.getY(); i++)
                A.add(new Pair(this.position.getX(),i));
        return A;
    }

    @Override
    void coverSquares(Board currBoard) {
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
