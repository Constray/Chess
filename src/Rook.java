import java.util.ArrayList;

public class Rook extends Figure {
    public  boolean Castling;

    public Rook(Color NewColor, boolean cast, ArrayList<Figure> FigList, Pair P){
        super(NewColor, FigList, P);
        Castling = cast;
    }
    @Override
    public Type getType(){
        return Type.ROOK;
    }
    @Override
    public void CreateMoveList(Board CurrBoard){
        MoveList.clear();
        //Right
        for(int i = this.Position.getX() + 1; i < 8 ; i++){
            if(CurrBoard.getSquare(i, this.Position.getY()).currFig == null) {
                MoveList.add(new Pair(i, this.Position.getY()));
                CurrBoard.CoverSquare(this.FigColor, i, this.Position.getY());
            }
            else if(CurrBoard.getSquare(i, this.Position.getY()).currFig.FigColor != this.FigColor){
                MoveList.add(new Pair(i, this.Position.getY()));
                CurrBoard.CoverSquare(this.FigColor, i, this.Position.getY());
                break;
            }
            else
                break;
        }
        //Left
        for(int i = this.Position.getX() - 1; i >= 0 ; i--){
            if(CurrBoard.getSquare(i, this.Position.getY()).currFig == null) {
                MoveList.add(new Pair(i, this.Position.getY()));
                CurrBoard.CoverSquare(this.FigColor, i, this.Position.getY());
            }
            else if(CurrBoard.getSquare(i, this.Position.getY()).currFig.FigColor != this.FigColor){
                MoveList.add(new Pair(i, this.Position.getY()));
                CurrBoard.CoverSquare(this.FigColor, i, this.Position.getY());
                break;
            }
            else
                break;
        }
        //Up
        for(int i = this.Position.getY() + 1; i < 8 ; i++){
            if(CurrBoard.getSquare(this.Position.getX(), i).currFig == null) {
                MoveList.add(new Pair(this.Position.getX(), i));
                CurrBoard.CoverSquare(this.FigColor, this.Position.getX(), i);
            }
            else if(CurrBoard.getSquare(this.Position.getX(), i).currFig.FigColor != this.FigColor){
                MoveList.add(new Pair(this.Position.getX(), i));
                CurrBoard.CoverSquare(this.FigColor, this.Position.getX(), i);
                break;
            }
            else
                break;
        }
        //Down
        for(int i = this.Position.getY() - 1; i >= 0; i--){
            if(CurrBoard.getSquare(this.Position.getX(), i).currFig == null) {
                MoveList.add(new Pair(this.Position.getX(), i));
                CurrBoard.CoverSquare(this.FigColor, this.Position.getX(), i);
            }
            else if(CurrBoard.getSquare(this.Position.getX(), i).currFig.FigColor != this.FigColor){
                MoveList.add(new Pair(this.Position.getX(), i));
                CurrBoard.CoverSquare(this.FigColor, this.Position.getX(), i);
                break;
            }
            else
                break;
        }
    }
}
