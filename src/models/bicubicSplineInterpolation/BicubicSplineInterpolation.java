package src.models.bicubicSplineInterpolation;

import src.datatypes.Array;
import src.datatypes.Matrix;
import src.datatypes.Tuple3;

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
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                grid.set(i, j, matrix.get(start_x + i, start_y + j));
            }
        }
        return grid;
    }

    public Matrix createGridArray(Matrix grid) {
        int i, j, k;
        k = 0;
        Matrix gridArray = new Matrix(16, 1);
        for (i = 0; i < 4; i++) {
            for (j = 0; j < 4; j++) {
                gridArray.set(k, 0, grid.get(i, j));
                k++;
            }
        }
        return gridArray;
    }

    public Matrix createCoefMatrix() {
        Matrix coefMatrix = new Matrix(16, 16);

        // f
        coefMatrix.set(0, 0, 1.0);
        for (int i = 0; i < 4; i++)
            coefMatrix.set(1, i, 1.0);
        for (int i = 0; i < 4; i++)
            coefMatrix.set(2, i * 4, 1.0);
        for (int i = 0; i < 16; i++)
            coefMatrix.set(3, i, 1.0);

        // fx
        coefMatrix.set(4, 1, 1.0);
        for (int i = 0; i < 4; i++)
            coefMatrix.set(5, i, Double.valueOf(i));
        for (int i = 1; i < 16; i += 4)
            coefMatrix.set(6, i, 1.0);
        for (int i = 0; i < 16; i++)
            coefMatrix.set(7, i, Double.valueOf(i % 4));

        // fy
        coefMatrix.set(8, 4, 1.0);
        for (int i = 0; i < 4; i++)
            coefMatrix.set(9, i + 4, 1.0);
        for (int i = 4; i < 16; i += 4)
            coefMatrix.set(10, i, Double.valueOf(i / 4));
        for (int i = 0; i < 16; i++)
            coefMatrix.set(11, i, Double.valueOf(i / 4));

        // fxy
        coefMatrix.set(12, 5, 1.0);
        for (int i = 0; i < 4; i++)
            coefMatrix.set(13, i + 4, Double.valueOf(i));
        for (int i = 5; i < 16; i += 4)
            coefMatrix.set(14, i, Double.valueOf(i / 4));
        for (int i = 0; i < 16; i++)
            coefMatrix.set(15, i, Double.valueOf((i % 4) * (i / 4)));

        return coefMatrix;
    }

    public Tuple3<Integer, Integer, Matrix> createAugmentedMatrix(Matrix matrix, Double x, Double y) {
        Matrix gridMatrix = createGrid(matrix, x, y);
        Matrix gridArray = createGridArray(gridMatrix);
        Matrix coefMatrix = createCoefMatrix();
        Matrix aug = coefMatrix.inverse().multiply(gridArray);

        return new Tuple3<>(16, 17, aug);
    }

    public Array bicubicSplineInterpolation(Matrix matrix, Double x, Double y) {
        Tuple3<Integer, Integer, Matrix> aug = createAugmentedMatrix(matrix, x, y);
        pers = new Array(16);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                pers.set(i * 4 + j, aug.getItem3().get(i * 4 + j, 0));
            }
        }
        return pers;
    }

    public void fit(Matrix matrix, Double x, Double y) {
        pers = bicubicSplineInterpolation(matrix, x, y);
    }

    public Double predict(Double x, Double y) {
        Double result = 0.0;
        int k = 0;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                result += pers.get(k) * Math.pow(x, i) * Math.pow(y, j);
                k++;
            }
        }

        return Math.max(0, Math.min(255, result));
    }
}
