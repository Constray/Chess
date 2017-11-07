package Game;

import java.util.ArrayList;

class Square {
    Figure currFig;
    boolean CoveredByWhite;
    boolean CoveredByBlack;

    Square() {
        currFig = null;
        CoveredByBlack = false;
        CoveredByWhite = false;
    }
}

public class Board {
    private Square[][] BoardSquares = new Square[8][8];

    Square getSquare(int x, int y) {
        try {
            return BoardSquares[x][y];
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }

    boolean SquareCanMove(Color Cl, int x, int y) {
        return (BoardSquares[x][y].currFig == null) || !(BoardSquares[x][y].currFig.getFigColor() == Cl);
    }

    boolean SquareCanMove(Color Cl, Square q){
        return (q.currFig == null) || !(q.currFig.getFigColor() == Cl);
    }

    //Меняет состояние квадрата, если его покрывает черная или белая фигура
    void CoverSquare(Color Cl, int x, int y) {
        switch (Cl) {
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
    //Список возможных действий, если король находиться под шахом
    private ArrayList<Pair> SaveMoveList = new ArrayList<>();

    private Color Turn;

    private King blackKing;
    private King whiteKing;

    private void SwitchTurn() {
        switch (Turn) {
            case WHITE:
                Turn = Color.BLACK;
                break;
            case BLACK:
                Turn = Color.WHITE;
                break;
        }
    }

    //Конструктор инициализирует игру
    public Board() {
        Turn = Color.WHITE;
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                BoardSquares[i][j] = new Square();
        //Placing pawns
        for (int i = 0; i < 8; i++) {
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
        BoardSquares[1][0].currFig =  new Knight(Color.WHITE, WhiteFigList, new Pair(1,0));
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
        blackKing = (King)BoardSquares[3][7].currFig;
        whiteKing =  (King)BoardSquares[3][0].currFig;
        InitMoveList();
    }

    private void InitMoveList() {
        switch (Turn) {
            case BLACK:
                for (Figure F:WhiteFigList)
                    F.CreateMoveList(this);
                for (Figure F:BlackFigList) {
                    F.CreateMoveList(this);
                }
                break;
            case WHITE:
                for (Figure F:BlackFigList)
                    F.CreateMoveList(this);
                for (Figure F:WhiteFigList) {
                    F.CreateMoveList(this);
                }
                break;
        }
    }

    //Очищает покрытие клеток фигурами
    private void clearCover() {
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++) {
                BoardSquares[i][j].CoveredByBlack = false;
                BoardSquares[i][j].CoveredByWhite = false;
            }
    }

    /**
     * This method is used for moving a figures on a board.
     * @param from Position of a square which from we move our figure.
     * @param to Position of a square which to we move our figure.
     * @return Returns false if king can't be saved and side which just moved wins
     *         and true if game continues.
     * @throws IllegalArgumentException  Occurs if from or to pairs is not on a board or
     *                                  if chosen figure can't move to this square.
     */
    public boolean moveChessPiece(Pair from, Pair to) throws IllegalArgumentException {
        if (!(from.OnBoard() && to.OnBoard())) {
            throw new IllegalArgumentException("Error: from or to parameters is not on a board");
        }
        if (!BoardSquares[from.getX()][from.getY()].currFig.getMoveList().contains(to)) {
            throw new IllegalArgumentException("Error:This figure cant move to that position");
        }
        clearCover();
        SaveMoveList.clear();
        //Проверка на рокировку
        if (BoardSquares[from.getX()][from.getY()].currFig.getType() == Type.KING
                && BoardSquares[to.getX()][to.getY()].currFig.getType() == Type.ROOK
                && BoardSquares[from.getX()][from.getY()].currFig.FigColor == BoardSquares[to.getX()][to.getY()].currFig.FigColor) {
            King temp = (King)BoardSquares[from.getX()][from.getY()].currFig;
            temp.Castling = false;
            Rook temp2 = (Rook)BoardSquares[to.getX()][to.getY()].currFig;
            temp2.Castling = false;
            if (to.getX() > from.getX()) {
                Figure tempFig = BoardSquares[to.getX()][to.getY()].currFig;
                BoardSquares[to.getX()][to.getY()].currFig = null;
                BoardSquares[5][to.getY()].currFig = tempFig;
                BoardSquares[5][to.getY()].currFig.setPosition(new Pair(5,to.getY()));
                tempFig = BoardSquares[from.getX()][from.getY()].currFig;
                BoardSquares[6][to.getY()].currFig = tempFig;
                BoardSquares[6][to.getY()].currFig.setPosition(new Pair(6,to.getY()));
            } else if (to.getX() < from.getX()) {
                Figure tempFig = BoardSquares[to.getX()][to.getY()].currFig;
                BoardSquares[to.getX()][to.getY()].currFig = null;
                BoardSquares[3][to.getY()].currFig = tempFig;
                BoardSquares[3][to.getY()].currFig.setPosition(new Pair(3,to.getY()));
                tempFig = BoardSquares[from.getX()][from.getY()].currFig;
                BoardSquares[2][to.getY()].currFig = tempFig;
                BoardSquares[2][to.getY()].currFig.setPosition(new Pair(2,to.getY()));
            }
        }
        // При движение башни или короля они больше не могут участвовать в рокировке
        if (BoardSquares[from.getX()][from.getY()].currFig.getType() == Type.KING) {
            King temp = (King)BoardSquares[from.getX()][from.getY()].currFig;
            temp.Castling = false;
        }
        if (BoardSquares[from.getX()][from.getY()].currFig.getType() == Type.ROOK) {
            Rook temp = (Rook)BoardSquares[from.getX()][from.getY()].currFig;
            temp.Castling = false;
        }
        switch (Turn) {
            case BLACK:
                if (BoardSquares[to.getX()][to.getY()].currFig != null)
                    WhiteFigList.remove(BoardSquares[to.getX()][to.getY()].currFig);
                BoardSquares[to.getX()][to.getY()].currFig = BoardSquares[from.getX()][from.getY()].currFig;
                BoardSquares[to.getX()][to.getY()].currFig.setPosition(to);
                BoardSquares[from.getX()][from.getY()].currFig = null;
                if (BoardSquares[to.getX()][to.getY()].currFig.getType() == Type.PAWN && to.getY() == 0) {
                    int index = BlackFigList.indexOf(BoardSquares[to.getX()][to.getY()].currFig);
                    BlackFigList.remove(BoardSquares[to.getX()][to.getY()].currFig);
                    BlackFigList.add(index, new Queen(Color.BLACK, to));
                }
                break;
            case WHITE:
                if (BoardSquares[to.getX()][to.getY()].currFig != null)
                    BlackFigList.remove(BoardSquares[to.getX()][to.getY()].currFig);
                BoardSquares[to.getX()][to.getY()].currFig = BoardSquares[from.getX()][from.getY()].currFig;
                BoardSquares[to.getX()][to.getY()].currFig.setPosition(to);
                BoardSquares[from.getX()][from.getY()].currFig = null;
                if (BoardSquares[to.getX()][to.getY()].currFig.getType() == Type.PAWN && to.getY() == 7) {
                    int index = WhiteFigList.indexOf(BoardSquares[to.getX()][to.getY()].currFig);
                    WhiteFigList.remove(BoardSquares[to.getX()][to.getY()].currFig);
                    WhiteFigList.add(index, new Queen(Color.WHITE, to));
                }
        }
        SwitchTurn();
        InitMoveList();
        switch (Turn) {
            case BLACK:
                if (BoardSquares[blackKing.Position.getX()][blackKing.Position.getY()].CoveredByWhite) {
                    int numOfCheckingFigures = 0;
                    for (Figure f:WhiteFigList) {
                        if (f.MoveList.contains(blackKing.Position)) {
                            numOfCheckingFigures++;
                            if (numOfCheckingFigures > 1) {
                                return false;
                            }
                            SaveMoveList.addAll(f.SaveKingList(this, blackKing.Position));
                        }
                    }
                    for (Figure f:BlackFigList) {
                        if (f != blackKing) {
                            f.MoveList.retainAll(SaveMoveList);
                        }
                    }
                    SaveMoveList.addAll(blackKing.getMoveList());
                    if (SaveMoveList.isEmpty()) {
                        return false;
                    }
                }
                break;
            case WHITE:
                if (BoardSquares[whiteKing.Position.getX()][whiteKing.Position.getY()].CoveredByBlack) {
                    int numOfCheckingFigures = 0;
                    for (Figure f:BlackFigList) {
                        if (f.MoveList.contains(whiteKing.Position)) {
                            numOfCheckingFigures++;
                            if (numOfCheckingFigures > 1) {
                                return false;
                            }
                            SaveMoveList.addAll(f.SaveKingList(this, whiteKing.Position));
                        }
                    }
                    for (Figure f:WhiteFigList) {
                        if (f != whiteKing) {
                            f.MoveList.retainAll(SaveMoveList);
                        }
                    }
                    SaveMoveList.addAll(whiteKing.getMoveList());
                    if (SaveMoveList.isEmpty()) {
                        return false;
                    }
                }
        }
        return true;
    }

    /**
     * Method used for getting movable figures list.
     * @return List of figures which can move on a current turn.
     */
    public ArrayList<Pair> getMovableFiguresList() {
        ArrayList<Pair> result = new ArrayList<>();
        switch (Turn) {
            case BLACK:
                for (Figure f:BlackFigList) {
                    if (!f.MoveList.isEmpty()) {
                        result.add(f.Position);
                    }
                }
                break;
            case WHITE:
                for (Figure f:WhiteFigList) {
                    if (!f.MoveList.isEmpty()) {
                        result.add(f.Position);
                    }
                }
        }
        return result;
    }

    /**
     * Method used for getting move list of chosen figure.
     * @param from Position of chosen figure on a board.
     * @return Move list of chosen figure.
     * @throws IllegalArgumentException Occurs if chosen position is not on a board.
     * @throws NullPointerException Occurs if chosen square does not contain any figure.
     */
    public ArrayList<Pair> getFigureMoveList(Pair from) throws IllegalArgumentException, NullPointerException {
        if (!from.OnBoard()) {
            throw new IllegalArgumentException("Error: inputted pair is out of board");
        }
        if (null == BoardSquares[from.getX()][from.getY()].currFig) {
            throw new NullPointerException("Error: suggested square does not contain any figure");
        }
        return BoardSquares[from.getX()][from.getY()].currFig.getMoveList();
    }
}
