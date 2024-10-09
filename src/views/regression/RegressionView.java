package src.views.regression;

import src.datatypes.Matrix;
import src.datatypes.Tuple4;

import java.util.Scanner;

public class RegressionView {
    public void showMenu() {
        System.out.println("Regresi");
        System.out.println("1. Regresi Linear Berganda");
        System.out.println("2. Regresi Kuadratik");
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

    public Tuple4<Integer, Integer, Matrix, Matrix> getInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan n (jumlah peubah): ");
        int n = scanner.nextInt();
        System.out.print("Masukkan m (jumlah sampel): ");
        int m = scanner.nextInt();
        System.out.println("Masukkan matriks X dan Y");
        Matrix X = new Matrix(m, n);
        Matrix Y = new Matrix(m, 1);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Double value = scanner.nextDouble();
                X.set(i, j, value);
            }
            Double value = scanner.nextDouble();
            Y.set(i, 0, value);
        }
        return new Tuple4<>(n, m, X, Y);
    }

    public void printMatrix(Matrix matrix) {
        for (int i = 0; i < matrix.getRowCount(); i++) {
            for (int j = 0; j < matrix.getColumnCount(); j++) {
                System.out.print(matrix.get(i, j) + " ");
            }
            System.out.println();
        }
    }
}
