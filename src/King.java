import java.util.ArrayList;

public class King extends Figure {
    public  boolean Castling;

    public King(Color NewColor, ArrayList<Figure> FigList, Pair P){
        super(NewColor, FigList, P);
        Castling = true;
    }
    @Override
    public Type getType(){
        return Type.KING;
    }
    @Override
    public void CreateMoveList( Board CurrBoard){
        MoveList.clear();
        switch (this.FigColor){
            case BLACK:
                CheckSurrBlack(CurrBoard);
                CheckCastlingBlack(CurrBoard);
                break;
            case WHITE:
                CheckSurrWhite(CurrBoard);
                CheckCastlingWhite(CurrBoard);
                break;
        }
    }

    private void CheckSurrBlack(Board CurrBoard){
        Board.Square CurSqare = CurrBoard.getSquare(this.Position.getX() - 1 , this.Position.getY() + 1);
        if(this.Position.OnBoard(-1, 1) && CurrBoard.SquareCanMove(this.FigColor,CurSqare) && !CurSqare.CoveredByWhite) {
            MoveList.add(new Pair(this.Position, -1, 1));
            CurrBoard.CoverSquare(this.FigColor, this.Position.getX() -1, this.Position.getY()+1);
        }
        CurSqare = CurrBoard.getSquare(this.Position.getX(), this.Position.getY() + 1);
        if(this.Position.OnBoard(0, 1) && CurrBoard.SquareCanMove(this.FigColor,CurSqare) && !CurSqare.CoveredByWhite) {
            MoveList.add(new Pair(this.Position, 0, 1));
            CurrBoard.CoverSquare(this.FigColor, this.Position.getX(), this.Position.getY()+1);
        }
        CurSqare = CurrBoard.getSquare(this.Position.getX() +1, this.Position.getY() + 1);
        if(this.Position.OnBoard(1, 1) && CurrBoard.SquareCanMove(this.FigColor,CurSqare) && !CurSqare.CoveredByWhite) {
            MoveList.add(new Pair(this.Position, 1, 1));
            CurrBoard.CoverSquare(this.FigColor, this.Position.getX() + 1, this.Position.getY()+1);
        }
        CurSqare = CurrBoard.getSquare(this.Position.getX() - 1, this.Position.getY());
        if(this.Position.OnBoard(-1, 0) && CurrBoard.SquareCanMove(this.FigColor,CurSqare) && !CurSqare.CoveredByWhite) {
            MoveList.add(new Pair(this.Position, -1, 0));
            CurrBoard.CoverSquare(this.FigColor, this.Position.getX() -1, this.Position.getY());
        }
        CurSqare = CurrBoard.getSquare(this.Position.getX() + 1, this.Position.getY());
        if(this.Position.OnBoard(1, 0) && CurrBoard.SquareCanMove(this.FigColor,CurSqare) && !CurSqare.CoveredByWhite) {
            MoveList.add(new Pair(this.Position, +1, 0));
            CurrBoard.CoverSquare(this.FigColor, this.Position.getX() +1, this.Position.getY());
        }
        CurSqare = CurrBoard.getSquare(this.Position.getX() - 1, this.Position.getY() - 1);
        if(this.Position.OnBoard(-1, -1) && CurrBoard.SquareCanMove(this.FigColor,CurSqare) && !CurSqare.CoveredByWhite) {
            MoveList.add(new Pair(this.Position, -1, -1));
            CurrBoard.CoverSquare(this.FigColor, this.Position.getX() -1, this.Position.getY()-1);
        }
        CurSqare = CurrBoard.getSquare(this.Position.getX(), this.Position.getY() -1);
        if(this.Position.OnBoard(0, -1) && CurrBoard.SquareCanMove(this.FigColor,CurSqare) && !CurSqare.CoveredByWhite) {
            MoveList.add(new Pair(this.Position, 0, -1));
            CurrBoard.CoverSquare(this.FigColor, this.Position.getX() , this.Position.getY()-1);
        }
        CurSqare = CurrBoard.getSquare(this.Position.getX() +1, this.Position.getY()-1);
        if(this.Position.OnBoard(1, -1) && CurrBoard.SquareCanMove(this.FigColor,CurSqare) && !CurSqare.CoveredByWhite){
            MoveList.add(new Pair(this.Position, 1, -1));
            CurrBoard.CoverSquare(this.FigColor, this.Position.getX() + 1, this.Position.getY() - 1);
        }
    }
    private void CheckSurrWhite(Board CurrBoard){
        Board.Square CurSqare = CurrBoard.getSquare(this.Position.getX() - 1 , this.Position.getY() + 1);
        if(this.Position.OnBoard(-1, 1) && CurrBoard.SquareCanMove(this.FigColor,CurSqare) && !CurSqare.CoveredByBlack) {
            MoveList.add(new Pair(this.Position, -1, 1));
            CurrBoard.CoverSquare(this.FigColor, this.Position.getX() -1, this.Position.getY()+1);
        }
        CurSqare = CurrBoard.getSquare(this.Position.getX(), this.Position.getY() + 1);
        if(this.Position.OnBoard(0, 1) && CurrBoard.SquareCanMove(this.FigColor,CurSqare) && !CurSqare.CoveredByBlack) {
            MoveList.add(new Pair(this.Position, 0, 1));
            CurrBoard.CoverSquare(this.FigColor, this.Position.getX(), this.Position.getY()+1);
        }
        CurSqare = CurrBoard.getSquare(this.Position.getX() +1, this.Position.getY() + 1);
        if(this.Position.OnBoard(1, 1) && CurrBoard.SquareCanMove(this.FigColor,CurSqare) && !CurSqare.CoveredByBlack) {
            MoveList.add(new Pair(this.Position, 1, 1));
            CurrBoard.CoverSquare(this.FigColor, this.Position.getX() + 1, this.Position.getY()+1);
        }
        CurSqare = CurrBoard.getSquare(this.Position.getX() - 1, this.Position.getY());
        if(this.Position.OnBoard(-1, 0) && CurrBoard.SquareCanMove(this.FigColor,CurSqare) && !CurSqare.CoveredByBlack) {
            MoveList.add(new Pair(this.Position, -1, 0));
            CurrBoard.CoverSquare(this.FigColor, this.Position.getX() -1, this.Position.getY());
        }
        CurSqare = CurrBoard.getSquare(this.Position.getX() + 1, this.Position.getY());
        if(this.Position.OnBoard(1, 0) && CurrBoard.SquareCanMove(this.FigColor,CurSqare) && !CurSqare.CoveredByBlack) {
            MoveList.add(new Pair(this.Position, +1, 0));
            CurrBoard.CoverSquare(this.FigColor, this.Position.getX() +1, this.Position.getY());
        }
        CurSqare = CurrBoard.getSquare(this.Position.getX() - 1, this.Position.getY() - 1);
        if(this.Position.OnBoard(-1, -1) && CurrBoard.SquareCanMove(this.FigColor,CurSqare) && !CurSqare.CoveredByBlack) {
            MoveList.add(new Pair(this.Position, -1, -1));
            CurrBoard.CoverSquare(this.FigColor, this.Position.getX() -1, this.Position.getY()-1);
        }
        CurSqare = CurrBoard.getSquare(this.Position.getX(), this.Position.getY() -1);
        if(this.Position.OnBoard(0, -1) && CurrBoard.SquareCanMove(this.FigColor,CurSqare) && !CurSqare.CoveredByBlack) {
            MoveList.add(new Pair(this.Position, 0, -1));
            CurrBoard.CoverSquare(this.FigColor, this.Position.getX() , this.Position.getY()-1);
        }
        CurSqare = CurrBoard.getSquare(this.Position.getX() +1, this.Position.getY()-1);
        if(this.Position.OnBoard(1, -1) && CurrBoard.SquareCanMove(this.FigColor,CurSqare) && !CurSqare.CoveredByBlack){
            MoveList.add(new Pair(this.Position, 1, -1));
            CurrBoard.CoverSquare(this.FigColor, this.Position.getX() + 1, this.Position.getY() - 1);
        }
    }
    private void CheckCastlingBlack(Board CurrBoard){
        if(Castling && !CurrBoard.getSquare(this.Position.getX(), this.Position.getY()).CoveredByWhite){
            Board.Square CurSquare = CurrBoard.getSquare(0, 7);
            if(CurSquare.currFig.getType() == Type.ROOK && ((Rook)CurSquare.currFig).Castling ){
                CurSquare = CurrBoard.getSquare(1, 7);
                if(CurSquare.currFig == null) {
                    CurSquare = CurrBoard.getSquare(2, 7);
                    if(CurSquare.currFig == null) {
                        CurSquare = CurrBoard.getSquare(3, 7);
                        if(CurSquare.currFig == null && !CurSquare.CoveredByWhite)
                            MoveList.add(new Pair(0,7));
                    }
                }
            }
            CurSquare = CurrBoard.getSquare(7, 7);
            if(CurSquare.currFig.getType() == Type.ROOK && ((Rook)CurSquare.currFig).Castling ) {
                CurSquare = CurrBoard.getSquare(6, 7);
                if (CurSquare.currFig == null) {
                    CurSquare = CurrBoard.getSquare(5, 7);
                    if (CurSquare.currFig == null && !CurSquare.CoveredByWhite)
                        MoveList.add(new Pair(7, 7));
                }
            }
        }
    }
    private void CheckCastlingWhite(Board CurrBoard){
        if(Castling && !CurrBoard.getSquare(this.Position.getX(), this.Position.getY()).CoveredByBlack){
            Board.Square CurSquare = CurrBoard.getSquare(0, 0);
            if(CurSquare.currFig.getType() == Type.ROOK && ((Rook)CurSquare.currFig).Castling ){
                CurSquare = CurrBoard.getSquare(1, 0);
                if(CurSquare.currFig == null) {
                    CurSquare = CurrBoard.getSquare(2, 0);
                    if(CurSquare.currFig == null) {
                        CurSquare = CurrBoard.getSquare(3, 0);
                        if(CurSquare.currFig == null && !CurSquare.CoveredByBlack)
                            MoveList.add(new Pair(0,0));
                    }
                }
            }
            CurSquare = CurrBoard.getSquare(7, 0);
            if(CurSquare.currFig.getType() == Type.ROOK && ((Rook)CurSquare.currFig).Castling ) {
                CurSquare = CurrBoard.getSquare(6, 0);
                if (CurSquare.currFig == null) {
                    CurSquare = CurrBoard.getSquare(5, 0);
                    if (CurSquare.currFig == null && !CurSquare.CoveredByBlack)
                        MoveList.add(new Pair(7, 0));
                }
            }
        }
    }
}
