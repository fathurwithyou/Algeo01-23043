package src.controllers;

import src.datatypes.Array;
import src.datatypes.Matrix;
import src.datatypes.Tuple3;
import src.helpers.ReshapeConst;
import src.models.sistemPersamaanLinier.Gauss;
import src.models.sistemPersamaanLinier.GaussJordan;
import src.models.sistemPersamaanLinier.MatriksBalikan;
import src.views.sistemPersamaanLinier.SistemPersamaanLinierView;

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
        Array result = gaussJordan.main(input);
        if (result.getSize() == 1) {
            view.showSingular(result.get(0).intValue());
            return null;
        }
        return result;
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
                Array resultGauss = gauss();
                if (resultGauss != null) {
                    System.out.println();
                    System.out.println("Solusi SPL");
                    view.printArray(resultGauss); }
                break;
            case 2:
                Array resultGaussJordan = gaussJordan();
                if (resultGaussJordan != null) {
                    System.out.println();
                    System.out.println("Solusi SPL");
                    view.printArray((resultGaussJordan)); }
                break;
            case 3:
                Array result = matriksBalikan();
                if (result != null)
                    view.printResult(result);
                break;
            case 4:
                kaidahCramer();
                break;
            default:
                break;
        }
    }
}
