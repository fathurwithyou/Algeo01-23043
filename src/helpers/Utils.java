package src.helpers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
            max = Math.max(max, Math.abs(matrix.get(i, col - 1)) + matrix.get(i, col - 1) < 0 ? 1 : 0);
        }
        return (int) max / 10 + 2 + sign;
    }

    public static void printMatrix(Matrix matrix, StringBuilder savedString) {
        int col = matrix.getColumnCount();
        int prec = 2; // Precision for floating-point numbers
        int w = 7;    
        int maxr = len(matrix) + prec;
    
        for (int i = 0; i < matrix.getRowCount(); i++) {
            savedString.append('['); // Start the row with '['
            for (int j = 0; j < matrix.getColumnCount() - 1; j++) {
                savedString.append(String.format("%-" + w + "." + prec + "f", matrix.get(i, j)));
                savedString.append("   "); // Add spacing between elements
            }
            // Append the last element in the row with a larger width (maxr)
            savedString.append(String.format("%-" + maxr + "." + prec + "f", matrix.get(i, col - 1)));
            savedString.append(']'); // Close the row with ']'
            savedString.append("\n"); 
        }
        System.out.println(savedString.toString());
    }
    

    public static void printMatrix(Matrix matrix) {
        int col = matrix.getColumnCount();
        int prec = 2;
        int w = 7; 
        int maxr = len(matrix) + prec;
        for (int i = 0; i < matrix.getRowCount(); i++) {
            System.out.print('[');
            for (int j = 0; j < matrix.getColumnCount() - 1; j++) {
                // Left-align with a fixed width
                System.out.printf("%-" + w + "." + prec + "f", matrix.get(i, j));
                System.out.print("   ");
            }
            System.out.printf("%-" + maxr + "." + prec + "f", matrix.get(i, col - 1));
            System.out.println(']');
        }
    }

    public static void printData(Matrix X, Matrix y) {
        int n = X.getRowCount();
        int m = X.getColumnCount();
        List<Integer> tableLength = new ArrayList<>();
        List<String> tableHeader = new ArrayList<>();

        for (int i = 0; i < m + 1; i++) {
            tableLength.add(0);
        }

        for (int i = 0; i < m; i++) {
            tableHeader.add("x" + (i + 1));
            tableLength.add(tableHeader.get(i).length());
        }
        tableHeader.add("y");
        tableLength.add(tableHeader.get(m).length());

        // get max length of each column
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                String element = String.format("%.2f", X.get(i, j));
                tableLength.set(j, Math.max(tableLength.get(j), element.length()));
            }
            String element = String.format("%.2f", y.get(i, 0));
            tableLength.set(m, Math.max(tableLength.get(m), element.length()));
        }

        // print header
        System.out.print("|");
        for (int i = 0; i < m+1; i++) {
            System.out.print(" \033[1m" + tableHeader.get(i) + "\033[0m");
            for (int j = 0; j < tableLength.get(i) - tableHeader.get(i).length() + 1; j++) {
                System.out.print(" ");
            }
            System.out.print("|");
        }
        System.out.println();

        // print separator
        System.out.print("+");
        for (int i = 0; i < m+1; i++) {
            for (int j = 0; j < tableLength.get(i) + 2; j++) {
                System.out.print("-");
            }
            System.out.print("+");
        }
        System.out.println();


        // print body
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                String element = String.format("%.2f", X.get(i, j));
                System.out.print("| " + element);
                for (int k = 0; k < tableLength.get(j) - element.length() + 1; k++) {
                    System.out.print(" ");
                }
            }
            String element = String.format("%.2f", y.get(i, 0));
            System.out.print("| " + element);
            for (int k = 0; k < tableLength.get(m) - element.length() + 1; k++) {
                System.out.print(" ");
            }
            System.out.println("|");
        }
    }

}
