package src.helpers;

import src.datatypes.Array;
import src.datatypes.Matrix;

public class ReshapeConst {
    public Array reshapeConst(Matrix matrix, int row, int col) {
        Array res = new Array(row);
        for (int i = 0; i < row; i++) {
            res.set(i, matrix.get(i, 0));
        }

        return res;
    }

}
