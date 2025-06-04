package controllers.listeners;

import models.Model;
import views.View;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class MyComboBoxListener implements ItemListener {
    private Model model;
    private View view;



    public MyComboBoxListener(Model model, View view) {
        this.model = model;
        this.view = view;


    }

    @Override
    public void itemStateChanged(ItemEvent e) {
       //System.out.println(e.getItem()); //Test
        if (e.getStateChange() == ItemEvent.SELECTED) {
            //System.out.println(e.getItem()); //Test
            String number = e.getItem().toString(); // Võta väärtus stringina
            int size = Integer.parseInt(number); // Tee eelnev nr. string => täisarvuks
            view.getLblGameBoard().setText(String.format("%d x %d", size, size));
            //view.getLblGameBoard().setText(number + " x " + number);
            model.setBoardSize(size); //määrab mängulaua suuruse
            view.pack(); // Et suurus muutuks
            view.repaint(); //joonista uuesti

        }
    }
}
