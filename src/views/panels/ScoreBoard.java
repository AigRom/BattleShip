package views.panels;

import javax.swing.*;
import java.awt.*;

public class ScoreBoard extends JPanel {

    public ScoreBoard(JTable table, JButton closeButton) {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Tabel kerimisribaga
        JScrollPane scrollPane = new JScrollPane(table);
        //scrollPane.setPreferredSize(new Dimension(520, 360));
        add(scrollPane, BorderLayout.CENTER);

        // Sulgemisnupp alla keskele
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnPanel.setOpaque(false);
        btnPanel.add(closeButton);

        add(btnPanel, BorderLayout.SOUTH);
    }

    public void clearContent() {
        removeAll();
        revalidate();
        repaint();
    }
}
