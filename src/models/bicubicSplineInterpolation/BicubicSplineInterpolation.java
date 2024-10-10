package src.models.bicubicSplineInterpolation;

import src.datatypes.Matrix;
import src.models.matriksBalikan.GaussJordanMethod;

/*maaf kalau salah.............:((((((((((*/

public class BicubicSplineInterpolation {
    private Matrix coefficients;

    public void fit(Matrix Z) {

        Matrix X = generateMatrixX();
        Matrix X_inv = inverseOfMatrix(X);
        double[] Z_flat = flattenMatrix(Z);
        coefficients = solveLinearSystem(X_inv, Z_flat);
    }

    public double predict(double a, double b) {
        return bicubicSplineEquation(coefficients, a, b);
    }

    private Matrix generateMatrixX() {
        Matrix X = new Matrix(16, 16);
        int row = 0;

        for (int y = -1; y <= 2; y++) {
            for (int x = -1; x <= 2; x++) {
                int col = 0;
                for (int j = 0; j <= 3; j++) {
                    for (int i = 0; i <= 3; i++) {
                        X.set(row, col, Math.pow(x, i) * Math.pow(y, j));
                        col++;
                    }
                }
                row++;
            }
        }
        return X;
    }

    private double bicubicSplineEquation(Matrix variables, double a, double b) {
        double value = 0.0;
        for (int j = 0; j <= 3; j++) {
            for (int i = 0; i <= 3; i++) {
                value += variables.get(i, j) * Math.pow(a, i) * Math.pow(b, j);
            }
        }
        return value;
    }

    private Matrix inverseOfMatrix(Matrix matrix) {
        GaussJordanMethod gaussJordan = new GaussJordanMethod();
        return gaussJordan.main(matrix);
    }

    private Matrix solveLinearSystem(Matrix X_inv, double[] Z_flat) {
        Matrix solution = new Matrix(4, 4);
        int row = 0, col = 0;

        for (int i = 0; i < X_inv.getRowCount(); i++) {
            if (row >= 4) { 
                row = 0; 
                col++; 
            }
            double sum = 0;
            for (int j = 0; j < X_inv.getColumnCount(); j++) {
                sum += X_inv.get(i, j) * Z_flat[j];
            }
            solution.set(row, col, sum);
            row++;
        }
        return solution;
    }

    private double[] flattenMatrix(Matrix matrix) {
        double[] array = new double[matrix.getRowCount() * matrix.getColumnCount()];
        int index = 0;

        for (int i = 0; i < matrix.getRowCount(); i++) {
            for (int j = 0; j < matrix.getColumnCount(); j++) {
                array[index++] = matrix.get(i, j);
            }
        }
        return array;
    }
}
