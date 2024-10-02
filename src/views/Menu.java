package src.views;

import java.util.*;

public class Menu {
    public void showMenu() {
        System.out.println("MENU");
        System.out.println("1. Sistem Persamaan Linier");
        System.out.println("2. Determinan");
        System.out.println("3. Matriks Balikan");
        System.out.println("4. Interpolasi Polinom");
        System.out.println("5. Interpolasi Bicubic Spline");
        System.out.println("6. Regresi Linier dan Kuadratik Berganda");
        System.out.println("7. Interpolasi Gambar");
        System.out.println("8. Keluar");
        System.out.print("Pilih menu: ");
    }

    public int getChoice() {
        Scanner scanner = new Scanner(System.in);
        String temp;
        int choice;
        do {
            showMenu();
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
