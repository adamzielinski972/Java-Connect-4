package connectfour;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileWriter;

/**
 * A class to perform actions related to user input such as asking if
 * the user wants to save, load, or continue playing. Also deals with
 * printing to terminal, ex. printing the game board
 * 
 * @author Adam Zielinski
 */

public class TextUI {   
    private int loadChoice;
    private int gameChoice;
    private Scanner input;
    private int columnChoice;
    private String fileName;
    private ArrayList<String> fileVals;
    private ArrayList<String> fileVals2;

    public TextUI() {
        loadChoice = 0;
        gameChoice = 0;
        input = new Scanner(System.in);
        columnChoice = 0;
        fileName  = "";
        fileVals = new ArrayList<String>();
        fileVals2 = new ArrayList<String>();
    }

    public int getGameChoice() {
        return gameChoice;
    }

    public int getColumnChoice() {
        return columnChoice;
    }

    public ArrayList<String> getFileVals2() {
        return fileVals2;
    }

    public int getLoadChoice() {
        return loadChoice;
    }
 
    public void printBoard(ArrayList<Character> board) { 
        for (Character i : board){ //runs through each element in the board ArrayList and prints it
            System.out.print(i);
        }
        System.out.print("\n"); //prints a newline at the end to space out code
    }

    public void getInput1() {
        System.out.print("Press 1 to load game or 2 to play a new one: "); //asks for input
        loadChoice = input.nextInt(); //gets input and assigns it to variable
        input.nextLine();//removes newline character
    }

    public boolean checkInput1(int choice) {
        if (choice == 1 || choice == 2) { //makes sure input is valid
           
            return true;
        }
        System.out.println("Please enter either 1 or 2");
        return false;
    }

    public void loadFile() {
        try {
            File newFile = new File(fileName); //creates new file variable
            Scanner newScanner =  new Scanner(newFile);
            while (newScanner.hasNextLine()) { //scans entire file line by line
                String data = newScanner.next(); //grabs line
                fileVals.add(data); //adds it to arraylist
            }
            newScanner.close(); //closes scanner
        } catch (FileNotFoundException e) { //throws exception if file cannot be loaded
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void parseString() {
        int i;  
        String tempString;

        for(i = 0; i < 6; i++) { //goes through each line in file
            tempString = fileVals.get(i); //grabs line
            String[] string1 = tempString.split(","); //adds each element in betweem commas to list
            for (String j: string1) { //runs through list of values
                fileVals2.add(j); //adds them to arraylist
            }
        }
    }

    public void getInput2() {
        System.out.print("Press 1 to save game or 2 to play: "); //asks for input
        gameChoice = input.nextInt(); //gets input and assigns it to variable  
        input.nextLine();
    }

    public boolean checkInput2(int choice) {
        if (choice == 1 || choice == 2) {   
            return true;
        }   
        System.out.println("Please enter either 1 or 2");
        return false;
    }

    public void getFileName() {
        System.out.print("Enter the name of the file: ");
        fileName = input.nextLine();
    }

    public boolean createFile() {
        try {
            File newFile = new File(fileName); //creates variable
            if (!newFile.createNewFile()) { //creates file
                System.out.println("File already exists."); //checks if file was already created
                return false;
            }
        } catch (IOException e) { //throws exception if error occurs when creating file
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return true;
    }

    public void writeToFile(ArrayList<Character> board) {
        int i;

        try {
            FileWriter newFile = new FileWriter(fileName); //creates new file writing variable
            for (i = 0; i < 96; i++) { //runs though entire board arraylist
                if (board.get(i) == ' ') { //checks if space is empty
                    newFile.write("0,"); //adds 0 to file
                } else if (board.get(i) == 'R') {
                    newFile.write("1,");
                } else if (board.get(i) == 'Y') {
                    newFile.write("2,");
                } else if (board.get(i) == '\n' && i != 95) {
                    newFile.write("\n");
                }
            }
            newFile.close();
        } catch (IOException e) { //throws exception if error occurs during writing
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void getInput3() {
        System.out.print("Enter a column between 1 and 7: "); //asks for input
        columnChoice = input.nextInt(); //gets input and assigns it to variable
        input.nextLine();  
    }

    public boolean checkInput3(int choice) {
        if (choice < 1 || choice > 7) {
            System.out.println("Please enter a number between 1 and 7");
            return false;
        }

        return true;
    }

    public boolean checkFullColumn(ArrayList<Integer> columnVals) {

        if (columnVals.get(columnChoice-1) == 6) { //checks if there are too many pieces in the column
            System.out.println("Sorry that column is full!");
            return false;
        }
        return true;
    }

    public void printWinner(char winner) {
        System.out.println("The winner is " + winner + "!");
    }

}
