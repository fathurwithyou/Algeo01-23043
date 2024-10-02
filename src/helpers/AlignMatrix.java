package src.helpers;

import src.datatypes.Matrix;

public class AlignMatrix {
    public int alignMatrix(Matrix matrix) {
        int n = matrix.getRowCount();
        for (int col = 0; col < n; col++) {
            int iMax = col;
            Double vMax = matrix.get(iMax, col);
            for (int row = col+1; row < n; row++) {
                if(Math.abs(matrix.get(row, col)) > vMax) {
                    iMax = row;
                    vMax = matrix.get(iMax, col);
                }
            }

            if (matrix.get(iMax, col) == 0) {
                return col;
            }

            if (iMax != col) {
                swapRows(matrix, iMax, col);
            }
        }
        return -1;
    }

    private void swapRows(Matrix matrix, int row1, int row2) {
        int m = matrix.getColumnCount();
        for (int col = 0; col < m; col++) {
            double temp = matrix.get(row1, col);
            matrix.set(row1, col, matrix.get(row2, col));
            matrix.set(row2, col, temp);
        }
    }
}
