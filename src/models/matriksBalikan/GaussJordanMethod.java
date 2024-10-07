package src.models.matriksBalikan;

import src.datatypes.Matrix;

import src.helpers.AddIdentity;
import src.models.sistemPersamaanLinier.GaussJordan;
import src.views.matriksBalikan.MatriksBalikanView;

public class GaussJordanMethod {
    private GaussJordan gaussJordan = new GaussJordan();
    private MatriksBalikanView view = new MatriksBalikanView();
    public Matrix extractMatrix(Integer n, Matrix matrix) {
        // extract start from n to n*2 and m to m*2
        Matrix result = new Matrix(n, n);
        for (int i = 0; i < n; i++) {
            for (int j = n; j < n * 2; j++) {
                result.set(i, j - n, matrix.get(i, j));
            }
        }
        return result;
    }

    public Matrix inverseMatrix(Integer n, Integer m, Matrix matrix) {
        matrix = gaussJordan.gaussJordanElimination(n, m, matrix);
        view.printMatrix(matrix);
        if (matrix.getRowCount() == 1 && matrix.getColumnCount() == 1){
            return matrix;
        }
        matrix = extractMatrix(n, matrix);
        return matrix;
    }

    public Matrix main(Matrix matrix){
        Integer n = matrix.getRowCount();
        Integer m = matrix.getColumnCount();
        matrix = inverseMatrix(n, m, new AddIdentity().addIdentity(matrix));
        return matrix;
    }
}
