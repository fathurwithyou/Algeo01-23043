package src.views.bicubicSplineInterpolation;

import java.util.Scanner;
import src.datatypes.Matrix;
import src.datatypes.Tuple3;

public class BicubicSplineInterpolationView {
    public Tuple3<Matrix, Double, Double> getInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan n (jumlah sampel): ");
        int n = scanner.nextInt();
        Matrix matrix = new Matrix(n, n);
        System.out.println("Masukkan nilai-nilai pada matrix: ");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Double valueX = scanner.nextDouble();
                matrix.set(i, j, valueX);
            }
        }
        System.out.print("Masukkan nilai x yang ingin diprediksi: ");
        Double x = scanner.nextDouble();
        System.out.print("Masukkan nilai y yang ingin diprediksi: ");
        Double y = scanner.nextDouble();
        return new Tuple3<>(matrix, x, y);
    }

    public void printPrediction(Double result) {
        System.out.println(result);
    }
}
