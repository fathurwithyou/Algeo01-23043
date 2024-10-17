package src.datatypes;

import java.util.ArrayList;
import java.util.List;

public class Array {
    private List<Double> data;

    public Array(int size) {
        data = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            data.add(0.0); 
        }
    }

    public Array(List<Double> data) {
        this.data = data;
    }

    public List<Double> getData() {
        return data;
    }

    public void setData(List<Double> data) {
        this.data = data;
    }

    public int getSize() {
        return data.size();
    }

    public Double get(int index) {
        return data.get(index);
    }

    public void set(int index, Double value) {
        data.set(index, value);
    }

    public void print() {
        for (Double value : data) {
            System.out.print(value + " ");
        }
        System.out.println();
    }
}
