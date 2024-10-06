package src.controllers;

import src.datatypes.Array;
import src.datatypes.Matrix;
import src.datatypes.Tuple3;
import src.helpers.GetConst;
import src.models.sistemPersamaanLinier.Gauss;
import src.models.sistemPersamaanLinier.GaussJordan;
import src.models.sistemPersamaanLinier.MatriksBalikan;
import src.views.sistemPersamaanLinier.SistemPersamaanLinierView;

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

    public void main() {
        int choice = view.getChoice();
        GetConst getConst = new GetConst();
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
