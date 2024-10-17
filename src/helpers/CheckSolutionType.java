package src.helpers;

import src.datatypes.Matrix;

public class CheckSolutionType {
    
    public int checkSolutionType(Matrix matrix) {
        Matrix A = new  GetMainMatrix().main(matrix);
        Matrix b = new GetConst().getConst(matrix);
        int numVariables = A.getColumnCount();
        
        Matrix augmentedMatrix = new Matrix(A.getRowCount(), numVariables + 1);
        for (int i = 0; i < A.getRowCount(); i++) {
            for (int j = 0; j < numVariables; j++) {
                augmentedMatrix.set(i, j, A.get(i, j));
            }
            augmentedMatrix.set(i, numVariables, b.get(i, 0));
        }

        // Calculate ranks
        int rankA = calculateRank(A);
        int rankAugmented = calculateRank(augmentedMatrix);

        // Check solution type
        if (rankA < rankAugmented) {
            return 0;
        } else if (rankA == rankAugmented && rankA == numVariables) {
            return 1;
        } else if (rankA == rankAugmented && rankA < numVariables) {
            return 2;
        } else {
            return 3;
        }
    }

    private int calculateRank(Matrix matrix) {
        int rank = 0;
        int rows = matrix.getRowCount();
        int cols = matrix.getColumnCount();

        boolean[] rowUsed = new boolean[rows];
        
        for (int col = 0; col < cols; col++) {
            int pivotRow = -1;
            for (int row = 0; row < rows; row++) {
                if (!rowUsed[row] && matrix.get(row, col) != 0) {
                    pivotRow = row;
                    break;
                }
            }

            if (pivotRow == -1) continue; 

            rowUsed[pivotRow] = true;
            rank++;

            for (int row = pivotRow + 1; row < rows; row++) {
                if (matrix.get(row, col) != 0) {
                    double factor = matrix.get(row, col) / matrix.get(pivotRow, col);
                    for (int j = col; j < cols; j++) {
                        double newValue = matrix.get(row, j) - factor * matrix.get(pivotRow, j);
                        matrix.set(row, j, newValue);
                    }
                }
            }
        }
        
        return rank;
    }
}
