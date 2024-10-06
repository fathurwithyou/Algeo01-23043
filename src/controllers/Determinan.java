package src.controllers;

// View
import src.views.determinan.DeterminanView;

// Model
import src.models.determinan.ReduksiBaris;
import src.models.sistemPersamaanLinier.GaussJordan;
// Data types
import src.datatypes.Matrix;
import src.datatypes.Tuple3;

public class Determinan {
    private DeterminanView view;

    public Determinan() {
        view = new DeterminanView();
    }

    public double reduksiBaris() {
        Tuple3<Integer, Integer, Matrix> input = view.getInput();
        Matrix matrix = input.getItem3();
        ReduksiBaris reduksiBaris = new ReduksiBaris();
        double result = reduksiBaris.main(matrix);
        view.showSingular(result);    
        return result;
    }

    public void main() {
        int choice = view.getChoice();
        switch (choice) {
            case 1:
                // determinan matriks 2 x 2
                break;
            case 2:
                reduksiBaris();
                break;
            case 3:
                // ekspansi kofaktor
                break;
            default:
                break;
        }
    }
}
