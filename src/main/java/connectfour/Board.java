package connectfour;
import java.util.ArrayList;
import java.util.Collections;

/**
 * A class to perform actions related to the connect four board such as
 * updating the board based on user input or file input and checking
 * win conditions
 * @author Adam Zielinski
 */

public class Board {

    private ArrayList<Character> gameBoard;
    private ArrayList<Integer> posToIndexList;
    private ArrayList<Integer> positionsList;
    private ArrayList<Integer> columnVals;
    private char playerTurn;

    /**
     * Method to construct attributes of board class
     * @param None
     * @return None
     */
    public Board() {
        gameBoard =  new ArrayList<Character>();
        posToIndexList =  new ArrayList<Integer>();
        positionsList = new ArrayList<Integer>();
        columnVals =  new ArrayList<Integer>();
        Collections.addAll(gameBoard,
        '|',' ','|',' ','|',' ','|',' ','|',' ','|',' ','|',' ','|','\n',
        '|',' ','|',' ','|',' ','|',' ','|',' ','|',' ','|',' ','|','\n',
        '|',' ','|',' ','|',' ','|',' ','|',' ','|',' ','|',' ','|','\n',
        '|',' ','|',' ','|',' ','|',' ','|',' ','|',' ','|',' ','|','\n',
        '|',' ','|',' ','|',' ','|',' ','|',' ','|',' ','|',' ','|','\n',
        '|',' ','|',' ','|',' ','|',' ','|',' ','|',' ','|',' ','|','\n');
        Collections.addAll(posToIndexList,
        1, 3, 5, 7, 9, 11, 13, 
        17, 19, 21, 23, 25, 27, 29, 
        33, 35, 37, 39, 41, 43, 45,
        49, 51, 53, 55, 57, 59, 61,
        65, 67, 69, 71, 73, 75, 77,
        81, 83, 85, 87, 89, 91, 93);
        Collections.addAll(positionsList, 81,83,85,87,89,91,93);
        Collections.addAll(columnVals, 0,0,0,0,0,0,0);
        playerTurn = 'R';
    }

    /**
     * Method to get the gameBoard list
     * @param None
     * @return gameBoard
     */
    public ArrayList<Character> getBoard() {
        return gameBoard;
    }

    /**
     * Method to get the posToIndexList
     * @param None
     * @return posToIndexList
     */
    public ArrayList<Integer> getPosToIndex() {
        return posToIndexList;
    }

    /**
     * Method to get the columnVals list
     * @param None
     * @return columnVals
     */
    public ArrayList<Integer> getColumnVals() {
        return columnVals;
    }

    /**
     * Method to get the playerTurn
     * @param None
     * @return playerTurn
     */
    public char getPlayerTurn() {   
        return playerTurn;
    }

    /**
     * Method to add populate the board based on the values
     * from the loaded file
     * @param fileVals
     * @return None
     */
    public void addFileVals(ArrayList<String> fileVals) {
        int i;
        int count;
        count = 0;
        
        for (i = 0; i < 42; i++) { //runs through each element from loaded file
            if (fileVals.get(count).equals("1")) { //adds a R to the list if 1 is found
                gameBoard.set(posToIndexList.get(count), 'R');
                count++; //moves index down
            } else if (fileVals.get(count).equals("2")) { //adds a Y to the list if 2 is found
                gameBoard.set(posToIndexList.get(count), 'Y');
                count++;
            } else {
                count++;
            }
        }
    }

    /**
     * Method to switch whose turn it is to play
     * @param turn
     * @return None
     */
    public void switchPlayer(char turn) { //
        
        if (turn == 'R') { //if R just played then switch to Y
            turn = 'Y';
        } else { //if Y just played then switch to R
            turn = 'R';
        }
        playerTurn = turn;
    }

    /**
     * Method to add a piece to the board 
     * @param turn
     * @param column
     * @return None
     */
    public void addCol(char turn, int column) {
        gameBoard.set(positionsList.get(column-1),turn); //adds piece to the desired column
        positionsList.set(column-1, positionsList.get(column-1) - 16); //moves index of that column to the next row up
        columnVals.set(column-1, columnVals.get(column-1) + 1); //adds 1 to the number of pieces in that column
    }

