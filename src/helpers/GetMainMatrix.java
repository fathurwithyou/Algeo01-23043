package src.helpers;

import src.datatypes.Matrix;

public class GetMainMatrix {
    public Matrix main(Matrix matrix) {
        int rows = matrix.getRowCount();
        int cols = matrix.getColumnCount();
        Matrix mainMatrix = new Matrix(rows, cols-1);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols-1; j++) {
                mainMatrix.set(i, j, matrix.get(i, j));
            }
        }
        return mainMatrix;
    }
}
