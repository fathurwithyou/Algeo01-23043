package datatypes;

import java.util.ArrayList;
import java.util.List;

public class Matrix {
    private List<List<Integer>> data;

    // Constructor that takes number of rows and columns
    public Matrix(int rows, int cols) {
        data = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < cols; j++) {
                row.add(0); // Initialize with default value, e.g., 0
            }
            data.add(row);
        }
    }

    // Existing constructor for initializing with a predefined list
    public Matrix(List<List<Integer>> data) {
        this.data = data;
    }

    public List<List<Integer>> getData() {
        return data;
    }

    public void setData(List<List<Integer>> data) {
        this.data = data;
    }

    public int getRowCount() {
        return data.size();
    }

    public int getColumnCount() {
        if (!data.isEmpty()) {
            return data.get(0).size();
        }
        return 0; // Return 0 if there are no rows
    }

    public Integer get(int row, int column) {
        return data.get(row).get(column);
    }

    public void set(int row, int column, Integer value) {
        data.get(row).set(column, value);
    }
}
