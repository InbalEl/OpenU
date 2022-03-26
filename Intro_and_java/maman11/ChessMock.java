public class ChessMock {
    public static void main(String[] args) {

        final char BISHOP = 'b';
        final char ROOK = 'r';
        final char KNIGHT = 'k';
            
        final byte MIN = 1;
        final byte MAX = 8;
        
        char first = args[0].charAt(0);

        final int row1 = Integer.parseInt(args[1]);
        final int col1 = Integer.parseInt(args[2]);
        
        char second = args[3].charAt(0);

        final int row2 = Integer.parseInt(args[4]);
        final int col2 = Integer.parseInt(args[5]);
        
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
                        if (((MIN <= firstThreatCol) && (firstThreatCol <= MAX)) ||
                        ((MIN <= secondThreatCol) && (secondThreatCol <= MAX)))
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
                        // equals 3
                        
                        int rowDiffAbs = Math.abs(row1 - row2);
                        int colDiffAbs = Math.abs(col1 - col2);
                        
                        if (rowDiffAbs + colDiffAbs == 3) {
                            threatsDetected = true;
                            System.out.println("knight threatens " +
                            (second == ROOK ? "rook" : "bishop"));
                        }
                    }
                    break;
                }

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
                        if (((MIN <= firstThreatCol) && (firstThreatCol <= MAX)) ||
                        ((MIN <= secondThreatCol) && (secondThreatCol <= MAX)))
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
                        // equals 3
                        
                        int rowDiffAbs = Math.abs(row2 - row1);
                        int colDiffAbs = Math.abs(col2 - col1);
                        
                        if (rowDiffAbs + colDiffAbs == 3) {
                            threatsDetected = true;
                            System.out.println("knight threatens " +
                            (first == ROOK ? "rook" : "bishop"));
                        }
                    }
                    break;
                }

                if (!threatsDetected) {
                    System.out.println("no threat");
                }
        }
    }
}