package datatypes;

import java.util.ArrayList;
import java.util.List;

public class Array {
    private List<Integer> data;

    // Constructor that takes the size of the array
    public Array(int size) {
        data = new ArrayList<>(size);
        // Initialize the list with default values (e.g., 0)
        for (int i = 0; i < size; i++) {
            data.add(0); // or any default value you want
        }
    }

    // Existing constructor for initializing with a predefined list
    public Array(List<Integer> data) {
        this.data = data;
    }

    public List<Integer> getData() {
        return data;
    }

    public void setData(List<Integer> data) {
        this.data = data;
    }

    public int getSize() {
        return data.size();
    }

    public Integer get(int index) {
        return data.get(index);
    }

    public void set(int index, Integer value) {
        data.set(index, value);
    }
}
