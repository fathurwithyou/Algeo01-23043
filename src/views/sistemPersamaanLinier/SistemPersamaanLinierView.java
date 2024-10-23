package src.views.sistemPersamaanLinier;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import src.datatypes.Array;
import src.datatypes.Matrix;
import src.datatypes.Tuple3;
import src.helpers.GetString;
import src.helpers.Utils;
import src.views.Pprint;

public class SistemPersamaanLinierView {
    private String[] filepath = new String[] { "sistemPersamaanLinier/header", "sistemPersamaanLinier/gauss", "sistemPersamaanLinier/gaussJordan", "sistemPersamaanLinier/matriksBalikan", "sistemPersamaanLinier/kaidahCramer" };
    private Pprint pprint = new Pprint();
    public void showMenu() {
        showHeader(0);
        System.out.println("\033[1m>>> Available Methods:");
        System.out.println("1. Metode Eliminasi Gauss");
        System.out.println("2. Metode Eliminasi Gauss-Jordan");
        System.out.println("3. Metode Matriks Balikan");
        System.out.println("4. Kaidah Cramer");
        System.out.println("5. Keluar");
    }

    public void showHeader(int c) {
        Utils.clearTerminal();
        String header = "\n\033[1m\033[32m" + GetString.main(filepath[c]) + "\033[0m";
        System.out.println(header);
    }

    public int getChoice() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            showMenu();
            pprint.inputBoundary();
            choice = scanner.nextInt();
        } while (choice < 1 || choice > 5);
        return choice;
    }

    public Tuple3<Integer, Integer, Matrix> getInput() {
        pprint.inputMatrix();
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

    public Tuple3<Integer, Integer, Matrix> getSquareInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan n: ");
        int n = scanner.nextInt();

        Matrix augmentedMatrix = new Matrix(n, n + 1);
        System.out.println("Masukkan matriks augmented:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n + 1; j++) {
                Double value = scanner.nextDouble();
                augmentedMatrix.set(i, j, value);
            }
        }
        return new Tuple3<>(n, n + 1, augmentedMatrix);
    }

    public Tuple3<Integer, Integer, Matrix> getInputFromFile() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan nama file: ");
        String filename = scanner.nextLine();
        try {
            File file = new File("test/sistemPersamaanLinier/input/" + filename + ".txt");
            Scanner fileScanner = new Scanner(file);
            List<List<Double>> X = new ArrayList<>();
            String[] values;

            while (fileScanner.hasNextLine()) {
                values = fileScanner.nextLine().split("\\s+");
                List<Double> row = new ArrayList<>();
                for (int i = 0; i < values.length; i++) {
                    row.add(Double.parseDouble(values[i]));
                }
                X.add(row);
            }
            int n = X.size();
            int m = X.get(0).size();
            Matrix tmp = new Matrix(n, m);

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    tmp.set(i, j, X.get(i).get(j));
                }
            }
            return new Tuple3<>(n, m-1, tmp);

        } catch (FileNotFoundException e) {
            System.out.println("File tidak ditemukan.");
            return new Tuple3<>(0, 0, null);
        }
    }

    public void showSingular(int flag) {
        if (flag != -1) {
            pprint.showResult();
            if (flag == 2) {
                System.out.println("Sistem persamaan linear memiliki banyak solusi");
            }
            else{
                System.out.println("Sistem persamaan linear tidak memiliki solusi");
            }
        }
        else{
            System.out.println("Sistem persamaan linear tidak dapat diselesaikan dengan metode ini");
        }
    }

    public void showFreeVariable(List<String> freeVariable) {
        for (String var : freeVariable) {
            System.out.println(var);
        }
    }

    public void printResult(Array result) {
        for (int i = 0; i < result.getSize(); i++) {
            System.out.println("x" + (i + 1) + " = " + result.get(i));
        }
    }

    public void saveOutput(String result) {
        Scanner scanner = new Scanner(System.in);
        do {
            pprint.showSave();
            String choice = scanner.nextLine();
            if (choice.equals("Y") || choice.equals("y")) {
                break;
            } else if (choice.equals("N") || choice.equals("n")) {
                return;
            } else {
                System.out.println("Masukkan tidak valid.");
            }
        } while (true);

        String folder = "test/sistemPersamaanLinier/output/";
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
                    writer.write(result);
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

    public void printMatrix(Matrix matrix) {
        for (int i = 0; i < matrix.getRowCount(); i++) {
            for (int j = 0; j < matrix.getColumnCount(); j++) {
                System.out.print(matrix.get(i, j) + " ");
            }
            System.out.println();
        }
    }

}
