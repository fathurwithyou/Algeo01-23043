package src.models.determinan;

import src.datatypes.Matrix;

public class EkspansiKofaktor {
    public double result = 0.0;

    public double ekspansiKofaktor(Matrix matrix) {
        if (matrix.getRowCount() == 1 && matrix.getColumnCount() == 1) {
            return matrix.get(0, 0);
        }

        double result = 0.0;
        for (int i = 0; i < matrix.getColumnCount(); i++) {
            if (i % 2 == 0) {
                result += matrix.get(0, i) * ekspansiKofaktor(matrix.minor(0, i));
            } else {
                result -= matrix.get(0, i) * ekspansiKofaktor(matrix.minor(0, i));
            }
        }
        return result;
    }

    public double main(Matrix matrix){
        return ekspansiKofaktor(matrix);
    }
}
