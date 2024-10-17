package src.helpers;

import src.datatypes.Matrix;
import src.datatypes.Array;

public class GetUniqueEquation {
    public int cols;

    public Matrix main(Matrix matrix) {
        int rows = matrix.getRowCount();
        int cols = matrix.getColumnCount();
        this.cols = cols;

        Matrix uniqueMatrix = new Matrix(0, cols);

        for (int i = 0; i < rows; i++) {
            Array currentRow = matrix.getRow(i);
            if (!isDuplicateOrLinearlyDependent(currentRow, uniqueMatrix)) {
                uniqueMatrix = addRowToMatrix(uniqueMatrix, currentRow);
            }
        }

        return uniqueMatrix;
    }

    private boolean isDuplicateOrLinearlyDependent(Array row, Matrix uniqueMatrix) {
        int rowCount = uniqueMatrix.getRowCount();
        int colCount = row.getSize();

        for (int i = 0; i < rowCount; i++) {
            boolean isDependent = true;
            double factor = 0;

            for (int j = 0; j < colCount; j++) {
                double currentValue = row.get(j);
                double uniqueValue = uniqueMatrix.get(i, j);

                if (j == 0) {
                    if (uniqueValue != 0) {
                        factor = currentValue / uniqueValue;
                    }
                } else {
                    if (uniqueValue != 0 && Math.abs(currentValue / uniqueValue - factor) > 1e-9) {
                        isDependent = false;
                        break;
                    }
                }
            }

            if (isDependent) {
                return true;
            }
        }
        return false;
    }

    private Matrix addRowToMatrix(Matrix matrix, Array row) {
        int currentRows = matrix.getRowCount();
        Matrix newMatrix = new Matrix(currentRows + 1, cols);

        for (int i = 0; i < currentRows; i++) {
            for (int j = 0; j < cols; j++) {
                newMatrix.set(i, j, matrix.get(i, j));
            }
        }

        for (int j = 0; j < cols; j++) {
            newMatrix.set(currentRows, j, row.get(j));
        }

        return newMatrix;
    }
}
