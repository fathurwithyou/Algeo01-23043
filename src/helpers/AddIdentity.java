package src.helpers;

import src.datatypes.Matrix;

public class AddIdentity {
    public Matrix addIdentity(Matrix matrix) {
        int n = matrix.getRowCount();
        Matrix result = new Matrix(n, n*2);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result.set(i, j, matrix.get(i, j));
            }
            result.set(i, i+n, 1.0);
        }
        return result;
    }
}
