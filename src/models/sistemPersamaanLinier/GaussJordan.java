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

        int pivotRow = 0;
        for (int col = 0; col < cols; col++) {
            int maxRow = pivotRow;
            for (int row = pivotRow + 1; row < rows; row++) {
                if (Math.abs(augmentedMatrix.get(row, col)) > Math.abs(augmentedMatrix.get(maxRow, col))) {
                    maxRow = row;
                }
            }

            if (augmentedMatrix.get(maxRow, col) == 0) {
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

        List<String> solutions = new ArrayList<>();
        for (int col = 0; col < cols; col++) {
            if (isPivotColumn[col]) {
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
