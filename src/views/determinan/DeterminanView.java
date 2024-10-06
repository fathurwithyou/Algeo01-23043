package src.views.determinan;

import java.util.*;

import src.datatypes.Matrix;
import src.datatypes.Tuple3;

public class DeterminanView {
    public void showMenu() {
        System.out.println("Determinan");
        System.out.println("1. Determinan matriks 2 x 2");
        System.out.println("2. Metode reduksi baris");
        System.out.println("3. Metode ekspansi kofaktor");
        System.out.println("4. Keluar");
    }

    public int getChoice() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            showMenu();
            System.out.print("Pilihan: ");
            choice = scanner.nextInt();
        } while (choice < 1 || choice > 4);
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

    public void printDeterminant(double determinant) {
        System.out.println(determinant);
    }

}
