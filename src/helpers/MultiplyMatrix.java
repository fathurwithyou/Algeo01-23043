package src.helpers;

import src.datatypes.Matrix;

public class MultiplyMatrix {
    public Matrix multiplyMatrix(Matrix matrix1, Matrix matrix2) {
        Matrix result = new Matrix(matrix1.getRowCount(), matrix2.getColumnCount());
        for (int i = 0; i < matrix1.getRowCount(); i++) {
            for (int j = 0; j < matrix2.getColumnCount(); j++) {
                double sum = 0;
                for (int k = 0; k < matrix1.getColumnCount(); k++) {
                    sum += matrix1.get(i, k) * matrix2.get(k, j);
                }
                result.set(i, j, sum);
            }
        }
        return result;
    }
}
