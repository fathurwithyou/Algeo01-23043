package src.models.determinan;

import src.datatypes.Matrix;
import src.helpers.AlignMatrix;
import src.helpers.BackwardElimination;

public class ReduksiBaris {
    private AlignMatrix alignMatrix = new AlignMatrix();
    private BackwardElimination backwardElimination = new BackwardElimination();
    private Triangular triangular = new Triangular();

    public Matrix reduksiBarisElimination(Matrix matrix) {
        int n = matrix.getRowCount();
        alignMatrix.alignMatrix(matrix); 
        backwardElimination.backwardElimination(n, n, matrix);
        return matrix;
    }

    public double calculateDeterminant(Matrix matrix) {
        double determinant = 1.0;
        int n = matrix.getRowCount();
        for (int i = 0; i < n; i++) {
            determinant *= matrix.get(i, i);
        }
        return determinant;
    }

    public double main(Matrix matrix) {
        triangular.makeUpperTriangular(matrix);
        return calculateDeterminant(matrix);
    }
}
