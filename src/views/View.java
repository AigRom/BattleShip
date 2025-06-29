package views;

import controllers.Controller;
import models.Model;
import views.panels.GameBoard;
import views.panels.InfoBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;

public class View extends JFrame {
    private Model model;
    private GameBoard gameBoard;
    private InfoBoard infoBoard;
    private JPanel scorePanel; // edetabeli paneel
    private JLayeredPane layeredPane; // kihiline paneel

    public View(Model model) {
        super("Laevade pommitamine");
        this.model = model;

        gameBoard = new GameBoard(model);
        infoBoard = new InfoBoard();

        // Loo kihid: mängulaud ja edetabel
        layeredPane = new JLayeredPane();
        layeredPane.setLayout(null); // käsitsi paigutus

        // Edetabeli paneel mängulaua peale
        scorePanel = new JPanel();
        scorePanel.setOpaque(false); // laseb tausta paista läbi
        scorePanel.setVisible(false);
        scorePanel.setLayout(new BorderLayout());

        Dimension boardSize = gameBoard.getPreferredSize();
        gameBoard.setBounds(0, 0, boardSize.width, boardSize.height);
        scorePanel.setBounds(0, 0, boardSize.width, boardSize.height);

        layeredPane.setPreferredSize(boardSize);
        layeredPane.add(gameBoard, Integer.valueOf(0));     // alumine kiht
        layeredPane.add(scorePanel, Integer.valueOf(1));    // pealmine kiht

        // Asetame kihid ja infoBoardi põhikonteinerisse
        JPanel container = new JPanel(new BorderLayout());
        container.add(layeredPane, BorderLayout.CENTER);
        container.add(infoBoard, BorderLayout.EAST);

        add(container);

        // Kui akna suurust muudetakse, uuendame ka mängulaua ja edetabeli suurust
        addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent e) {
                Dimension newSize = layeredPane.getSize();
                gameBoard.setBounds(0, 0, newSize.width, newSize.height);
                scorePanel.setBounds(0, 0, newSize.width, newSize.height);
                layeredPane.revalidate();
                layeredPane.repaint();
            }
        });

        pack();
        setMinimumSize(getSize());
    }

    public JLabel getLblMouseXY() { return infoBoard.getLblMouseXY(); }
    public JLabel getLblID() { return infoBoard.getLblID(); }
    public JLabel getLblRowCol() { return infoBoard.getLblRowCol(); }
    public JLabel getLblTime() { return infoBoard.getLblTime(); }
    public JLabel getLblShip() { return infoBoard.getLblShip(); }
    public JLabel getLblGameBoard() { return infoBoard.getLblGameBoard(); }

    public JComboBox<String> getCmbSize() { return infoBoard.getCmbSize(); }
    public JButton getBtnNewGame() { return infoBoard.getBtnNewGame(); }
    public JButton getBtnScoreBoard() { return infoBoard.getBtnScoreBoard(); }

    public JRadioButton getRdoFile() { return infoBoard.getRdoFile(); }
    public JRadioButton getRdoDb() { return infoBoard.getRdoDb(); }
    public JCheckBox getChkWhere() { return infoBoard.getChkWhere(); }

    public void registerGameBoardMouse(Controller controller) {
        gameBoard.addMouseListener(controller);
        gameBoard.addMouseMotionListener(controller);
    }

    public void registerComboBox(ItemListener itemListener) {
        infoBoard.getCmbSize().addItemListener(itemListener);
    }

    public void registerNewGameButton(ActionListener actionListener) {
        infoBoard.getBtnNewGame().addActionListener(actionListener);
    }

    public void registerScoreBoardButton(ActionListener actionListener) {
        infoBoard.getBtnScoreBoard().addActionListener(actionListener);
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public JPanel getScorePanel() {
        return scorePanel;
    }

    public void updateBoardSize() {
        Dimension newSize = gameBoard.getPreferredSize();
        gameBoard.setBounds(0, 0, newSize.width, newSize.height);
        scorePanel.setBounds(0, 0, newSize.width, newSize.height);
        layeredPane.setPreferredSize(newSize);
        layeredPane.revalidate();
        layeredPane.repaint();
        pack(); // vajadusel muudab akna suurust
    }
}
