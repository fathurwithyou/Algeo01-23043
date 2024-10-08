package src.views.linearRegression;

import java.util.Scanner;

import src.datatypes.Matrix;
import src.datatypes.Tuple4;   

public class LinearRegressionView {
    public void showMenu() {
        System.out.println("Regresi Linear Berganda");
        System.out.println("1. Metode OLS");
        System.out.println("2. Keluar");
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

    public void printMatrix(Matrix matrix) {
        for (int i = 0; i < matrix.getRowCount(); i++) {
            for (int j = 0; j < matrix.getColumnCount(); j++) {
                System.out.print(matrix.get(i, j) + " ");
            }
            System.out.println();
        }
    }
}
