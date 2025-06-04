package models;

import java.util.ArrayList;

public class Model {
    private int boardSize = 10; //Vaikimisi mängulaua suurus
    private ArrayList<GridData> gridData;
    private Game game; //Laevade info mängulaual

    public Model() {
        gridData = new ArrayList<>();
    }

    /**
     * Tagastab hiire kordinaatide järgi massiiivi indeksi ehk lahtri id
     * @param mouseX hiire x kordinaat
     * @param mouseY hiire y kordinaat
     * @return lahtri id
     */
    public int checkGridIndex(int mouseX, int mouseY) {
        int result = -1; //Viga
        int index = 0;
        for(GridData gd : gridData) {
            if(mouseX > gd.getX() && mouseX <= (gd.getX() + gd.getWidth()) && mouseY > gd.getY() && mouseY <= (gd.getY() + gd.getHeight())) {
                result = index;
            }
            index++;
        }
        return result;
    }

    /**
     * Tagastab mängulaua rea numbri saadud id põhjal (checkGridIndex)
     * @param id mängulaua lahtri id
     * @return mängulaua reanumber
     */
    public int getRowById(int id){
        if(id != -1) {
            return gridData.get(id).getRow();
        }
        return -1; //Viga
    }

    /**
     * Tagastab mängulaua veeru numbri saadud id põhjal (checkGridIndex)
     * @param id mängulaua id
     * @return mängulaua veeru number
     */
    public int getColById(int id){
        if(id != -1) {
            return gridData.get(id).getCol();
        }
        return -1; //Viga
    }

    public void setupNewGame(){
        game = new Game(boardSize);


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

    //SETTERID


    public void setBoardSize(int boardSize) {
        this.boardSize = boardSize;
    }

    public void setGridData(ArrayList<GridData> gridData) {
        this.gridData = gridData;
    }
}
