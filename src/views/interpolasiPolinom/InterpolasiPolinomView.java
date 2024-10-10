package src.views.interpolasiPolinom;

import  java.util.Scanner;
import src.datatypes.Matrix;
import src.datatypes.Tuple4;

public class InterpolasiPolinomView {
    public Tuple4<Integer, Integer, Matrix, Matrix> getInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan n (jumlah sampel): ");
        int n = scanner.nextInt();
        Matrix X = new Matrix(n+1, 1);
        Matrix Y = new Matrix (n, 1); //sampai sini
        System.out.println("Masukkan x y:");
        for (int i = 0; i < n; i++) {
            Double valueX = scanner.nextDouble();
            X.set(i, 0, valueX);
            Double valueY = scanner.nextDouble();
            Y.set(i, 0, valueY);  
        }
        Double value = scanner.nextDouble();
        X.set(n, 0, value); //x test
        return new Tuple4<>(n+1, 1, X, Y);
    }

    public void printPrediction(Double y) {
        System.out.println(y);
    }
}
