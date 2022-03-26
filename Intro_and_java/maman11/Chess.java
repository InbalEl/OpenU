/*******************************************************************************
 * Chess:
 * This program reads from the user the input of 2 chessmen (rook, bishop or
 * knight) on a 8X8 chess board (range being 1-8), and assesses whether
 * one of the pieces threatens the other.
 * If the input is invalid (chessmen are identical, illegal position, identical
 * positions), a message regarding the problem will be printed.
 * 
 * Inbal Elmalech
 * ID 301860169
 * 
*******************************************************************************/

import java.util.Scanner;

public class Chess {
    public static void main(String[] args) {

        final char BISHOP = 'b';
        final char ROOK = 'r';
        final char KNIGHT = 'k';
            
        final byte MIN = 1;
        final byte MAX = 8;

        Scanner scan = new Scanner (System.in);
        
        // Reading first chessman info
        System.out.println("Please enter the type"+
                           " of the first chessman");
        char first = scan.next().charAt(0);
        
        System.out.println("Please enter the number of row");
        int row1 = scan.nextInt();
        
        System.out.println("Please enter the number of column");
        int col1 = scan.nextInt();

        // Reading second chessman info
        System.out.println("Please enter the type"+
                           " of the second chessman");
        char second = scan.next().charAt(0);

        System.out.println("Please enter the number of row");
        int row2 = scan.nextInt();

        System.out.println("Please enter the number of column");
        int col2 = scan.nextInt();
        
        // Validating input
        if (first == second) {
            System.out.println("Chessmen should be different from each other");
        } // chessmen are the same

        else if (MIN > row1 || row1 > MAX || MIN > col1 || col1 > MAX ||
                 MIN > row2 || row2 > MAX || MIN > col2 || col2 > MAX) {
            System.out.println("Position is not legal");
        } // Chessmen positions not legal

        else if ((row1 == row2) && (col1 == col2)) {
            System.out.println("Chessmen positions should not be identical");
        } // Chessmen positions are identical
        
        else {
            // Checking if first threatens second
            
            boolean threatsDetected = false;
            
            switch(first) {
                case ROOK:
                    if ((row1 == row2) || (col1 == col2)) {
                        threatsDetected = true;
                        System.out.println("rook threatens " +
                        (second == BISHOP ? "bishop" : "knight"));
                    }
                    break;
                case BISHOP:
                    if ((row1 != row2) && (col1 != col2)) {

                        // There are 2 possible col coordinates for a threat:
                        int diff = row1 - row2;
                        int firstThreatCol = col1 + diff;
                        int secondThreatCol = col1 - diff;
                        
                        // if either of the col threat coordinates is legal
                        if (((MIN <= firstThreatCol) && (firstThreatCol <= MAX))
                            || ((MIN <= secondThreatCol) && (secondThreatCol <= MAX)))
                        {
                            // if second chessman's col position is under threat
                            if ((col2 == firstThreatCol) ||
                                (col2 == secondThreatCol)) {
                                
                                threatsDetected = true;
                                System.out.println("bishop threatens " +
                                (second == ROOK ? "rook" : "knight"));
                            }
                        }
                    }
                    break;
                case KNIGHT:
                    if ((row1 != row2) && (col1 != col2)) {
                        
                        // A Knight's threat occurs when the sum of the
                        // absolute values of the row and col differences
                        // equals 3, excluding same-row/same-col situations
                        
                        int rowDiffAbs = Math.abs(row1 - row2);
                        int colDiffAbs = Math.abs(col1 - col2);
                        
                        if (rowDiffAbs + colDiffAbs == 3) {
                            threatsDetected = true;
                            System.out.println("knight threatens " +
                            (second == ROOK ? "rook" : "bishop"));
                        }
                    }
                    break;
                } // first chessman switch

                // Checking if second threatens first
                switch(second) {
                    case ROOK:
                    if ((row2 == row1) || (col2 == col1)) {
                        threatsDetected = true;
                        System.out.println("rook threatens " +
                        (first == BISHOP ? "bishop" : "knight"));
                    }
                    break;
                case BISHOP:
                    if ((row2 != row1) && (col2 != col1)) {

                        // There are 2 possible col coordinates for a threat:
                        int diff = row2 - row1;
                        int firstThreatCol = col2 + diff;
                        int secondThreatCol = col2 - diff;
                        
                        // if either of the col threat coordinates is legal
                        if (((MIN <= firstThreatCol) && (firstThreatCol <= MAX))
                            || ((MIN <= secondThreatCol) && (secondThreatCol <= MAX)))
                        {
                            // if first chessman's col position is under threat
                            if ((col1 == firstThreatCol) ||
                                (col1 == secondThreatCol)) {
                                
                                threatsDetected = true;
                                System.out.println("bishop threatens " +
                                (first == ROOK ? "rook" : "knight"));
                            }
                        }
                    }
                    break;
                case KNIGHT:
                    if ((row2 != row1) && (col2 != col1)) {
                        
                        // A Knight's threat occurs when the sum of the
                        // absolute values of the row and col differences
                        // equals 3, excluding same-row/same-col situations
                        
                        int rowDiffAbs = Math.abs(row2 - row1);
                        int colDiffAbs = Math.abs(col2 - col1);
                        
                        if (rowDiffAbs + colDiffAbs == 3) {
                            threatsDetected = true;
                            System.out.println("knight threatens " +
                            (first == ROOK ? "rook" : "bishop"));
                        }
                    }
                    break;
                }// second chessman switch

                if (!threatsDetected) {
                    System.out.println("no threat");
                }
        } // assessing threats
        
        scan.close();
    } // main
} // chess