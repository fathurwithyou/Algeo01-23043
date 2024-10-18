package src.models.interpolasiPolinom;

import src.datatypes.Array;
import src.datatypes.Matrix;
import src.datatypes.Tuple3;
import src.models.sistemPersamaanLinier.Gauss;

public class InterpolasiPolinom {
    private Gauss gauss = new Gauss();
    private Array pers;

    public Tuple3<Integer, Integer, Matrix> persamaanLanjar(Integer n, Integer m, Matrix X, Matrix Y) {

        Matrix aug = new Matrix(n-1, n);
        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < n-1; j++) {
                Double x  = X.get(i,0);
                aug.set(i, j, Math.pow(x,j));
                }
            }
        for (int k = 0; k < n-1; k++) {
            Double y = Y.get(k,0);
            aug.set(k, n-1, y);        
        }
        return new Tuple3<>(n-1, n, aug);
    }

    public Array interpolasiPolinom(Integer n, Integer m, Matrix X, Matrix Y) {
        Tuple3<Integer, Integer, Matrix> aug = persamaanLanjar(n, m, X, Y);
        pers = gauss.main(aug.getItem3());
        return pers; //coef of linear equation (a0, a1x, a2x^2 dst)
    }

    public void fit(Matrix X, Matrix y) {
        int n = X.getRowCount();
        int m = 1;
        pers = interpolasiPolinom(n, m, X, y);
    }

    public Double predict(Double x) {
        Double y = 0.00;
        for (int i = 0; i < pers.getSize(); i++) {
            y += pers.get(i)*Math.pow(x, i);
        }
        return y;
    }
}
