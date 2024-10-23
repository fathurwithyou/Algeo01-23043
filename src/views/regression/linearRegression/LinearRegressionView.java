package src.views.regression.linearRegression;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import src.datatypes.Matrix;
import src.helpers.GetString;
import src.helpers.Utils;
import src.views.regression.RegressionView;
import src.views.Menu;
import src.views.Pprint;

public class LinearRegressionView extends RegressionView {
    private Menu menu = new Menu();
    private Pprint pprint = new Pprint();

    @Override
    public void showMenu() {
        System.out.println("Regresi Linear Berganda");
        System.out.println("1. Metode OLS");
        System.out.println("2. Metode Ridge");
        System.out.println("3. Keluar");
    }

    @Override
    public int getChoice() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            showMenu();
            System.out.print("Pilihan: ");
            choice = scanner.nextInt();
        } while (choice < 1 || choice > 3);
        return choice;
    }

    public void showHeader() {
        Utils.clearTerminal();
        String header = "\n\033[1m\033[32m" + GetString.main("regression/linearRegression/header") + "\033[0m";
        System.out.println(header);
    }

    public void printOutput(Matrix beta, Matrix y_pred, StringBuilder savedString) {
        pprint.showResult();
        savedString.append("f(X) = ").append(String.format("%.2f", beta.get(0, 0)));

        for (int i = 1; i < beta.getRowCount(); i++) {
            double coeff = beta.get(i, 0);
            if (coeff >= 0) {
                savedString.append(" + ").append(String.format("%.2f", coeff)).append("x").append(i);
            } else {
                savedString.append(" - ").append(String.format("%.2f", Math.abs(coeff))).append("x").append(i);
            }
        }

        savedString.append("\n"); // Add a new line after the equation

        for (int i = 0; i < y_pred.getRowCount(); i++) {
            savedString.append("f(x").append(i + 1).append(") = ")
                    .append(String.format("%.2f", y_pred.get(i, 0)))
                    .append("\n");
        }

        System.out.print("\033[1m" + savedString.toString());
    }

    public void saveOutput(StringBuilder result) {
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

        String folder = "test/linearRegression/output/";
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
                    writer.write(result.toString());
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
}
