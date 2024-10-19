package src.models.bicubicSplineInterpolation;

import src.datatypes.Array;
import src.datatypes.Matrix;
import src.datatypes.Tuple3;
import src.models.sistemPersamaanLinier.Gauss;

public class BicubicSplineInterpolation {

    private Gauss gauss = new Gauss();
    private Array pers;

    public Matrix createGrid(Matrix matrix, Double x, Double y) {
        Matrix grid = new Matrix(4, 4);
        int start_x = (int) Math.floor(x) - 1;
        int start_y = (int) Math.floor(y) - 1;
        start_x = Math.max(0, start_x);
        start_y = Math.max(0, start_y);
        start_x = Math.min(matrix.getRowCount() - 4, start_x);
        start_y = Math.min(matrix.getColumnCount() - 4, start_y);        
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                grid.set(i, j, matrix.get(start_x + i, start_y + j));
            }
        }
        return grid;
    }

    public Array createGridArray(Matrix grid) {
        int i, j, k;
        k = 0;
        Array gridArray = new Array(16);
        for (i = 0; i < 4; i++) {
            for (j = 0; j < 4; j++) {
                gridArray.set(k, grid.get(i, j));
                k++;
            }
        }
        return gridArray;
    }

    public Matrix createCoefMatrix(Double x, Double y) {
        Matrix coefMatrix = new Matrix(16, 16);
        int i, j, row, col;
        double a, b;
        
        row = 0;
        for (a = (int) Math.floor(x) - 1; a < (int) Math.floor(y) + 3; a++) {
            for (b = (int) Math.floor(y) - 1; b < (int) Math.floor(y) + 3; b++) {
                col = 0;
                for (i = 0; i < 4; i++) {
                    for (j = 0; j < 4; j++) {
                        coefMatrix.set(row, col, Math.pow(a, i) * Math.pow(b, j));
                        col++;
                    }
                }
                row++;
            }
        }

        return coefMatrix;
    }

    public Tuple3<Integer, Integer, Matrix> createAugmentedMatrix (Matrix matrix, Double x, Double y) {
        Matrix gridMatrix = createGrid(matrix, x, y);
        Array gridArray = createGridArray(gridMatrix);
        Matrix coefMatrix = createCoefMatrix(x, y); 
        Matrix aug = new Matrix(16, 17);

        for (int row = 0; row < 16; row++) {
            for (int col = 0; col < 16; col++) {
                aug.set(row, col, coefMatrix.get(row, col));
            }
            aug.set(row, 16, gridArray.get(row));
        }
        
        return new Tuple3<>(16, 17, aug);
    }
    
    public Array bicubicSplineInterpolation(Matrix matrix, Double x, Double y) {
        Tuple3<Integer, Integer, Matrix> aug = createAugmentedMatrix(matrix, x, y);
        pers = gauss.main(aug);
        return pers;
    }
    
    
    public void fit(Matrix matrix, Double x, Double y) {
        pers = bicubicSplineInterpolation(matrix, x, y);
    } 

    public Double predict(Double x, Double y) {
        Double result;
        result = 0.0;
        int k = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                result += pers.get(k)*Math.pow(x, i)*Math.pow(y, j);
                k++;
            }
        }
        return result;
    }
}
