package org.example.sortingapplication.model;

import java.util.List;

public class ShellSort {
    public void sort(List<Double> data) {
        int n = data.size();
        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                double temp = data.get(i);
                int j;
                for (j = i; j >= gap && data.get(j - gap) > temp; j -= gap) {
                    data.set(j, data.get(j - gap));
                }
                data.set(j, temp);
            }
        }
    }
}
