package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Game {

    private Board gameBoard;
    private boolean figureClicked = false;
    private boolean boardHovered = false;

    private Pair moveFrom = new Pair(0,0);
    private Pair moveTo = new Pair(0, 0);
    private Pair hoveredSquare = new Pair(0,0);

    public ArrayList<String> getGameSaveList() {
        return gameSaveList;
    }

    private ArrayList<String>  gameSaveList;
    private ArrayList<Pair> clickableSquaresList;

    private static final BufferedImage emptyBoard;

    private static final BufferedImage blackBishop;
    private static final BufferedImage blackKing;
    private static final BufferedImage blackKnight;
    private static final BufferedImage blackPawn;
    private static final BufferedImage blackQueen;
    private static final BufferedImage blackRook;
    
    private static final BufferedImage whiteBishop;
    private static final BufferedImage whiteKing;
    private static final BufferedImage whiteKnight;
    private static final BufferedImage whitePawn;
    private static final BufferedImage whiteQueen;
    private static final BufferedImage whiteRook;

    static {
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
            tempEmptyBoard = ImageIO.read(new File("src\\Main\\resources\\brown.png"));
            tempBlackBishop = ImageIO.read(new File("src\\Main\\resources\\bB.png"));
            tempBlackKing = ImageIO.read(new File("src\\Main\\resources\\bK.png"));
            tempBlackKnight = ImageIO.read(new File("src\\Main\\resources\\bN.png"));
            tempBlackPawn = ImageIO.read(new File("src\\Main\\resources\\bP.png"));
            tempBlackQueen = ImageIO.read(new File("src\\Main\\resources\\bQ.png"));
            tempBlackRook = ImageIO.read(new File("src\\Main\\resources\\bR.png"));
            tempWhiteBishop = ImageIO.read(new File("src\\Main\\resources\\wB.png"));
            tempWhiteKing = ImageIO.read(new File("src\\Main\\resources\\wK.png"));
            tempWhiteKnight = ImageIO.read(new File("src\\Main\\resources\\wN.png"));
            tempWhitePawn = ImageIO.read(new File("src\\Main\\resources\\wP.png"));
            tempWhiteQueen = ImageIO.read(new File("src\\Main\\resources\\wQ.png"));
            tempWhiteRook = ImageIO.read(new File("src\\Main\\resources\\wR.png"));
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

    /**
     * Method for getting board image.
     * @return Current board image.
     */
    public BufferedImage getCurrentBoard() {
        Graphics2D g = currentBoard.createGraphics();
        g.drawImage(emptyBoard,0, 0, 512, 512,  null);
        g.drawImage(currentMoveDedicationLayer, 0, 0, 512, 512,  null);
        g.drawImage(currentKingUnderAttackLayer,0, 0, 512, 512,  null);
        g.drawImage(currentHoveringDedicationLayer, 0, 0, 512, 512,  null);
        g.drawImage(currentFigureLayer, 0, 0, 512, 512,  null);
        g.dispose();
        return currentBoard;
    }

    private BufferedImage currentBoard;
    private BufferedImage currentMoveDedicationLayer;
    private BufferedImage currentKingUnderAttackLayer;
    private BufferedImage currentHoveringDedicationLayer;
    private BufferedImage currentFigureLayer;
    private static final Color emptyColor = new Color(0,0,0, 0);
    private static final Color dedicationColor = new Color(0, 255, 0, 128);
    private static final Color kingUnderAttackColor = new Color(255,0,0,128);

    /**
     * Initializes Game class with starting chess positions.
     */
    public Game() {
        gameBoard = new Board();
        gameSaveList = new ArrayList<>();
        currentBoard = new BufferedImage(512, 512, BufferedImage.TYPE_INT_RGB);
        currentFigureLayer = new BufferedImage(1024,1024, BufferedImage.TYPE_INT_ARGB);
        currentKingUnderAttackLayer = new BufferedImage(1024, 1024, BufferedImage.TYPE_INT_ARGB);
        currentMoveDedicationLayer = new BufferedImage(1024, 1024, BufferedImage.TYPE_INT_ARGB);
        currentHoveringDedicationLayer = new BufferedImage(1024, 1024, BufferedImage.TYPE_INT_ARGB);
        fillBoardWithFigures();
        clickableSquaresList = gameBoard.getMovableFiguresList();
    }

    private void clearLayer(BufferedImage layer) {
        Graphics2D g = layer.createGraphics();
        g.setBackground(emptyColor);
        g.clearRect(0,0, layer.getWidth(), layer.getHeight());
        g.dispose();
    }

    private void fillSquare(Pair point) {
        Figure fig = gameBoard.getSquare(point.getX(), point.getY()).currFig;
        if (null != fig) {
            Graphics2D g = currentFigureLayer.createGraphics();
            fillSquareWithFigure(fig, g);
            g.dispose();
        } else {
            fillSquareWithColor(point, currentFigureLayer.createGraphics(), emptyColor);
        }
    }

    /**
     * When you use this method you should create new graphics.
     * @param point Coordinates of changing square.
     * @param g Graphics, which you should put in format: layer.createGraphics().
     * @param color Chosen color.
     */
    private void fillSquareWithColor(Pair point, Graphics2D g, Color color) {
        if (color == emptyColor) {
            g.setBackground(color);
            g.clearRect(point.getX() * 128, (7 - point.getY()) * 128, 128, 128);
        } else {
            g.setColor(color);
            g.fillRect(point.getX() * 128, (7 - point.getY()) * 128, 128, 128);
        }
        g.dispose();
    }

    private void fillSquareWithFigure(Figure currFig, Graphics2D g) {
        if (null == currFig) {
            return;
        }
        switch (currFig.figColor) {
            case BLACK:
                switch (currFig.getType()) {
                    case KING:
                        g.drawImage(blackKing, currFig.position.getX() * 128, (7 - currFig.position.getY()) * 128, 128, 128, null);
                        break;
                    case ROOK:
                        g.drawImage(blackRook, currFig.position.getX() * 128, (7 - currFig.position.getY()) * 128, 128, 128, null);
                        break;
                    case PAWN:
                        g.drawImage(blackPawn, currFig.position.getX() * 128, (7 - currFig.position.getY()) * 128, 128, 128, null);
                        break;
                    case QUEEN:
                        g.drawImage(blackQueen, currFig.position.getX() * 128, (7 - currFig.position.getY()) * 128, 128, 128, null);
                        break;
                    case BISHOP:
                        g.drawImage(blackBishop, currFig.position.getX() * 128, (7 - currFig.position.getY()) * 128, 128, 128, null);
                        break;
                    case KNIGHT:
                        g.drawImage(blackKnight, currFig.position.getX() * 128, (7 - currFig.position.getY()) * 128, 128, 128, null);
                        break;
                    default:
                }
                break;
            case WHITE:
                switch (currFig.getType()) {
                    case KING:
                        g.drawImage(whiteKing, currFig.position.getX() * 128, (7 - currFig.position.getY()) * 128, 128, 128, null);
                        break;
                    case ROOK:
                        g.drawImage(whiteRook, currFig.position.getX() * 128, (7 - currFig.position.getY()) * 128, 128, 128, null);
                        break;
                    case PAWN:
                        g.drawImage(whitePawn, currFig.position.getX() * 128, (7 - currFig.position.getY()) * 128, 128, 128, null);
                        break;
                    case QUEEN:
                        g.drawImage(whiteQueen, currFig.position.getX() * 128, (7 - currFig.position.getY()) * 128, 128, 128, null);
                        break;
                    case BISHOP:
                        g.drawImage(whiteBishop, currFig.position.getX() * 128, (7 - currFig.position.getY()) * 128, 128, 128, null);
                        break;
                    case KNIGHT:
                        g.drawImage(whiteKnight, currFig.position.getX() * 128, (7 - currFig.position.getY()) * 128, 128, 128, null);
                        break;
                    default:
                }
                break;
            default:
        }
    }

    private class FigureLayerProcessingThread implements Runnable {
        private Thread thread;
        private final int column;
        private Graphics2D graphics;

        FigureLayerProcessingThread(int column, Graphics2D g) {
            this.column = column;
            this.graphics = g;
        }

        @Override
        public void run() {
            for (int i = 0; i < 8; i++) {
                fillSquareWithFigure(gameBoard.getSquare(column, i).currFig, graphics);
            }
            graphics.dispose();
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
        FigureLayerProcessingThread[] threads = new FigureLayerProcessingThread[8];
        for (int i = 0; i < 8; i++) {
            threads[i] = new FigureLayerProcessingThread(i, currentFigureLayer.createGraphics());
            threads[i].start();
        }
        for (FigureLayerProcessingThread thread:threads) {
            thread.join();
        }
    }

    /**
     * Repaints mouse hovering layer which represents current mouse position on a board.
     * @param hoveredSquare Coordinates of hovered square.
     * @param hover Parameter which represents if mouse hovering board at the moment or not.
     * @return false if nothing changed and true if opposite.
     */
    public boolean mouseHover(Pair hoveredSquare, boolean hover) {
        if ((!boardHovered) && hover && clickableSquaresList.contains(hoveredSquare)) {
            this.hoveredSquare.setX(hoveredSquare.getX());
            this.hoveredSquare.setY(hoveredSquare.getY());
            boardHovered = true;
            fillSquareWithColor(this.hoveredSquare, currentHoveringDedicationLayer.createGraphics(), dedicationColor);
            return true;
        } else if (boardHovered && (!hover)) {
            boardHovered = false;
            fillSquareWithColor(this.hoveredSquare, currentHoveringDedicationLayer.createGraphics(), emptyColor);
            return true;
        } else if (boardHovered && clickableSquaresList.contains(hoveredSquare)) {
            if (!this.hoveredSquare.equals(hoveredSquare)) {
                fillSquareWithColor(this.hoveredSquare, currentHoveringDedicationLayer.createGraphics(), emptyColor);
                this.hoveredSquare.setX(hoveredSquare.getX());
                this.hoveredSquare.setY(hoveredSquare.getY());
                fillSquareWithColor(this.hoveredSquare, currentHoveringDedicationLayer.createGraphics(), dedicationColor);
                return true;
            }
        } else if (boardHovered && !clickableSquaresList.contains(hoveredSquare)) {
            fillSquareWithColor(this.hoveredSquare, currentHoveringDedicationLayer.createGraphics(), emptyColor);
            this.hoveredSquare.setX(hoveredSquare.getX());
            this.hoveredSquare.setY(hoveredSquare.getY());
            return true;
        }
        return false;
    }

    private class MoveDedicationLayerProcessingThread implements Runnable {
        private Thread thread;
        private Pair pair;
        private Graphics2D graphics;

        MoveDedicationLayerProcessingThread(Pair pair, Graphics2D graphics) {
            this.pair = pair;
            this.graphics = graphics;
            this.graphics.setColor(dedicationColor);
        }

        @Override
        public void run() {
            for (int i = 0; i < 8; i++) {
                pair.setY(i);
                if (clickableSquaresList.contains(pair)) {
                    fillDedicationSquare(pair, graphics);
                }
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

    private void fillDedicationSquare(Pair pair, Graphics2D g) {
        if (null == gameBoard.getSquare(pair.getX(), pair.getY()).currFig) {
            g.fillOval((pair.getX() * 128 + 48), ((7 - pair.getY()) * 128 + 48), 32, 32);
        } else {
            int[] xpoints = new int[]{(pair.getX() * 128), (pair.getX() * 128), (pair.getX() * 128 + 16)};
            int[] ypoints = new int[]{((7 - pair.getY()) * 128), ((7 - pair.getY()) * 128 + 16), ((7 - pair.getY()) * 128)};
            g.fillPolygon(xpoints, ypoints, 3);
            xpoints[0] += 128;
            xpoints[1] += 128;
            xpoints[2] += 96;
            g.fillPolygon(xpoints, ypoints, 3);
            ypoints[0] += 128;
            ypoints[1] += 96;
            ypoints[2] += 128;
            g.fillPolygon(xpoints, ypoints, 3);
            xpoints[0] -= 128;
            xpoints[1] -= 128;
            xpoints[2] -= 96;
            g.fillPolygon(xpoints, ypoints, 3);
        }
    }

    private void fillMoveDedicationLayer() {
        if (!figureClicked) {
            return;
        }
        MoveDedicationLayerProcessingThread[] threads = new MoveDedicationLayerProcessingThread[8];
        for (int i = 0; i < 8; i++) {
            threads[i] = new MoveDedicationLayerProcessingThread(new Pair(i, 0), currentMoveDedicationLayer.createGraphics());
            threads[i].start();
        }
        for (MoveDedicationLayerProcessingThread thread:threads) {
            thread.join();
        }
    }

    private void parseStringToMove(String str) throws IllegalArgumentException {
        if (str.length() != 4) {
            throw new IllegalArgumentException("Error: Invalid string cannot be parsed");
        }
        moveFrom.setX(Character.getNumericValue(str.charAt(0)) - Character.getNumericValue('a'));
        moveFrom.setY(Character.getNumericValue(str.charAt(1)) - 1);
        moveTo.setX(Character.getNumericValue(str.charAt(2)) - Character.getNumericValue('a'));
        moveTo.setY(Character.getNumericValue(str.charAt(3)) - 1);
    }

    private String parseMoveToString() {
        return moveFrom.toString() + moveTo.toString();
    }

    /**
     * This method used for mouse click event.
     * User can manipulate with board using this method.
     * @param clickedSquare Position of clicked square.
     * @return null if move was canceled or figure was chose and current move.toString() if game continues and
     *         winning message string if someone wins.
     */
    public String mouseBoardClick(Pair clickedSquare) {
        if (!figureClicked) {
            if (!clickableSquaresList.contains(clickedSquare)) {
                return null;
            }
            moveFrom.setPair(clickedSquare);
            figureClicked = true;
            clickableSquaresList = gameBoard.getSquare(clickedSquare.getX(), clickedSquare.getY()).currFig.moveList;
            fillSquareWithColor(clickedSquare, currentMoveDedicationLayer.createGraphics(), dedicationColor);
            fillMoveDedicationLayer();
            return null;
        }
        if (clickedSquare.equals(moveFrom)) {
            figureClicked = false;
            clearLayer(currentMoveDedicationLayer);
            clickableSquaresList = gameBoard.getMovableFiguresList();
            return null;
        }
        if (!clickableSquaresList.contains(clickedSquare)) {
            return null;
        }
        figureClicked = false;
        boolean gameContinues = true;
        moveTo.setPair(clickedSquare);
        try {
            gameContinues = gameBoard.moveChessPiece(moveFrom, moveTo);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        fillSquareWithColor(moveTo, currentFigureLayer.createGraphics(), emptyColor);
        ArrayList<Pair> changedSquaresList = gameBoard.getChangedSquareList();
        for (Pair pair: changedSquaresList) {
            fillSquare(pair);
        }
        gameSaveList.add(parseMoveToString());
        clickableSquaresList = gameBoard.getMovableFiguresList();
        if (!gameContinues || clickableSquaresList.isEmpty()) {
            FigColor color = gameBoard.getTurn();
            switch (color) {
                case WHITE:
                    return "Congratulations! Black wins!";
                case BLACK:
                    return "Congratulations! White wins!";
                default:
            }
        }
        clearLayer(currentKingUnderAttackLayer);
        clearLayer(currentMoveDedicationLayer);
        Pair kingPos = gameBoard.getKingCoordinatesIfHeUnderAttack();
        if (null != kingPos) {
            Graphics2D g = currentKingUnderAttackLayer.createGraphics();
            g.setColor(kingUnderAttackColor);
            g.fillOval(kingPos.getX() * 128, (7 - kingPos.getY()) * 128, 128, 128);
            g.dispose();
        }
        return parseMoveToString();
    }

    /**
     * Make bot moving.
     * @return Bot move string or game end string.
     */
    public String botMove() {
        boolean gameContinues = true;
        try {
            gameContinues = gameBoard.botMove();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        parseStringToMove(gameBoard.getBotMoveString());
        fillSquareWithColor(moveTo, currentFigureLayer.createGraphics(), emptyColor);
        ArrayList<Pair> changedSquaresList = gameBoard.getChangedSquareList();
        for (Pair pair: changedSquaresList) {
            fillSquare(pair);
        }
        gameSaveList.add(parseMoveToString());
        clickableSquaresList = gameBoard.getMovableFiguresList();
        if (!gameContinues || clickableSquaresList.isEmpty()) {
            FigColor color = gameBoard.getTurn();
            switch (color) {
                case WHITE:
                    return "Congratulations! Black wins!";
                case BLACK:
                    return "Congratulations! White wins!";
                default:
            }
        }
        clearLayer(currentKingUnderAttackLayer);
        clearLayer(currentMoveDedicationLayer);
        Pair kingPos = gameBoard.getKingCoordinatesIfHeUnderAttack();
        if (null != kingPos) {
            Graphics2D g = currentKingUnderAttackLayer.createGraphics();
            g.setColor(kingUnderAttackColor);
            g.fillOval(kingPos.getX() * 128, (7 - kingPos.getY()) * 128, 128, 128);
            g.dispose();
        }
        return parseMoveToString();
    }

    /**
     * Loads game from text file if it's not completed.
     * @param filePath File path chosen in open file dialog.
     */
    public void loadGameFromFile(String filePath) throws IllegalArgumentException {
        gameSaveList.clear();
        this.gameBoard = new Board();
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            try {
                for (String line; (line = br.readLine()) != null; ) {
                    parseStringToMove(line);
                    if (!gameBoard.moveChessPiece(moveFrom, moveTo)) {
                        throw new IllegalArgumentException("Error:Can't load completed game");
                    }
                    gameSaveList.add(parseMoveToString());
                }
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            throw e;
        } finally {
            clearLayer(currentFigureLayer);
            fillBoardWithFigures();
            clearLayer(currentKingUnderAttackLayer);
            clearLayer(currentMoveDedicationLayer);
            Pair kingPos = gameBoard.getKingCoordinatesIfHeUnderAttack();
            if (null != kingPos) {
                Graphics2D g = currentKingUnderAttackLayer.createGraphics();
                g.setColor(kingUnderAttackColor);
                g.fillOval(kingPos.getX() * 128, (7 - kingPos.getY()) * 128, 128, 128);
                g.dispose();
            }
            clickableSquaresList = gameBoard.getMovableFiguresList();
        }
    }

    /**
     * Loads game from string array.
     * @param strArray Strings parsed from XML save file.
     */
    public void loadFromStringArray(ArrayList<String> strArray) {
        gameSaveList.clear();
        this.gameBoard = new Board();
        try {
            for (String line:strArray) {
                parseStringToMove(line);
                if (!gameBoard.moveChessPiece(moveFrom, moveTo)) {
                    throw new IllegalArgumentException("Error:Can't load completed game");
                }
                gameSaveList.add(parseMoveToString());
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            throw e;
        } finally {
            clearLayer(currentFigureLayer);
            fillBoardWithFigures();
            clearLayer(currentKingUnderAttackLayer);
            clearLayer(currentMoveDedicationLayer);
            Pair kingPos = gameBoard.getKingCoordinatesIfHeUnderAttack();
            if (null != kingPos) {
                Graphics2D g = currentKingUnderAttackLayer.createGraphics();
                g.setColor(kingUnderAttackColor);
                g.fillOval(kingPos.getX() * 128, (7 - kingPos.getY()) * 128, 128, 128);
                g.dispose();
            }
            clickableSquaresList = gameBoard.getMovableFiguresList();
        }
    }

    /**
     * Saves current game.
     * @param filePath File path chosen in save file dialog.
     */
    public void createSaveFile(String filePath) {
        BufferedWriter bw;
        try {
            bw = new BufferedWriter(new FileWriter(filePath));
            for (String str:gameSaveList) {
                bw.write(str);
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
