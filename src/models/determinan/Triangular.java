package src.models.determinan;

import src.datatypes.Matrix;

public class Triangular {

    public void makeUpperTriangular(Matrix matrix) {
        int n = matrix.getRowCount();
        for (int i = 0; i < n; i++) {
            if (matrix.get(i, i) == 0) {
                for (int j = i + 1; j < n; j++) {
                    if (matrix.get(j, i) != 0) {
                        swapRows(matrix, i, j);
                        break;
                    }
                }
            }

            for (int j = i + 1; j < n; j++) {
                double factor = matrix.get(j, i) / matrix.get(i, i);
                for (int k = i; k < n; k++) {
                    matrix.set(j, k, matrix.get(j, k) - factor * matrix.get(i, k));
                }
            }
        }
    }

    private void swapRows(Matrix matrix, int row1, int row2) {
        for (int i = 0; i < matrix.getColumnCount(); i++) {
            double temp = matrix.get(row1, i);
            matrix.set(row1, i, matrix.get(row2, i));
            matrix.set(row2, i, temp);
        }
    }

    public boolean isUpperTriangular(Matrix matrix) {
        int n = matrix.getRowCount();
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (matrix.get(i, j) != 0) {
                    return false;
                }
            }
        }
        return true;
    }

}
