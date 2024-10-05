package src.models.determinan;

import src.datatypes.Matrix;
import src.datatypes.Tuple3;
import src.helpers.AlignMatrix;
import src.helpers.ForwardElimination;
import src.helpers.NormalizeMatrix;

public class ReduksiBaris {
    private AlignMatrix alignMatrix = new AlignMatrix();
    private ForwardElimination forwardElimination = new ForwardElimination();
    private NormalizeMatrix normalizeMatrix = new NormalizeMatrix();

    public double calculateDeterminant(Matrix matrix) {
        double determinant = 1.0;
        int n = matrix.getRowCount();
        for (int i = 0; i < n; i++) {
            determinant *= matrix.get(i, i);
        }
        return determinant;
    }

    public Tuple3<Integer, Matrix, Double> reduksiBarisElimination(Matrix matrix) {
        int n = matrix.getRowCount();
        alignMatrix.alignMatrix(matrix); 
        forwardElimination.forwardElimination(n, n, matrix);
        normalizeMatrix.normalizeMatrix(n, n, matrix);
        double determinant = calculateDeterminant(matrix); 
    
        return new Tuple3<>(n, matrix, determinant);
    }

    public Tuple3<Integer, Matrix, Double> main(Matrix matrix) {
        return reduksiBarisElimination(matrix);
    }
}
