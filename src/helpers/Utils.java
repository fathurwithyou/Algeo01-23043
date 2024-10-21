package src.helpers;

import java.io.IOException;

import src.datatypes.Matrix;

public class Utils {
    public static void clearTerminal() {
        String os = System.getProperty("os.name").toLowerCase();

        try {
            if (os.contains("win")) {
                // Windows command to clear terminal
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else if (os.contains("nix") || os.contains("nux") || os.contains("mac")) {
                // Unix/Linux/macOS command to clear terminal
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static int len(Matrix matrix) {
        double max = -(1 << 31);
        int sign = 0;
        int col = matrix.getColumnCount();
        for (int i = 0; i < matrix.getRowCount(); i++) {
            max = Math.max(max, Math.abs(matrix.get(i, col - 1)));
            sign |= matrix.get(i, col - 1) < 0 ? 1 : 0;
        }
        return (int) max / 10 + 2 + sign;
    }

    public static void printMatrix(Matrix matrix) {
        int col = matrix.getColumnCount();
        int prec = 2;
        int w = 7; // Set a fixed width for alignment (adjust as needed)
        int maxr = len(matrix) + prec;
        for (int i = 0; i < matrix.getRowCount(); i++) {
            System.out.print('[');
            for (int j = 0; j < matrix.getColumnCount() - 1; j++) {
                // Left-align with a fixed width
                System.out.printf("%-" + w + "." + prec + "f", matrix.get(i, j));
                System.out.print("   ");
            }
            System.out.printf("%-" + maxr + "." + prec + "f", matrix.get(i, col-1));
            System.out.println(']');
        }
    }
}
