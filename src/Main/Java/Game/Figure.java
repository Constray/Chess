package game;

import  java.util.ArrayList;

import static java.lang.Math.abs;

public abstract class Figure {
    protected FigColor figColor;

    protected ArrayList<Pair> moveList;

    protected Pair position;

    Figure(FigColor newColor, Pair p) {
        figColor = newColor;
        moveList = new ArrayList<>();
        position = p;
    }

    Figure(FigColor newColor, ArrayList<Figure> figList, Pair pos) {
        figColor = newColor;
        moveList = new ArrayList<>();
        position = pos;
        figList.add(this);
    }

    FigColor getFigColor(){
        return figColor;
    }

    public void setPosition(Pair position) {
        this.position.setPair(position);
    }

    //Cоздаёт список клеток, которые может занять фигура противоположенного цвета, чтобы спасти своего короля
    abstract ArrayList<Pair> saveKingList(Board currBoard, Pair kingPos);

    abstract Type getType();

    abstract void createMoveList(Board currBoard);

    abstract void coverSquares(Board currBoard);

    public ArrayList<Pair> getMoveList(){
        return moveList;
    }

    boolean canMoveHorizontally(Board curBoard) {
        King curKing = curBoard.getKing(this.figColor);
        if (this.position.getX() != curKing.position.getX()) {
            return true;
        }
        //Если фигура над королём
        if (this.position.getY() > curKing.position.getY()) {
            for (int i = this.position.getY() + 1; i < 8; i++) {
                if (null != curBoard.getSquare(this.position.getX(),i).currFig) {
                    if (curBoard.getSquare(this.position.getX(),i).currFig.figColor == this.figColor) {
                        return true;
                    } else if (curBoard.getSquare(this.position.getX(),i).currFig.getType() != Type.QUEEN
                            && curBoard.getSquare(this.position.getX(),i).currFig.getType() != Type.ROOK) {
                        return true;
                    } else {
                        for (int j = this.position.getY() - 1; j > 0; j--) {
                            if (null != curBoard.getSquare(this.position.getX(),j).currFig) {
                                return !(curBoard.getSquare(this.position.getX(),j).currFig == curKing);
                            }
                        }
                    }
                }
            }
        } else if (this.position.getY() < curKing.position.getY()) {
            for (int i = this.position.getY() - 1; i >= 0; i--) {
                if (null != curBoard.getSquare(this.position.getX(),i).currFig) {
                    if (curBoard.getSquare(this.position.getX(),i).currFig.figColor == this.figColor) {
                        return true;
                    } else if (curBoard.getSquare(this.position.getX(),i).currFig.getType() != Type.QUEEN
                            && curBoard.getSquare(this.position.getX(),i).currFig.getType() != Type.ROOK) {
                        return true;
                    } else {
                        for (int j = this.position.getY() + 1; j < 8; j++) {
                            if (null != curBoard.getSquare(this.position.getX(),j).currFig) {
                                return !(curBoard.getSquare(this.position.getX(),j).currFig == curKing);
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    boolean canMoveVertically(Board curBoard) {
        King curKing = curBoard.getKing(this.figColor);
        if (this.position.getY() != curKing.position.getY()) {
            return true;
        }
        if (this.position.getX() > curKing.position.getX()) {
            for (int i = this.position.getX() + 1; i < 8; i++) {
                if (null != curBoard.getSquare(i,this.position.getY()).currFig) {
                    if (curBoard.getSquare(i,this.position.getY()).currFig.figColor == this.figColor) {
                        return true;
                    } else if (curBoard.getSquare(i,this.position.getY()).currFig.getType() != Type.QUEEN
                            && curBoard.getSquare(i,this.position.getY()).currFig.getType() != Type.ROOK) {
                        return true;
                    } else {
                        for (int j = this.position.getX() - 1; j > 0; j--) {
                            if (null != curBoard.getSquare(j,this.position.getY()).currFig) {
                                return !(curBoard.getSquare(j,this.position.getY()).currFig == curKing);
                            }
                        }
                    }
                }
            }
        } else if (this.position.getX() < curKing.position.getX()) {
            for (int i = this.position.getX() - 1; i >= 0; i--) {
                if (null != curBoard.getSquare(i,this.position.getY()).currFig) {
                    if (curBoard.getSquare(i,this.position.getY()).currFig.figColor == this.figColor) {
                        return true;
                    } else if (curBoard.getSquare(i,this.position.getY()).currFig.getType() != Type.QUEEN
                            && curBoard.getSquare(i,this.position.getY()).currFig.getType() != Type.ROOK) {
                        return true;
                    } else {
                        for (int j = this.position.getX() + 1; j < 8; j++) {
                            if (null != curBoard.getSquare(j,this.position.getY()).currFig) {
                                return !(curBoard.getSquare(j,this.position.getY()).currFig == curKing);
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    boolean canMoveLeftDownDiagonal(Board curBoard) {
        King curKing = curBoard.getKing(this.figColor);
        if (abs((this.position.getY() - curKing.position.getY())) != abs((this.position.getX() - curKing.position.getX()))) {
            return true;
        }
        if (this.position.getX() > curKing.position.getX()) {
            for (int i = this.position.getX() + 1, j = this.position.getY() + 1; i < 8 && j < 8; i++, j++) {
                if (null != curBoard.getSquare(i,j).currFig) {
                    if (curBoard.getSquare(i,j).currFig.figColor == this.figColor) {
                        return true;
                    } else if (curBoard.getSquare(i,j).currFig.getType() != Type.QUEEN
                            && curBoard.getSquare(i,j).currFig.getType() != Type.BISHOP) {
                        return true;
                    } else {
                        for (int k = this.position.getX() - 1, l = this.position.getY() - 1; k > 0 && l > 0; k--, l--) {
                            if (null != curBoard.getSquare(k,l).currFig) {
                                return !(curBoard.getSquare(k,l).currFig == curKing);
                            }
                        }
                    }
                }
            }
        } else if (this.position.getX() < curKing.position.getX()) {
            for (int i = this.position.getX() - 1, j = this.position.getY() - 1; i > 0 && j > 0; i--, j--) {
                if (null != curBoard.getSquare(i, j).currFig) {
                    if (curBoard.getSquare(i, j).currFig.figColor == this.figColor) {
                        return true;
                    } else if (curBoard.getSquare(i, j).currFig.getType() != Type.QUEEN
                            && curBoard.getSquare(i, j).currFig.getType() != Type.BISHOP) {
                        return true;
                    } else {
                        for (int k = this.position.getX() + 1, l = this.position.getY() + 1; k < 8 && l < 8; k++, l++) {
                            if (null != curBoard.getSquare(k, l).currFig) {
                                return !(curBoard.getSquare(k, l).currFig == curKing);
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    boolean canMoveRightDownDiagonal(Board curBoard) {
        King curKing = curBoard.getKing(this.figColor);
        if (abs((this.position.getY() - curKing.position.getY())) != abs((this.position.getX() - curKing.position.getX()))) {
            return true;
        }
        if (this.position.getX() > curKing.position.getX()) {
            for (int i = this.position.getX() + 1, j = this.position.getY() - 1; i < 8 && j > 0; i++, j--) {
                if (null != curBoard.getSquare(i,j).currFig) {
                    if (curBoard.getSquare(i,j).currFig.figColor == this.figColor) {
                        return true;
                    } else if (curBoard.getSquare(i,j).currFig.getType() != Type.QUEEN
                            && curBoard.getSquare(i,j).currFig.getType() != Type.BISHOP) {
                        return true;
                    } else {
                        for (int k = this.position.getX() - 1, l = this.position.getY() + 1; k > 0 && l < 8; k--, l++) {
                            if (null != curBoard.getSquare(k,l).currFig) {
                                return !(curBoard.getSquare(k,l).currFig == curKing);
                            }
                        }
                    }
                }
            }
        } else if (this.position.getX() < curKing.position.getX()) {
            for (int i = this.position.getX() - 1, j = this.position.getY() + 1; i > 0 && j < 8; i--, j++) {
                if (null != curBoard.getSquare(i, j).currFig) {
                    if (curBoard.getSquare(i, j).currFig.figColor == this.figColor) {
                        return true;
                    } else if (curBoard.getSquare(i, j).currFig.getType() != Type.QUEEN
                            && curBoard.getSquare(i, j).currFig.getType() != Type.BISHOP) {
                        return true;
                    } else {
                        for (int k = this.position.getX() + 1, l = this.position.getY() - 1; k < 8 && l > 0; k++, l--) {
                            if (null != curBoard.getSquare(k, l).currFig) {
                                return !(curBoard.getSquare(k, l).currFig == curKing);
                            }
                        }
                    }
                }
            }
        }
        return true;
    }
}

enum FigColor {
    BLACK, WHITE;
}
enum Type {
    KING, QUEEN, ROOK, BISHOP, KNIGHT, PAWN;
}
