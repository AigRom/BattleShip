package controllers.listeners;

import models.GameTimer;
import models.Model;
import views.View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyNewGameListener implements ActionListener {
    private Model model;
    private View view;
    private GameTimer gameTimer;


    public MyNewGameListener(Model model, View view, GameTimer gameTimer) {
        this.model = model;
        this.view = view;
        this.gameTimer = gameTimer;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //System.out.println("Oota sellega, mäng pole valmis!");
        if(!gameTimer.isRunning()) { //Mäng ei käi
            new Thread(() -> {
                model.setupNewGame(); //teeb uue mängu
                model.getGame().setupGameBoard();
                model.getGame().showGameBoard();
                view.getLblShip().setText(model.getGame().getShipsCounter() + " / " + model.getGame().getShipsParts());
                SwingUtilities.invokeLater(() -> {
                    view.getBtnNewGame().setText("Katkesta mäng");
                    view.getBtnScoreBoard().setEnabled(false); //edetabeli nupp halliks, kui uus mäng algab
                    view.getCmbSize().setEnabled(false); //mängulaua suuruse muutmine deaktktiveeritud
                    gameTimer.start();
                });

            }).start();


        }
        else {
            gameTimer.stop();
            view.getBtnNewGame().setText("Uus mäng");
            view.getBtnScoreBoard().setEnabled(true); //edetabeli nupp aktiivseks tagasi
            view.getCmbSize().setEnabled(true); // mängulaua suuruse valik tagasi aktiivseks

        }
    }
}
