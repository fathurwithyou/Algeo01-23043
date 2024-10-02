package src.helpers;

import src.datatypes.Matrix;

public class ForwardElimination {
    public void forwardElimination(Matrix matrix) {
        int n = matrix.getRowCount();
        for (int col = 0; col < n - 1; col++) {
            for (int row = col + 1; row < n; row++) {
                double multiplier = matrix.get(row, col) / matrix.get(col, col);
                for (int i = col; i < n; i++) {
                    matrix.set(row, i, matrix.get(row, i) - multiplier * matrix.get(col, i));
                }
            }
        }
    }
}
