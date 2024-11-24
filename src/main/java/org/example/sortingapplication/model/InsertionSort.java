package org.example.sortingapplication.model;

import java.util.List;

public class InsertionSort {
    public long sort(List<Double> data) {
        int n = data.size();

        for (int i = 1; i < n; i++) {
            double key = data.get(i);
            int j = i - 1;

            while (j >= 0 && data.get(j) > key) {
                data.set(j + 1, data.get(j));
                j--;
            }

            data.set(j + 1, key);
        }
        return 0;
    }
}
