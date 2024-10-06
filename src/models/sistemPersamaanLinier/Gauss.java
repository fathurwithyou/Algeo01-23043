package src.models.sistemPersamaanLinier;

import src.datatypes.Array;
import src.datatypes.Matrix;
import src.datatypes.Tuple3;
import src.helpers.AlignMatrix;
import src.helpers.BackwardSubstitution;
import src.helpers.ForwardElimination;
import src.helpers.NormalizeMatrix;

public class Gauss {

    private CheckConsistency checkConsistency = new CheckConsistency();
    private NormalizeMatrix normalizeMatrix = new NormalizeMatrix();

    // //temp
    // private SistemPersamaanLinierView view = new SistemPersamaanLinierView();

    public Matrix gaussElimination(Integer m, Integer n, Matrix matrix) {
        
        AlignMatrix alignMatrix = new AlignMatrix();
        ForwardElimination forwardElimination = new ForwardElimination();
        alignMatrix.alignMatrix(matrix);

        // System.out.println("Aligned Matrix:");
        // view.printMatrix(matrix); //temp
        // System.out.println();

        forwardElimination.forwardElimination(m, n, matrix);

        // System.out.println("Eliminated Matrix:");
        // view.printMatrix(matrix); //temp
        // System.out.println();
        
        int flag = checkConsistency.checkConsistency(m, n, matrix);
        if (flag != -1) {
            Matrix singular = new Matrix(1,1);
            singular.set(0,0, (double)flag);
            return singular;
        }

        normalizeMatrix.normalizeMatrix(m, n, matrix);

        // System.out.println("Normalized Matrix");
        // view.printMatrix(matrix); //temp
        // System.out.println();

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


