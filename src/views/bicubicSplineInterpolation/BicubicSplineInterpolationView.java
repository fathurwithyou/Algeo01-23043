package src.views.bicubicSplineInterpolation;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import src.datatypes.Matrix;
import src.datatypes.Tuple3;
import src.helpers.GetString;
import src.helpers.Utils;

public class BicubicSplineInterpolationView {
    public void showHeader() {
        Utils.clearTerminal();
        String header = "\n\033[1m\033[32m" + GetString.main("bicubicSplineInterpolation/header") + "\033[0m";
        System.out.println(header);
    }

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

    public Tuple3<Matrix, Double, Double> getInputFromFile() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan path file input: ");
        String filename = scanner.nextLine();
        try {
            File file = new File("test/bicubicSplineInterpolation/" + filename + ".txt");
            Scanner fileScanner = new Scanner(file);
            List<List<Double>> X = new ArrayList<>();

            List<Double> row = new ArrayList<>();
            for (int j = 0; j < 4; j++) {
                String values[] = fileScanner.nextLine().split("\\s+");
                for (int i = 0; i < 4; i++) {
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
            String values[] = fileScanner.nextLine().split("\\s+");
            Double x = Double.parseDouble(values[0]);
            Double y = Double.parseDouble(values[1]);

            return new Tuple3<>(matrix, x, y);

        } catch (FileNotFoundException e) {
            System.out.println("File tidak ditemukan.");
            return new Tuple3<>(null, 0.0, 0.0);
        }
    }

    public void printPrediction(Double result, Double x, Double y) {
        System.out.print("f(" + x + ", " + y + ") = ");
        System.out.println(result);
    }
}
