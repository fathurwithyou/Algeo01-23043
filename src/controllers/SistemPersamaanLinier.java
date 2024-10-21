package src.controllers;

import src.views.Menu;
import src.views.Pprint;
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
import src.helpers.Utils;
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
    private Pprint pprint = new Pprint();
    private Tuple3<Integer, Integer, Matrix> input;
    private Matrix matrix;
    private int n, m;

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

    public void getInput(int c) {
        view.showHeader(c);
        int method = new Menu().getMethod();
        if (method == 1) {
            input = view.getInputFromFile();
            pprint.inputMatrix();
            Utils.printMatrix(input.getItem3());
            matrix = input.getItem3();
        } else {
            pprint.inputMatrix();
            input = view.getInput();
            matrix = input.getItem3();
        }
    }

    public void main() {
        int choice = view.getChoice();
        getInput(choice);
        Array result = null;
        switch (choice) {
            case 1:
                result = gauss();
                break;
            case 2:
                result = gaussJordan();
                break;
            case 3:
                result = matriksBalikan();
                break;
            case 4:
                result = kaidahCramer();

                break;
            default:
                break;
        }
        if (result != null) {
            pprint.showResult();
            view.printResult(result);
        }
    }
}
