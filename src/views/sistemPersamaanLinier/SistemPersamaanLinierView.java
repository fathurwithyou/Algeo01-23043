package src.views.sistemPersamaanLinier;

import java.util.*;
import src.datatypes.Array;
import src.datatypes.Matrix;
import src.datatypes.Tuple3;

public class SistemPersamaanLinierView {

    /** Menampilkan Opsi Sistem Persamaan Linear **/
    public void showMenu() {
        System.out.println("Sistem Persamaan Linier");
        System.out.println("1. Metode eliminasi Gauss");
        System.out.println("2. Metode eliminasi Gauss-Jordan");
        System.out.println("3. Metode matriks balikan");
        System.out.println("4. Kaidah Cramer");
        System.out.println("5. Keluar");
    }

    /** Memilih Nomor Opsi Sistem Persamaan Linear**/
    public int getChoice() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            showMenu();
            System.out.print("Pilihan: ");
            choice = scanner.nextInt();
        } while (choice < 1 || choice > 5);
        return choice;
    }

    /** Menerima Input Peubah (n), Baris (m), dan Augmented Matrix **/
    public Tuple3<Integer, Integer, Matrix> getInput() {

        //Input n dan m
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan n (banyak peubah): ");
        int n = scanner.nextInt();
        System.out.print("Masukkan m (banyak persamaan): ");
        int m = scanner.nextInt();

        //Membuat dan menerima matriks augmented
        Matrix coefMatrix = new Matrix(m, n + 1);

        System.out.println("Masukkan matriks augmented:");
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n+1; j++) {
                Double value = scanner.nextDouble();
                coefMatrix.set(i, j, value);
            }
        }
        //Mengembalikan Properti Matriks
        return new Tuple3<>(m, n+1, coefMatrix);
    }

    /** Menampilkan Output Bukan Solusi **/
    public void showSingular(int flag) {
        if (flag != -1) {
            if (flag == 0) {
                System.out.println("Sistem persamaan linier memiliki banyak solusi.");
            }
            else{
                System.out.println("Sistem persamaan linier tidak memiliki solusi.");
            }
        }
    }

    /** Menampilkan Matriks **/
    public void printMatrix(Matrix matrix) {
        for (int i = 0; i < matrix.getRowCount(); i++) {
            for (int j = 0; j < matrix.getColumnCount(); j++) {
                System.out.print(matrix.get(i, j) + " ");
            }
            System.out.println();
        }
    }

    /** Menampilkan Array **/
    public void printArray(Array array) {
        for (int i = 0; i < array.getSize(); i++) {
            System.out.print(array.get(i) + " ");
            System.out.println();
        }
    }
}
