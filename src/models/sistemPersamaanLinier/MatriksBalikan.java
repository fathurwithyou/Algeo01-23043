package src.models.sistemPersamaanLinier;

import src.datatypes.*;
import src.helpers.GetConst;
import src.helpers.AddIdentity;

public class MatriksBalikan {
    private GaussJordan gaussJordan = new GaussJordan();

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
        if (matrix.getRowCount() == 1 && matrix.getColumnCount() == 1)
            return matrix;
        matrix = extractMatrix(n, matrix);
        return matrix;
    }

    public Matrix main(Tuple3<Integer, Integer, Matrix> data) {
        int n = data.getItem1();
        int m = data.getItem2();
        Matrix matrix = data.getItem3();
        Matrix b = new GetConst().getConst(matrix);
        matrix = new AddIdentity().addIdentity(matrix);
        Matrix invertedMatrix = inverseMatrix(n, m, matrix);
        Matrix result = invertedMatrix.multiply(b);
        return result;
    }
}
