package src.helpers;

import src.datatypes.Matrix;

public class SwapRows {
    public void swapRows(Matrix matrix, int row1, int row2) {
        int cols = matrix.getColumnCount();
        for (int col = 0; col < cols; col++) {
            double temp = matrix.get(row1, col);
            matrix.set(row1, col, matrix.get(row2, col));
            matrix.set(row2, col, temp);
        }
    }
}
