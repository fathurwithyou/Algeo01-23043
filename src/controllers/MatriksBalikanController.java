package src.controllers;

import src.views.matriksBalikan.MatriksBalikanView;

import src.datatypes.Matrix;
import src.datatypes.Tuple3;
import src.models.determinan.EkspansiKofaktor;
import src.models.matriksBalikan.AdjoinMethod;
import src.models.matriksBalikan.GaussJordanMethod;

public class MatriksBalikanController {
    private MatriksBalikanView view = new MatriksBalikanView();
    private AdjoinMethod adjoinMethod = new AdjoinMethod();
    public GaussJordanMethod gaussJordanMethod = new GaussJordanMethod();

    public boolean isSingular(Matrix matrix) {
        return new EkspansiKofaktor().main(matrix) == 0.0;
    }

    public Matrix adjoinMethod() {
        return adjoinMethod.main(view.getInput().getItem3());
    }

    public Matrix gaussJordanMethod() {
        Tuple3<Integer, Integer, Matrix> input = view.getInput();
        Matrix matrix = input.getItem3();
        if (isSingular(matrix)) {
            view.showSingular();
            return null;
        }
        Matrix result = gaussJordanMethod.main(matrix);
        return result;
    }

    public void main() {
        int choice = view.getChoice();
        Matrix matrix = null;
        switch (choice) {
            case 1:
                matrix = gaussJordanMethod();
                if (matrix != null)
                    view.printMatrix(matrix);
                break;
            case 2:
                matrix = adjoinMethod();
                if (matrix != null)
                    view.printMatrix(matrix);
                break;
            case 3:
                System.out.println("Keluar");
                break;
        }
    }

}
