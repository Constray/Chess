package Game;

import java.util.ArrayList;

class Queen extends Figure {
    Queen(Color NewColor, ArrayList<Figure> FigList, Pair P){
        super(NewColor, FigList, P);
    }
    @Override
    Type getType(){
        return Type.QUEEN;
    }
    @Override
    void CreateMoveList(Board CurrBoard) {
        MoveList.clear();
        CoverSquares(CurrBoard);
        //Up-Right
        for (int i = this.Position.getX() + 1, j = this.Position.getY() + 1; Pair.OnBoard(i, j, false); i++, j++) {
            if (CurrBoard.getSquare(i, j).currFig == null)
                MoveList.add(new Pair(i, j));
            else if (CurrBoard.getSquare(i, j).currFig.FigColor != this.FigColor) {
                MoveList.add(new Pair(i, j));
                CurrBoard.CoverSquare(this.FigColor, i, j);
                break;
            } else
                break;
        }
        //Up-Left
        for (int i = this.Position.getX() - 1, j = this.Position.getY() + 1; Pair.OnBoard(i, j, false); i--, j++) {
            if (CurrBoard.getSquare(i, j).currFig == null)
                MoveList.add(new Pair(i, j));
            else if (CurrBoard.getSquare(i, j).currFig.FigColor != this.FigColor) {
                MoveList.add(new Pair(i, j));
                CurrBoard.CoverSquare(this.FigColor, i, j);
                break;
            } else
                break;
        }
        //Down-Right
        for (int i = this.Position.getX() + 1, j = this.Position.getY() - 1; Pair.OnBoard(i, j, false); i++, j--) {
            if (CurrBoard.getSquare(i, j).currFig == null)
                MoveList.add(new Pair(i, j));
            else if (CurrBoard.getSquare(i, j).currFig.FigColor != this.FigColor) {
                MoveList.add(new Pair(i, j));
                CurrBoard.CoverSquare(this.FigColor, i, j);
                break;
            } else
                break;
        }
        //Down-Left
        for (int i = this.Position.getX() - 1, j = this.Position.getY() - 1; Pair.OnBoard(i, j, false); i--, j--) {
            if (CurrBoard.getSquare(i, j).currFig == null)
                MoveList.add(new Pair(i, j));
            else if (CurrBoard.getSquare(i, j).currFig.FigColor != this.FigColor) {
                MoveList.add(new Pair(i, j));
                CurrBoard.CoverSquare(this.FigColor, i, j);
                break;
            } else
                break;
        }
        //Right
        for(int i = this.Position.getX() + 1; i < 8 ; i++){
            if(CurrBoard.getSquare(i, this.Position.getY()).currFig == null)
                MoveList.add(new Pair(i, this.Position.getY()));
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
            if(CurrBoard.getSquare(i, this.Position.getY()).currFig == null)
                MoveList.add(new Pair(i, this.Position.getY()));
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
            if(CurrBoard.getSquare(this.Position.getX(), i).currFig == null)
                MoveList.add(new Pair(this.Position.getX(), i));
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
            if(CurrBoard.getSquare(this.Position.getX(), i).currFig == null)
                MoveList.add(new Pair(this.Position.getX(), i));
            else if(CurrBoard.getSquare(this.Position.getX(), i).currFig.FigColor != this.FigColor){
                MoveList.add(new Pair(this.Position.getX(), i));
                CurrBoard.CoverSquare(this.FigColor, this.Position.getX(), i);
                break;
            }
            else
                break;
        }
    }

    @Override
    ArrayList<Pair> SaveKingList(Board CurrBoard, Pair KingPos) {
        ArrayList<Pair> A = new ArrayList<>();
        A.add(new Pair(this.Position.getX(), this.Position.getY()));
        //Если король сверху-справа
        if (KingPos.getX() > this.Position.getX() && KingPos.getY() > this.Position.getY())
            for (int i = this.Position.getX() + 1, j = this.Position.getY() + 1; i < KingPos.getX() && j < KingPos.getY(); i++, j++)
                A.add(new Pair(i,j));
        //Есди король сверху-слева
        else if (KingPos.getX() < this.Position.getX() && KingPos.getY() > this.Position.getY())
            for (int i = this.Position.getX() - 1, j = this.Position.getY() + 1; i > KingPos.getX() && j < KingPos.getY(); i--, j++)
                A.add(new Pair(i,j));
        //Если король снизу-справа
        else if (KingPos.getX() > this.Position.getX() && KingPos.getY() < this.Position.getY())
            for (int i = this.Position.getX() + 1, j = this.Position.getY() - 1; i < KingPos.getX() && j > KingPos.getY(); i++, j--)
                A.add(new Pair(i,j));
        //Если король снизу-слева
        else if (KingPos.getX() < this.Position.getX() && KingPos.getY() < this.Position.getY())
            for (int i = this.Position.getX() - 1, j = this.Position.getY() - 1; i > KingPos.getX() && j > KingPos.getY(); i--, j--)
                A.add(new Pair(i,j));
        //Если король справа
        else if (KingPos.getX() > this.Position.getX())
            for (int i = this.Position.getX() + 1; i < KingPos.getX(); i++)
                A.add(new Pair(i,this.Position.getY()));
        //Если король слева
        else if (KingPos.getX() < this.Position.getX())
            for (int i = KingPos.getX() - 1; i > this.Position.getX(); i--)
                A.add(new Pair(i,this.Position.getY()));
        //Если король сверху
        else if (KingPos.getY() > this.Position.getY())
            for (int i = this.Position.getY() + 1; i < KingPos.getY(); i++)
                A.add(new Pair(this.Position.getX(),i));
        //Если король снизу
        else if (KingPos.getY() < this.Position.getY())
            for (int i = KingPos.getY() - 1; i > Position.getY(); i++)
                A.add(new Pair(this.Position.getX(),i));
        return A;
    }

