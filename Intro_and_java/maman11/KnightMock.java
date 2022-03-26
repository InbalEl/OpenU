public class KnightMock {
    public static void main(String[] args) {
        
        // todo: check exactly which materials are relevant ot this maman
        // todo: add documentation
        // todo: read conventions doc
        
        final byte RANGE_MIN = 1;
        final byte RANGE_MAX = 8;        

        final int row = Integer.parseInt(args[0]) + 1;
        final int col = Integer.parseInt(args[1]) + 1;

        System.out.println("KightMock row=" + row + ", col=" + col);
        // If the inputted (col, row) is within the board
        if ((RANGE_MIN <= col && col <= RANGE_MAX) &&
            (RANGE_MIN <= row && row <= RANGE_MAX)) {
            
            int possibleCol, possibleRow;
            // System.out.println("Moves:");

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
        }
    }
}