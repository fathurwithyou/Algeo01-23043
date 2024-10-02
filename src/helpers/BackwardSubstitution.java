package src.helpers;
import src.datatypes.Array;
import src.datatypes.Matrix;


public class BackwardSubstitution {
    public Array backwardSubstitution(Matrix matrix) {
        int n = matrix.getRowCount();
        Array result = new Array(n);
        for (int i = n - 1; i >= 0; i--) {
            double sum = 0;
            for (int j = i + 1; j < n; j++) {
                sum += matrix.get(i, j) * result.get(j);
            }
            result.set(i, (matrix.get(i, n) - sum) / matrix.get(i, i));
        }
        return result;
    }
}
