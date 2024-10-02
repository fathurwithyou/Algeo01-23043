package src.helpers;
import src.datatypes.Array;
import src.datatypes.Matrix;

public class GetConst {
    public Array getConst(Matrix arr) {
        int n = arr.getRowCount();
        Array result = new Array(n);
        for (int i = 0; i < n; i++) {
            result.set(i, arr.get(i, n-1));
        }
        return result;
    }
}
