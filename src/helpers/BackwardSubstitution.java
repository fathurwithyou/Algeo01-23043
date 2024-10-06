package src.helpers;
import src.datatypes.Array;
import src.datatypes.Matrix;


public class BackwardSubstitution {
    private static final double EPSILON = 1e-9;
    
    public Array backwardSubstitution(Matrix matrix) {
        int n = matrix.getColumnCount();
        Array result = new Array(n-1);
        for (int i = n - 2; i >= 0; i--) {
            double sum = 0;
            for (int j = i + 1; j < n-1; j++) {
                sum += matrix.get(i, j) * result.get(j);
            }
            result.set(i, (matrix.get(i, n-1) - sum) / matrix.get(i, i));
            if (Math.abs(result.get(i)) < EPSILON)
                    result.set(i, 0.0);
        }
        return result;
    }
}
