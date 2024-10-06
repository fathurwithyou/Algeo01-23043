package src.helpers;

import src.datatypes.Matrix;

public class ForwardElimination {

    //temp
    private static final double EPSILON = 1e-9;

    public int forwardElimination(Integer n, Integer m, Matrix matrix) {
        for (int col = 0; col < Math.min(m,n); col++) {
            for (int row = col + 1; row < n; row++) {      
                if (matrix.get(col, col) == 0) {
                    return col;
                }
                double multiplier = matrix.get(row, col) / matrix.get(col, col);
                for (int i = col; i < matrix.getColumnCount(); i++) {
                    matrix.set(row, i, matrix.get(row, i) - multiplier * matrix.get(col, i));
                    if (Math.abs(matrix.get(row, i)) < EPSILON)
                        matrix.set(row, i, 0.0);
                }
            }
        }
        return -1;
    }
}
