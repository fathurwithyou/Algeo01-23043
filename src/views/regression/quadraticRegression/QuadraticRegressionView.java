package src.views.regression.quadraticRegression;

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

public class QuadraticRegressionView extends RegressionView {
    private Pprint pprint = new Pprint();

    public void showHeader() {
        Utils.clearTerminal();
        String header = "\n\033[1m\033[32m" + GetString.main("regression/quadraticRegression/header") + "\033[0m";
        System.out.println(header);
    }

    public void printOutput(Matrix beta, Matrix y_pred, int numFeatures, StringBuilder savedString) {
        pprint.showResult();
        savedString.append("f(X) = ").append(String.format("%.2f", beta.get(0, 0)));

        int index = 1;

        // Linear terms
        for (int i = 1; i <= numFeatures; i++) {
            double coeff = beta.get(index++, 0);
            if (coeff >= 0) {
                savedString.append(" + ").append(String.format("%.2f", coeff)).append("x").append(i);
            } else {
                savedString.append(" - ").append(String.format("%.2f", Math.abs(coeff))).append("x").append(i);
            }
        }

        // Squared terms
        for (int i = 1; i <= numFeatures; i++) {
            double coeff = beta.get(index++, 0);
            if (coeff >= 0) {
                savedString.append(" + ").append(String.format("%.2f", coeff)).append("x").append(i).append("^2");
            } else {
                savedString.append(" - ").append(String.format("%.2f", Math.abs(coeff))).append("x").append(i)
                        .append("^2");
            }
        }

        // Interaction terms
        for (int i = 1; i <= numFeatures; i++) {
            for (int j = i + 1; j <= numFeatures; j++) {
                double coeff = beta.get(index++, 0);
                if (coeff >= 0) {
                    savedString.append(" + ").append(String.format("%.2f", coeff)).append("x").append(i).append("x")
                            .append(j);
                } else {
                    savedString.append(" - ").append(String.format("%.2f", Math.abs(coeff))).append("x").append(i)
                            .append("x").append(j);
                }
            }
        }

        savedString.append("\n"); 
        
        for (int i = 0; i < y_pred.getRowCount(); i++) {
            savedString.append("f(x").append(i + 1).append(") = ")
                    .append(String.format("%.2f", y_pred.get(i, 0))).append("\n");
        }

        System.out.print(savedString.toString());
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

        String folder = "test/quadraticRegression/output/";
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
