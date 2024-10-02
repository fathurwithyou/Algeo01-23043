package src.helpers;

import src.datatypes.Matrix;

public class BackwardElimination {
    public void backwardElimination(Integer n, Integer m, Matrix matrix) {
        for (int col = n - 1; col >= 0; col--) {
            for (int row = col - 1; row >= 0; row--) {
                double multiplier = matrix.get(row, col) / matrix.get(col, col);
                for (int i = col; i < matrix.getColumnCount(); i++) {
                    matrix.set(row, i, matrix.get(row, i) - multiplier * matrix.get(col, i));
                }
            }
        }
    }
}
