import java.util.ArrayList;

public class Queen extends Figure {
    public Queen(Color NewColor, ArrayList<Figure> FigList, Pair P){
        super(NewColor, FigList, P);
    }
    @Override
    public Type getType(){
        return Type.QUEEN;
    }
    @Override
    public void CreateMoveList(Board CurrBoard) {
        MoveList.clear();
        //Up-Right
        for (int i = this.Position.getX() + 1, j = this.Position.getY() + 1; Pair.OnBoard(i, j, false); i++, j++) {
            if (CurrBoard.getSquare(i, j).currFig == null) {
                MoveList.add(new Pair(i, j));
                CurrBoard.CoverSquare(this.FigColor, i, j);
            }
            else if (CurrBoard.getSquare(i, j).currFig.FigColor != this.FigColor) {
                MoveList.add(new Pair(i, j));
                CurrBoard.CoverSquare(this.FigColor, i, j);
                break;
            } else
                break;
        }
        //Up-Left
        for (int i = this.Position.getX() - 1, j = this.Position.getY() + 1; Pair.OnBoard(i, j, false); i--, j++) {
            if (CurrBoard.getSquare(i, j).currFig == null) {
                MoveList.add(new Pair(i, j));
                CurrBoard.CoverSquare(this.FigColor, i, j);
            }
            else if (CurrBoard.getSquare(i, j).currFig.FigColor != this.FigColor) {
                MoveList.add(new Pair(i, j));
                CurrBoard.CoverSquare(this.FigColor, i, j);
                break;
            } else
                break;
        }
        //Down-Right
        for (int i = this.Position.getX() + 1, j = this.Position.getY() - 1; Pair.OnBoard(i, j, false); i++, j--) {
            if (CurrBoard.getSquare(i, j).currFig == null) {
                MoveList.add(new Pair(i, j));
                CurrBoard.CoverSquare(this.FigColor, i, j);
            }
            else if (CurrBoard.getSquare(i, j).currFig.FigColor != this.FigColor) {
                MoveList.add(new Pair(i, j));
                CurrBoard.CoverSquare(this.FigColor, i, j);
                break;
            } else
                break;
        }
        //Down-Left
        for (int i = this.Position.getX() - 1, j = this.Position.getY() - 1; Pair.OnBoard(i, j, false); i--, j--) {
            if (CurrBoard.getSquare(i, j).currFig == null) {
                MoveList.add(new Pair(i, j));
                CurrBoard.CoverSquare(this.FigColor, i, j);
            }
            else if (CurrBoard.getSquare(i, j).currFig.FigColor != this.FigColor) {
                MoveList.add(new Pair(i, j));
                break;
            } else
                break;
        }
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
