package src.views.matriksBalikan;

import java.util.Scanner;
import src.datatypes.Matrix;
import src.datatypes.Tuple3;
import src.helpers.GetString;
import src.helpers.Utils;
import src.views.Pprint;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MatriksBalikanView {
    private String[] filepath = new String[] { "matriksBalikan/header", "matriksBalikan/gaussJordan", "matriksBalikan/adjoin" };
    private Pprint pprint = new Pprint();

    public void showMenu() {
        showHeader(0);
        System.out.println("\033[1m>>> Available Methods:");
        System.out.println("1. Metode GaussJordanElimination");
        System.out.println("2. Metode Adjoint");
        System.out.println("3. Keluar");
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
        } while (choice < 1 || choice > 3);
        return choice;
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
        System.out.print("\033[1mFilename: ");
        String filename = scanner.nextLine();
        try {
            File file = new File("test/matriksBalikan/input/" + filename + ".txt");
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
            Matrix tmp = new Matrix(n, n);

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    tmp.set(i, j, X.get(i).get(j));
                }
            }
            return new Tuple3<>(n, n, tmp);

        } catch (FileNotFoundException e) {
            System.out.println("File tidak ditemukan.");
            return new Tuple3<>(0, 0, null);
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

        String folder = "test/matriksBalikan/output/";
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

    public void showSingular() {
        System.out.println("Matriks singular");
    }

}
