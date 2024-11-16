package org.example.sortingapplication.model;

import java.util.List;

public class ShellSort {
    public long sort(List<Double> dataItem) {
        long startTime = System.nanoTime();

        int n = dataItem.size();
        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                double tempValue = dataItem.get(i);
                int j;
                for (j = i; j >= gap && dataItem.get(j - gap) > tempValue; j -= gap) {
                    dataItem.set(j, dataItem.get(j - gap));
                }
                dataItem.set(j, tempValue);
            }
        }

        long endTime = System.nanoTime();
        return (endTime - startTime) / 1_000_000;
    }
}
