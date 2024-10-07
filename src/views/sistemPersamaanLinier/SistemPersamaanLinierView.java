package src.views.sistemPersamaanLinier;

import java.util.*;

import src.datatypes.Matrix;
import src.datatypes.Array;
import src.datatypes.Tuple3;

public class SistemPersamaanLinierView {
    public void showMenu() {
        System.out.println("Sistem Persamaan Linier");
        System.out.println("1. Metode eliminasi Gauss");
        System.out.println("2. Metode eliminasi Gauss-Jordan");
        System.out.println("3. Metode matriks balikan");
        System.out.println("4. Kaidah Cramer");
        System.out.println("5. Keluar");
    }

    public int getChoice() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            showMenu();
            System.out.print("Pilihan: ");
            choice = scanner.nextInt();
        } while (choice < 1 || choice > 5);
        return choice;
    }

    public Tuple3<Integer, Integer, Matrix> getInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan n: ");
        int n = scanner.nextInt();
        System.out.print("Masukkan m: ");
        int m = scanner.nextInt();

        Matrix coefMatrix = new Matrix(n, m + 1);
        System.out.println("Masukkan matriks augmented:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m+1; j++) {
                Double value = scanner.nextDouble();
                coefMatrix.set(i, j, value);
            }
        }
        return new Tuple3<>(n, m, coefMatrix);
    }

    public void showSingular(int flag) {
        if (flag != -1) {
            if (flag == 0) {
                System.out.println("Sistem persamaan linier memiliki banyak solusi");
            }
            else{
                System.out.println("tidak ada solusi");
            }
        }
    }

    public void printMatrix(Matrix matrix) {
        for (int i = 0; i < matrix.getRowCount(); i++) {
            for (int j = 0; j < matrix.getColumnCount(); j++) {
                System.out.print(matrix.get(i, j) + " ");
            }
            System.out.println();
        }
    }

    public void printResult(Array result) {
        int n = result.getSize();
        System.out.print("[");
        for (int i = 0; i < n; i++) {
            System.out.print(result.get(i));
            if (i != n - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

}
