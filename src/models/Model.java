package models;

import java.awt.*;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Model {
    private int boardSize = 10; //Vaikimisi mängulaua suurus
    private ArrayList<GridData> gridData;
    private Game game; //Laevade info mängulaual

    // Edetabeli failiga seotud tegemised'
    private String scoreFile = "scores.txt";
    private String[] columnNames = new String[]{"nimi", "Aeg", "klikke", "Laua suurus", "Mängu aeg"};
    // Edetabeli andmebaasiga seotud muutujad
    private String scoreDatabase = "scores.db";
    private String scoreTable = "scores";

    public Model() {
        gridData = new ArrayList<>();
    }

    /**
     * Tagastab hiire kordinaatide järgi massiiivi indeksi ehk lahtri id
     *
     * @param mouseX hiire x kordinaat
     * @param mouseY hiire y kordinaat
     * @return lahtri id
     */
    public int checkGridIndex(int mouseX, int mouseY) {
        int result = -1; //Viga
        int index = 0;
        for (GridData gd : gridData) {
            if (mouseX > gd.getX() && mouseX <= (gd.getX() + gd.getWidth()) && mouseY > gd.getY() && mouseY <= (gd.getY() + gd.getHeight())) {
                result = index;
            }
            index++;
        }
        return result;
    }

    /**
     * Tagastab mängulaua rea numbri saadud id põhjal (checkGridIndex)
     *
     * @param id mängulaua lahtri id
     * @return mängulaua reanumber
     */
    public int getRowById(int id) {
        if (id != -1) {
            return gridData.get(id).getRow();
        }
        return -1; //Viga
    }

    /**
     * Tagastab mängulaua veeru numbri saadud id põhjal (checkGridIndex)
     *
     * @param id mängulaua id
     * @return mängulaua veeru number
     */
    public int getColById(int id) {
        if (id != -1) {
            return gridData.get(id).getCol();
        }
        return -1; //Viga
    }

    public void setupNewGame() {
        game = new Game(boardSize);


    }

    public void drawUserBoard(Graphics g) {
        ArrayList<GridData> gdList = getGridData(); //see loodi laua joonistamisel
        int[][] matrix = game.getBoardMatrix(); //siin on laevade info 0, 1-5, 7 , 8

        for (GridData gd : gdList) {
            int row = gd.getRow();
            int col = gd.getCol();
            int cellvalue = matrix[row][col]; //väärtus. 0, 1-5, 7 ,8

            //Määrame värvi ja suuruse vastavalt lahtri väärtusest
            Color color = null;
            int padding = 0;

            switch (cellvalue) {
                case 0:
                    color = new Color(0, 190, 255);
                    break;
                case 7:
                    color = Color.GREEN;
                    break;
                case 8:
                    color = Color.RED;
                    padding = 3;
                    break;
                default:
                    if (cellvalue >= 1 && cellvalue <= 5) {
                        //kommenteeri välja kui ei soovi mängu ajal laevu näha
                        color = Color.YELLOW;
                    }
            }
            // Kui värv on määratud joonista ruut.
            if (color != null) {
                g.setColor(color);// määra värv
                g.fillRect(gd.getX() + padding, gd.getY() + padding, gd.getWidth() - 2 * padding, gd.getHeight() - 2 * padding);
            }
        }
    }

    public boolean checkFileExistsAndContent() {
        File file = new File(scoreFile);
        if (!file.exists()) {
            return false;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(scoreFile))) {
            String line = br.readLine();
            if (line == null) {
                return false; //ridu pole üldse
            }
            String[] columns = line.split(";");
            return columns.length == columnNames.length; // LIHTSUSTATUD IF LAUSE

        } catch (IOException e) {
            //throw new RuntimeException(e); //kui faili ei ole rakendus lõpetab töö
            return false;
        }
    }

    /**
     * edetabeli faili sisu loetakse massiiivi ja tagastatakse
     *
     * @return list (edetabeli info)
     */
    public ArrayList<ScoreData> readFromFile() {
        ArrayList<ScoreData> scoreData = new ArrayList<>();
        File file = new File(scoreFile);
        if (file.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(scoreFile))) {
                int lineNumber = 0;
                for (String line; (line = br.readLine()) != null; ) {
                    if (lineNumber > 0) {
                        String[] columns = line.split(";");
                        if (Integer.parseInt(columns[3]) == boardSize) {
                            String name = columns[0];
                            int gameTime = Integer.parseInt(columns[1]);
                            int clicks = Integer.parseInt(columns[2]);
                            int board = Integer.parseInt(columns[3]);
                            LocalDateTime played = LocalDateTime.parse(columns[4], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                            scoreData.add(new ScoreData(name, gameTime, clicks, board, played));
                        }
                    }
                    lineNumber++;
                }


            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return scoreData;//Tagasta sisu

    }




    //GETTERID


    public Game getGame() {
        return game;
    }

    public int getBoardSize() {
        return boardSize;
    }

    public ArrayList<GridData> getGridData() {
        return gridData;
    }

    public String getScoreFile() {
        return scoreFile;
    }

    public String[] getColumnNames() {
        return columnNames;
    }

    public String getScoreDatabase() {
        return scoreDatabase;
    }

    public String getScoreTable() {
        return scoreTable;
    }
    //SETTERID


    public void setBoardSize(int boardSize) {
        this.boardSize = boardSize;
    }

    public void setGridData(ArrayList<GridData> gridData) {
        this.gridData = gridData;
    }


}
