package src.models.determinan;

import src.datatypes.Matrix;

public class Matrix2x2 {

    public double calculateDeterminant(Matrix matrix) {
        double a = matrix.get(0, 0);
        double b = matrix.get(0, 1);
        double c = matrix.get(1, 0);
        double d = matrix.get(1, 1);
        return a * d - b * c;
    }

    public double main(Matrix matrix) {
        return calculateDeterminant(matrix);
    }
}
