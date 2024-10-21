package src.views.determinan;

import src.datatypes.Matrix;
import src.datatypes.Tuple3;
import src.helpers.GetString;
import src.helpers.Utils;
import src.views.Menu;
import src.views.Pprint;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class DeterminanView {
    private Menu menu = new Menu();
    private Pprint pprint = new Pprint();
    private String filepath[] = new String[] { "determinan/header", "determinan/matrix2x2", "determinan/reduksiBaris",
            "determinan/ekspansiKofaktor" };

    public void showMenu() {
        showHeader(0);
        System.out.println(">>> Available Methods:");
        System.out.println("1. Determinan matriks 2 x 2");
        System.out.println("2. Metode reduksi baris");
        System.out.println("3. Metode ekspansi kofaktor");
        System.out.println("4. Keluar");
    }

    public int getChoice() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            Utils.clearTerminal();
            showMenu();
            pprint.inputBoundary();
            choice = scanner.nextInt();
        } while (choice < 1 || choice > 4);
        return choice;
    }

    public void showHeader(int c) {
        Utils.clearTerminal();
        String header = "\n\033[1m\033[32m" + GetString.main(filepath[c]) + "\033[0m";
        System.out.println(header);
    }

    public Tuple3<Integer, Integer, Matrix> getInput() {
        pprint.inputMatrix();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan n: ");
        int n = scanner.nextInt();

        Matrix coefMatrix = new Matrix(n, n);
        System.out.println("Masukkan matriks:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Double value = scanner.nextDouble();
                coefMatrix.set(i, j, value);
            }
        }
        return new Tuple3<>(n, n, coefMatrix);
    }

    public Tuple3<Integer, Integer, Matrix> getInputFromFile(int degree) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan path file input: ");
        String filename = scanner.nextLine();
        try {
            File file = new File("test/matriksBalikanDeterminan/" + filename + ".txt");
            Scanner fileScanner = new Scanner(file);
            List<List<Double>> X = new ArrayList<>();

            while (fileScanner.hasNextLine()) {
                String values[] = fileScanner.nextLine().split("\\s+");
                List<Double> row = new ArrayList<>();
                for (int i = 0; i < values.length; i++) {
                    row.add(Double.parseDouble(values[i]));
                }
                X.add(row);
            }
            int n = X.size();
            Matrix matrix = new Matrix(n, n);

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    matrix.set(i, j, X.get(i).get(j));
                }
            }
            return new Tuple3<>(n, n, matrix);

        } catch (FileNotFoundException e) {
            System.out.println("File tidak ditemukan.");
            return new Tuple3<>(0, 0, null);
        }
    }

    public void printDeterminant(double determinant) {
        pprint.showResult();
        System.out.printf("Determinan: %.2f\n", determinant);
    }

}
