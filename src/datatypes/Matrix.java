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
                row.add(0.0); 
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
        if (!data.isEmpty()) {
            return data.get(0).size();
        }
        return 0;
    }

    public Double get(int row, int column) {
        return data.get(row).get(column);
    }

    public void set(int row, int column, Double value) {
        data.get(row).set(column, value);
    }
}
