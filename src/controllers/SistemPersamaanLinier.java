package src.controllers;

// view

// data types
import src.datatypes.Array;
import src.datatypes.Matrix;
import src.datatypes.Tuple3;
import src.helpers.GetConst;
import src.helpers.GetMainMatrix;
import src.models.sistemPersamaanLinier.Gauss;
import src.models.sistemPersamaanLinier.GaussJordan;
import src.models.sistemPersamaanLinier.MatriksBalikan;
import src.models.sistemPersamaanLinier.KaidahCramer;
import src.views.sistemPersamaanLinier.SistemPersamaanLinierView;

public class SistemPersamaanLinier {
    private SistemPersamaanLinierView view;
    private GetMainMatrix getMainMatrix = new GetMainMatrix();

    public boolean isSingular(Matrix matrix) {
        if (matrix.getRowCount() != matrix.getColumnCount()-1) {
            return true;
        }
        if (getMainMatrix.main(matrix).determinant() == 0) {
            return true;
        }
        return false;
    }

    public SistemPersamaanLinier() {
        view = new SistemPersamaanLinierView();
    }

    public Array matriksBalikan() {
        Tuple3<Integer, Integer, Matrix> input = view.getInput();
        if (isSingular(input.getItem3())) {
            view.showSingular(0);
            return null;
        }
        MatriksBalikan matriksBalikan = new MatriksBalikan();
        Matrix result = matriksBalikan.main(input);
        return result.flatten();
    }

    public Array gaussJordan() {
        GaussJordan gaussJordan = new GaussJordan();
        Tuple3<Integer, Integer, Matrix> input = view.getInput();

        if (input.getItem1() == input.getItem2()) {
            Matrix matrix = gaussJordan.main(input);
            if (matrix.getRowCount() == 1 && matrix.getColumnCount() == 1) {
                view.showSingular(matrix.get(0, 0).intValue());
                return null;
            }
            Matrix result = new GetConst().getConst(matrix);
            return result.flatten();
        }

        view.showSingular(0);
        return null;
    }

    public Array gauss() {
        Gauss gauss = new Gauss();
        Tuple3<Integer, Integer, Matrix> input = view.getInput();
        Array result = gauss.main(input);
        if (result.getSize() == 1) {
            view.showSingular(result.get(0).intValue());
            return null;
        }
        return result;
    }

    public Array kaidahCramer() {
        Matrix augmentedMatrix = view.getSquareInput().getItem3();
        if (isSingular(augmentedMatrix)) {
            view.showSingular(0);
            return null;
        }
        KaidahCramer kaidahCramer = new KaidahCramer();
        Matrix result = kaidahCramer.main(augmentedMatrix);
        return result.flatten();
    }

    public void main() {
        int choice = view.getChoice();
        Array result = null;
        switch (choice) {
            case 1:
                result = gauss();
                if (result != null) {
                    view.printResult(result);
                }
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
                Array cramer_result = kaidahCramer();
                if (cramer_result != null) {
                    view.printResult(cramer_result);
                }
                break;
            default:
                break;
        }
    }
}
