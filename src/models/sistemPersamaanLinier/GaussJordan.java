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


    public Matrix gaussJordanElimination(Integer m, Integer n, Matrix matrix) {
        AlignMatrix alignMatrix = new AlignMatrix();
        ForwardElimination forwardElimination = new ForwardElimination();
        BackwardElimination backwardElimination = new BackwardElimination();
        alignMatrix.alignMatrix(matrix);
        int flag = checkConsistency.checkConsistency(m, n, matrix);
        if(flag != -1) {
            Matrix singular = new Matrix(1, 1);
            singular.set(0, 0, (double)flag);
            return singular;
        }

        forwardElimination.forwardElimination(m, n, matrix);
        flag = checkConsistency.checkConsistency(m, n, matrix);
        if(flag != -1) {
            Matrix singular = new Matrix(1, 1);
            singular.set(0, 0, (double)flag);
            return singular;
        }

        backwardElimination.backwardElimination(m, n, matrix);
        normalizeMatrix.normalizeMatrix(m, n, matrix);
        return matrix;
    }

    public Matrix main(Tuple3<Integer, Integer, Matrix> data) {
        int m = data.getItem1();
        int n = data.getItem2();
        Matrix result = gaussJordanElimination(m, n, data.getItem3());
        return result;
    }
}
