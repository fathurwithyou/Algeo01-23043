package src.helpers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GetString {
    public static String main(String filename) {
        String root = "src/views/";
        try {
            StringBuilder asciiArt = new StringBuilder();

            BufferedReader reader = new BufferedReader(new FileReader(root + filename + ".txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                asciiArt.append(line).append("\n");
            }
            reader.close();
            return asciiArt.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
