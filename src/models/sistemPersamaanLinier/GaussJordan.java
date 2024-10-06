package src.models.sistemPersamaanLinier;

import src.datatypes.Array;
import src.datatypes.Matrix;
import src.datatypes.Tuple3;
import src.helpers.AlignMatrix;
import src.helpers.BackwardElimination;
import src.helpers.ForwardElimination;
import src.helpers.NormalizeMatrix;

public class GaussJordan {
    private CheckConsistency checkConsistency = new CheckConsistency();
    private NormalizeMatrix normalizeMatrix = new NormalizeMatrix();

    //temp
    private static final double EPSILON = 1e-9;

        // //temp
        // private SistemPersamaanLinierView view = new SistemPersamaanLinierView();

    public Matrix gaussJordanElimination(Integer m, Integer n, Matrix matrix) {
        AlignMatrix alignMatrix = new AlignMatrix();
        ForwardElimination forwardElimination = new ForwardElimination();
        BackwardElimination backwardElimination = new BackwardElimination();
        alignMatrix.alignMatrix(matrix);
        int flag = checkConsistency.checkConsistency(m, n, matrix);
        if(flag != -1) {
            Matrix singular = new Matrix(1, 1);
            singular.set(0, 0, (double)flag);
            return singular;
        }

        // System.out.println("Aligned Matrix:");
        // view.printMatrix(matrix); //temp
        // System.out.println();

        forwardElimination.forwardElimination(m, n, matrix);
        flag = checkConsistency.checkConsistency(m, n, matrix);
        if(flag != -1) {
            Matrix singular = new Matrix(1, 1);
            singular.set(0, 0, (double)flag);
            return singular;
        }

        // System.out.println("For Eliminated Matrix:");
        // view.printMatrix(matrix); //temp
        // System.out.println();

        backwardElimination.backwardElimination(m, n, matrix);

        // System.out.println("Back Eliminated Matrix:");
        // view.printMatrix(matrix); //temp
        // System.out.println();

        normalizeMatrix.normalizeMatrix(m, n, matrix);

        // System.out.println("Normalized Matrix");
        // view.printMatrix(matrix); //temp
        // System.out.println();

        return matrix;
    }

    public Array main(Tuple3<Integer, Integer, Matrix> data) {
        int m = data.getItem1();
        int n = data.getItem2();
        Matrix rawResult = gaussJordanElimination(m, n, data.getItem3());

        if (rawResult.getRowCount() == 1 && rawResult.getColumnCount() == 1) {
            Array flag = new Array(1); 
            flag.set(0, rawResult.get(0, 0));
            return flag;

        } else {
            Array result = new Array(n-1);
            for (int i = 0; i < n-1; i++) {
                result.set(i, rawResult.get(i, n-1));
                if (Math.abs(result.get(i)) < EPSILON)
                    result.set(i, 0.0);
            }
            return result;
        }   
    }
}
