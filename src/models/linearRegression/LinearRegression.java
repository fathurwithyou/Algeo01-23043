package src.models.linearRegression;

import src.datatypes.Matrix;

public class LinearRegression {
    private Matrix beta;
    private String method;
    private double alpha;
    private boolean fitIntercept;

    public LinearRegression() {
        this("OLS", 0.0, true);
    }

    public LinearRegression(String method) {
        this(method, 0.0, true);
    }

    public LinearRegression(String method, double alpha) {
        this(method, alpha, true);
    }

    public LinearRegression(String method, double alpha, boolean fitIntercept) {
        this.method = method;
        this.alpha = alpha;
        this.fitIntercept = fitIntercept;
    }

    public void fit(Matrix X, Matrix y) {
        if (fitIntercept) {
            X = addInterceptColumn(X);
        }

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
        int n = X.getColumnCount();
        Matrix I = Matrix.identity(n);
        Matrix X_transpose = X.transpose();
        Matrix X_transpose_X = X_transpose.multiply(X);
        Matrix regularizationTerm = I.multiplyConst(alpha);
        Matrix X_transpose_y = X_transpose.multiply(y);
        beta = (X_transpose_X.add(regularizationTerm)).inverse().multiply(X_transpose_y);
    }

    public Matrix predict(Matrix X) {
        if (fitIntercept) {
            X = addInterceptColumn(X);
        }
        return X.multiply(beta);
    }

    public Matrix getBeta() {
        return beta;
    }

    private Matrix addInterceptColumn(Matrix X) {
        int m = X.getRowCount();
        int n = X.getColumnCount();
        Matrix X_augmented = new Matrix(m, n + 1);
        for (int i = 0; i < m; i++) {
            X_augmented.set(i, 0, 1.0);
            for (int j = 0; j < n; j++) {
                X_augmented.set(i, j + 1, X.get(i, j));
            }
        }
        return X_augmented;
    }
}
