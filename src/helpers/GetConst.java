package src.helpers;

import src.datatypes.Matrix;

public class GetConst {
    public Matrix getConst(Matrix arr) {
        int n = arr.getRowCount();
        Matrix result = new Matrix(1, n);
        for (int i = 0; i < n; i++) {
            result.set(0, i, arr.get(i, n));
        }
        return result;
    }
}
