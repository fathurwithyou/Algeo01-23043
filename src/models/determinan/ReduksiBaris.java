package src.models.determinan;

import src.datatypes.Matrix;
import src.models.sistemPersamaanLinier.Gauss;
import src.helpers.SwapRows;

public class ReduksiBaris {
    private Gauss gauss = new Gauss();
    private double determinant = 1;
    private SwapRows swapRows = new SwapRows();
    

    public boolean isNearZero(double x) {
        return Math.abs(x) < gauss.EPSILON;
    }

    public void gaussElimination(Matrix augmentedMatrix) {
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
                determinant = 0;
                return;
            }
            if(maxRow != pivot){
                determinant *= -1;
            }

            swapRows.swapRows(augmentedMatrix, pivot, maxRow);
            double pivotElement = augmentedMatrix.get(pivot, pivot);
            determinant *= pivotElement;
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
        return;
    }   

    public void reduksiBarisElimination(Matrix matrix) {
        int n = matrix.getRowCount();
        Matrix newMatrix = new Matrix(n, n + 1);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                newMatrix.set(i, j, matrix.get(i, j));
            }
        }
        gaussElimination(newMatrix);
        return;
    }

    public double main(Matrix matrix) {
        reduksiBarisElimination(matrix);
        return determinant;
    }
}
