package src.controllers;

// view
import src.views.sistemPersamaanLinier.SistemPersamaanLinierView;
import src.views.sistemPersamaanLinier.MatriksBalikanView;

// model
import src.models.sistemPersamaanLinier.MatriksBalikan;
import src.models.sistemPersamaanLinier.CheckConsistency;
import src.models.sistemPersamaanLinier.GaussJordan;

// data types
import src.datatypes.Matrix;
import src.datatypes.Tuple3;
import src.helpers.AlignMatrix;

public class SistemPersamaanLinier {
    private SistemPersamaanLinierView view;
    private AlignMatrix alignMatrix;

    public SistemPersamaanLinier() {
        view = new SistemPersamaanLinierView();
        alignMatrix = new AlignMatrix();
    }

    public void matriksBalikan() {
        Tuple3<Integer, Integer, Matrix> input = view.getInput();
        MatriksBalikan doMatriksBalikan = new MatriksBalikan();
        doMatriksBalikan.main(input);
    }

    public Matrix gaussJordan() {
        GaussJordan gaussJordan = new GaussJordan();
        Tuple3<Integer, Integer, Matrix> input = view.getInput();
        Matrix matrix = input.getItem3();
        Matrix result = gaussJordan.main(input);
        if (result.getRowCount() == 1 && result.getColumnCount() == 1) {
            view.showSingular(result.get(0, 0).intValue());
            return null;
        }
        return result;
    }

    public void main() {
        int choice = view.getChoice();
        switch (choice) {
            case 1:
                // Metode eliminasi Gauss
                break;
            case 2:
                Matrix result = gaussJordan();
                if (result != null)
                    view.printMatrix(result);
                break;
            case 3:
                matriksBalikan();
                break;
            case 4:
                // Kaidah Cramer
                break;
            default:
                break;
        }
    }
}
