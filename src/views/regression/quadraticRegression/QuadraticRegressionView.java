package src.views.regression.quadraticRegression;

import java.util.Scanner;

import src.views.regression.RegressionView;

public class QuadraticRegressionView extends RegressionView {

    public void showMenu() {
        System.out.println("Regresi Kuadratik");
        System.out.println("1. Hitung Regresi Kuadratik");
        System.out.println("2. Keluar");
    }

    public int getChoice() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            showMenu();
            System.out.print("Pilihan: ");
            choice = scanner.nextInt();
        } while (choice < 1 || choice > 2);
        return choice;
    }
}
