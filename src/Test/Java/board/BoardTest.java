package board;

import game.Board;
import game.Pair;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    @Test
    void moveChessPieceInvalidArgumentExceptionTest() {
        assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                Board board = new Board();
                board.moveChessPiece(new Pair(0,0), new Pair(7,7 ));
            }
        });
    }

    @Test
    void getKingCoordinatesIfHeUnderAttackNullTest() {
        Board board = new Board();
        assertNull(board.getKingCoordinatesIfHeUnderAttack());
    }

    @Test
    void getMovableFigureFirstTurnTest() {
        Board board = new Board();
        assertTrue(board.getMovableFiguresList().toArray().length == 10);
    }

    @Test
    void getChangedSquareListSizeAtSecondMoveTest() {
        Board board = new Board();
        board.moveChessPiece(new Pair(0,1), new Pair(0, 2));
        assertTrue(board.getChangedSquareList().toArray().length == 2);
    }
}