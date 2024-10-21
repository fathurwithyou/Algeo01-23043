package src.models.sistemPersamaanLinier;

import src.datatypes.Matrix;
import src.helpers.ForwardElimination;
import src.helpers.AlignMatrix;
import src.helpers.BackwardElimination;
import src.helpers.NormalizeMatrix;
import java.util.ArrayList;
import java.util.List;

public class GaussJordan {
    private NormalizeMatrix normalizeMatrix = new NormalizeMatrix();
    private double EPSILON = 1e-6;

    public boolean isInside(double x) {
        return Math.abs(x) < EPSILON;
    }

    public List<String> gaussJordanFreeVariable(Matrix augmentedMatrix) {
        int rows = augmentedMatrix.getRowCount();
        int cols = augmentedMatrix.getColumnCount() - 1;
        boolean[] isPivotColumn = new boolean[cols];

        int pivotRow = 0;
        for (int col = 0; col < cols; col++) {
            int maxRow = pivotRow;
            for (int row = pivotRow + 1; row < rows; row++) {
                if (Math.abs(augmentedMatrix.get(row, col)) > Math.abs(augmentedMatrix.get(maxRow, col))) {
                    maxRow = row;
                }
            }

            if (isInside(augmentedMatrix.get(maxRow, col))) {
                continue;
            }

            swapRows(augmentedMatrix, pivotRow, maxRow);

            double pivotElement = augmentedMatrix.get(pivotRow, col);
            for (int j = 0; j < cols + 1; j++) {
                augmentedMatrix.set(pivotRow, j, augmentedMatrix.get(pivotRow, j) / pivotElement);
            }

            for (int row = 0; row < rows; row++) {
                if (row != pivotRow) {
                    double factor = augmentedMatrix.get(row, col);
                    for (int j = 0; j < cols + 1; j++) {
                        augmentedMatrix.set(row, j,
                                augmentedMatrix.get(row, j) - factor * augmentedMatrix.get(pivotRow, j));
                    }
                }
            }

            isPivotColumn[col] = true;
            pivotRow++;

            if (pivotRow == rows) {
                break;
            }
        }

        return backSubstitutionWithFreeVariables(augmentedMatrix, isPivotColumn, rows, cols);
    }

    private List<String> backSubstitutionWithFreeVariables(Matrix augmentedMatrix, boolean[] isPivotColumn, int rows,
            int cols) {
        List<String> solutions = new ArrayList<>();

        for (int row = rows - 1; row >= 0; row--) {
            int pivotCol = -1;

            for (int col = 0; col < cols; col++) {
                if (isPivotColumn[col] && !isInside(augmentedMatrix.get(row, col))) {
                    pivotCol = col;
                    break;
                }
            }

            if (pivotCol == -1) {
                continue;
            }
            StringBuilder expression = new StringBuilder("x" + (pivotCol + 1) + " = ");
            double constant = augmentedMatrix.get(row, cols);

            if (!isInside(constant)) {
                expression.append(constant);
            } else {
                expression.append("0");
            }

            boolean firstTerm = true;

            for (int col = pivotCol + 1; col < cols; col++) {
                if (!isPivotColumn[col] && !isInside(augmentedMatrix.get(row, col))) {
                    double coefficient = augmentedMatrix.get(row, col);
                    if (firstTerm) {
                        expression.append(" - ");
                    } else {
                        expression.append("-");
                    }
                    expression.append(coefficient).append("t").append(col + 1); 
                    firstTerm = true;
                }
            }

            solutions.add(expression.toString());
        }

        for (int col = 0; col < cols; col++) {
            if (!isPivotColumn[col]) {
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
