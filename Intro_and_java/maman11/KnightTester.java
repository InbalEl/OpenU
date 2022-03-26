import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class KnightTester {
    public static void main(String[] args) {
        Board board = new Board();
        board.printBoard();

        for (int row = 0; row < 8; ++row) {
            for (int col = 0; col < 8 ; ++col) {
                board.setSquare(row, col, '\u265E');
                runKnight(board, row, col);
                board.printBoard();
                board.clearBoard();
            }
        }
    }

    private static void runKnight(Board b, int row, int col) {
        try {
            Runtime rt = Runtime.getRuntime();
            String[] commands = {"java", "KnightMock",
                                 Integer.toString(row), Integer.toString(col)};
            Process p = rt.exec(commands);

            BufferedReader stdInput = new BufferedReader(
                            new InputStreamReader(p.getInputStream()));
            
            BufferedReader stdError = new BufferedReader(
                            new InputStreamReader(p.getErrorStream()));

            String output = null;

            while ((output = stdInput.readLine()) != null) {
                // System.out.println(output);
                parseMove(b, output);
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

    private static void parseMove(Board b, String s){
        if (Character.isDigit(s.charAt(0))) {
            String[] coordinates = s.split(" ");
            b.setSquare((Integer.parseInt(coordinates[0])-1),
                        (Integer.parseInt(coordinates[1])-1),
                        'X');
        }

    }
}