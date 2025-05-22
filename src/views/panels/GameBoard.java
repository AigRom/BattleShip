package views.panels;

import models.GridData;
import models.Model;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameBoard extends JPanel {
    private Model model;
    private int startX = 5;
    private int startY = 5;
    private int width = 30; //Ruudu laius
    private int height = 30; //Ruudu kõrgus
    private String[] alphabet = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O"};

    public GameBoard(Model model) {
        this.model = model;
        setBackground(new Color(86, 172, 205));

    }

    @Override
    public Dimension getPreferredSize() {
        int w = (width * model.getBoardSize()) + width + (2 * startX);
        int h = (height * model.getBoardSize()) + height + (2 * startY);
        return new Dimension(w, h);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_DEFAULT);

        g.setFont(new Font("Verdana", Font.BOLD, 14)); //kirja suurus mängulaual (ei soovita muuta!!!)

        //Tähestik koos ruutudega
        drawColumnAlphabet(g);

        //Vasakule serva numbrid ruutudega
        drawRowColumn(g);

        //Puuduv osa mängulauast
        drawGameGrid(g);


    }

    private void drawGameGrid(Graphics g) {
        ArrayList<GridData> matrix = new ArrayList<>(); //Tühaj maatriksi loomine
        g.setColor(Color.black);

        int x = startX +width;
        int y = startY +height;
        int i = 1;

        for(int r = 0; r < model.getBoardSize(); r++) { //rida
            for(int c = 0; c < model.getBoardSize(); c++) { //veerg
                g.drawRect(x,y,width,height); // Joonistab ühe ruudu
                matrix.add(new GridData(r, c, x, y, width, height));
                x += width;
            }
            //j'rgmise rea seaded
            y = (startY + height) + (height * i) ;
            i += 1;
            x = startX + width;
        }
        model.setGridData(matrix);





    }

    private void drawRowColumn(Graphics g) {
        int i = 1; // esimene number mänguväljal
        g.setColor(Color.BLACK);

        for (int y = startY + height; y < (height * model.getBoardSize()) + height; y += height) {
            g.drawRect(startX, y, width, height);
            if(i < 10) {
                g.drawString(String.valueOf(i), startX + (width / 2) - 5, y + 2 * (startY + startY));

            } else {
                g.drawString(String.valueOf(i), startX + (width / 2) - 10, y + 2 * (startY + startY));

            }
            i++;
        }


    }

    private void drawColumnAlphabet(Graphics g) {
        int i = 0; //Tähestiku massiivi esimese tähe indeks A=0, B=1 jne
        g.setColor(Color.WHITE); //tähestiku joonistamise värv

        for (int x = startX; x <= (width * model.getBoardSize()) + width; x += width) {
            g.drawRect(x, startY, width, height); //joonista ruut 30x30
            if (x > startX) {
                g.drawString(alphabet[i], x + (width / 2) - 5, 2 * (startY + startY) + 5);
                i++; //Suurendab tähe indeksit ühe võrra
            }
        }
    }
}
