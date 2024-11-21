package org.example.sortingapplication.model;

import java.util.List;

public class InsertionSort {
    public long sort(List<Double> data) {
        long startTime = System.nanoTime();

        for (int i = 1; i < data.size(); i++) {
            double key = data.get(i);
            int j = i - 1;

            while (j >= 0 && data.get(j) > key) {
                data.set(j + 1, data.get(j));
                j = j - 1;
            }
            data.set(j + 1, key);
        }

        long endTime = System.nanoTime();
        return (endTime - startTime) / 1_000_000;
    }
}
