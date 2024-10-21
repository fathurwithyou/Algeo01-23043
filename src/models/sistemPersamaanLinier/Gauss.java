package src.models.sistemPersamaanLinier;

import src.datatypes.Array;
import src.datatypes.Matrix;
import src.helpers.AlignMatrix;
import src.helpers.BackwardSubstitution;
import src.helpers.ForwardElimination;
import src.helpers.NormalizeMatrix;

public class Gauss {
    private NormalizeMatrix normalizeMatrix = new NormalizeMatrix();

    public Matrix gaussElimination(Integer m, Integer n, Matrix matrix) {

        AlignMatrix alignMatrix = new AlignMatrix();
        ForwardElimination forwardElimination = new ForwardElimination();
        alignMatrix.alignMatrix(matrix);

        forwardElimination.forwardElimination(m, n, matrix);

        normalizeMatrix.normalizeMatrix(m, n, matrix);

        return matrix;
    }

    public Array main(Matrix data) {
        BackwardSubstitution backwardSubstitution = new BackwardSubstitution();
        int m = data.getRowCount();
        int n = data.getColumnCount() - 1;
        Matrix result = gaussElimination(m, n, data);
        Array solution = backwardSubstitution.backwardSubstitution(result);
        return solution;
    }

}
