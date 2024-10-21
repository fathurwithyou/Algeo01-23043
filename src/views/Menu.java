package src.views;

import java.util.*;
import java.io.*;

import src.helpers.GetString;
import src.helpers.Utils;

public class Menu {
    private Pprint pprint = new Pprint();

   

    public void showMenu() {
        String coloredAsciiArt = "\033[32m" + GetString.main("ascii") + "\033[0m";
        System.out.println(coloredAsciiArt);

        System.out.println("\033[1m>>> Available Methods:");
        System.out.println("1. Sistem Persamaan Linier");
        System.out.println("2. Determinan");
        System.out.println("3. Matriks Balikan");
        System.out.println("4. Interpolasi Polinom");
        System.out.println("5. Interpolasi Bicubic Spline");
        System.out.println("6. Regresi Linier dan Kuadratik Berganda");
        System.out.println("7. Interpolasi Gambar");
        System.out.println("8. Keluar");

    }

    public int getMethod() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\033[1m>>> Available Input Methods:");
            System.out.println("1. File");
            System.out.println("2. Stdin");
            pprint.inputBoundary();
            choice = scanner.nextInt();
        } while (choice < 1 || choice > 2);
        return choice;
    }

    public int getChoice() {
        Scanner scanner = new Scanner(System.in);
        String temp;
        int choice;
        do {
            Utils.clearTerminal();
            showMenu();
            pprint.inputBoundary();
            temp = scanner.nextLine();
            try {
                choice = Integer.parseInt(temp);
            } catch (NumberFormatException e) {
                choice = 0;
            }
            if (choice < 1 || choice > 8) {
                System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }

        } while (choice < 1 || choice > 8);
        return choice;
    }
}
