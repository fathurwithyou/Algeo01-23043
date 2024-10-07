package src.controllers;

// view
import src.views.sistemPersamaanLinier.SistemPersamaanLinierView;

// model
import src.models.sistemPersamaanLinier.MatriksBalikan;
import src.models.sistemPersamaanLinier.GaussJordan;

// helpers
import src.helpers.GetConst;
import src.helpers.ReshapeConst;
// data types
import src.datatypes.Matrix;
import src.datatypes.Array;
import src.datatypes.Tuple3;

public class SistemPersamaanLinier {
    private SistemPersamaanLinierView view;

    public SistemPersamaanLinier() {
        view = new SistemPersamaanLinierView();
    }

    public Array matriksBalikan() {
        Tuple3<Integer, Integer, Matrix> input = view.getInput();
        MatriksBalikan matriksBalikan = new MatriksBalikan();
        if (input.getItem1() == input.getItem2()) {
            Matrix result = matriksBalikan.main(input);
            if (result.getRowCount() == 1 && result.getColumnCount() == 1) {
                view.showSingular(result.get(0, 0).intValue());
                return null;
            }
            return new ReshapeConst().reshapeConst(result, input.getItem1(), 1);
        }
        return null;

    }

    public Array gaussJordan() {
        GaussJordan gaussJordan = new GaussJordan();
        Tuple3<Integer, Integer, Matrix> input = view.getInput();

        if (input.getItem1() == input.getItem2()) {
            Matrix matrix = gaussJordan.main(input);
            // view.printMatrix(matrix);
            if (matrix.getRowCount() == 1 && matrix.getColumnCount() == 1) {
                view.showSingular(matrix.get(0, 0).intValue());
                return null;
            }
            Matrix result = new GetConst().getConst(matrix);
            return result.getRow(0);
        }

        view.showSingular(0);
        return null;
    }

    public void main() {
        int choice = view.getChoice();
        Array result = null;
        switch (choice) {
            case 1:
                // Metode eliminasi Gauss
                break;
            case 2:
                result = gaussJordan();
                if (result != null)
                    view.printResult(result);
                break;
            case 3:
                result = matriksBalikan();
                if (result != null)
                    view.printResult(result);
                break;
            case 4:
                // Kaidah Cramer
                break;
            default:
                break;
        }
    }
}
