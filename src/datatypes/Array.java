package src.datatypes;

import java.util.ArrayList;
import java.util.List;

public class Array {
    private List<Integer> data;

    public Array(int size) {
        data = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            data.add(0); 
        }
    }

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
