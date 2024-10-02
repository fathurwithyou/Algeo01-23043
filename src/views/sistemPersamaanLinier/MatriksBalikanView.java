package src.views.sistemPersamaanLinier;
import java.util.Scanner;

import src.datatypes.Matrix;
import src.datatypes.Tuple3;

public class MatriksBalikanView {
    public Tuple3<Integer, Integer, Matrix> getInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan n: ");
        int n = scanner.nextInt();
        System.out.print("Masukkan m: ");
        int m = scanner.nextInt();

        Matrix coefMatrix = new Matrix(n, m+1);
        System.out.println("Masukkan matriks augmented:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                Double value = scanner.nextDouble();
                coefMatrix.set(i, j, value);
            }
        }
        System.out.println("Masukkan konstanta:");
        for (int i = 0; i < n; i++) {
            Double value = scanner.nextDouble();
            coefMatrix.set(i, m, value);
        }
        return new Tuple3<>(n, m, coefMatrix);
    }
}
