package src.helpers;

import src.datatypes.Matrix;

public class NormalizeMatrix {
    public void normalizeMatrix(Integer n, Integer m, Matrix matrix) {
        for (int i = 0; i < n; i++) {
            double divider = matrix.get(i, i);
            for (int j = 0; j < matrix.getColumnCount(); j++) {
                matrix.set(i, j, matrix.get(i, j) / divider);
            }
        }
    }
}
