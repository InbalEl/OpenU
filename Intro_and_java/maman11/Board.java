import java.util.Arrays;

public class Board {
    private final int _ROWS = 8;
    private final int _COLS = 8;
    private final char _EMPTY_SQUARE = '\u25A0';

    private char[][] _board;

    public Board() {
        _board = new char[_ROWS][_COLS];
        clearBoard();
    }

    public void clearBoard() {
        for (int y = 0; y < 8 ; ++y) {
            Arrays.fill(_board[y], _EMPTY_SQUARE);
        }
    }

    public void setSquare(int row, int col, char c) {
        _board[row][col] = c;
    }

    public void printBoard() {
        System.out.println("   1 2 3 4 5 6 7 8");
        System.out.println("-------------------");
        
        for (int y = 0; y < 8; ++y) {
            System.out.print(y + 1 + "| ");
            for (int x = 0; x < 8 ; ++x) {
                if (_board[y][x] != _EMPTY_SQUARE) {
                    System.out.print("\u001B[31m" + _board[y][x]
                                       + "\u001B[0m" + " ");
                } else {
                    System.out.print(_board[y][x] + " ");
                }
            }
            System.out.println();
        }
        
        System.out.println("-------------------");
    }
}