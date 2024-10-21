package src.views;

import src.helpers.GetString;

public class Pprint {
    private String root = "src/views/";

    public void inputMatrix(){
        String boundary = "\n\033[1m\033[31m" + GetString.main("matrix") + "\033[0m";
        System.out.println(boundary);        
    }

    public void inputBoundary(boolean all) {
        String boundary = "\n\033[1m\033[34m" + GetString.main("input") + "\033[0m";

        System.out.println(boundary);
        if (all) {

            System.out.println("\033[1mEnter command in number format.");
            System.out.print("Input: \033[0m");
        }
    }

    public void inputBoundary() {
        inputBoundary(true);
    }

    public void showData() {
        String boundary = "\n\033[1m\033[33m" + GetString.main("data") + "\033[0m";
        System.out.println(boundary);
    }

    public void showResult() {
        String boundary = "\n\033[1m\033[32m" + GetString.main("result") + "\033[0m";
        System.out.println(boundary);
    }
}
