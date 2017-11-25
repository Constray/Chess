package gui;

import game.Game;
import game.Pair;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.log4j.Logger;

import report.PdfAndHtmlReport;
import report.XmlReport;

class MainFrame extends javax.swing.JFrame {

    MainFrame() {
        initComponents();
        initGameComponents();
        initToolBarIcons();
        txtFileChooser.setFileFilter(new FileNameExtensionFilter("Text file (*.txt)", "txt"));
        xmlFileChooser.setFileFilter(new FileNameExtensionFilter("XML file (*.xml)", "xml"));
        pdfHtmlFileChooser.setFileFilter(new FileNameExtensionFilter("PDF and HTML file (*.pdf, *.html)", "pdf", "html"));
        int n = JOptionPane.showConfirmDialog(this,"Do you want to play with bot?",
                "bot", JOptionPane.YES_NO_OPTION);
        if (n == 0) {
            playWithBot = true;
            Object[] options = {"White",
                    "Black"};
            int m = JOptionPane.showOptionDialog(this,"Do you want to play as white or black?",
                    "Choose your side.", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            if (m == 1) {
                String moveString = mainGame.botMove();
                movesTextArea.append(moveString + "\n");
                refreshImage();
            }
        }
    }

    private Game mainGame;

    private ImageIcon boardIcon = new ImageIcon();

    private boolean mouseOnBoard = false;

    private Pair mouseOnBoardPosition = new Pair(0,0);

    private Point mouseLocation;

    private  boolean playWithBot = false;

    private static final Logger log = Logger.getLogger(MainFrame.class);

    private final JFileChooser txtFileChooser = new JFileChooser();
    private final JFileChooser xmlFileChooser = new JFileChooser();
    private final JFileChooser pdfHtmlFileChooser = new JFileChooser();

    private void initGameComponents() {
        mainGame = new Game();
        refreshImage();
    }

    private void refreshImage() {
        boardIcon.setImage(mainGame.getCurrentBoard());
        this.boardImage.setIcon(boardIcon);
    }

    private BufferedImage resizeIcon(BufferedImage image) {
        BufferedImage result = new BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = result.createGraphics();
        g.drawImage(image, 0, 0, 32, 32, null);
        g.dispose();
        return result;
    }

    private void initToolBarIcons() {
        ImageIcon saveFileIcon = null;
        ImageIcon loadFileIcon = null;
        ImageIcon saveXmlIcon = null;
        ImageIcon loadXmlIcon = null;
        ImageIcon savePdfIcon = null;
        try {
            saveFileIcon = new ImageIcon(resizeIcon(ImageIO.read(new File("src\\Main\\resources\\icons\\fileSave.png"))));
            loadFileIcon = new ImageIcon(resizeIcon(ImageIO.read(new File("src\\Main\\resources\\icons\\fileRead.png"))));
            saveXmlIcon = new ImageIcon(resizeIcon(ImageIO.read(new File("src\\Main\\resources\\icons\\xmlSave.png"))));
            loadXmlIcon = new ImageIcon(resizeIcon(ImageIO.read(new File("src\\Main\\resources\\icons\\xmlRead.png"))));
            savePdfIcon = new ImageIcon(resizeIcon(ImageIO.read(new File("src\\Main\\resources\\icons\\pdfReport.png"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        saveFileButton.setIcon(saveFileIcon);
        loadFileButton.setIcon(loadFileIcon);
        saveToXML.setIcon(saveXmlIcon);
        loadFromXML.setIcon(loadXmlIcon);
        savePDFandHTML.setIcon(savePdfIcon);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        boardPanel = new javax.swing.JPanel();
        boardImage = new javax.swing.JLabel();
        toolBar = new javax.swing.JToolBar();
        saveFileButton = new javax.swing.JButton();
        loadFileButton = new javax.swing.JButton();
        saveToXML = new javax.swing.JButton();
        loadFromXML = new javax.swing.JButton();
        savePDFandHTML = new javax.swing.JButton();
        textAreaScrollPane = new javax.swing.JScrollPane();
        movesTextArea = new javax.swing.JTextArea();
        gameStartButton = new javax.swing.JButton();
        aLabel = new javax.swing.JLabel();
        bLabel = new javax.swing.JLabel();
        cLabel = new javax.swing.JLabel();
        dLabel = new javax.swing.JLabel();
        eLabel = new javax.swing.JLabel();
        fLabel = new javax.swing.JLabel();
        gLabel = new javax.swing.JLabel();
        hLabel = new javax.swing.JLabel();
        Label8 = new javax.swing.JLabel();
        Label7 = new javax.swing.JLabel();
        Label6 = new javax.swing.JLabel();
        Label5 = new javax.swing.JLabel();
        Label4 = new javax.swing.JLabel();
        Label3 = new javax.swing.JLabel();
        Label2 = new javax.swing.JLabel();
        Label1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setFocusable(false);
        setMaximumSize(new java.awt.Dimension(784, 666));
        setMinimumSize(new java.awt.Dimension(784, 666));
        setResizable(false);

        boardPanel.setToolTipText(null);
        boardPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        boardPanel.setMaximumSize(new java.awt.Dimension(512, 512));
        boardPanel.setMinimumSize(new java.awt.Dimension(512, 512));
        boardPanel.setName(""); // NOI18N

        boardImage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        boardImage.setText("   ");
        boardImage.setToolTipText(null);
        boardImage.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        boardImage.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        boardImage.setInheritsPopupMenu(false);
        boardImage.setMaximumSize(new java.awt.Dimension(512, 512));
        boardImage.setMinimumSize(new java.awt.Dimension(512, 512));
        boardImage.setName(""); // NOI18N
        boardImage.setOpaque(true);
        boardImage.setPreferredSize(new java.awt.Dimension(512, 512));
        boardImage.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                boardImageMouseMoved(evt);
            }
        });
        boardImage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                boardImageMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                boardImageMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                boardImageMouseExited(evt);
            }
        });

        javax.swing.GroupLayout boardPanelLayout = new javax.swing.GroupLayout(boardPanel);
        boardPanel.setLayout(boardPanelLayout);
        boardPanelLayout.setHorizontalGroup(
                boardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(boardImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        boardPanelLayout.setVerticalGroup(
                boardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(boardImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        toolBar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        toolBar.setFloatable(false);
        toolBar.setMaximumSize(new java.awt.Dimension(220, 42));
        toolBar.setMinimumSize(new java.awt.Dimension(220, 42));

        saveFileButton.setToolTipText("Сохранить текущую игру");
        saveFileButton.setFocusable(false);
        saveFileButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        saveFileButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        saveFileButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                saveFileButtonMouseClicked(evt);
            }
        });
        toolBar.add(saveFileButton);

        loadFileButton.setToolTipText("Загрузить игру");
        loadFileButton.setFocusable(false);
        loadFileButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        loadFileButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        loadFileButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                loadFileButtonMouseClicked(evt);
            }
        });
        toolBar.add(loadFileButton);

        saveToXML.setToolTipText("Сохранить в XML файл");
        saveToXML.setFocusable(false);
        saveToXML.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        saveToXML.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        saveToXML.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                saveToXMLMouseClicked(evt);
            }
        });
        toolBar.add(saveToXML);

        loadFromXML.setToolTipText("Загрузить из XML файла");
        loadFromXML.setFocusable(false);
        loadFromXML.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        loadFromXML.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        loadFromXML.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                loadFromXMLMouseClicked(evt);
            }
        });
        toolBar.add(loadFromXML);

        savePDFandHTML.setToolTipText("Создаёт HTML и PDF отчеты");
        savePDFandHTML.setFocusable(false);
        savePDFandHTML.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        savePDFandHTML.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        savePDFandHTML.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                savePDFandHTMLMouseClicked(evt);
            }
        });
        toolBar.add(savePDFandHTML);

        textAreaScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        textAreaScrollPane.setToolTipText(null);
        textAreaScrollPane.setAutoscrolls(true);

        movesTextArea.setEditable(false);
        movesTextArea.setColumns(20);
        movesTextArea.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        movesTextArea.setRows(5);
        textAreaScrollPane.setViewportView(movesTextArea);

        gameStartButton.setText("Start Game!");
        gameStartButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                gameStartButtonMouseClicked(evt);
            }
        });

        aLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        aLabel.setText("a");
        aLabel.setMaximumSize(new java.awt.Dimension(34, 128));
        aLabel.setMinimumSize(new java.awt.Dimension(34, 64));

        bLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bLabel.setText("b");
        bLabel.setMaximumSize(new java.awt.Dimension(34, 128));
        bLabel.setMinimumSize(new java.awt.Dimension(34, 64));

        cLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cLabel.setText("c");
        cLabel.setMaximumSize(new java.awt.Dimension(34, 128));
        cLabel.setMinimumSize(new java.awt.Dimension(34, 64));

        dLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dLabel.setText("d");
        dLabel.setMaximumSize(new java.awt.Dimension(34, 128));
        dLabel.setMinimumSize(new java.awt.Dimension(34, 64));

        eLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        eLabel.setText("e");
        eLabel.setToolTipText(null);
        eLabel.setMaximumSize(new java.awt.Dimension(34, 128));
        eLabel.setMinimumSize(new java.awt.Dimension(34, 64));

        fLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fLabel.setText("f");
        fLabel.setMaximumSize(new java.awt.Dimension(34, 128));
        fLabel.setMinimumSize(new java.awt.Dimension(34, 64));

        gLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        gLabel.setText("g");
        gLabel.setMaximumSize(new java.awt.Dimension(34, 128));
        gLabel.setMinimumSize(new java.awt.Dimension(34, 64));

        hLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        hLabel.setText("h");
        hLabel.setMaximumSize(new java.awt.Dimension(34, 128));
        hLabel.setMinimumSize(new java.awt.Dimension(34, 64));

        Label8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Label8.setText("8");
        Label8.setMaximumSize(new java.awt.Dimension(20, 128));
        Label8.setMinimumSize(new java.awt.Dimension(20, 64));

        Label7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Label7.setText("7");
        Label7.setMaximumSize(new java.awt.Dimension(20, 128));
        Label7.setMinimumSize(new java.awt.Dimension(20, 64));

        Label6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Label6.setText("6");
        Label6.setMaximumSize(new java.awt.Dimension(20, 128));
        Label6.setMinimumSize(new java.awt.Dimension(20, 64));

        Label5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Label5.setText("5");
        Label5.setMaximumSize(new java.awt.Dimension(20, 128));
        Label5.setMinimumSize(new java.awt.Dimension(20, 64));

        Label4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Label4.setText("4");
        Label4.setMaximumSize(new java.awt.Dimension(20, 128));
        Label4.setMinimumSize(new java.awt.Dimension(20, 64));

        Label3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Label3.setText("3");
        Label3.setMaximumSize(new java.awt.Dimension(20, 128));
        Label3.setMinimumSize(new java.awt.Dimension(20, 64));

        Label2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Label2.setText("2");
        Label2.setMaximumSize(new java.awt.Dimension(20, 128));
        Label2.setMinimumSize(new java.awt.Dimension(20, 64));

        Label1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Label1.setText("1");
        Label1.setMaximumSize(new java.awt.Dimension(20, 128));
        Label1.setMinimumSize(new java.awt.Dimension(20, 64));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(30, 30, 30)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                        .addComponent(Label8, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(Label7, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(Label6, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(Label5, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(Label4, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(Label3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(Label2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(Label1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(boardPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(aLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(bLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(cLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(dLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(eLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(fLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(gLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(hLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addComponent(toolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(49, 49, 49)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(textAreaScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(gameStartButton, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(25, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(toolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(Label8, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(Label7, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(Label6, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(Label5, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(Label4, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(4, 4, 4)
                                                .addComponent(Label3, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(Label2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(Label1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(textAreaScrollPane)
                                        .addComponent(boardPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(gameStartButton, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(aLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(bLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(cLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(dLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(eLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(fLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(gLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(hLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())
        );

        boardPanel.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void gameStartButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_gameStartButtonMouseClicked
        gameStartButton.setEnabled(false);
        boardImage.setEnabled(false);
        playWithBot = false;
        int n = JOptionPane.showConfirmDialog(this,"Do you want to play with bot?",
                "bot", JOptionPane.YES_NO_OPTION);
        if (n == 0) {
            playWithBot = true;
            Object[] options = {"White",
                    "Black"};
            int m = JOptionPane.showOptionDialog(this,"Do you want to play as white or black?",
                    "Choose your side.", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            if (m == 1) {
                String moveString = mainGame.botMove();
                movesTextArea.append(moveString + "\n");
                refreshImage();
            }
        }
        if (evt.getSource() == gameStartButton) {
            mainGame = new Game();
            this.movesTextArea.setText("");
            refreshImage();
            System.gc();
        }
        boardImage.setEnabled(true);
        log.info("Game restarted");
        gameStartButton.setEnabled(true);
    }//GEN-LAST:event_gameStartButtonMouseClicked

    private void saveFileButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveFileButtonMouseClicked
        saveFileButton.setEnabled(false);
        if (evt.getSource() == saveFileButton) {
            int fileChooseResult = txtFileChooser.showSaveDialog(this);
            if (fileChooseResult == JFileChooser.APPROVE_OPTION) {
                mainGame.createSaveFile(txtFileChooser.getSelectedFile().getAbsolutePath() + ".txt");
            }
        }
        log.info("Game saved to txt file");
        saveFileButton.setEnabled(true);
    }//GEN-LAST:event_saveFileButtonMouseClicked

    private void loadFileButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loadFileButtonMouseClicked
        loadFileButton.setEnabled(false);
        if (evt.getSource() == loadFileButton) {
            int fileChooseResult = txtFileChooser.showOpenDialog(this);
            if (fileChooseResult == JFileChooser.APPROVE_OPTION) {
                try {
                    mainGame.loadGameFromFile(txtFileChooser.getSelectedFile().getAbsolutePath());
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Corrupted file input.");
                    log.error("Corrupted file input.");
                }
                ArrayList<String> strings = mainGame.getGameSaveList();
                movesTextArea.setText("");
                for (String str:strings) {
                    movesTextArea.append(str + "\n");
                }
            }
            playWithBot = false;
            int n = JOptionPane.showConfirmDialog(this,"Do you want to play with bot?",
                    "bot", JOptionPane.YES_NO_OPTION);
            if (n == 0) {
                playWithBot = true;
                Object[] options = {"White",
                        "Black"};
                int m = JOptionPane.showOptionDialog(this,"Do you want to play as white or black?",
                        "Choose your side.", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                if (m != mainGame.getGameSaveList().size() % 2) {
                    String moveString = mainGame.botMove();
                    movesTextArea.append(moveString + "\n");
                    refreshImage();
                }
            }
            refreshImage();
        }
        log.info("Game loaded from txt file");
        loadFileButton.setEnabled(true);
    }//GEN-LAST:event_loadFileButtonMouseClicked

    private void saveToXMLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveToXMLMouseClicked
        saveToXML.setEnabled(false);
        if (evt.getSource() == saveToXML) {
            int fileChooseResult = xmlFileChooser.showSaveDialog(this);
            if (fileChooseResult == JFileChooser.APPROVE_OPTION) {
                XmlReport.saveXmlFile(xmlFileChooser.getSelectedFile().getAbsolutePath() + ".xml", mainGame.getGameSaveList());
            }
        }
        log.info("Game saved to XML file");
        saveToXML.setEnabled(true);
    }//GEN-LAST:event_saveToXMLMouseClicked

    private void loadFromXMLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loadFromXMLMouseClicked
        loadFromXML.setEnabled(false);
        if (evt.getSource() == loadFromXML) {
            int fileChooseResult = xmlFileChooser.showOpenDialog(this);
            if (fileChooseResult == JFileChooser.APPROVE_OPTION) {
                try {
                    mainGame.loadFromStringArray(XmlReport.readXmlFile(xmlFileChooser.getSelectedFile().getAbsolutePath()));
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Corrupted file input.");
                }
                ArrayList<String> strings = mainGame.getGameSaveList();
                movesTextArea.setText("");
                for (String str:strings) {
                    movesTextArea.append(str + "\n");
                }
            }
            refreshImage();
        }
        playWithBot = false;
        int n = JOptionPane.showConfirmDialog(this,"Do you want to play with bot?",
                "bot", JOptionPane.YES_NO_OPTION);
        if (n == 0) {
            playWithBot = true;
            Object[] options = {"White",
                    "Black"};
            int m = JOptionPane.showOptionDialog(this,"Do you want to play as white or black?",
                    "Choose your side.", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            if (m != mainGame.getGameSaveList().size() % 2) {
                String moveString = mainGame.botMove();
                movesTextArea.append(moveString + "\n");
                refreshImage();
            }
        }
        log.info("Game loaded from XML file");
        loadFromXML.setEnabled(true);
    }//GEN-LAST:event_loadFromXMLMouseClicked

    private void savePDFandHTMLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_savePDFandHTMLMouseClicked
        savePDFandHTML.setEnabled(false);
        PdfAndHtmlReport.writePdfFile("PDFReport.pdf", mainGame.getGameSaveList());
        PdfAndHtmlReport.writeHtmlFile("HTMLReport.html", mainGame.getGameSaveList());
        savePDFandHTML.setEnabled(true);
    }//GEN-LAST:event_savePDFandHTMLMouseClicked

    private void boardImageMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_boardImageMouseExited
        boardImage.setEnabled(false);
        mouseOnBoard = false;
        if (mainGame.mouseHover(mouseOnBoardPosition, false)) {
            refreshImage();
        }
        boardImage.setEnabled(true);
    }//GEN-LAST:event_boardImageMouseExited

    private void boardImageMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_boardImageMouseEntered
        boardImage.setEnabled(false);
        mouseOnBoard = true;
        mouseLocation = evt.getPoint();
        mouseOnBoardPosition.setX(mouseLocation.x / (boardImage.getWidth() / 8));
        mouseOnBoardPosition.setY(7 - (mouseLocation.y / (boardImage.getHeight() / 8)));
        if (mainGame.mouseHover(mouseOnBoardPosition, true)) {
            refreshImage();
        }
        boardImage.setEnabled(true);
    }//GEN-LAST:event_boardImageMouseEntered

    private void boardImageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_boardImageMouseClicked
        boardImage.setEnabled(false);
        mouseLocation = evt.getPoint();
        mouseOnBoardPosition.setX(mouseLocation.x / (boardImage.getWidth() / 8));
        mouseOnBoardPosition.setY(7 - (mouseLocation.y / (boardImage.getHeight() / 8)));
        String moveString = mainGame.mouseBoardClick(mouseOnBoardPosition);
        if (null != moveString) {
            if (moveString.length() == 4) {
                this.movesTextArea.append(moveString + "\n");
                if (playWithBot) {
                    moveString = mainGame.botMove();
                    movesTextArea.append(moveString + "\n");
                    refreshImage();
                }
            }
            if (moveString.length() > 4) {
                refreshImage();
                int n = JOptionPane.showConfirmDialog(this,moveString + "\nWould you like to save report?",
                                                      "Someone wins", JOptionPane.YES_NO_OPTION);
                if (n == 0) {
                    PdfAndHtmlReport.writePdfFile("PDFReport.pdf", mainGame.getGameSaveList());
                    PdfAndHtmlReport.writeHtmlFile("HTMLReport.html", mainGame.getGameSaveList());
                }
                mainGame = new Game();
                this.movesTextArea.setText("");
            }
        }
        refreshImage();
        log.info("Board clicked at position:" + mouseOnBoardPosition.toString());
        boardImage.setEnabled(true);
    }//GEN-LAST:event_boardImageMouseClicked

    private void boardImageMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_boardImageMouseMoved
        if (mouseOnBoard) {
            boardImage.setEnabled(false);
            mouseLocation = evt.getPoint();
            mouseOnBoardPosition.setX(mouseLocation.x / (boardImage.getWidth() / 8));
            mouseOnBoardPosition.setY(7 - (mouseLocation.y / (boardImage.getHeight() / 8)));
            if (mainGame.mouseHover(mouseOnBoardPosition, true)) {
                refreshImage();
            }
            boardImage.setEnabled(true);
        }
    }//GEN-LAST:event_boardImageMouseMoved

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Label1;
    private javax.swing.JLabel Label2;
    private javax.swing.JLabel Label3;
    private javax.swing.JLabel Label4;
    private javax.swing.JLabel Label5;
    private javax.swing.JLabel Label6;
    private javax.swing.JLabel Label7;
    private javax.swing.JLabel Label8;
    private javax.swing.JLabel aLabel;
    private javax.swing.JLabel bLabel;
    private javax.swing.JLabel boardImage;
    private javax.swing.JPanel boardPanel;
    private javax.swing.JLabel cLabel;
    private javax.swing.JLabel dLabel;
    private javax.swing.JLabel eLabel;
    private javax.swing.JLabel fLabel;
    private javax.swing.JLabel gLabel;
    private javax.swing.JButton gameStartButton;
    private javax.swing.JLabel hLabel;
    private javax.swing.JButton loadFileButton;
    private javax.swing.JButton loadFromXML;
    private javax.swing.JTextArea movesTextArea;
    private javax.swing.JButton saveFileButton;
    private javax.swing.JButton savePDFandHTML;
    private javax.swing.JButton saveToXML;
    private javax.swing.JScrollPane textAreaScrollPane;
    private javax.swing.JToolBar toolBar;
    // End of variables declaration//GEN-END:variables
}
