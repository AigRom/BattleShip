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

    }
}
