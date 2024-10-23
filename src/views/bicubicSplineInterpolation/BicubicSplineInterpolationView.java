package src.views.bicubicSplineInterpolation;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import src.datatypes.Array;
import src.datatypes.Matrix;
import src.datatypes.Tuple3;
import src.helpers.GetString;
import src.helpers.Utils;
import java.io.FileWriter;
import java.io.IOException;
import src.views.Pprint;

public class BicubicSplineInterpolationView {
    private Pprint pprint = new Pprint();

    public void showHeader() {
        Utils.clearTerminal();
        String header = "\n\033[1m\033[32m" + GetString.main("bicubicSplineInterpolation/header") + "\033[0m";
        System.out.println(header);
    }

    public Tuple3<Matrix, Array, Array> getInput() {
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
        Array X_test = new Array(n);
        Array y_test = new Array(n);
        System.out.print("Masukkan nilai x yang ingin diprediksi: ");
        Double x = scanner.nextDouble();
        System.out.print("Masukkan nilai y yang ingin diprediksi: ");
        Double y = scanner.nextDouble();
        X_test.set(0, x);
        y_test.set(0, y);
        return new Tuple3<>(matrix, X_test, y_test);
    }

    public Tuple3<Matrix, Array, Array> getInputFromFile() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan path file input: ");
        String filename = scanner.nextLine();
        try {
            File file = new File("test/bicubicSplineInterpolation/input/" + filename + ".txt");
            Scanner fileScanner = new Scanner(file);
            List<List<Double>> X = new ArrayList<>();
            List<Double> X_test = new ArrayList<>();
            List<Double> y_test = new ArrayList<>();

            for (int j = 0; j < 4; j++) {
                String values[] = fileScanner.nextLine().split("\\s+");
                List<Double> row = new ArrayList<>();
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
            while (fileScanner.hasNextLine()) {
                String values[] = fileScanner.nextLine().split("\\s+");
                Double x = Double.parseDouble(values[0]);
                Double y = Double.parseDouble(values[1]);
                X_test.add(x);
                y_test.add(y);
            }

            Array test = new Array(X_test.size());
            Array test2 = new Array(y_test.size());
            for (int i = 0; i < X_test.size(); i++) {
                test.set(i, X_test.get(i));
                test2.set(i, y_test.get(i));
            }
            return new Tuple3<>(matrix, test, test2);

        } catch (FileNotFoundException e) {
            System.out.println("File tidak ditemukan.");
            return new Tuple3<>(null, null, null);
        }
    }

    public void saveOutput(StringBuilder sb) {
        Scanner scanner = new Scanner(System.in);

        do {
            pprint.showSave();
            String choice = scanner.nextLine();
            if (choice.equalsIgnoreCase("Y")) {
                break;
            } else if (choice.equalsIgnoreCase("N")) {
                return;
            } else {
                System.out.println("Masukkan tidak valid.");
            }
        } while (true);

        String folder = "test/bicubicSplineInterpolation/output/";
        String filename;

        do {
            System.out.print("Filename: ");
            filename = scanner.nextLine();
            String filePath = folder + filename + ".txt";

            File file = new File(filePath);
            if (file.exists()) {
                System.out.println("File already exists. Please choose a different name.");
            } else {
                try {
                    FileWriter writer = new FileWriter(filePath);
                    writer.write(sb.toString());
                    writer.close();

                    System.out.println("File saved successfully at: " + filePath);
                    break;
                } catch (IOException e) {
                    System.out.println("Gagal menyimpan file.");
                    e.printStackTrace();
                    break;
                }
            }
        } while (true);
    }

    public void printPrediction(Double result, Array X_test, Array y_test, Array pred, StringBuilder sb) {
        for (int i = 0; i < X_test.getSize(); i++) {
            sb.append("f(")
                    .append(X_test.get(i))
                    .append(", ")
                    .append(y_test.get(i))
                    .append(") = ")
                    .append(String.format("%.3f", pred.get(i)))
                    .append("\n");
        }
        System.out.print(sb.toString());
    }

}
