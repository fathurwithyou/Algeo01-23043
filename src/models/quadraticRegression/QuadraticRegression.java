package src.models.quadraticRegression;

import src.datatypes.Matrix;
import src.views.regression.RegressionView;

public class QuadraticRegression {
    private Matrix beta;
    private RegressionView view = new RegressionView();
    private String method;
    private double alpha;

    public QuadraticRegression(String method, double alpha) {
        this.view = new RegressionView();
        this.method = method;
        this.alpha = alpha;
    }

    private Matrix processX(Matrix X) {
        int rows = X.getRowCount();
        int cols = X.getColumnCount();
        int newCols = cols * (cols + 1) / 2 + 1; 
        Matrix X_quadratic = new Matrix(rows, newCols);
        int index = 0;

        for (int i = 0; i < rows; i++) {
            X_quadratic.set(i, index++, 1.0); // Bias
        }

        for (int j = 0; j < cols; j++) {
            // Original
            for (int i = 0; i < rows; i++) {
                if (index < newCols) {
                    X_quadratic.set(i, index++, X.get(i, j));
                }
            }

            // Squared
            for (int i = 0; i < rows; i++) {
                if (index < newCols) { 
                    X_quadratic.set(i, index++, Math.pow(X.get(i, j), 2));
                }
            }

            // Interaction
            for (int k = j + 1; k < cols; k++) {
                for (int i = 0; i < rows; i++) {
                    if (index < newCols) { 
                        X_quadratic.set(i, index++, X.get(i, j) * X.get(i, k));
                    }
                }
            }
        }

        return X_quadratic;
    }

    public void fit(Matrix X, Matrix y) {
        Matrix X_quadratic = processX(X);
        
        int newCols = X_quadratic.getColumnCount(); 
        
        Matrix I = Matrix.identity(newCols); 
        
        Matrix X_transpose = X_quadratic.transpose();
        Matrix X_transpose_X = X_transpose.multiply(X_quadratic);
        Matrix regularizationTerm = I.multiplyConst(alpha);
        Matrix X_transpose_y = X_transpose.multiply(y);

        // beta = (X^T * X + lambda * I)^(-1) * X^T * y
        beta = (X_transpose_X.add(regularizationTerm)).inverse().multiply(X_transpose_y);
    }

    public Matrix predict(Matrix X) {
        Matrix X_quadratic = processX(X);
        return X_quadratic.multiply(beta);
    }

    public Matrix getBeta() {
        return beta;
    }
}
