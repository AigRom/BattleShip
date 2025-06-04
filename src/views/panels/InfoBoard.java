package views.panels;

import javax.swing.*;
import java.awt.*;

public class InfoBoard extends JPanel {

    private JPanel pnlComponent = new JPanel(new GridBagLayout());
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
    private JRadioButton rdoFile; //info loetakse failist
    private JRadioButton rdoDb; //info loetakse andmebaasist
    private ButtonGroup btnGroup = new ButtonGroup(); //mõlemad rdo nupud on siin
    private JCheckBox chkWhere; //eraldi aknas linnuke





    public InfoBoard() {
        //setLayout(new FlowLayout(FlowLayout.LEFT)); //See on algne Layout
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(400, 188));
        setBackground(new Color(178, 241, 213));

        //sellele paneelile lähevad kõik komponendid
        pnlComponent.setBackground(new Color(230, 237, 213));

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
        setupRadioButtons();


        //Tühja osa täitmine mis on veniv
        gbc.gridx = 0;
        gbc.gridy = 12;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        JPanel flowPanel = new JPanel();
        flowPanel.setOpaque(false);
        pnlComponent.add(flowPanel, gbc);

        add(pnlComponent, BorderLayout.CENTER);

    }




    private void setupLine1() {
        // Esimese rea esimene veerg
        JLabel label = new JLabel("Hiir");
        label.setFont(fontBold);
        gbc.gridx = 0; //veerg
        gbc.gridy = 0; //rida
        pnlComponent.add(label, gbc);

        //esimese  rea teine veerg
        lblMouseXY = new JLabel("x = 0 & y = 0 ");
        lblMouseXY.setFont(fontNormal);
        gbc.gridx = 1;
        gbc.gridy = 0;
        pnlComponent.add(lblMouseXY, gbc);
    }

    private void setupLine2() {
        JLabel label = new JLabel("Lahtri ID");
        label.setFont(fontBold);
        gbc.gridx = 0;
        gbc.gridy = 1;
        pnlComponent.add(label, gbc);

        lblID = new JLabel("Teadmata");
        lblID.setFont(fontNormal);
        gbc.gridx = 1;
        gbc.gridy = 1;
        pnlComponent.add(lblID, gbc);
    }

    private void setupLine3() {
        JLabel label = new JLabel("Rida : Veerg");
        label.setFont(fontBold);
        gbc.gridx = 0;
        gbc.gridy = 2;
        pnlComponent.add(label, gbc);

        lblRowCol = new JLabel("0 : 0");
        lblRowCol.setFont(fontNormal);
        gbc.gridx = 1;
        gbc.gridy = 2;
        pnlComponent.add(lblRowCol, gbc);


    }

    private void setupLine4() {
        JLabel label = new JLabel("Mängu aeg");
        label.setFont(fontBold);
        gbc.gridx = 0;
        gbc.gridy = 3;
        pnlComponent.add(label, gbc);

        lblTime = new JLabel("00:00");
        lblTime.setFont(fontNormal);
        gbc.gridx = 1;
        gbc.gridy = 3;
        pnlComponent.add(lblTime, gbc);
    }

    private void setupLine5() {
        JLabel label = new JLabel("Laevad");
        label.setFont(fontBold);
        gbc.gridx = 0;
        gbc.gridy = 4;
        pnlComponent.add(label, gbc);

        lblShip = new JLabel("0 / 0");
        lblShip.setFont(fontNormal);
        gbc.gridx = 1;
        gbc.gridy = 4;
        pnlComponent.add(lblShip, gbc);

    }

    private void setupLine6() {
        JLabel label = new JLabel("Laua suurus");
        label.setFont(fontBold);
        gbc.gridx = 0;
        gbc.gridy = 5;
        pnlComponent.add(label, gbc);

        lblGameBoard = new JLabel("10 x 10");
        lblGameBoard.setFont(fontNormal);
        gbc.gridx = 1;
        gbc.gridy = 5;
        pnlComponent.add(lblGameBoard, gbc);

    }

    private void setupComboBox() {
        JLabel label = new JLabel("Vali laua suurus");
        label.setFont(fontBold);

        gbc.gridx = 0;
        gbc.gridy = 6;
        pnlComponent.add(label, gbc);

        cmbSize = new JComboBox<>(boardSizes);
        cmbSize.setFont(fontNormal);
        cmbSize.setPreferredSize(new Dimension(106, 28));
        gbc.gridx = 1;
        gbc.gridy = 6;
        pnlComponent.add(cmbSize, gbc);
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
        pnlComponent.add(label, gbc);

        btnNewGame = new JButton("Uus mäng");
        btnNewGame.setFont(fontNormal);
        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.gridheight = 1;
        pnlComponent.add(btnNewGame, gbc);

        btnScoreBoard = new JButton("Edetabel");
        btnScoreBoard.setFont(fontNormal);
        //btnScoreBoard.setPreferredSize(new Dimension(150, 28));
        gbc.gridx = 1;
        gbc.gridy = 8;
        pnlComponent.add(btnScoreBoard, gbc);


    }
    private void setupRadioButtons() {
        JLabel label = new JLabel("Edetabeli Sisu");
        label.setFont(fontBold);
        label.setPreferredSize(new Dimension(106, 28));
        gbc.gridx = 0;
        gbc.gridy = 9;
        pnlComponent.add(label, gbc);


        rdoFile = new JRadioButton("Fail");
        rdoFile.setFont(fontNormal);
        rdoFile.setSelected(true);
        rdoFile.setBackground(new Color(230, 237, 213));
        gbc.gridx = 1;
        gbc.gridy = 9;
        pnlComponent.add(rdoFile, gbc);

        rdoDb = new JRadioButton("Andmebaas");
        rdoDb.setFont(fontNormal);
        rdoDb.setBackground(new Color(230, 237, 213));
        gbc.gridx = 1;
        gbc.gridy = 10;
        pnlComponent.add(rdoDb, gbc);



        chkWhere = new JCheckBox("Eraldi aknas");
        chkWhere.setFont(fontNormal);
        chkWhere.setBackground(new Color(230, 237, 213));
        chkWhere.setSelected(true);
        gbc.gridx = 1;
        gbc.gridy = 11;
        pnlComponent.add(chkWhere, gbc);

        btnGroup.add(rdoFile);
        btnGroup.add(rdoDb);




    }
    //GETTERS


    public JPanel getPnlComponent() {
        return pnlComponent;


    }
}
