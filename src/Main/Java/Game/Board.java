package game;

import java.util.ArrayList;

class Square {
    Figure currFig;
    boolean coveredByWhite;
    boolean coveredByBlack;

    Square() {
        currFig = null;
        coveredByBlack = false;
        coveredByWhite = false;
    }
}

public class Board {
    private Square[][] boardSquares = new Square[8][8];

    Square getSquare(int x, int y) {
        try {
            return boardSquares[x][y];
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }

    boolean squareCanMove(FigColor Cl, int x, int y) {
        return (boardSquares[x][y].currFig == null) || !(boardSquares[x][y].currFig.getFigColor() == Cl);
    }

    boolean squareCanMove(FigColor Cl, Square q){
        return (q.currFig == null) || !(q.currFig.getFigColor() == Cl);
    }

    //Меняет состояние квадрата, если его покрывает черная или белая фигура
    void coverSquare(FigColor Cl, int x, int y) {
        if (!Pair.OnBoard(x, y, false)) {
            return;
        }
        switch (Cl) {
            case WHITE:
                if (!boardSquares[x][y].coveredByWhite)
                    boardSquares[x][y].coveredByWhite = true;
                break;
            case BLACK:
                if (!boardSquares[x][y].coveredByBlack)
                    boardSquares[x][y].coveredByBlack = true;
                break;
        }
    }

    private ArrayList<Figure> whiteFigList = new ArrayList<>();
    private ArrayList<Figure> blackFigList = new ArrayList<>();
    //Список возможных действий, если король находиться под шахом
    private ArrayList<Pair> saveMoveList = new ArrayList<>();

    private ArrayList<Pair> changedSquareList = new ArrayList<>();

    public FigColor getTurn() {
        return turn;
    }

    private FigColor turn;

    private King blackKing;
    private King whiteKing;

    public King getKing(FigColor cl) {
        switch(cl) {
            case BLACK:
                return blackKing;
            case WHITE:
                return whiteKing;
        }
        return null;
    }

    private void switchTurn() {
        switch (turn) {
            case WHITE:
                turn = FigColor.BLACK;
                break;
            case BLACK:
                turn = FigColor.WHITE;
                break;
        }
    }

    //Конструктор инициализирует игру
    public Board() {
        turn = FigColor.WHITE;
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                boardSquares[i][j] = new Square();
        //Placing pawns
        for (int i = 0; i < 8; i++) {
            boardSquares[i][1].currFig = new Pawn(FigColor.WHITE, whiteFigList, new Pair(i,1));
            boardSquares[i][6].currFig = new Pawn(FigColor.BLACK, blackFigList, new Pair(i,6));
        }
        //Placing rooks
        boardSquares[7][7].currFig = new Rook(FigColor.BLACK, true, blackFigList, new Pair(7,7));
        boardSquares[0][7].currFig = new Rook(FigColor.BLACK, true, blackFigList, new Pair(0,7));
        boardSquares[7][0].currFig = new Rook(FigColor.WHITE, true, whiteFigList, new Pair(7,0));
        boardSquares[0][0].currFig = new Rook(FigColor.WHITE, true, whiteFigList, new Pair(0,0));
        //Placing knights
        boardSquares[1][7].currFig =  new Knight(FigColor.BLACK, blackFigList, new Pair(1,7));
        boardSquares[6][7].currFig =  new Knight(FigColor.BLACK, blackFigList, new Pair(6,7));
        boardSquares[1][0].currFig =  new Knight(FigColor.WHITE, whiteFigList, new Pair(1,0));
        boardSquares[6][0].currFig =  new Knight(FigColor.WHITE, whiteFigList, new Pair(6,0));
        //Placing bishops
        boardSquares[2][7].currFig = new Bishop(FigColor.BLACK, blackFigList, new Pair(2,7));
        boardSquares[5][7].currFig = new Bishop(FigColor.BLACK, blackFigList, new Pair(5,7));
        boardSquares[2][0].currFig = new Bishop(FigColor.WHITE, whiteFigList, new Pair(2,0));
        boardSquares[5][0].currFig = new Bishop(FigColor.WHITE, whiteFigList, new Pair(5,0));
        //Placing queens
        boardSquares[3][7].currFig = new Queen(FigColor.BLACK, blackFigList, new Pair(3,7));
        boardSquares[3][0].currFig = new Queen(FigColor.WHITE, whiteFigList, new Pair(3,0));
        //Placing kings
        boardSquares[4][7].currFig = new King(FigColor.BLACK, blackFigList, new Pair(4,7));
        boardSquares[4][0].currFig = new King(FigColor.WHITE, whiteFigList, new Pair(4,0));
        blackKing = (King) boardSquares[4][7].currFig;
        whiteKing =  (King) boardSquares[4][0].currFig;
        initMoveList();
    }

    private void initMoveList() {
        switch (turn) {
            case BLACK:
                for (Figure F: whiteFigList)
                    F.createMoveList(this);
                for (Figure F: blackFigList) {
                    F.createMoveList(this);
                }
                break;
            case WHITE:
                for (Figure F: blackFigList)
                    F.createMoveList(this);
                for (Figure F: whiteFigList) {
                    F.createMoveList(this);
                }
                break;
        }
    }

    //Очищает покрытие клеток фигурами
    private void clearCover() {
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++) {
                boardSquares[i][j].coveredByBlack = false;
                boardSquares[i][j].coveredByWhite = false;
            }
    }

