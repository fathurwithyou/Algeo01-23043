package src.models.linearRegression;

import src.datatypes.Matrix;
import src.views.linearRegression.LinearRegressionView;

public class LinearRegression {
    private Matrix beta;
    private LinearRegressionView view;
    private String method;
    private double alpha;

    public LinearRegression() {
        this("OLS", 0.0);
    }

    public LinearRegression(String method, double alpha) {
        this.view = new LinearRegressionView();
        this.method = method;
        this.alpha = alpha;
    }

    public void fit(Matrix X, Matrix y) {
        if ("OLS".equalsIgnoreCase(method)) {
            fitOLS(X, y);
        } else if ("Ridge".equalsIgnoreCase(method)) {
            fitRidge(X, y);
        } else {
            throw new UnsupportedOperationException("Fitting method " + method + " is not implemented.");
        }
    }

    private void fitOLS(Matrix X, Matrix y) {
        Matrix X_transpose = X.transpose();
        Matrix X_transpose_X = X_transpose.multiply(X);
        Matrix X_transpose_X_inv = X_transpose_X.inverse();
        Matrix X_transpose_y = X_transpose.multiply(y);
        beta = X_transpose_X_inv.multiply(X_transpose_y);
    }

    private void fitRidge(Matrix X, Matrix y) {
        int n = X.getRowCount();
        Matrix I = Matrix.identity(n);
        Matrix X_transpose = X.transpose();
        Matrix X_transpose_X = X_transpose.multiply(X);
        Matrix regularizationTerm = I.multiplyConst(alpha);
        Matrix X_transpose_y = X_transpose.multiply(y);
        beta = (X_transpose_X.add(regularizationTerm)).inverse().multiply(X_transpose_y);
    }

    public Matrix predict(Matrix X) {
        view.printMatrix(beta);
        return X.multiply(beta);
    }

    public Matrix getBeta() {
        return beta;
    }
}
