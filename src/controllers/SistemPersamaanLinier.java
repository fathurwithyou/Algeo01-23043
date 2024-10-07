package src.controllers;

// view
import src.views.sistemPersamaanLinier.SistemPersamaanLinierView;

// model
import src.models.sistemPersamaanLinier.MatriksBalikan;
import src.models.sistemPersamaanLinier.GaussJordan;
import src.models.sistemPersamaanLinier.KaidahCramer;
// data types
import src.datatypes.Matrix;
import src.datatypes.Tuple3;

public class SistemPersamaanLinier {
    private SistemPersamaanLinierView view;

    public SistemPersamaanLinier() {
        view = new SistemPersamaanLinierView();
    }

    public void matriksBalikan() {
        Tuple3<Integer, Integer, Matrix> input = view.getInput();
        MatriksBalikan doMatriksBalikan = new MatriksBalikan();
        doMatriksBalikan.main(input);
    }

    public Matrix gaussJordan() {
        GaussJordan gaussJordan = new GaussJordan();
        Tuple3<Integer, Integer, Matrix> input = view.getInput();
        Matrix result = gaussJordan.main(input);
        if (result.getRowCount() == 1 && result.getColumnCount() == 1) {
            view.showSingular(result.get(0, 0).intValue());
            return null;
        }
        return result;
    }

    public void kaidahCramer() {
        Matrix augmentedMatrix = view.getSquareInput().getItem3();
        KaidahCramer kaidahCramer = new KaidahCramer();
        Matrix result = kaidahCramer.main(augmentedMatrix);    
        
        if (result != null) {
            if (result.getRowCount() == 1 && result.getColumnCount() == 1) {
                int singularValue = result.get(0, 0).intValue();
                view.showSingular(singularValue);
            } else {
                view.printMatrix(result);
            }
        } else {
            view.showSingular(0);
        }
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
                kaidahCramer();
                break;
            default:
                break;
        }
    }
}
