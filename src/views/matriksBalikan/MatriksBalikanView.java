package src.views.matriksBalikan;

import java.util.Scanner;
import src.datatypes.Matrix;
import src.datatypes.Tuple3;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class MatriksBalikanView {
    private final String[] filepath = { "test/matriksBalikanDeterminan/" };

    public void showMenu() {
        System.out.println("Matriks Balikan");
        System.out.println("1. Metode GaussJordanElimination");
        System.out.println("2. Metode Adjoint");
        System.out.println("3. Keluar");
    }

    public int getChoice() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            showMenu();
            System.out.print("Pilihan: ");
            choice = scanner.nextInt();
        } while (choice < 1 || choice > 3);
        return choice;
    }

    public Tuple3<Integer, Integer, Matrix> getInput() {
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
        System.out.print("Masukkan nama file: ");
        String filename = scanner.nextLine();
        try {
            File file = new File(filepath[degree - 1] + filename + ".txt");
            Scanner fileScanner = new Scanner(file);
            List<List<Double>> X = new ArrayList<>();
            String[] values;

            while (fileScanner.hasNextLine()) {
                values = fileScanner.nextLine().split("\\s+");
                List<Double> row = new ArrayList<>();
                for (int i = 0; i < values.length; i++) {
                    row.add(Double.parseDouble(values[i]));
                }
                X.add(row);
            }
            int n = X.size();
            Matrix tmp = new Matrix(n, n);

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    tmp.set(i, j, X.get(i).get(j));
                }
            }
            return new Tuple3<>(n, n, tmp);

        } catch (FileNotFoundException e) {
            System.out.println("File tidak ditemukan.");
            return new Tuple3<>(0, 0, null);
        }
    }

    public void showSingular() {
        System.out.println("Matriks singular");
    }

}
