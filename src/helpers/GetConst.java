package src.helpers;

import src.datatypes.Matrix;

public class GetConst {
    public Matrix getConst(Matrix arr) {
        int n = arr.getRowCount();
        int m = arr.getColumnCount();
        Matrix result = new Matrix(n, 1);
        for (int i = 0; i < n; i++) {
            result.set(i, 0, arr.get(i, m-1));
        }
        return result;
    }
}
