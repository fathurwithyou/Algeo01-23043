package src.helpers;

import src.datatypes.Matrix;

public class MakeAugmented {
    public Matrix main(Matrix X, Matrix y) {
        Matrix X_augmented = new Matrix(X.getRowCount(), X.getColumnCount() + 1);
        for (int i = 0; i < X.getRowCount(); i++) {
            for (int j = 0; j < X.getColumnCount(); j++) {
                X_augmented.set(i, j, X.get(i, j));
            }
            X_augmented.set(i, X.getColumnCount(), 1.0);
        }
        return X_augmented;
    }   
}
