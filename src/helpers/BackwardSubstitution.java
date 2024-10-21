package src.helpers;

import src.datatypes.Array;
import src.datatypes.Matrix;

public class BackwardSubstitution {
    private double EPSILON = 1e-6;

    public boolean isZeroRow(Matrix matrix, int row, int m) {
        for (int i = 0; i < m; i++) {
            if (Math.abs(matrix.get(row, i)) > EPSILON) {
                return false;
            }
        }
        return true;
    }

    public Array backwardSubstitution(Matrix matrix) {
        int n = matrix.getRowCount(); // Number of rows
        int m = matrix.getColumnCount() - 1; // Number of columns minus the augmented column
        Array result = new Array(m);

        for (int i = 0; i < m; i++) {
            result.set(i, 0.0);
        }

        for (int i = n - 1; i >= 0; i--) {
            if (isZeroRow(matrix, i, m)) {
                continue;
            }

            double sum = 0;
            for (int j = i + 1; j < m; j++) {
                sum += matrix.get(i, j) * result.get(j); // sum = coefficient * known variable
            }

            result.set(i, (matrix.get(i, m) - sum) / matrix.get(i, i));
        }

        return result;
    }

}