    /**
     * This method is used for moving a figures on a board.
     * @param from position of a square which from we move our figure.
     * @param to position of a square which to we move our figure.
     * @return Returns false if king can't be saved and side which just moved wins
     *         and true if game continues.
     * @throws IllegalArgumentException  Occurs if from or to pairs is not on a board or
     *                                  if chosen figure can't move to this square.
     */
    public boolean moveChessPiece(Pair from, Pair to) throws IllegalArgumentException {
        if (!(from.OnBoard() && to.OnBoard())) {
            throw new IllegalArgumentException("Error:from or to parameters is not on a board");
        }
        if (!boardSquares[from.getX()][from.getY()].currFig.getMoveList().contains(to)) {
            throw new IllegalArgumentException("Error:This figure cant move to that position");
        }
        clearCover();
        saveMoveList.clear();
        changedSquareList.clear();
        changedSquareList.add(from);
        changedSquareList.add(to);
        boolean castlingMove = false;
        //Проверка на рокировку
        if (boardSquares[from.getX()][from.getY()].currFig.getType() == Type.KING
                && (null !=  boardSquares[to.getX()][to.getY()].currFig)
                && boardSquares[to.getX()][to.getY()].currFig.getType() == Type.ROOK
                && boardSquares[from.getX()][from.getY()].currFig.figColor == boardSquares[to.getX()][to.getY()].currFig.figColor) {
            King temp = (King) boardSquares[from.getX()][from.getY()].currFig;
            temp.Castling = false;
            Rook temp2 = (Rook) boardSquares[to.getX()][to.getY()].currFig;
            temp2.Castling = false;
            if (to.getX() > from.getX()) {
                Figure tempFig = boardSquares[to.getX()][to.getY()].currFig;
                boardSquares[to.getX()][to.getY()].currFig = null;
                boardSquares[5][to.getY()].currFig = tempFig;
                boardSquares[5][to.getY()].currFig.setPosition(new Pair(5,to.getY()));
                changedSquareList.add(boardSquares[5][to.getY()].currFig.position);
                tempFig = boardSquares[from.getX()][from.getY()].currFig;
                boardSquares[from.getX()][from.getY()].currFig = null;
                boardSquares[6][to.getY()].currFig = tempFig;
                boardSquares[6][to.getY()].currFig.setPosition(new Pair(6,to.getY()));
                changedSquareList.add(boardSquares[6][to.getY()].currFig.position);
            } else if (to.getX() < from.getX()) {
                Figure tempFig = boardSquares[to.getX()][to.getY()].currFig;
                boardSquares[to.getX()][to.getY()].currFig = null;
                boardSquares[3][to.getY()].currFig = tempFig;
                boardSquares[3][to.getY()].currFig.setPosition(new Pair(3,to.getY()));
                changedSquareList.add(boardSquares[3][to.getY()].currFig.position);
                tempFig = boardSquares[from.getX()][from.getY()].currFig;
                boardSquares[from.getX()][from.getY()].currFig = null;
                boardSquares[2][to.getY()].currFig = tempFig;
                boardSquares[2][to.getY()].currFig.setPosition(new Pair(2,to.getY()));
                changedSquareList.add(boardSquares[2][to.getY()].currFig.position);
            }
            castlingMove = true;
        }
        // При движение башни или короля они больше не могут участвовать в рокировке
        if (!castlingMove && boardSquares[from.getX()][from.getY()].currFig.getType() == Type.KING) {
            King temp = (King) boardSquares[from.getX()][from.getY()].currFig;
            temp.Castling = false;
        }
        if (!castlingMove && boardSquares[from.getX()][from.getY()].currFig.getType() == Type.ROOK) {
            Rook temp = (Rook) boardSquares[from.getX()][from.getY()].currFig;
            temp.Castling = false;
        }
        if (!castlingMove && boardSquares[from.getX()][from.getY()].currFig.getType() == Type.PAWN) {
            Pawn temp = (Pawn)boardSquares[from.getX()][from.getY()].currFig;
            if (temp.FirsStep) {
                temp.FirsStep = false;
            }
        }
        if (!castlingMove) {
            switch (turn) {
                case BLACK:
                    if (boardSquares[to.getX()][to.getY()].currFig != null)
                        whiteFigList.remove(boardSquares[to.getX()][to.getY()].currFig);
                    boardSquares[to.getX()][to.getY()].currFig = boardSquares[from.getX()][from.getY()].currFig;
                    boardSquares[to.getX()][to.getY()].currFig.setPosition(to);
                    boardSquares[from.getX()][from.getY()].currFig = null;
                    if (boardSquares[to.getX()][to.getY()].currFig.getType() == Type.PAWN && to.getY() == 0) {
                        int index = blackFigList.indexOf(boardSquares[to.getX()][to.getY()].currFig);
                        boardSquares[to.getX()][to.getY()].currFig = new Queen(FigColor.BLACK, new Pair(to.getX(), to.getY()));
                        blackFigList.set(index, boardSquares[to.getX()][to.getY()].currFig);
                    }
                    break;
                case WHITE:
                    if (boardSquares[to.getX()][to.getY()].currFig != null)
                        blackFigList.remove(boardSquares[to.getX()][to.getY()].currFig);
                    boardSquares[to.getX()][to.getY()].currFig = boardSquares[from.getX()][from.getY()].currFig;
                    boardSquares[to.getX()][to.getY()].currFig.setPosition(to);
                    boardSquares[from.getX()][from.getY()].currFig = null;
                    if (boardSquares[to.getX()][to.getY()].currFig.getType() == Type.PAWN && to.getY() == 7) {
                        int index = whiteFigList.indexOf(boardSquares[to.getX()][to.getY()].currFig);
                        boardSquares[to.getX()][to.getY()].currFig = new Queen(FigColor.WHITE,  new Pair(to.getX(), to.getY()));
                        whiteFigList.set(index, boardSquares[to.getX()][to.getY()].currFig);
                    }
            }
        }
        switchTurn();
        initMoveList();
        switch (turn) {
            case BLACK:
                if (boardSquares[blackKing.position.getX()][blackKing.position.getY()].coveredByWhite) {
                    int numOfCheckingFigures = 0;
                    for (Figure f: whiteFigList) {
                        if (f.moveList.contains(blackKing.position)) {
                            numOfCheckingFigures++;
                            if (numOfCheckingFigures > 1) {
                                return false;
                            }
                            saveMoveList.addAll(f.saveKingList(this, blackKing.position));
                        }
                    }
                    for (Figure f: blackFigList) {
                        if (f != blackKing) {
                            f.moveList.retainAll(saveMoveList);
                        }
                    }
                    saveMoveList.addAll(blackKing.getMoveList());
                    if (saveMoveList.isEmpty()) {
                        return false;
                    }
                }
                break;
            case WHITE:
                if (boardSquares[whiteKing.position.getX()][whiteKing.position.getY()].coveredByBlack) {
                    int numOfCheckingFigures = 0;
                    for (Figure f: blackFigList) {
                        if (f.moveList.contains(whiteKing.position)) {
                            numOfCheckingFigures++;
                            if (numOfCheckingFigures > 1) {
                                return false;
                            }
                            saveMoveList.addAll(f.saveKingList(this, whiteKing.position));
                        }
                    }
                    for (Figure f: whiteFigList) {
                        if (f != whiteKing) {
                            f.moveList.retainAll(saveMoveList);
                        }
                    }
                    saveMoveList.addAll(whiteKing.getMoveList());
                    if (saveMoveList.isEmpty()) {
                        return false;
                    }
                }
        }
        return true;
    }

    /**
     * Returns current turning side king position if he under attack.
     * @return King position if he attacked or null if not.
     */
    public Pair getKingCoordinatesIfHeUnderAttack() {
        switch (turn) {
            case WHITE:
                if (boardSquares[whiteKing.position.getX()][whiteKing.position.getY()].coveredByBlack) {
                    return whiteKing.position;
                }
                break;
            case BLACK:
                if (boardSquares[blackKing.position.getX()][blackKing.position.getY()].coveredByWhite) {
                    return blackKing.position;
                }
                break;
        }
        return null;
    }

    /**
     * Method used for getting movable figures list.
     * @return List of figures which can move on a current turn.
     */
    public ArrayList<Pair> getMovableFiguresList() {
        ArrayList<Pair> result = new ArrayList<>();
        switch (turn) {
            case BLACK:
                for (Figure f: blackFigList) {
                    if (!f.moveList.isEmpty()) {
                        result.add(f.position);
                    }
                }
                break;
            case WHITE:
                for (Figure f: whiteFigList) {
                    if (!f.moveList.isEmpty()) {
                        result.add(f.position);
                    }
                }
        }
        return result;
    }


    public ArrayList<Pair> getChangedSquareList() {
        return changedSquareList;
    }
}
