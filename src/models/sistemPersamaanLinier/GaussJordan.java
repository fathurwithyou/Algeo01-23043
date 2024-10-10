package src.models.sistemPersamaanLinier;

import src.datatypes.Matrix;
import src.datatypes.Tuple3;
import src.helpers.ForwardElimination;
import src.helpers.AlignMatrix;
import src.helpers.BackwardElimination;
import src.helpers.NormalizeMatrix;

public class GaussJordan {
    private CheckConsistency checkConsistency = new CheckConsistency();
    private NormalizeMatrix normalizeMatrix = new NormalizeMatrix();

    public Matrix gaussJordanElimination(Integer n, Integer m, Matrix matrix) {
        AlignMatrix alignMatrix = new AlignMatrix();
        ForwardElimination forwardElimination = new ForwardElimination();
        BackwardElimination backwardElimination = new BackwardElimination();
        alignMatrix.alignMatrix(matrix);
        int flag = checkConsistency.checkConsistency(n, m, matrix);
        if (flag != -1) {
            Matrix singular = new Matrix(1, 1);
            singular.set(0, 0, (double) flag);
            return singular;
        }

        forwardElimination.forwardElimination(n, m, matrix);
        flag = checkConsistency.checkConsistency(n, m, matrix);
        if (flag != -1) {
            Matrix singular = new Matrix(1, 1);
            singular.set(0, 0, (double) flag);
            return singular;
        }

        backwardElimination.backwardElimination(n, m, matrix);
        normalizeMatrix.normalizeMatrix(n, m, matrix);
        return matrix;
    }

    public Matrix main(Tuple3<Integer, Integer, Matrix> data) {
        int n = data.getItem1();
        int m = data.getItem2();
        Matrix result = gaussJordanElimination(n, m, data.getItem3());
        return result;
    }
}
