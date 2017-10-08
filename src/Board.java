import java.util.ArrayList;

enum init_type{
    EMPTY, CLASSIC;
}

public class Board {
    public class Square {
        public Figure currFig;
        public boolean CoveredByWhite;
        public boolean CoveredByBlack;

        public  Square(){
            currFig = null;
            CoveredByBlack = false;
            CoveredByWhite = false;
        }
    }

    private Square[][] BoardSquares = new Square[8][8];

    public Square getSquare(int x, int y){
        return BoardSquares[x][y];
    }

    public  boolean SquareCanMove(Color Cl, int x, int y){
        return (BoardSquares[x][y].currFig == null) || !(BoardSquares[x][y].currFig.getFigColor() == Cl);
    }

    public  boolean SquareCanMove(Color Cl, Square q){
        return (q.currFig == null) || !(q.currFig.getFigColor() == Cl);
    }

    public void CoverSquare(Color Cl, int x, int y){
        switch (Cl){
            case WHITE:
                if(!BoardSquares[x][y].CoveredByWhite)
                    BoardSquares[x][y].CoveredByWhite = true;
                break;
            case BLACK:
                if(!BoardSquares[x][y].CoveredByBlack)
                    BoardSquares[x][y].CoveredByBlack = true;
                break;
        }
    }

    private ArrayList<Figure> WhiteFigList = new ArrayList<>();
    private ArrayList<Figure> BlackFigList = new ArrayList<>();

    private Color Turn;

    private void SwitchTurn(){
        switch (Turn){
            case WHITE:
                Turn = Color.BLACK;
                break;
            case BLACK:
                Turn = Color.WHITE;
                break;
        }
    };

    public Color getTurn(){
        return  Turn;
    }

    public Board(init_type Init){
        Turn = Color.WHITE;
        switch (Init){
            case EMPTY:
                for(Square[] sq:BoardSquares)
                    for (Square Sq:sq)
                        Sq = new Square();
                break;
            case CLASSIC:
                //Placing pawns
                for (int i = 0; i < 8; i++){
                    BoardSquares[i][1].currFig = new Pawn(Color.WHITE, WhiteFigList, new Pair(i,1));
                    BoardSquares[i][6].currFig = new Pawn(Color.BLACK, BlackFigList, new Pair(i,6));
                }
                //Placing rooks
                BoardSquares[7][7].currFig = new Rook(Color.BLACK, true, BlackFigList, new Pair(7,7));
                BoardSquares[0][7].currFig = new Rook(Color.BLACK, true, BlackFigList, new Pair(7,7));
                BoardSquares[7][0].currFig = new Rook(Color.WHITE, true, WhiteFigList, new Pair(7,0));
                BoardSquares[0][0].currFig = new Rook(Color.WHITE, true, WhiteFigList, new Pair(0,0));
                //Placing knights
                BoardSquares[1][7].currFig =  new Knight(Color.BLACK, BlackFigList, new Pair(1,7));
                BoardSquares[6][7].currFig =  new Knight(Color.BLACK, BlackFigList, new Pair(6,7));
                BoardSquares[1][0].currFig =  new Knight(Color.BLACK, WhiteFigList, new Pair(1,0));
                BoardSquares[6][0].currFig =  new Knight(Color.WHITE, WhiteFigList, new Pair(6,0));
                //Placing bishops
                BoardSquares[2][7].currFig = new Bishop(Color.BLACK, BlackFigList, new Pair(2,7));
                BoardSquares[5][7].currFig = new Bishop(Color.BLACK, BlackFigList, new Pair(5,7));
                BoardSquares[2][0].currFig = new Bishop(Color.WHITE, WhiteFigList, new Pair(2,0));
                BoardSquares[5][0].currFig = new Bishop(Color.WHITE, WhiteFigList, new Pair(5,0));
                //Placing queens
                BoardSquares[4][7].currFig = new Queen(Color.BLACK, BlackFigList, new Pair(4,7));
                BoardSquares[4][0].currFig = new Queen(Color.WHITE, WhiteFigList, new Pair(4,0));
                //Placing kings
                BoardSquares[3][7].currFig = new King(Color.BLACK, BlackFigList, new Pair(3,7));
                BoardSquares[3][0].currFig = new King(Color.WHITE, WhiteFigList, new Pair(3,0));
                InitMoveList();
                break;
        }
    }

    private void InitMoveList(){
        switch (Turn){
            case BLACK:
                for(Figure F:WhiteFigList)
                    F.CreateMoveList(this);
                for (Figure F:BlackFigList) {
                    F.CreateMoveList(this);
                }
                break;
            case WHITE:
                for(Figure F:BlackFigList)
                    F.CreateMoveList(this);
                for (Figure F:WhiteFigList) {
                    F.CreateMoveList(this);
                }
                break;
        }
    }
}