    @Override
    void CoverSquares(Board CurrBoard) {
        //Up-Right
        for (int i = this.Position.getX() + 1, j = this.Position.getY() + 1; Pair.OnBoard(i, j, false); i++, j++) {
            if (CurrBoard.getSquare(i, j).currFig == null)
                CurrBoard.CoverSquare(this.FigColor, i, j);
            else if (CurrBoard.getSquare(i, j).currFig.FigColor != this.FigColor) {
                CurrBoard.CoverSquare(this.FigColor, i, j);
                if(CurrBoard.getSquare(i, j).currFig.getType() != Type.KING)
                    break;
            } else
                break;
        }
        //Up-Left
        for (int i = this.Position.getX() - 1, j = this.Position.getY() + 1; Pair.OnBoard(i, j, false); i--, j++) {
            if (CurrBoard.getSquare(i, j).currFig == null)
                CurrBoard.CoverSquare(this.FigColor, i, j);
            else if (CurrBoard.getSquare(i, j).currFig.FigColor != this.FigColor) {
                CurrBoard.CoverSquare(this.FigColor, i, j);
                if(CurrBoard.getSquare(i, j).currFig.getType() != Type.KING)
                    break;
            } else
                break;
        }
        //Down-Right
        for (int i = this.Position.getX() + 1, j = this.Position.getY() - 1; Pair.OnBoard(i, j, false); i++, j--) {
            if (CurrBoard.getSquare(i, j).currFig == null)
                CurrBoard.CoverSquare(this.FigColor, i, j);
            else if (CurrBoard.getSquare(i, j).currFig.FigColor != this.FigColor) {
                CurrBoard.CoverSquare(this.FigColor, i, j);
                if(CurrBoard.getSquare(i, j).currFig.getType() != Type.KING)
                    break;
            } else
                break;
        }
        //Down-Left
        for (int i = this.Position.getX() - 1, j = this.Position.getY() - 1; Pair.OnBoard(i, j, false); i--, j--) {
            if (CurrBoard.getSquare(i, j).currFig == null)
                CurrBoard.CoverSquare(this.FigColor, i, j);
            else if (CurrBoard.getSquare(i, j).currFig.FigColor != this.FigColor) {
                CurrBoard.CoverSquare(this.FigColor, i, j);
                if (CurrBoard.getSquare(i, j).currFig.getType() != Type.KING)
                    break;
            } else
                break;
        }
        //Right
        for(int i = this.Position.getX() + 1; i < 8 ; i++){
            if(CurrBoard.getSquare(i, this.Position.getY()).currFig == null)
                CurrBoard.CoverSquare(this.FigColor, i, this.Position.getY());
            else if(CurrBoard.getSquare(i, this.Position.getY()).currFig.FigColor != this.FigColor){
                CurrBoard.CoverSquare(this.FigColor, i, this.Position.getY());
                if(CurrBoard.getSquare(i, this.Position.getY()).currFig.getType() != Type.KING)
                    break;
            }
            else
                break;
        }
        //Left
        for(int i = this.Position.getX() - 1; i >= 0 ; i--){
            if(CurrBoard.getSquare(i, this.Position.getY()).currFig == null)
                CurrBoard.CoverSquare(this.FigColor, i, this.Position.getY());
            else if(CurrBoard.getSquare(i, this.Position.getY()).currFig.FigColor != this.FigColor){
                CurrBoard.CoverSquare(this.FigColor, i, this.Position.getY());
                if(CurrBoard.getSquare(i, this.Position.getY()).currFig.getType() != Type.KING)
                    break;
            }
            else
                break;
        }
        //Up
        for(int i = this.Position.getY() + 1; i < 8 ; i++){
            if(CurrBoard.getSquare(this.Position.getX(), i).currFig == null)
                CurrBoard.CoverSquare(this.FigColor, this.Position.getX(), i);
            else if(CurrBoard.getSquare(this.Position.getX(), i).currFig.FigColor != this.FigColor){
                CurrBoard.CoverSquare(this.FigColor, this.Position.getX(), i);
                if(CurrBoard.getSquare(this.Position.getX(), i).currFig.getType() != Type.KING)
                    break;
            }
            else
                break;
        }
        //Down
        for(int i = this.Position.getY() - 1; i >= 0; i--){
            if(CurrBoard.getSquare(this.Position.getX(), i).currFig == null)
                CurrBoard.CoverSquare(this.FigColor, this.Position.getX(), i);
            else if(CurrBoard.getSquare(this.Position.getX(), i).currFig.FigColor != this.FigColor){
                CurrBoard.CoverSquare(this.FigColor, this.Position.getX(), i);
                if(CurrBoard.getSquare(this.Position.getX(), i).currFig.getType() != Type.KING)
                    break;
            }
            else
                break;
        }
    }
}
