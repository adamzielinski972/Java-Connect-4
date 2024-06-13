package connectfour;

/**
 * A class to perform actions related to running the game. This
 * class creates objects of other classes and calls their methods
 * in order to run the game
 * 
 * @author Adam Zielinski
 */

public class ConnectFour {

    public static void main(String[] args) { //main method

        Board board = new Board(); //creates a new board object
        TextUI textUI = new TextUI(); //creates new textUI object
        
        while(true) { //asks if they want to load file or play a new game 
            textUI.getInput1();
            if (textUI.checkInput1(textUI.getLoadChoice())) { //makes sure input is valid
                break;
            }
        }

        if (textUI.getLoadChoice() == 1) { //if they want to load
            textUI.getFileName(); //gets file name
            textUI.loadFile(); //loads file
            textUI.parseString(); //parses file info
            board.addFileVals(textUI.getFileVals2()); //adds file info to a list
            textUI.printBoard(board.getBoard()); //prints loaded board
            System.out.println("Board loaded"); 
        }
        while(true) { //runs game loop
            textUI.getInput2();//asks for continue playing or save game
            if (textUI.checkInput2(textUI.getGameChoice())) { //checks input
                if (textUI.getGameChoice() == 1) { //if they want to save
                    textUI.getFileName();//get name of save file
                    textUI.createFile(); //create the file
                    textUI.writeToFile(board.getBoard()); //save file
                } else { //if they want to play
                    textUI.getInput3(); //ask for column
                    if (textUI.checkInput3(textUI.getColumnChoice())) {
                        if (textUI.checkFullColumn(board.getColumnVals())) {//makes sure column isn't full
                        
                            board.addCol(board.getPlayerTurn(),textUI.getColumnChoice()); //adds piece to column
                            textUI.printBoard(board.getBoard()); //print the new board
                            //checks win conditions
                            if (!board.checkHorizontalWin() || !board.checkVerticalWin() 
                            || !board.checkUpDiagonalWin() || !board.checkDownDiagonalWin()) {
                                textUI.printWinner(board.getPlayerTurn()); //if winner then print it
                                break;
                            }
                            board.switchPlayer(board.getPlayerTurn()); //switch whose turn it is
                        }
                    }
                }
                
            }
        } 
    } 
    
}
