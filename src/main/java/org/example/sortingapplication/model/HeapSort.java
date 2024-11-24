package org.example.sortingapplication.model;

import java.util.List;

public class HeapSort {
    public long sort(List<Double> data) {
        int n = data.size();

        // Build a max heap by rearranging the elements
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(data, n, i);
        }

        for (int i = n - 1; i > 0; i--) {
            // Swap the root of the heap (largest element) with the last element
            double temp = data.get(0);
            data.set(0, data.get(i));
            data.set(i, temp);

            // Rebuild the heap for the remaining unsorted elements
            heapify(data, i, 0);
        }
        return 0;
    }

    // Maintains the heap property for a subtree rooted at index i
    private void heapify(List<Double> data, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && data.get(left) > data.get(largest)) {
            largest = left;
        }

        if (right < n && data.get(right) > data.get(largest)) {
            largest = right;
        }

        if (largest != i) {
            double swap = data.get(i);
            data.set(i, data.get(largest));
            data.set(largest, swap);

            heapify(data, n, largest);
        }
    }
}
