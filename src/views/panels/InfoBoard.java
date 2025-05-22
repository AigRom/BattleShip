package views.panels;

import javax.swing.*;
import java.awt.*;

public class InfoBoard extends JPanel {

    private JPanel pnlcomponent = new JPanel(new GridBagLayout());
    private GridBagConstraints gbc = new GridBagConstraints();

    //Kaks kirja stiili
    private Font fontBold = new Font("Verdana", Font.BOLD, 14);
    private Font fontNormal = new Font("Verdana", Font.PLAIN, 14);

    //Võimalikud mängulaua suurused
    private String[] boardSizes = {"10", "11", "12", "13", "14", "15"};

    // Defineerime sildi (Label) muutujad

    private JLabel lblMouseXY;
    private JLabel lblID;
    private JLabel lblRowCol;
    private JLabel lblTime;
    private JLabel lblShip;
    private JLabel lblGameBoard;

    private JComboBox<String> cmbSize;

    private JButton btnNewGame;
    private JButton btnScoreBoard;

    //TODO Edetabeliga seotud asjad


    public InfoBoard() {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setPreferredSize(new Dimension(400, 188));
        setBackground(new Color(178, 241, 213));

        //sellele paneelile lähevad kõik komponendid
        pnlcomponent.setBackground(new Color(230, 237, 213));

        gbc.anchor = GridBagConstraints.WEST; //=17 joondamine vasakule
        gbc.insets = new Insets(2, 2, 2, 2); // Ümber teksti 2 px tühjust

        setupLine1();
        setupLine2();
        setupLine3();
        setupLine4();
        setupLine5();
        setupLine6();
        setupComboBox();
        setupButtons();


        add(pnlcomponent);
    }


    private void setupLine1() {
        // Esimese rea esimene veerg
        JLabel label = new JLabel("Hiir");
        label.setFont(fontBold);
        gbc.gridx = 0; //veerg
        gbc.gridy = 0; //rida
        pnlcomponent.add(label, gbc);

        //esimese  rea teine veerg
        lblMouseXY = new JLabel("x = 0 & y = 0 ");
        lblMouseXY.setFont(fontNormal);
        gbc.gridx = 1;
        gbc.gridy = 0;
        pnlcomponent.add(lblMouseXY, gbc);
    }

    private void setupLine2() {
        JLabel label = new JLabel("Lahtri ID");
        label.setFont(fontBold);
        gbc.gridx = 0;
        gbc.gridy = 1;
        pnlcomponent.add(label, gbc);

        lblID = new JLabel("Teadmata");
        lblID.setFont(fontNormal);
        gbc.gridx = 1;
        gbc.gridy = 1;
        pnlcomponent.add(lblID, gbc);
    }

    private void setupLine3() {
        JLabel label = new JLabel("Rida : Veerg");
        label.setFont(fontBold);
        gbc.gridx = 0;
        gbc.gridy = 2;
        pnlcomponent.add(label, gbc);

        lblRowCol = new JLabel("0 : 0");
        lblRowCol.setFont(fontNormal);
        gbc.gridx = 1;
        gbc.gridy = 2;
        pnlcomponent.add(lblRowCol, gbc);


    }

    private void setupLine4() {
        JLabel label = new JLabel("Mängu aeg");
        label.setFont(fontBold);
        gbc.gridx = 0;
        gbc.gridy = 3;
        pnlcomponent.add(label, gbc);

        lblTime = new JLabel("00:00");
        lblTime.setFont(fontNormal);
        gbc.gridx = 1;
        gbc.gridy = 3;
        pnlcomponent.add(lblTime, gbc);
    }

    private void setupLine5() {
        JLabel label = new JLabel("Laevad");
        label.setFont(fontBold);
        gbc.gridx = 0;
        gbc.gridy = 4;
        pnlcomponent.add(label, gbc);

        lblShip = new JLabel("0 / 0");
        lblShip.setFont(fontNormal);
        gbc.gridx = 1;
        gbc.gridy = 4;
        pnlcomponent.add(lblShip, gbc);

    }

    private void setupLine6() {
        JLabel label = new JLabel("Laua suurus");
        label.setFont(fontBold);
        gbc.gridx = 0;
        gbc.gridy = 5;
        pnlcomponent.add(label, gbc);

        lblGameBoard = new JLabel("10 x 10");
        lblGameBoard.setFont(fontNormal);
        gbc.gridx = 1;
        gbc.gridy = 5;
        pnlcomponent.add(lblGameBoard, gbc);

    }

    private void setupComboBox() {
        JLabel label = new JLabel("Vali laua suurus");
        label.setFont(fontBold);

        gbc.gridx = 0;
        gbc.gridy = 6;
        pnlcomponent.add(label, gbc);

        cmbSize = new JComboBox<>(boardSizes);
        cmbSize.setFont(fontNormal);
        cmbSize.setPreferredSize(new Dimension(106, 28));
        gbc.gridx = 1;
        gbc.gridy = 6;
        pnlcomponent.add(cmbSize, gbc);
    }

    private void setupButtons() {
        JLabel label = new JLabel("Nupud");
        label.setFont(fontBold);

        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridheight = 2;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.NONE;
        pnlcomponent.add(label, gbc);

        btnNewGame = new JButton("Uus mäng");
        btnNewGame.setFont(fontNormal);
        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.gridheight = 1;
        pnlcomponent.add(btnNewGame, gbc);

        btnScoreBoard = new JButton("Edetabel");
        btnScoreBoard.setFont(fontNormal);
        btnScoreBoard.setPreferredSize(new Dimension(106, 28));
        gbc.gridx = 1;
        gbc.gridy = 8;
        pnlcomponent.add(btnScoreBoard, gbc);


    }

}
