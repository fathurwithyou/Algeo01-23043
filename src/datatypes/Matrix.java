package src.datatypes;

import java.util.ArrayList;
import java.util.List;

import src.models.matriksBalikan.AdjoinMethod;

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

        Array flatten = new Array(rows * cols);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                flatten.set(i * cols + j, this.get(i, j)); 
            }
        }

        return flatten; 
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
                continue; 
            int minorCol = 0;
            for (int j = 0; j < getColumnCount(); j++) {
                if (j == colToRemove)
                    continue; 
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
        return new AdjoinMethod().main(this);
    }

    public Matrix multiply(Matrix other) {
        int rows = this.getRowCount();
        int cols = other.getColumnCount();
        Matrix result = new Matrix(rows, cols);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                double sum = 0;
                for (int k = 0; k < this.getColumnCount(); k++) {
                    sum += this.get(i, k) * other.get(k, j);
                }
                result.set(i, j, sum);
            }
        }
        return result;
    }
}
