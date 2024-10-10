package src.views.regression.linearRegression;

import java.util.Scanner;

import src.views.regression.RegressionView;

public class LinearRegressionView extends RegressionView {

    @Override
    public void showMenu() {
        super.showMenu(); // Call the parent class menu
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

    public double getAlpha() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan alpha: ");
        return scanner.nextDouble();
    }
}
