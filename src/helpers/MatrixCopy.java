package src.helpers;

import src.datatypes.Matrix;

public class MatrixCopy {
    public Matrix copy(Matrix original) {
        int rows = original.getRowCount();
        int cols = original.getColumnCount();
        Matrix copy = new Matrix(rows, cols);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                copy.set(i, j, original.get(i, j)); 
            }
        }
        return copy;
    }
}
