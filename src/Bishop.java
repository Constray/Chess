import java.util.ArrayList;

public class Bishop extends Figure{
    public Bishop(Color NewColor, ArrayList<Figure> FigList, Pair P){
        super(NewColor, FigList, P);
    }
    @Override
    public Type getType(){
        return Type.BISHOP;
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
    }
}
