package src.views.imageResizing;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import javax.imageio.ImageIO;

import src.helpers.GetString;
import src.helpers.Utils;
import src.views.Pprint;

public class ImageResizingViews {
    private String filepath = "test/imageResizing/"; 
    private Pprint pprint = new Pprint();

    public void showMenu() {
        showHeader();
        System.out.println("\033[1m>>> Available Method:");
        System.out.println("1. Proses gambar dari file");
        System.out.println("2. Keluar");
    }

    public void showHeader() {
        Utils.clearTerminal();
        String header = "\n\033[1m\033[32m" + GetString.main("imageResizing/header") + "\033[0m";
        System.out.println(header);
    }

    public int getChoice() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            showMenu();
            pprint.inputBoundary(false);
            System.out.print("\033[1mMasukkan pilihan: ");
            choice = scanner.nextInt();
        } while (choice < 1 || choice > 2);
        return choice;
    }

    public BufferedImage getInputFromFile() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\033[1mMasukkan nama file gambar: ");
        String filename = scanner.nextLine();
        String fullPath = filepath + filename + ".png";  
        
        try {
            File file = new File(fullPath);
            BufferedImage image = ImageIO.read(file); 
            System.out.println("\033[1mGambar berhasil dimuat dari " + fullPath);
            return image;
        } catch (FileNotFoundException e) {
            System.out.println("\033[31mFile tidak ditemukan di lokasi: " + fullPath);
            return null;
        } catch (IOException e) {
            System.out.println("\033[31mGagal membaca file gambar.");
            return null;
        }
    }

    public int getNewWidth() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\033[1mMasukkan lebar baru: ");
        return scanner.nextInt();
    }

    public int getNewHeight() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\033[1mMasukkan tinggi baru: ");
        return scanner.nextInt();
    }

    public void saveImageToFile(BufferedImage image, String outputFilename) {
        String fullPath = filepath + outputFilename + ".png";  
        try {
            File outputFile = new File(fullPath);
            ImageIO.write(image, "png", outputFile);
            System.out.println("\033[1mGambar hasil berhasil disimpan di " + fullPath);
        } catch (IOException e) {
            System.out.println("\033[31mGagal menyimpan gambar hasil.");
        }
    }

    public void showProcessingMessage() {
        System.out.println("\033[1mSedang memproses gambar...\033[0m");
    }

    public void showCompletionMessage() {
        System.out.println("\033[1mProses selesai. Gambar telah diproses.\033[0m");
    }
}
