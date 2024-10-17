package src.models.sistemPersamaanLinier;

import src.datatypes.Array;
import src.datatypes.Matrix;
import src.datatypes.Tuple3;
import src.helpers.AlignMatrix;
import src.helpers.BackwardSubstitution;
import src.helpers.ForwardElimination;
import src.helpers.NormalizeMatrix;

public class Gauss {
    private NormalizeMatrix normalizeMatrix = new NormalizeMatrix();

    public Matrix gaussElimination(Integer m, Integer n, Matrix matrix) {
        
        AlignMatrix alignMatrix = new AlignMatrix();
        ForwardElimination forwardElimination = new ForwardElimination();
        alignMatrix.alignMatrix(matrix);

        forwardElimination.forwardElimination(m, n, matrix);

        normalizeMatrix.normalizeMatrix(m, n, matrix);

        return matrix;
    }

    public Array main(Tuple3<Integer, Integer, Matrix> data) {
        BackwardSubstitution backwardSubstitution = new BackwardSubstitution();
        int m = data.getItem1();
        int n = data.getItem2();
        Matrix result = gaussElimination(m, n, data.getItem3());

        if (result.getRowCount() == 1 && result.getColumnCount() == 1) {
            Array flag = new Array(1); 
            flag.set(0, result.get(0, 0));
            return flag;
        } else {
            Array solution = backwardSubstitution.backwardSubstitution(result);
            return solution;
        }
    }

}


