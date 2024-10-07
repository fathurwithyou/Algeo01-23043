package src.controllers;

// View
import src.views.determinan.DeterminanView;

// Model
import src.models.determinan.ReduksiBaris;
import src.models.determinan.EkspansiKofaktor;
import src.models.determinan.Matrix2x2;

// Data types
import src.datatypes.Matrix;
import src.datatypes.Tuple3;

public class Determinan {
    private DeterminanView view;

    public Determinan() {
        view = new DeterminanView();
    }

    public double matrix2x2() {
        Tuple3<Integer, Integer, Matrix> input = view.getInput();
        Matrix matrix = input.getItem3();

        // validasi input
        if (matrix.getRowCount() != 2 || matrix.getColumnCount() != 2) {
            System.out.println("Matriks harus berukuran 2x2.");
            return Double.NaN; // kalau error return NaN, kalau mau while loop sampai hasil benar boleh
        }

        Matrix2x2 matrix2x2 = new Matrix2x2();
        double result = matrix2x2.main(matrix);
        return result;
    }

    public double reduksiBaris() {
        Tuple3<Integer, Integer, Matrix> input = view.getInput();
        Matrix matrix = input.getItem3();
        ReduksiBaris reduksiBaris = new ReduksiBaris();
        double result = reduksiBaris.main(matrix);
        return result;
    }
    
    public double ekspansiKofaktor(){
        Tuple3<Integer, Integer, Matrix> input = view.getInput();
        Matrix matrix = input.getItem3();
        double result = new EkspansiKofaktor().main(matrix);
        return result;
    }

    public void main() {
        int choice = view.getChoice();
        switch (choice) {
            case 1:
                double matrix2x2_result = matrix2x2();
                view.printDeterminant(matrix2x2_result);
                break;
            case 2:
                double reduksibaris_result = reduksiBaris();
                view.printDeterminant(reduksibaris_result);
                break;
            case 3:
                double ekspansikofaktor_result = ekspansiKofaktor();
                view.printDeterminant(ekspansikofaktor_result);
                break;
            default:
                break;
        }
    }
}
