package game;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Game {

    private Board gameBoard;
    private boolean figureClicked = false;

    private Pair moveFrom;
    private Pair moveTo;

    private ArrayList<String>  gameSaveList;
    private ArrayList<Pair> clickableSquaresList;
    private ArrayList<Pair> changedSquareList;

    private final BufferedImage emptyBoard;

    private final BufferedImage blackBishop;
    private final BufferedImage blackKing;
    private final BufferedImage blackKnight;
    private final BufferedImage blackPawn;
    private final BufferedImage blackQueen;
    private final BufferedImage blackRook;

    private final BufferedImage whiteBishop;
    private final BufferedImage whiteKing;
    private final BufferedImage whiteKnight;
    private final BufferedImage whitePawn;
    private final BufferedImage whiteQueen;
    private final BufferedImage whiteRook;

    private BufferedImage currentBoard;
    private BufferedImage currentBoardWithDedicatedSquares;

    public Game() {
        gameBoard = new Board();
        gameSaveList = new ArrayList<>();
        clickableSquaresList = new ArrayList<>();
        changedSquareList = new ArrayList<>();
        BufferedImage tempEmptyBoard = null;
        BufferedImage tempBlackBishop = null;
        BufferedImage tempBlackKing = null;
        BufferedImage tempBlackKnight = null;
        BufferedImage tempBlackPawn = null;
        BufferedImage tempBlackQueen = null;
        BufferedImage tempBlackRook = null;
        BufferedImage tempWhiteBishop = null;
        BufferedImage tempWhiteKing = null;
        BufferedImage tempWhiteKnight = null;
        BufferedImage tempWhitePawn = null;
        BufferedImage tempWhiteQueen = null;
        BufferedImage tempWhiteRook = null;
        try {
            tempEmptyBoard = ImageIO.read(new File("src\\Main\\Resources\\brown.png"));
            tempBlackBishop = ImageIO.read(new File("src\\Main\\Resources\\bB.png"));
            tempBlackKing = ImageIO.read(new File("src\\Main\\Resources\\bK.png"));
            tempBlackKnight = ImageIO.read(new File("src\\Main\\Resources\\bN.png"));
            tempBlackPawn = ImageIO.read(new File("src\\Main\\Resources\\bP.png"));
            tempBlackQueen = ImageIO.read(new File("src\\Main\\Resources\\bQ.png"));
            tempBlackRook = ImageIO.read(new File("src\\Main\\Resources\\bR.png"));
            tempWhiteBishop = ImageIO.read(new File("src\\Main\\Resources\\wB.png"));
            tempWhiteKing = ImageIO.read(new File("src\\Main\\Resources\\wK.png"));
            tempWhiteKnight = ImageIO.read(new File("src\\Main\\Resources\\wN.png"));
            tempWhitePawn = ImageIO.read(new File("src\\Main\\Resources\\wP.png"));
            tempWhiteQueen = ImageIO.read(new File("src\\Main\\Resources\\wQ.png"));
            tempWhiteRook = ImageIO.read(new File("src\\Main\\Resources\\wR.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        emptyBoard = tempEmptyBoard;
        blackRook = tempBlackRook;
        blackQueen  = tempBlackQueen;
        blackPawn = tempBlackPawn;
        blackKnight = tempBlackKnight;
        blackKing = tempBlackKing;
        blackBishop = tempBlackBishop;
        whiteRook = tempWhiteRook;
        whiteQueen = tempWhiteQueen;
        whitePawn = tempWhitePawn;
        whiteKnight = tempWhiteKnight;
        whiteKing = tempWhiteKing;
        whiteBishop = tempWhiteBishop;
    }

    private void fillSquareWithFigure(Figure currFig) {
        if (null == currFig) {
            return;
        }
        Graphics2D g = currentBoard.createGraphics();
        switch (currFig.figColor) {
            case BLACK:
                switch (currFig.getType()) {
                    case KING:
                        g.drawImage(blackKing, (7 - currFig.position.getX()) * 128, (7 - currFig.position.getY()) * 128, 128, 128, null);
                        break;
                    case ROOK:
                        g.drawImage(blackRook, (7 - currFig.position.getX()) * 128, (7 - currFig.position.getY()) * 128, 128, 128, null);
                        break;
                    case PAWN:
                        g.drawImage(blackPawn, (7 - currFig.position.getX()) * 128, (7 - currFig.position.getY()) * 128, 128, 128, null);
                        break;
                    case QUEEN:
                        g.drawImage(blackQueen, (7 - currFig.position.getX()) * 128, (7 - currFig.position.getY()) * 128, 128, 128, null);
                        break;
                    case BISHOP:
                        g.drawImage(blackBishop, (7 - currFig.position.getX()) * 128, (7 - currFig.position.getY()) * 128, 128, 128, null);
                        break;
                    case KNIGHT:
                        g.drawImage(blackKnight, (7 - currFig.position.getX()) * 128, (7 - currFig.position.getY()) * 128, 128, 128, null);
                        break;
                }
                break;
            case WHITE:
                switch (currFig.getType()) {
                    case KING:
                        g.drawImage(whiteKing, (7 - currFig.position.getX()) * 128, (7 - currFig.position.getY()) * 128, 128, 128, null);
                        break;
                    case ROOK:
                        g.drawImage(whiteRook, (7 - currFig.position.getX()) * 128, (7 - currFig.position.getY()) * 128, 128, 128, null);
                        break;
                    case PAWN:
                        g.drawImage(whitePawn, (7 - currFig.position.getX()) * 128, (7 - currFig.position.getY()) * 128, 128, 128, null);
                        break;
                    case QUEEN:
                        g.drawImage(whiteQueen, (7 - currFig.position.getX()) * 128, (7 - currFig.position.getY()) * 128, 128, 128, null);
                        break;
                    case BISHOP:
                        g.drawImage(whiteBishop, (7 - currFig.position.getX()) * 128, (7 - currFig.position.getY()) * 128, 128, 128, null);
                        break;
                    case KNIGHT:
                        g.drawImage(whiteKnight, (7 - currFig.position.getX()) * 128, (7 - currFig.position.getY()) * 128, 128, 128, null);
                        break;
                }
        }
        g.dispose();
    }

    private class BufferedImageProcessingThread implements Runnable {
        private Thread thread;
        private final int column;

        BufferedImageProcessingThread(int column) {
            this.column = column;
        }

        @Override
        public void run() {
            for (int i = 0; i < 8; i++) {
                fillSquareWithFigure(gameBoard.getSquare(column, i).currFig);
            }
        }

        void start() {
            thread = new Thread(this);
            thread.start();
        }

        void join() {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void fillBoardWithFigures() {
        currentBoard = emptyBoard;
        BufferedImageProcessingThread[] threads = new BufferedImageProcessingThread[8];
        for (int i = 0; i < 8; i++) {
            threads[i] = new BufferedImageProcessingThread(i);
            threads[i].start();
        }
        for (BufferedImageProcessingThread thread:threads) {
            thread.join();
        }
    }

    public static void main(String[] args) {
        Game g = new Game();
        g.fillBoardWithFigures();
        System.out.println(" ");
    }

}
