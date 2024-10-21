package src.views.regression;

import src.datatypes.Matrix;
import src.datatypes.Tuple5;
import src.helpers.GetString;
import src.helpers.Utils;
import src.datatypes.Tuple4;
import src.datatypes.Tuple3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import src.views.Pprint;

public class RegressionView {
    private Pprint pprint = new Pprint();
    private String[] filepath = new String[] { "test/linearRegression/", "test/quadraticRegression/" };

    public void showMenu() {
        String coloredAsciiArt = "\033[32m" + GetString.main("regression/header") + "\033[0m";
        System.out.println(coloredAsciiArt);
        System.out.println("\033[1m>>> Available Methods:");
        System.out.println("1. Regresi Linear Berganda");
        System.out.println("2. Regresi Kuadratik");
        System.out.println("3. Keluar");
    }

    public int getChoice() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            Utils.clearTerminal();
            showMenu();
            pprint.inputBoundary();
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

    public Tuple3<Integer, Integer, Matrix> getInputToPredict(int n) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan m (jumlah sampel): ");
        int m = scanner.nextInt();
        System.out.println("Masukkan matriks X");
        Matrix X = new Matrix(m, n);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Double value = scanner.nextDouble();
                X.set(i, j, value);
            }
        }
        return new Tuple3<>(n, m, X);
    }

    public Tuple5<Integer, Integer, Matrix, Matrix, Matrix> getInputFromFile(int degree) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan path file input: ");
        String filename = scanner.nextLine();
        try {
            File file = new File(filepath[degree - 1] + filename + ".txt");
            Scanner fileScanner = new Scanner(file);
            int m, n;
            List<List<Double>> XList = new ArrayList<>();
            List<Double> YList = new ArrayList<>();
            List<List<Double>> test = new ArrayList<>();
            List<Double> row = new ArrayList<>();

            String[] values = fileScanner.nextLine().split("\\s+");
            n = values.length;
            for (int i = 0; i < n - 1; i++) {
                row.add(Double.parseDouble(values[i]));
            }
            XList.add(row);
            YList.add(Double.parseDouble(values[n - 1]));

            while (fileScanner.hasNextLine()) {
                values = fileScanner.nextLine().split("\\s+");
                row = new ArrayList<>();
                if (values.length != n) {
                    for (int i = 0; i < values.length; i++) {
                        row.add(Double.parseDouble(values[i]));
                    }
                    test.add(row);
                    continue;
                }
                for (int i = 0; i < values.length; i++) {
                    if (i == values.length - 1) {
                        YList.add(Double.parseDouble(values[i]));
                    } else {
                        row.add(Double.parseDouble(values[i]));
                    }
                }
                XList.add(row);
            }
            Matrix X = new Matrix(XList.size(), n - 1);
            Matrix Y = new Matrix(XList.size(), 1);
            Matrix X_test = new Matrix(test.size(), n - 1);

            for (int i = 0; i < XList.size(); i++) {
                for (int j = 0; j < n - 1; j++) {
                    X.set(i, j, XList.get(i).get(j));
                }
                Y.set(i, 0, YList.get(i));
            }
            for (int i = 0; i < test.size(); i++) {
                for (int j = 0; j < n - 1; j++) {
                    X_test.set(i, j, test.get(i).get(j));
                }
            }

            m = XList.size();
            return new Tuple5<>(m, n, X, Y, X_test);

        } catch (FileNotFoundException e) {
            System.out.println("File tidak ditemukan.");
            return new Tuple5<>(0, 0, new Matrix(0, 0), new Matrix(0, 0), new Matrix(0, 0));
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
}
