package src.models.bicubicSplineInterpolation;

import src.datatypes.Array;
import src.datatypes.Matrix;

public class BicubicSplineInterpolation {

    private Array pers;

    public Matrix createGrid(Matrix matrix, Double x, Double y) {
        Matrix grid = new Matrix(4, 4);
        int start_x = (int) Math.floor(x) - 1;
        int start_y = (int) Math.floor(y) - 1;
        start_x = Math.max(0, start_x);
        start_y = Math.max(0, start_y);
        start_x = Math.min(matrix.getRowCount() - 4, start_x);
        start_y = Math.min(matrix.getColumnCount() - 4, start_y);
        for (int i = 0; i <= 3; i++) {
            for (int j = 0; j <= 3; j++) {
                grid.set(i, j, matrix.get(start_x + i, start_y + j));
            }
        }
        return grid;
    }

    public Matrix createGridArray(Matrix grid) {
        int i, j, k;
        k = 0;
        Matrix gridArray = new Matrix(16, 1);
        for (i = 0; i <= 3; i++) {
            for (j = 0; j <= 3; j++) {
                gridArray.set(k, 0, grid.get(i, j));
                k++;
            }
        }
        return gridArray;
    }

    public Matrix createCoefMatrix() {
        Matrix coefMatrix = new Matrix(16, 16);
    
        int row = 0;
    
        // Baris 1-4: Nilai fungsi f(x, y)
        for (int y = 0; y <= 1; y++) {
            for (int x = 0; x <= 1; x++) {
                int col = 0;
                for (int j = 0; j <= 3; j++) {
                    for (int i = 0; i <= 3; i++) {
                        coefMatrix.set(row, col, Math.pow(x, i) * Math.pow(y, j));
                        col++;
                    }
                }
                row++;
            }
        }
    
        // Baris 5-8: Turunan parsial terhadap x (fx)
        for (int y = 0; y <= 1; y++) {
            for (int x = 0; x <= 1; x++) {
                int col = 0;
                for (int j = 0; j <= 3; j++) {
                    coefMatrix.set(row, col, 0.0); // Ketika i == 0
                    col++;
                    for (int i = 1; i <= 3; i++) {
                        coefMatrix.set(row, col, i * Math.pow(x, i - 1) * Math.pow(y, j));
                        col++;
                    }
                }
                row++;
            }
        }
    
        // Baris 9-12: Turunan parsial terhadap y (fy)
        for (int y = 0; y <= 1; y++) {
            for (int x = 0; x <= 1; x++) {
                int col = 0;
                for (int k = 0; k <= 3; k++) {
                    coefMatrix.set(row, col, 0.0); // Ketika j == 0
                    col++;
                }
                for (int j = 1; j <= 3; j++) {
                    for (int i = 0; i <= 3; i++) {
                        coefMatrix.set(row, col, j * Math.pow(x, i) * Math.pow(y, j - 1));
                        col++;
                    }
                }
                row++;
            }
        }
    
        // Baris 13-16: Turunan parsial campuran terhadap x dan y (fxy)
        for (int y = 0; y <= 1; y++) {
            for (int x = 0; x <= 1; x++) {
                int col = 0;
                for (int j = 0; j <= 3; j++) {
                    for (int i = 0; i <= 3; i++) {
                        double value = i * j * Math.pow(x, i - 1) * Math.pow(y, j - 1);
                        coefMatrix.set(row, col, Double.isNaN(value) ? 0.0 : value); // Menghindari NaN untuk i = 0 atau j = 0
                        col++;
                    }
                }
                row++;
            }
        }
    
        return coefMatrix;
    }    

    public Array bicubicSplineInterpolation(Matrix matrix, Double x, Double y) {
        Matrix gridMatrix = createGrid(matrix, x, y);
        Matrix gridArray = createGridArray(gridMatrix);
        Matrix coefMatrix = createCoefMatrix();
        Matrix mulMatrix = coefMatrix.inverse().multiply(gridArray);
        pers = new Array(16);
        for (int i = 0; i < mulMatrix.getRowCount(); i++) {
            pers.set(i, mulMatrix.get(i, 0));
        }
        return pers;
    }

    public void fit(Matrix matrix, Double x, Double y) {
        pers = bicubicSplineInterpolation(matrix, x, y);
    }

    public Double predict(Double x, Double y) {
        Double result = 0.0;
        int k = 0;
        
        for (int i = 0; i <= 3; i++) {
            for (int j = 0; j <= 3; j++) {
                result += pers.get(k) * Math.pow(y, i) * Math.pow(x, j);
                k++;
            }
        }
    
        return result;
    }    

} 