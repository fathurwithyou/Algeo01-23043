package src.models.sistemPersamaanLinier;

import src.datatypes.Matrix;
import src.datatypes.Tuple3;
import src.helpers.ForwardElimination;
import src.helpers.GetConst;
import src.helpers.GetMainMatrix;
import src.helpers.AlignMatrix;
import src.helpers.BackwardElimination;
import src.helpers.NormalizeMatrix;
import java.util.ArrayList;
import java.util.List;

public class GaussJordan {
    private NormalizeMatrix normalizeMatrix = new NormalizeMatrix();

    public List<String> gaussJordanFreeVariable(Matrix augmentedMatrix) {
      
        int rows = augmentedMatrix.getRowCount();
        int cols = augmentedMatrix.getColumnCount()-1;
        boolean[] isPivotColumn = new boolean[cols];

        // Perform Gauss-Jordan Elimination to transform the matrix to RREF
        int pivotRow = 0;
        for (int col = 0; col < cols; col++) {
            // Find the row with the largest absolute value in the current column
            int maxRow = pivotRow;
            for (int row = pivotRow + 1; row < rows; row++) {
                if (Math.abs(augmentedMatrix.get(row, col)) > Math.abs(augmentedMatrix.get(maxRow, col))) {
                    maxRow = row;
                }
            }

            // If the column is entirely zero, move to the next column
            if (augmentedMatrix.get(maxRow, col) == 0) {
                continue;
            }

            // Swap the current row with the row of the largest element
            swapRows(augmentedMatrix, pivotRow, maxRow);

            // Normalize the pivot row
            double pivotElement = augmentedMatrix.get(pivotRow, col);
            for (int j = 0; j < cols + 1; j++) {
                augmentedMatrix.set(pivotRow, j, augmentedMatrix.get(pivotRow, j) / pivotElement);
            }

            // Eliminate all other entries in the current column
            for (int row = 0; row < rows; row++) {
                if (row != pivotRow) {
                    double factor = augmentedMatrix.get(row, col);
                    for (int j = 0; j < cols + 1; j++) {
                        augmentedMatrix.set(row, j,
                                augmentedMatrix.get(row, j) - factor * augmentedMatrix.get(pivotRow, j));
                    }
                }
            }

            // Mark the current column as a pivot column
            isPivotColumn[col] = true;
            pivotRow++;

            // If we've processed all rows, break
            if (pivotRow == rows) {
                break;
            }
        }

        // Generate the solution in terms of free variables
        List<String> solutions = new ArrayList<>();
        for (int col = 0; col < cols; col++) {
            if (isPivotColumn[col]) {
                // The variable is dependent, so express it in terms of other variables
                StringBuilder expression = new StringBuilder("x" + (col + 1) + " = ");
                double constant = augmentedMatrix.get(col, cols);
                if (constant != 0) {
                    expression.append(constant);
                }
                boolean firstTerm = constant != 0;

                for (int j = 0; j < cols; j++) {
                    if (!isPivotColumn[j] && augmentedMatrix.get(col, j) != 0) {
                        if (firstTerm) {
                            expression.append(" - ");
                        } else {
                            expression.append("-");
                            firstTerm = true;
                        }
                        expression.append(augmentedMatrix.get(col, j) + "t" + (j + 1));
                    }
                }

                solutions.add(expression.toString());
            } else {
                // The variable is free
                solutions.add("x" + (col + 1) + " = t" + (col + 1));
            }
        }

        return solutions;
    }

    private void swapRows(Matrix matrix, int row1, int row2) {
        int cols = matrix.getColumnCount();
        for (int col = 0; col < cols; col++) {
            double temp = matrix.get(row1, col);
            matrix.set(row1, col, matrix.get(row2, col));
            matrix.set(row2, col, temp);
        }
    }

    public Matrix gaussJordanElimination(Integer n, Integer m, Matrix matrix) {
        AlignMatrix alignMatrix = new AlignMatrix();
        ForwardElimination forwardElimination = new ForwardElimination();
        BackwardElimination backwardElimination = new BackwardElimination();
        alignMatrix.alignMatrix(matrix);

        forwardElimination.forwardElimination(n, m, matrix);

        backwardElimination.backwardElimination(n, m, matrix);
        normalizeMatrix.normalizeMatrix(n, m, matrix);

        return matrix;
    }

    public Matrix main(Matrix data) {
        int n = data.getRowCount();
        int m = data.getColumnCount();
        Matrix result = gaussJordanElimination(n, n, data);
        return result;
    }
}
