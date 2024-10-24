package src.models.sistemPersamaanLinier;

import src.datatypes.Matrix;
import src.helpers.GetConst;
import src.helpers.MatrixCopy;

public class KaidahCramer {
    private MatrixCopy matrixCopy = new MatrixCopy();
    private GetConst getConst = new GetConst();

    public Matrix replaceColumn(int n, Matrix matrix, int column, Matrix constantMatrix) {
        Matrix result = new Matrix(n, n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (j == column) {
                    result.set(i, j, constantMatrix.get(i, 0));
                } else {
                    result.set(i, j, matrix.get(i, j));
                }
            }
        }
        return result;
    }

    public Matrix main(Matrix augmentedMatrix) {
        int n = augmentedMatrix.getRowCount();
        Matrix coefMatrix = new Matrix(n, n);
        Matrix constMatrix = getConst.getConst(augmentedMatrix);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                coefMatrix.set(i, j, augmentedMatrix.get(i, j));
            }
        }

        double detA = matrixCopy.copy(coefMatrix).determinant(); 
        
        Matrix solution = new Matrix(n, 1); 
        for (int i = 0; i < n; i++) {
            Matrix replacedMatrix = replaceColumn(n, matrixCopy.copy(coefMatrix), i, constMatrix);
            double detAi = replacedMatrix.determinant() ; 
            solution.set(i, 0, detAi / detA);
        }

        return solution;
    }
}
