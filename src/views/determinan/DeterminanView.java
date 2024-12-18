package src.views.determinan;

import src.datatypes.Matrix;
import src.datatypes.Tuple3;
import src.helpers.GetString;
import src.helpers.Utils;
import src.views.Pprint;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class DeterminanView {
    private Pprint pprint = new Pprint();
    private String filepath[] = new String[] { "determinan/header", "determinan/matrix2x2", "determinan/reduksiBaris",
            "determinan/ekspansiKofaktor" };

    public void showMenu() {
        showHeader(0);
        System.out.println("\033[1m>>> Available Methods:");
        System.out.println("1. Determinan Matriks 2 x 2");
        System.out.println("2. Metode Reduksi Baris");
        System.out.println("3. Metode Ekspansi Kofaktor");
        System.out.println("4. Keluar");
    }

    public int getChoice() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            Utils.clearTerminal();
            showMenu();
            pprint.inputBoundary();
            choice = scanner.nextInt();
        } while (choice < 1 || choice > 4);
        return choice;
    }

    public void showHeader(int c) {
        Utils.clearTerminal();
        String header = "\n\033[1m\033[32m" + GetString.main(filepath[c]) + "\033[0m";
        System.out.println(header);
    }

    public Tuple3<Integer, Integer, Matrix> getInput() {
        pprint.inputMatrix();
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

    public Tuple3<Integer, Integer, Matrix> getInputFromFile() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan path file input: ");
        String filename = scanner.nextLine();
        try {
            File file = new File("test/determinan/input/" + filename + ".txt");
            Scanner fileScanner = new Scanner(file);
            List<List<Double>> X = new ArrayList<>();

            while (fileScanner.hasNextLine()) {
                String values[] = fileScanner.nextLine().split("\\s+");
                List<Double> row = new ArrayList<>();
                for (int i = 0; i < values.length; i++) {
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
            return new Tuple3<>(n, n, matrix);

        } catch (FileNotFoundException e) {
            System.out.println("File tidak ditemukan.");
            return new Tuple3<>(0, 0, null);
        }
    }

    public void saveOutput(Double result) {
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

        String folder = "test/determinan/output/";
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
                    writer.write("Determinan = " + result);
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

    public void printDeterminant(double determinant) {
        pprint.showResult();
        System.out.printf("Determinan: %.2f\n", determinant);
    }

}
