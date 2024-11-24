package org.example.sortingapplication.model;

import java.util.List;

public class InsertionSort {
    public long sort(List<Double> data) {
        int n = data.size();

        // Iterate over the list starting from the second element
        for (int i = 1; i < n; i++) {
            double key = data.get(i);
            int j = i - 1;

            // Move elements of the sorted portion that are greater than the key one position ahead
            while (j >= 0 && data.get(j) > key) {
                data.set(j + 1, data.get(j));
                j--;
            }

            // Place the key in its correct position in the sorted portion
            data.set(j + 1, key);
        }
        return 0;
    }
}
