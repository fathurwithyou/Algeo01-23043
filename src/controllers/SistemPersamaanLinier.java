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
    private String savedString;

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
            view.showSingular(-1);
            savedString = "Sistem persamaan linier ini tidak memiliki solusi";

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
            savedString = "Sistem persamaan linier ini tidak memiliki solusi";

            return null;
        }
        if (sol == 2) {
            view.showSingular(2);
            List<String> res = gaussJordan.gaussJordanFreeVariable(input.getItem3());
            savedString = res.toString();
            view.showFreeVariable(res);
            return null;
        }

        Array result = gaussJordan.main(getUniqueEquation.main(input.getItem3()));
        return result;
    }

    public Array gauss() {
        Gauss gauss = new Gauss();
        int sol = check.checkSolutionType(input.getItem3());
        if (sol == 0) {
            view.showSingular(0);
            savedString = "Sistem persamaan linier ini tidak memiliki solusi";
            return null;
        }
        if (sol == 2) {
            view.showSingular(2);
            List<String> res = new GaussJordan().gaussJordanFreeVariable(input.getItem3());
            view.showFreeVariable(res);
            savedString = res.toString();
            return null;
        }
        matrix = getUniqueEquation.main(input.getItem3());
        Array result = gauss.main(matrix);
        return result;
    }

    public Array kaidahCramer() {
        if (isSingular(matrix)) {
            view.showSingular(-1);
            savedString = "Sistem persamaan linier ini tidak memiliki solusi";

            return null;
        }
        KaidahCramer kaidahCramer = new KaidahCramer();
        Matrix result = kaidahCramer.main(matrix);
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
            input = view.getInput();
            matrix = input.getItem3();
            Utils.printMatrix(matrix);
        }
    }

    public void main() {
        int choice = view.getChoice();
        if (choice == 5) {
            pprint.thanks();
            return;
        }
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
            savedString = result.toString();
        }
        String resultString = savedString.substring(1, savedString.length() - 1); 
        String[] elements = resultString.split(", "); 
        resultString = String.join("\n", elements);
        view.saveOutput(resultString);
        pprint.thanks();
    }
}
