package src.views.bicubicSplineInterpolation;

import java.util.Scanner;
import src.datatypes.Matrix;
import src.datatypes.Tuple3;

public class BicubicSplineInterpolationView {
    private Scanner scanner = new Scanner(System.in);

    public Tuple3<Matrix, Double, Double> getInput() {

        Matrix Z = new Matrix(4, 4);
        
        System.out.println("Masukkan nilai-nilai grid Z (matrix 4x4): ");
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Z.set(i, j, scanner.nextDouble());
            }
        }

        System.out.print("Masukkan nilai a yang ingin diprediksi: ");
        Double a = scanner.nextDouble();
        System.out.print("Masukkan nilai b yang ingin diprediksi: ");
        Double b = scanner.nextDouble();

        return new Tuple3<>(Z, a, b);
    }

    public void printPrediction(Double result) {
        System.out.printf("%.2f\n", result);
    }
}
