package models;

import java.util.Random;
import java.util.stream.IntStream;

public class Game {
    private int boardSize; // mängulaeva suurus vaikimisi 10x10
    private int [][] boardMatrix; // mängulaual asuvad laevad
    private Random random = new Random();
    private int[] ships = {4, 3, 3, 2, 2, 2, 1}; //laeva pikkused(US)
    //private int[] ships = {4, 3, 3, 2, 2, 2, 1, 1, 1, 1}; //(EE)
    //private int[] ships = {5,4, 4, 3, 3, 3, 2, 2, 2, 2, 1, 1, 1, 1, 1}; //(EE++)
    private int shipsCounter = 0;
    private int clickCounter = 0;

    public Game(int boardSize) {
        this.boardSize = boardSize;
        this.boardMatrix = new int[boardSize][boardSize];

    }


    /**
     * näitab konsoolis mängulaua sisu
     */
    public void showGameBoard() {
        System.out.println(); //tühi rida
        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {
                System.out.print(boardMatrix[row][col] + " ");
            }
            System.out.println(); //Veeru lõpus uuele reale
        }
    }

    public void setupGameBoard() {
        boardMatrix = new int[boardSize][boardSize];
        int shipsTotal = ships.length;
        int shipsPlaced = 0;
        //TODO laevade järjekorra segamine

        while (shipsPlaced < shipsTotal) {
            int length = ships[shipsPlaced]; //millis laeva paigutada (laeva pikkus)
            boolean placed = false; //laeva pole paigutatud

            // Valime juhusliku alguspunkti

            int startRow = random.nextInt(boardSize);
            int startCol = random.nextInt(boardSize);

            //Käime kogu laua läbi alates sellest punktist


            outerLoop: //Lihtsalt silt (label) ehk nimi for-loopile
            for (int rOffset = 0; rOffset < boardSize; rOffset++) {
                int r = (startRow + rOffset) % boardSize;
                for (int cOffset = 0; cOffset < boardSize; cOffset++) {
                    int c = (startCol + cOffset) % boardSize;

                    boolean vertical = random.nextBoolean(); //Määrame juhusliku suuna /true = vertical
                    if(tryPlaceShip(r, c, length, vertical)|| tryPlaceShip(r, c, length, !vertical)) {
                        placed = true; //laev paigutatud
                        break outerLoop;//katkestab for loop kordused
                    }
                }
            }
            if(placed) {
                shipsPlaced++; //Järgmine laev

            } else { // Kui ei leitud sobivat kohta
                setupGameBoard(); // Ise enda välja kutsumine
                return;

            }
        }
        // eemaldame ajutised kaitsetsoonid (9-d), jättes alles ainult laevad (1-4) ja tühjad veekohad
        replaceNineToZero();

    }

    private void replaceNineToZero() {
        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {
                if (boardMatrix[row][col] == 9) {
                    boardMatrix[row][col] = 0;
                }
            }
        }
    }

    private boolean tryPlaceShip(int row, int col, int length, boolean vertical) {
        //Kontrolli kas laev üldse mahub mängulauale
        if(vertical && row + length > boardSize) return false;
        if(!vertical && col + length > boardSize) return false;

        //kontrolli kas vesi on vaba
        if(!canPlaceShip(row, col, length, vertical)) return false;

        //Kirjutame laeva mänguväljale
        for(int i = 0; i < length; i++) {
            int r = vertical? row +i : row;
            int c = vertical? col : col + i; // sama veeru pikkus
            boardMatrix[r][c] = length; //määrame laeva lahtrisse selle pikkuse
        }


        //Määrame ümber laeva kaitse tsooni
        makeSurrounding(row, col, length, vertical);
        return true;
    }

    private void makeSurrounding(int row, int col, int length, boolean vertical) {
        Area area = getShipSurroundingArea(row, col, length, vertical);
        for(int r = area.startRow; r <= area.endRow; r++) {
            for(int c = area.startCol; c <= area.endCol; c++) {
                if(boardMatrix[r][c] == 0) {
                    boardMatrix[r][c] = 9;
                }
            }
        }
    }

    private boolean canPlaceShip(int row, int col, int length, boolean vertical) {
        Area area = getShipSurroundingArea(row, col, length, vertical);// Saame laeva ümbritseva ala

        // kontrollime et kuskil pole tühjust (0), katkestame
        for(int r = area.startRow; r <= area.endRow; r++) {
            for(int c = area.startCol; c <= area.endCol; c++) {
                if(boardMatrix[r][c] > 0 && boardMatrix[r][c] <= 5) return false;
            }
        }
        return true;
    }

    private Area getShipSurroundingArea(int row, int col, int length, boolean vertical) {
        // Arvutame ümbritseva ala piirid hoides neid mängulaua piires
        int startRow = Math.max(0, row - 1);
        int endRow = Math.min(boardSize - 1, vertical ? row + length : row + 1);
        int startCol = Math.max(0, col - 1);
        int endCol = Math.min(boardSize - 1, vertical ? col + 1 : col + length);
        return new Area(startRow, endRow, startCol, endCol);
    }

    /**
     * selles lahtris klikkis kasutaja hiirega, kas sai pihta või läks mööda
     * @param row rida
     * @param col   veerg
     * @param what millega tegu (7 või 8)
     */
    public void setUserClick(int row, int col, int what) {
        if(what == 7) {
            boardMatrix[row][col] = 7; // pihtas
        } else if (what == 8){
            boardMatrix[row][col] = 8; // möödas
        }
    }

    //Getters


    public int[][] getBoardMatrix() {
        return boardMatrix;
    }

    public int getShipsCounter() {
        return shipsCounter;
    }

    public int getClickCounter() {
        return clickCounter;
    }

    public int getShipsParts() {
        return IntStream.of(ships).sum();
    }

    /**
     * Kas mäng on läbi
     * @return true kui on läbi ja false kui ei ole läbi
     */
    public boolean isGameOver() {
        return getShipsCounter() == getShipsParts();
    }

    //SETTERS

    /**
     * Suurendab leitud laevade arvu, etteantud väärtuse võrra
     * @param shipsCounter etteantud väärtuys (1)
     */
    public void setShipsCounter(int shipsCounter) {
        this.shipsCounter += shipsCounter;
    }

    public void setClickCounter(int clickCounter) {
        this.clickCounter += clickCounter;
    }
}
