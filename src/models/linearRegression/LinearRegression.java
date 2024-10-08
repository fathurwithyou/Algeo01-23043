package src.models.linearRegression;

import src.datatypes.Matrix;
import src.views.linearRegression.LinearRegressionView;

public class LinearRegression {
    private Matrix beta;
    private LinearRegressionView view = new LinearRegressionView();

    public LinearRegression() {
    }

    public void fit(Matrix X, Matrix y) {
        Matrix X_transpose = X.transpose();
        Matrix X_transpose_X = X_transpose.multiply(X);
        Matrix X_transpose_X_inv = X_transpose_X.inverse();
        Matrix X_transpose_y = X_transpose.multiply(y);
        
        // beta = (X^T * X)^(-1) * X^T * y
        beta = X_transpose_X_inv.multiply(X_transpose_y);
    }

    public Matrix predict(Matrix X) {
        view.printMatrix(beta);
        return X.multiply(beta);
    }

    public Matrix getBeta() {
        return beta;
    }
}
