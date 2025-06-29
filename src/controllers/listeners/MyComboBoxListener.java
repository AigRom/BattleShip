package controllers.listeners;

import models.Model;
import views.View;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class MyComboBoxListener implements ItemListener {
    private Model model;
    private View view;

    // Konstruktor: seob mudeli ja vaate
    public MyComboBoxListener(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        // Kontrollime, kas kasutaja valis uue väärtuse rippmenüüst
        if (e.getStateChange() == ItemEvent.SELECTED) {

            // Kui mingi mäng on olemas (olgu läbi või katkestatud), kustutame selle,
            // et mängulaud ei jääks ekraanile nähtavale
            if(model.getGame() != null) {
                model.setGame(null);
            }

            // Loeme valitud laua suuruse stringina ja teisendame täisarvuks
            String number = e.getItem().toString();
            int size = Integer.parseInt(number);

            // Uuendame infot kasutajaliideses (näiteks: 10 x 10)
            view.getLblGameBoard().setText(String.format("%d x %d", size, size));

            // Määrame uue mängulaua suuruse mudelis
            model.setBoardSize(size);

            // Kohandame GUI mõõdud ja joonistame uuesti
            view.pack();
            view.updateBoardSize();
            view.repaint();
        }
    }
}
