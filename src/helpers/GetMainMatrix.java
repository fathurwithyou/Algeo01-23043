package src.helpers;

import src.datatypes.Matrix;

public class GetMainMatrix {
    public Matrix main(Matrix matrix) {
        int rows = matrix.getRowCount();
        Matrix mainMatrix = new Matrix(rows, rows);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < rows; j++) {
                mainMatrix.set(i, j, matrix.get(i, j));
            }
        }
        return mainMatrix;
    }
}
