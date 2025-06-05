package controllers.listeners;

import models.Database;
import models.Model;
import models.ScoreData;
import views.View;
import views.dialogs.ScoreBoardDialog;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;

public class MyScoreBoardListener implements ActionListener {
    private Model model;
    private View view;
    private JDialog dlgScoreBoard;

    public MyScoreBoardListener(Model model, View view) {
        this.model = model;
        this.view = view;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ArrayList<ScoreData> result;
        if(view.getRdoFile().isSelected()) {
            result = model.readFromFile();
            if(createTable(result)) {
                setupDlgScoreBoard();


            } else {
                JOptionPane.showMessageDialog(view, "Andmeid pole");
            }

        } else { // Andmebaas
            try(Database db = new Database(model)) {
                result = db.select(model.getBoardSize());
                if(!result.isEmpty() && createTableDb(result)) {
                    setupDlgScoreBoard();


                } else {
                    JOptionPane.showMessageDialog(view, "Andmebaasi tabel on tühi!");
                }
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }

    }

    private boolean createTableDb(ArrayList<ScoreData> result) {
        if(!result.isEmpty()) {
            String[][] data = new String[result.size()][5]; // 5 VEERUGA TABEL
            for(int i = 0; i < result.size(); i++) {
                data[i][0] = result.get(i).getName();
                data[i][1] = result.get(i).formatGameTime(result.get(i).getTime());
                data[i][2] = String.valueOf(result.get(i).getHits());
                data[i][3] = String.valueOf(result.get(i).getBoard());
                data[i][4] = result.get(i).getPlayedTime().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"));
            }
            // loome read-only TableModel (topeltklikk lahtris võimatu)
            DefaultTableModel tableModel = new DefaultTableModel(data, model.getColumnNames()) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false; //ei luba lahtri sisu muuta
                }
            };
            JTable table = new JTable(tableModel);

            //TODO Tabelil klikkimine
            table.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if(e.getClickCount() == 2 && !e.isConsumed()) {
                        e.consume();
                        int row = table.rowAtPoint(e.getPoint());
                        int col = table.columnAtPoint(e.getPoint());

                        StringBuilder rowData = new StringBuilder();
                        for(int i = 0; i < table.getColumnCount(); i++) {
                            rowData.append(table.getValueAt(row, i)).append(" | ");
                        }
                        JOptionPane.showMessageDialog(table, "Valitud rida:\n" + rowData);

                        //Ainult lahtri sisu
                        Object cellObject = table.getModel().getValueAt(row,col);
                        JOptionPane.showMessageDialog(table, "Valitud lahter:\n" + cellObject);


                    }

                }
            });



            //Teeme tabeli päise rasvaseks
            JTableHeader header = table.getTableHeader();
            Font headerFont = header.getFont().deriveFont(Font.BOLD);
            header.setFont(headerFont);

            int[] columnWidths = {100, 120, 80, 90, 150};
            for(int i = 0; i < columnWidths.length; i++) {
                table.getColumnModel().getColumn(i).setPreferredWidth(columnWidths[i]);
            }
            // Joondame alates teisest veerust paremale serva
            DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
            rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
            for(int i = 1; i < model.getColumnNames().length; i++) {
                table.getColumnModel().getColumn(i).setCellRenderer(rightRenderer);

            }
            dlgScoreBoard = new ScoreBoardDialog(view);
            JScrollPane scrollPane = new JScrollPane(table);
            dlgScoreBoard.add(scrollPane);
            dlgScoreBoard.setTitle("Edetabel Andmebaas");
            return true;

        }
        return false;
    }

    private boolean createTable(ArrayList<ScoreData> result) {
        if(!result.isEmpty()) {
            Collections.sort(result);
            //Loome 2 mõõtmelise stringide massiiivi
            String[][] data = new String[result.size()][5]; // 5 VEERUGA TABEL
            for(int i = 0; i < result.size(); i++) {
                data[i][0] = result.get(i).getName();
                data[i][1] = result.get(i).formatGameTime(result.get(i).getTime());
                data[i][2] = String.valueOf(result.get(i).getHits());
                data[i][3] = String.valueOf(result.get(i).getBoard());
                data[i][4] = result.get(i).getPlayedTime().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"));
            }

            JTable table = new JTable(data, model.getColumnNames());

            //määrame veergude laiused
            int[] columnWidths = {100, 80, 60, 80, 160};
            for(int i = 0; i < columnWidths.length; i++) {
                table.getColumnModel().getColumn(i).setPreferredWidth(columnWidths[i]);
            }

            //Loo edetabeli aken kerismisribaga
            dlgScoreBoard = new ScoreBoardDialog(view);
            dlgScoreBoard.add(new JScrollPane(table));
            dlgScoreBoard.setTitle("edetabel Failist");
            return true;

        }
        return false;
    }
    private void setupDlgScoreBoard() {
        dlgScoreBoard.setModal(true); //teise akna peal
        dlgScoreBoard.pack();
        dlgScoreBoard.setLocationRelativeTo(null); //Paigutame keset ekraani
        dlgScoreBoard.setVisible(true); //tee nähtavaks
    }
}
