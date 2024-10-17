package src.helpers;

import src.datatypes.Matrix;

public class GetConst {
    public Matrix getConst(Matrix arr) {
        int row = arr.getRowCount();
        int col = arr.getColumnCount();
        Matrix constMatrix = new Matrix(row, 1);
        for (int i = 0; i < row; i++) {
            constMatrix.set(i, 0, arr.get(i, col-1));
        }
        return constMatrix;
    }
}
