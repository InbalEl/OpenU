/*******************************************************************************
 * Knight:
 * This program reads from the user input of a Knight's coordinates
 * on a 8X8 chess board (range being 1-8). If the input is legal, it
 * outputs all possible moves within the board, otherwise it prints a message.
 * 
 * Inbal Elmalech
 * ID 301860169
 * 
*******************************************************************************/

import java.util.Scanner;
public class Knight {
    public static void main(String[] args) {
                
        final byte RANGE_MIN = 1;
        final byte RANGE_MAX = 8;

        Scanner scan = new Scanner(System.in);
        
        // Reading input from user
        System.out.println("This program reads two integers which " +
        "represent the knight's location on the chess board: ");
        
        System.out.println("Please enter the number of row");
        final int row = scan.nextInt();
        
        System.out.println("Please enter the number of column");
        final int col = scan.nextInt();

        // If the inputted (col, row) is within the board
        if ((RANGE_MIN <= col && col <= RANGE_MAX) &&
            (RANGE_MIN <= row && row <= RANGE_MAX)) {
            
            int possibleCol, possibleRow;
            System.out.println("Moves:");

            // Checking all 8 possible directions of Knight movement,
            // clockwise from 12:00
            
            // 1
            possibleCol = col + 1;
            possibleRow = row - 2;

            // Checking that the result of the last move is within the board
            if ((RANGE_MIN <= possibleCol && possibleCol <= RANGE_MAX) &&
                (RANGE_MIN <= possibleRow && possibleRow <= RANGE_MAX)) {
                System.out.println(possibleRow + " " + possibleCol);
            }
            
            // 2
            possibleCol = col + 2;
            possibleRow = row - 1;
            
            // Checking that the result of the last move is within the board
            if ((RANGE_MIN <= possibleCol && possibleCol <= RANGE_MAX) &&
            (RANGE_MIN <= possibleRow && possibleRow <= RANGE_MAX)) {
                System.out.println(possibleRow + " " + possibleCol);
            }
            
            // 3
            possibleCol = col + 2;
            possibleRow = row + 1;
            
            // Checking that the result of the last move is within the board
            if ((RANGE_MIN <= possibleCol && possibleCol <= RANGE_MAX) &&
            (RANGE_MIN <= possibleRow && possibleRow <= RANGE_MAX)) {
                System.out.println(possibleRow + " " + possibleCol);
            }
            
            // 4
            possibleCol = col + 1;
            possibleRow = row + 2;
            
            // Checking that the result of the last move is within the board
            if ((RANGE_MIN <= possibleCol && possibleCol <= RANGE_MAX) &&
            (RANGE_MIN <= possibleRow && possibleRow <= RANGE_MAX)) {
                System.out.println(possibleRow + " " + possibleCol);
            }
            
            // 5
            possibleCol = col - 1;
            possibleRow = row + 2;
            
            // Checking that the result of the last move is within the board
            if ((RANGE_MIN <= possibleCol && possibleCol <= RANGE_MAX) &&
            (RANGE_MIN <= possibleRow && possibleRow <= RANGE_MAX)) {
                System.out.println(possibleRow + " " + possibleCol);
            }
            
            // 6
            possibleCol = col - 2;
            possibleRow = row + 1;
            
            // Checking that the result of the last move is within the board
            if ((RANGE_MIN <= possibleCol && possibleCol <= RANGE_MAX) &&
            (RANGE_MIN <= possibleRow && possibleRow <= RANGE_MAX)) {
                System.out.println(possibleRow + " " + possibleCol);
            }
            
            // 7
            possibleCol = col - 2;
            possibleRow = row - 1;
            
            // Checking that the result of the last move is within the board
            if ((RANGE_MIN <= possibleCol && possibleCol <= RANGE_MAX) &&
            (RANGE_MIN <= possibleRow && possibleRow <= RANGE_MAX)) {
                System.out.println(possibleRow + " " + possibleCol);
            }
            
            // 8
            possibleCol = col - 1;
            possibleRow = row - 2;
            
            // Checking that the result of the last move is within the board
            if ((RANGE_MIN <= possibleCol && possibleCol <= RANGE_MAX) &&
            (RANGE_MIN <= possibleRow && possibleRow <= RANGE_MAX)) {
                System.out.println(possibleRow + " " + possibleCol);
            }
            
        } // Initial Knight position is legal 
        else {
            System.out.println("input is illegal");
        } // Initial Knight position is illegal

        scan.close();
    } // main
} // Knight