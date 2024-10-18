package src.controllers;

// view
import src.views.sistemPersamaanLinier.SistemPersamaanLinierView;

// data types
import java.util.List;
import src.datatypes.Array;
import src.datatypes.Matrix;
import src.datatypes.Tuple3;

// helpers
import src.helpers.CheckSolutionType;
import src.helpers.GetConst;
import src.helpers.GetMainMatrix;
import src.helpers.GetUniqueEquation;
// models
import src.models.sistemPersamaanLinier.Gauss;
import src.models.sistemPersamaanLinier.GaussJordan;
import src.models.sistemPersamaanLinier.MatriksBalikan;
import src.models.sistemPersamaanLinier.KaidahCramer;

public class SistemPersamaanLinier {
    private SistemPersamaanLinierView view;
    private GetMainMatrix getMainMatrix = new GetMainMatrix();
    private CheckSolutionType check = new CheckSolutionType();
    private GetUniqueEquation getUniqueEquation = new GetUniqueEquation();

    public boolean isSingular(Matrix matrix) {
        if (matrix.getRowCount() != matrix.getColumnCount() - 1) {
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
        int sol = check.checkSolutionType(input.getItem3());
        if (sol == 0) {
            view.showSingular(0);
            return null;
        }
        if (sol == 2) {
            view.showSingular(2);
            List<String> res = gaussJordan.gaussJordanFreeVariable(input.getItem3());
            view.showFreeVariable(res);
            return null;
        }

        Matrix result = gaussJordan.main(getUniqueEquation.main(input.getItem3()));
        result = new GetConst().getConst(result);
        return result.flatten();
    }

    public Array gauss() {
        Gauss gauss = new Gauss();
        Tuple3<Integer, Integer, Matrix> input = view.getInput();
        int sol = check.checkSolutionType(input.getItem3());
        if (sol == 0) {
            view.showSingular(0);
            return null;
        }
        if (sol == 2) {
            view.showSingular(2);
            List<String> res = new GaussJordan().gaussJordanFreeVariable(input.getItem3());
            view.showFreeVariable(res);
            return null;
        }

        Array result = gauss.main(getUniqueEquation.main(input.getItem3()));
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
                result = kaidahCramer();
                if (result != null) {
                    view.printResult(result);
                }
                break;
            default:
                break;
        }
    }
}