    /**
     * Method to check if there are 4 pieces in a row horizontally
     * @param None
     * @return true/false
     */
    public boolean checkHorizontalWin() {
        int i;
        int j;

        for (i = 0; i < 6; i++) { //checks each row
            for (j = 0; j < 4; j++) { //checks 4 positions for each row
                //checks and moves index according to the for loop
                if (gameBoard.get(81 - (i*16) + (j*2)) == playerTurn 
                    && gameBoard.get(83 - (i*16) + (j*2)) == playerTurn 
                    && gameBoard.get(85 - (i*16) + (j*2)) == playerTurn 
                    && gameBoard.get(87 - (i*16) + (j*2)) == playerTurn) {
                        return false;
                    }
            }
        }
        return true;
    }

    /**
     * Method to check if there are 4 pieces in a row vertically
     * @param None
     * @return true/false
     */
    public boolean checkVerticalWin() {
        int i;
        int j;

        for (i = 0; i < 7; i++) { //checks 7 columns
            for (j = 0; j < 3; j++) { //checks 3 options for each column
            
                if (gameBoard.get(81 + (i*2) - (j*16)) == playerTurn 
                    && gameBoard.get(65 + (i*2) - (j*16)) == playerTurn 
                    && gameBoard.get(49 + (i*2) - (j*16)) == playerTurn 
                    && gameBoard.get(33 + (i*2) - (j*16)) == playerTurn) {
                        return false;
                    }
            }
        }
        return true;
    }

    /**
     * Method to check if there are 4 pieces in a row diagonally
     * going from bottom left to top right
     * @param None
     * @return true/false
     */
    public boolean checkUpDiagonalWin() {
        int i;

        //checks first 4 options
        for (i = 0; i < 4; i++) {
            if (gameBoard.get(49 + (i*2)) == playerTurn 
            && gameBoard.get(35 + (i*2)) == playerTurn 
            && gameBoard.get(21 + (i*2)) == playerTurn 
            && gameBoard.get(7 + (i*2)) == playerTurn) {
                return false;
            }
        }

        //checks second 4 options
        for (i = 0; i < 4; i++) {
            if (gameBoard.get(65 + (i*2)) == playerTurn 
            && gameBoard.get(51 + (i*2)) == playerTurn 
            && gameBoard.get(37 + (i*2)) == playerTurn 
            && gameBoard.get(23 + (i*2)) == playerTurn) {
                return false;
            }
        }

        //checks last 4 options
        for (i = 0; i < 4; i++) {
            if (gameBoard.get(81 + (i*2)) == playerTurn 
            && gameBoard.get(67 + (i*2)) == playerTurn 
            && gameBoard.get(53 + (i*2)) == playerTurn 
            && gameBoard.get(39 + (i*2)) == playerTurn) {
                return false;
            }
        }
        return true;
    }

    /**
     * Method to check if there are 4 pieces in a row diagonally
     * going from top left to bottom right
     * @param None
     * @return true/false
     */
    public boolean checkDownDiagonalWin() {
        int i;

        for (i = 0; i < 4; i++) {
            if (gameBoard.get(1 + (i*2)) == playerTurn 
            && gameBoard.get(19 + (i*2)) == playerTurn 
            && gameBoard.get(37 + (i*2)) == playerTurn 
            && gameBoard.get(55 + (i*2)) == playerTurn) {
                return false;
            }
        }

        for (i = 0; i < 4; i++) {
            if (gameBoard.get(17 + (i*2)) == playerTurn 
            && gameBoard.get(35 + (i*2)) == playerTurn 
            && gameBoard.get(53 + (i*2)) == playerTurn 
            && gameBoard.get(71 + (i*2)) == playerTurn) {
                return false;
            }
        }

        for (i = 0; i < 4; i++) {
            if (gameBoard.get(33 + (i*2)) == playerTurn 
            && gameBoard.get(51 + (i*2)) == playerTurn 
            && gameBoard.get(69 + (i*2)) == playerTurn 
            && gameBoard.get(87 + (i*2)) == playerTurn) {
                return false;
            }
        }
        return true;
    }
}
