package src.models.matriksBalikan;

import src.datatypes.Matrix;
import src.models.determinan.EkspansiKofaktor;

public class AdjoinMethod {
    public Matrix adjoinMethod(Matrix matrix){
        double determinant = 0;
        determinant = new EkspansiKofaktor().main(matrix);
        if (determinant == 0) {
            return null;
        }
        Matrix adjoin = new Matrix(matrix.getRowCount(), matrix.getColumnCount());
        for (int i = 0; i < matrix.getRowCount(); i++) {
            for (int j = 0; j < matrix.getColumnCount(); j++) {
                adjoin.set(i, j, Math.pow(-1, i + j) * new EkspansiKofaktor().main(matrix.minor(i, j)) / determinant);
            }   
        }
        adjoin = adjoin.transpose();
        return adjoin;
    }

    public Matrix main(Matrix matrix){
        matrix = adjoinMethod(matrix);  
        return matrix;
    }
}
