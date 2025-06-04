package models;

public class Area {
    int startRow;//ala ülemise rea indeks
    int endRow; // ala alumise rea indeks
    int startCol; // ala vasakpoolse veeru indeks
    int endCol;// ala parempoolse veeru indeks

    public Area(int startRow, int endRow, int startCol, int endCol) {
        this.startRow = startRow;
        this.endRow = endRow;
        this.startCol = startCol;
        this.endCol = endCol;
    }
}
