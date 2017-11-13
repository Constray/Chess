package game;

import java.util.ArrayList;

class King extends Figure {
    boolean Castling;

    King(FigColor NewColor, ArrayList<Figure> FigList, Pair P){
        super(NewColor, FigList, P);
        Castling = true;
    }
    @Override
    public Type getType(){
        return Type.KING;
    }
    @Override
    public void createMoveList(Board currBoard){
        moveList.clear();
        switch (this.figColor){
            case BLACK:
                CheckSurrBlack(currBoard);
                CheckCastlingBlack(currBoard);
                break;
            case WHITE:
                CheckSurrWhite(currBoard);
                CheckCastlingWhite(currBoard);
                break;
        }
    }

    private void CheckSurrBlack(Board CurrBoard){
        Square CurSquare = CurrBoard.getSquare(this.position.getX() - 1 , this.position.getY() + 1);
        if(CurSquare != null && CurrBoard.squareCanMove(this.figColor,CurSquare) && !CurSquare.coveredByWhite) {
            moveList.add(new Pair(this.position, -1, 1));
            CurrBoard.coverSquare(this.figColor, this.position.getX() -1, this.position.getY()+1);
        }
        CurSquare = CurrBoard.getSquare(this.position.getX(), this.position.getY() + 1);
        if(CurSquare != null && CurrBoard.squareCanMove(this.figColor,CurSquare) && !CurSquare.coveredByWhite) {
            moveList.add(new Pair(this.position, 0, 1));
            CurrBoard.coverSquare(this.figColor, this.position.getX(), this.position.getY()+1);
        }
        CurSquare = CurrBoard.getSquare(this.position.getX() +1, this.position.getY() + 1);
        if(CurSquare != null && CurrBoard.squareCanMove(this.figColor,CurSquare) && !CurSquare.coveredByWhite) {
            moveList.add(new Pair(this.position, 1, 1));
            CurrBoard.coverSquare(this.figColor, this.position.getX() + 1, this.position.getY()+1);
        }
        CurSquare = CurrBoard.getSquare(this.position.getX() - 1, this.position.getY());
        if(CurSquare != null && CurrBoard.squareCanMove(this.figColor,CurSquare) && !CurSquare.coveredByWhite) {
            moveList.add(new Pair(this.position, -1, 0));
            CurrBoard.coverSquare(this.figColor, this.position.getX() -1, this.position.getY());
        }
        CurSquare = CurrBoard.getSquare(this.position.getX() + 1, this.position.getY());
        if(CurSquare != null && CurrBoard.squareCanMove(this.figColor,CurSquare) && !CurSquare.coveredByWhite) {
            moveList.add(new Pair(this.position, +1, 0));
            CurrBoard.coverSquare(this.figColor, this.position.getX() +1, this.position.getY());
        }
        CurSquare = CurrBoard.getSquare(this.position.getX() - 1, this.position.getY() - 1);
        if(CurSquare != null && CurrBoard.squareCanMove(this.figColor,CurSquare) && !CurSquare.coveredByWhite) {
            moveList.add(new Pair(this.position, -1, -1));
            CurrBoard.coverSquare(this.figColor, this.position.getX() -1, this.position.getY()-1);
        }
        CurSquare = CurrBoard.getSquare(this.position.getX(), this.position.getY() -1);
        if(CurSquare != null && CurrBoard.squareCanMove(this.figColor,CurSquare) && !CurSquare.coveredByWhite) {
            moveList.add(new Pair(this.position, 0, -1));
            CurrBoard.coverSquare(this.figColor, this.position.getX() , this.position.getY()-1);
        }
        CurSquare = CurrBoard.getSquare(this.position.getX() +1, this.position.getY()-1);
        if(CurSquare != null && CurrBoard.squareCanMove(this.figColor,CurSquare) && !CurSquare.coveredByWhite){
            moveList.add(new Pair(this.position, 1, -1));
            CurrBoard.coverSquare(this.figColor, this.position.getX() + 1, this.position.getY() - 1);
        }
    }
    private void CheckSurrWhite(Board CurrBoard){
        Square CurSquare = CurrBoard.getSquare(this.position.getX() - 1 , this.position.getY() + 1);
        if(CurSquare != null && CurrBoard.squareCanMove(this.figColor,CurSquare) && !CurSquare.coveredByBlack) {
            moveList.add(new Pair(this.position, -1, 1));
            CurrBoard.coverSquare(this.figColor, this.position.getX() -1, this.position.getY()+1);
        }
        CurSquare = CurrBoard.getSquare(this.position.getX(), this.position.getY() + 1);
        if(CurSquare != null && CurrBoard.squareCanMove(this.figColor,CurSquare) && !CurSquare.coveredByBlack) {
            moveList.add(new Pair(this.position, 0, 1));
            CurrBoard.coverSquare(this.figColor, this.position.getX(), this.position.getY()+1);
        }
        CurSquare = CurrBoard.getSquare(this.position.getX() +1, this.position.getY() + 1);
        if(CurSquare != null && CurrBoard.squareCanMove(this.figColor,CurSquare) && !CurSquare.coveredByBlack) {
            moveList.add(new Pair(this.position, 1, 1));
            CurrBoard.coverSquare(this.figColor, this.position.getX() + 1, this.position.getY()+1);
        }
        CurSquare = CurrBoard.getSquare(this.position.getX() - 1, this.position.getY());
        if(CurSquare != null && CurrBoard.squareCanMove(this.figColor,CurSquare) && !CurSquare.coveredByBlack) {
            moveList.add(new Pair(this.position, -1, 0));
            CurrBoard.coverSquare(this.figColor, this.position.getX() -1, this.position.getY());
        }
        CurSquare = CurrBoard.getSquare(this.position.getX() + 1, this.position.getY());
        if(CurSquare != null && CurrBoard.squareCanMove(this.figColor,CurSquare) && !CurSquare.coveredByBlack) {
            moveList.add(new Pair(this.position, +1, 0));
            CurrBoard.coverSquare(this.figColor, this.position.getX() +1, this.position.getY());
        }
        CurSquare = CurrBoard.getSquare(this.position.getX() - 1, this.position.getY() - 1);
        if(CurSquare != null && CurrBoard.squareCanMove(this.figColor,CurSquare) && !CurSquare.coveredByBlack) {
            moveList.add(new Pair(this.position, -1, -1));
            CurrBoard.coverSquare(this.figColor, this.position.getX() -1, this.position.getY()-1);
        }
        CurSquare = CurrBoard.getSquare(this.position.getX(), this.position.getY() -1);
        if(CurSquare != null && CurrBoard.squareCanMove(this.figColor,CurSquare) && !CurSquare.coveredByBlack) {
            moveList.add(new Pair(this.position, 0, -1));
            CurrBoard.coverSquare(this.figColor, this.position.getX() , this.position.getY()-1);
        }
        CurSquare = CurrBoard.getSquare(this.position.getX() +1, this.position.getY()-1);
        if(CurSquare != null && CurrBoard.squareCanMove(this.figColor,CurSquare) && !CurSquare.coveredByBlack){
            moveList.add(new Pair(this.position, 1, -1));
            CurrBoard.coverSquare(this.figColor, this.position.getX() + 1, this.position.getY() - 1);
        }
    }
    private void CheckCastlingBlack(Board CurrBoard){
        if(Castling && !CurrBoard.getSquare(this.position.getX(), this.position.getY()).coveredByWhite){
            Square CurSquare = CurrBoard.getSquare(0, 7);
            if((null !=  CurSquare.currFig) && CurSquare.currFig.getType() == Type.ROOK && ((Rook)CurSquare.currFig).Castling ){
                CurSquare = CurrBoard.getSquare(1, 7);
                if(CurSquare.currFig == null) {
                    CurSquare = CurrBoard.getSquare(2, 7);
                    if(CurSquare.currFig == null) {
                        CurSquare = CurrBoard.getSquare(3, 7);
                        if(CurSquare.currFig == null && !CurSquare.coveredByWhite)
                            moveList.add(new Pair(0,7));
                    }
                }
            }
            CurSquare = CurrBoard.getSquare(7, 7);
            if((null !=  CurSquare.currFig) && CurSquare.currFig.getType() == Type.ROOK && ((Rook)CurSquare.currFig).Castling ) {
                CurSquare = CurrBoard.getSquare(6, 7);
                if (CurSquare.currFig == null) {
                    CurSquare = CurrBoard.getSquare(5, 7);
                    if (CurSquare.currFig == null && !CurSquare.coveredByWhite)
                        moveList.add(new Pair(7, 7));
                }
            }
        }
    }
    private void CheckCastlingWhite(Board CurrBoard) {
        if (Castling && !CurrBoard.getSquare(this.position.getX(), this.position.getY()).coveredByBlack) {
            Square CurSquare = CurrBoard.getSquare(0, 0);
            if ((null !=  CurSquare.currFig) && CurSquare.currFig.getType() == Type.ROOK && ((Rook)CurSquare.currFig).Castling ) {
                CurSquare = CurrBoard.getSquare(1, 0);
                if(CurSquare.currFig == null) {
                    CurSquare = CurrBoard.getSquare(2, 0);
                    if(CurSquare.currFig == null) {
                        CurSquare = CurrBoard.getSquare(3, 0);
                        if(CurSquare.currFig == null && !CurSquare.coveredByBlack)
                            moveList.add(new Pair(0,0));
                    }
                }
            }
            CurSquare = CurrBoard.getSquare(7, 0);
            if((null !=  CurSquare.currFig) && CurSquare.currFig.getType() == Type.ROOK && ((Rook)CurSquare.currFig).Castling ) {
                CurSquare = CurrBoard.getSquare(6, 0);
                if (CurSquare.currFig == null) {
                    CurSquare = CurrBoard.getSquare(5, 0);
                    if (CurSquare.currFig == null && !CurSquare.coveredByBlack)
                            moveList.add(new Pair(7, 0));
                }
            }
        }
    }

    //Такой ситуации, что король атакует короля в принципе не может произойти
    @Override
    ArrayList<Pair> saveKingList(Board currBoard, Pair kingPos) {
        return null;
    }

    //Заглушка для наследования
    @Override
    void coverSquares(Board currBoard) {
    }
}
