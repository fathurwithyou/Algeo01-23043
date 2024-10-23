package src.views.interpolasiPolinom;

import java.util.Scanner;
import src.datatypes.Matrix;
import src.datatypes.Tuple3;
import src.datatypes.Tuple4;
import src.datatypes.Tuple5;
import src.helpers.GetString;
import src.helpers.Utils;
import src.views.Pprint;
import src.datatypes.Array;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InterpolasiPolinomView {
    Pprint pprint = new Pprint();
    String filepath[] = new String[] { "interpolasiPolinom/header" };
    String ptest = "test/interpolasiPolinom/input/";

    public void showHeader(int c) {
        Utils.clearTerminal();
        String header = "\n\033[1m\033[32m" + GetString.main(filepath[c]) + "\033[0m";
        System.out.println(header);
    }

    public Tuple4<Integer, Integer, Matrix, Matrix> getInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan n (jumlah sampel): ");
        int n = scanner.nextInt();
        Matrix X = new Matrix(n, 1);
        Matrix Y = new Matrix(n, 1); // sampai sini
        System.out.println("Masukkan x y:");
        for (int i = 0; i < n; i++) {
            Double valueX = scanner.nextDouble();
            X.set(i, 0, valueX);
            Double valueY = scanner.nextDouble();
            Y.set(i, 0, valueY);
        }
        return new Tuple4<>(n, 1, X, Y);
    }

    public Tuple5<Integer, Integer, Matrix, Matrix, Matrix> getInputFromFile(int degree) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan path file input: ");
        String filename = scanner.nextLine();
        try {
            File file = new File(ptest + filename + ".txt");
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

    public Tuple3<Integer, Integer, Matrix> getInputToPredict(int n) {
        Scanner scanner = new Scanner(System.in);
        Matrix X = new Matrix(n, 1);
        System.out.println("Masukkan nilai yang ingin diprediksi:");
        for (int i = 0; i < n; i++) {
            Double value = scanner.nextDouble();
            X.set(i, 0, value);
        }
        return new Tuple3<>(n, 1, X);
    }

    public void printPrediction(Array beta, Double X_tst, Double y_pred, StringBuilder sb) {
        pprint.showResult(); 
    
        for (int i = 0; i < beta.getSize(); i++) {
            if (i == 0) {
                sb.append("f(x) = ").append(String.format("%.2f", beta.get(i)));
            } else {
                if (beta.get(i) >= 0) {
                    sb.append(" + ").append(String.format("%.2f", beta.get(i))).append("x^").append(i);
                } else {
                    sb.append(" - ").append(String.format("%.2f", Math.abs(beta.get(i)))).append("x^").append(i);
                }
            }
        }
        
        sb.append("\n");
        sb.append("f(").append(String.format("%.2f", X_tst)).append(") = ").append(String.format("%.2f", y_pred)).append("\n");
    
        System.out.print(sb.toString()); 
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

        String folder = "test/interpolasiPolinom/output/";
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
    
}
