import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class ChessTester {
    public static void main(String[] args) {
        Board board = new Board();

        // k static, b moving - V
        // b moving, k static - V
        // k static, r moving - V
        // r moving, k static - V
        // b static, r moving - V
        // r moving, b static - V
        // b static, k moving - V
        // k moving, b static - V
        // r static, b moving - V
        // b moving, r static - V
        // r static, k moving - V
        // k moving, r static - V

        final char K = '\u265E';
        final char R = '\u265C';
        final char B = '\u265D';

        final int STATIC_ROW = 5;
        final int STATIC_COL = 4;

        for (int row = 1; row <= 8; ++row) {
            for (int col = 1; col <= 8; ++col) {
                board.setSquare(STATIC_ROW-1, STATIC_COL-1, R);
                board.setSquare(row-1, col-1, K);

                board.printBoard();

                runChess('r', STATIC_ROW, STATIC_COL, 'k', row, col);
                board.clearBoard();
                System.out.println("~~~~~~~~~~~~~~~~~~");
            }
        }
    }

    private static void runChess(char c1, int row1, int col1,
                                 char c2, int row2, int col2) {
        try {
            Runtime rt = Runtime.getRuntime();
            String[] commands = {"java", "ChessMock", Character.toString(c1),
                                Integer.toString(row1), Integer.toString(col1),
                                Character.toString(c2),
                                Integer.toString(row2), Integer.toString(col2)};
            Process p = rt.exec(commands);

            BufferedReader stdInput = new BufferedReader(
                            new InputStreamReader(p.getInputStream()));
            
            BufferedReader stdError = new BufferedReader(
                            new InputStreamReader(p.getErrorStream()));

            String output = null;

            while ((output = stdInput.readLine()) != null) {
                System.out.println(output);
            }

            while ((output = stdError.readLine()) != null) {
                System.out.println(output);
            }

            p.waitFor();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
