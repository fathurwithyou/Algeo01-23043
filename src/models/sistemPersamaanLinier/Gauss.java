package src.models.sistemPersamaanLinier;

import src.datatypes.Array;
import src.datatypes.Matrix;
import src.helpers.BackwardSubstitution;
import src.helpers.SwapRows;

public class Gauss {
    private SwapRows swapRows = new SwapRows();
    private double EPSILON = 1e-6;

    public boolean isNearZero(double x) {
        return Math.abs(x) < EPSILON;
    }

    public Matrix gaussElimination(Matrix augmentedMatrix) {
        int rows = augmentedMatrix.getRowCount();
        int cols = augmentedMatrix.getColumnCount() - 1;

        for (int pivot = 0; pivot < Math.min(rows, cols); pivot++) {
            int maxRow = pivot;
            for (int row = pivot + 1; row < rows; row++) {
                if (Math.abs(augmentedMatrix.get(row, pivot)) > Math.abs(augmentedMatrix.get(maxRow, pivot))) {
                    maxRow = row;
                }
            }

            if (isNearZero(augmentedMatrix.get(maxRow, pivot))) {
                continue;
            }

            swapRows.swapRows(augmentedMatrix, pivot, maxRow);
            double pivotElement = augmentedMatrix.get(pivot, pivot);
            for (int col = 0; col <= cols; col++) {
                augmentedMatrix.set(pivot, col, augmentedMatrix.get(pivot, col) / pivotElement);
            }

            for (int row = pivot + 1; row < rows; row++) {
                double factor = augmentedMatrix.get(row, pivot);
                for (int col = pivot; col <= cols; col++) {
                    augmentedMatrix.set(row, col, augmentedMatrix.get(row, col) - factor * augmentedMatrix.get(pivot, col));
                }
            }
        }
        return augmentedMatrix;
    }

    public Array main(Matrix data) {
        BackwardSubstitution backwardSubstitution = new BackwardSubstitution();
        Matrix result = gaussElimination(data);
        Array solution = backwardSubstitution.backwardSubstitution(result);
        return solution;
    }
}
