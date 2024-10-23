package src.models.interpolasiPolinom;

import src.datatypes.Array;
import src.datatypes.Matrix;
import src.datatypes.Tuple3;
import src.models.sistemPersamaanLinier.Gauss;

public class InterpolasiPolinom {
    private Gauss gauss = new Gauss();
    private Array pers;

    public Tuple3<Integer, Integer, Matrix> persamaanLanjar(Integer n, Integer m, Matrix X, Matrix Y) {
        Matrix aug = new Matrix(n, n + 1);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n + 1; j++) {
                Double x = X.get(i, 0);
                aug.set(i, j, Math.pow(x, j));
            }
            aug.set(i, n, Y.get(i, 0));
        }

        return new Tuple3<>(n, n, aug);
    }

    public Array interpolasiPolinom(Integer n, Integer m, Matrix X, Matrix Y) {
        Tuple3<Integer, Integer, Matrix> aug = persamaanLanjar(n, m, X, Y);

        pers = gauss.main(aug.getItem3());
        return pers; 
    }

    public void fit(Matrix X, Matrix y) {
        int n = X.getRowCount();
        int m = 1;
        pers = interpolasiPolinom(n, m, X, y);
    }

    public Array getPers() {
        return pers;
    }

    public Matrix predict(Matrix X_test) {
        Matrix y_pred = new Matrix(X_test.getRowCount(), 1);
        for (int i = 0; i < X_test.getRowCount(); i++) {
            Double y = 0.0;
            for (int j = 0; j < pers.getSize(); j++) {
                y += pers.get(j) * Math.pow(X_test.get(i, 0), j);
            }
            y_pred.set(i, 0, y);
        }
        return y_pred;
    }
}
