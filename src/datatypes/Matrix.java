package src.datatypes;

import java.util.ArrayList;
import java.util.List;

public class Matrix {
    private List<List<Double>> data;

    public Matrix(int rows, int cols) {
        data = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            List<Double> row = new ArrayList<>();
            for (int j = 0; j < cols; j++) {
                row.add(Double.NaN);
            }
            data.add(row);
        }
    }

    public Matrix(List<List<Double>> data) {
        this.data = data;
    }

    public List<List<Double>> getData() {
        return data;
    }

    public void setData(List<List<Double>> data) {
        this.data = data;
    }

    public int getRowCount() {
        return data.size();
    }

    public int getColumnCount() {
        return data.isEmpty() ? 0 : data.get(0).size();
    }

    public Array getRow(int row) {
        return new Array(data.get(row));
    }

    public void print() {
        for (int i = 0; i < getRowCount(); i++) {
            for (int j = 0; j < getColumnCount(); j++) {
                System.out.print(this.get(i, j) + " ");
            }
            System.out.println();
        }
    }

    public Array flatten() {
        int rows = this.getRowCount();
        int cols = this.getColumnCount();

        // Create an Array ADT to hold the flattened data
        Array flatten = new Array(rows * cols);

        // Flatten the matrix
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                flatten.set(i * cols + j, this.get(i, j)); // Set each element in the flattened array
            }
        }

        return flatten; // Return the flattened Array ADT
    }

    public Double get(int row, int column) {
        return data.get(row).get(column);
    }

    public void set(int row, int column, Double value) {
        data.get(row).set(column, value);
    }

    public Matrix minor(int rowToRemove, int colToRemove) {
        Matrix minorMatrix = new Matrix(getRowCount() - 1, getColumnCount() - 1);
        int minorRow = 0;
        for (int i = 0; i < getRowCount(); i++) {
            if (i == rowToRemove)
                continue; // Skip the row
            int minorCol = 0;
            for (int j = 0; j < getColumnCount(); j++) {
                if (j == colToRemove)
                    continue; // Skip the column
                minorMatrix.set(minorRow, minorCol, this.get(i, j));
                minorCol++;
            }
            minorRow++;
        }
        return minorMatrix;
    }

    public Matrix transpose() {
        Matrix transposedMatrix = new Matrix(getColumnCount(), getRowCount());
        for (int i = 0; i < getRowCount(); i++) {
            for (int j = 0; j < getColumnCount(); j++) {
                transposedMatrix.set(j, i, this.get(i, j));
            }
        }
        return transposedMatrix;
    }

    public Matrix inverse() {
        int n = getRowCount();
        if (n != getColumnCount()) {
            throw new IllegalArgumentException("Matrix must be square.");
        }

        Matrix augmentedMatrix = augmentWithIdentity();
        augmentedMatrix.gaussianElimination();

        Matrix inverseMatrix = new Matrix(n, n);
        for (int i = 0; i < n; i++) {
            for (int j = n; j < 2 * n; j++) {
                inverseMatrix.set(i, j - n, augmentedMatrix.get(i, j));
            }
        }
        return inverseMatrix;
    }

    private Matrix augmentWithIdentity() {
        int n = getRowCount();
        Matrix augmentedMatrix = new Matrix(n, 2 * n); // Augmented matrix will have 2 * n columns
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                augmentedMatrix.set(i, j, this.get(i, j)); // Copy original matrix
                augmentedMatrix.set(i, j + n, i == j ? 1.0 : 0.0); // Add identity matrix
            }
        }
        return augmentedMatrix;
    }

    // Perform Gaussian elimination
    private void gaussianElimination() {
        int n = getRowCount();
        for (int i = 0; i < n; i++) {
            double pivot = data.get(i).get(i);
            if (pivot == 0) {
                throw new ArithmeticException("Matrix is singular.");
            }
            // Normalize pivot row
            for (int j = 0; j < 2 * n; j++) {
                data.get(i).set(j, data.get(i).get(j) / pivot);
            }

            // Eliminate other rows
            for (int k = 0; k < n; k++) {
                if (k != i) {
                    double factor = data.get(k).get(i);
                    for (int j = 0; j < 2 * n; j++) {
                        data.get(k).set(j, data.get(k).get(j) - factor * data.get(i).get(j));
                    }
                }
            }
        }
    }

    // Multiply this matrix by another matrix
    public Matrix multiply(Matrix otherMatrix) {
        if (this.getColumnCount() != otherMatrix.getRowCount()) {
            throw new IllegalArgumentException("Row and Column not match");
        }

        Matrix resultMatrix = new Matrix(this.getRowCount(), otherMatrix.getColumnCount());

        for (int i = 0; i < this.getRowCount(); i++) {
            for (int j = 0; j < otherMatrix.getColumnCount(); j++) {
                double sum = 0.0;
                for (int k = 0; k < this.getColumnCount(); k++) {
                    sum += this.get(i, k) * otherMatrix.get(k, j);
                }
                resultMatrix.set(i, j, sum);
            }
        }
        return resultMatrix;
    }
}
