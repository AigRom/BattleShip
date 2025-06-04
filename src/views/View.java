package views;

import models.Model;
import views.panels.GameBoard;
import views.panels.InfoBoard;

import javax.swing.*;
import java.awt.*;

public class View  extends JFrame {
    private Model model;
    private GameBoard gameBoard; //see on mängulaud
    private InfoBoard infoBoard;







    public View(Model model) {
        super("Laevade pommitamine");
        this.model = model;

        gameBoard = new GameBoard(model);//Mängulaua loomine
        infoBoard = new InfoBoard(); // loo teadete tahvel

        JPanel container = new JPanel(new BorderLayout());

        container.add(gameBoard, BorderLayout.CENTER); //Mängulaud ujuvale osale
        container.add(infoBoard, BorderLayout.EAST); //teadete tahvel vasakule serva

        add(container);
        //Test Farame ja Panel managerid
        System.out.println("JFrame:" + this.getLayout());
        System.out.println("container    " + container.getLayout());
        System.out.println("GameBoard    " + gameBoard.getLayout());
        System.out.println("InfoBoard    " + infoBoard.getLayout());
        System.out.println("pnlComponents" + infoBoard.getPnlComponent().getLayout());
//        JFrame:java.awt.BorderLayout[hgap=0,vgap=0]
//        containerjava.awt.BorderLayout[hgap=0,vgap=0]
//        GameBoardjava.awt.FlowLayout[hgap=5,vgap=5,align=center]
//        InfoBoardjava.awt.FlowLayout[hgap=5,vgap=5,align=left]
//        pnlComponentsjava.awt.GridBagLayout
    }
}
