package org.example.sortingapplication.model;

import java.util.List;

public class QuickSort {
    public void sort(List<Double> list) {
        quickSort(list, 0, list.size() - 1);
    }

    private void quickSort(List<Double> list, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(list, low, high);
            quickSort(list, low, pivotIndex - 1);
            quickSort(list, pivotIndex + 1, high);
        }
    }

    private int partition(List<Double> list, int low, int high) {
        Double pivot = list.get(high);
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (list.get(j) <= pivot) {
                i++;
                Double temp = list.get(i);
                list.set(i, list.get(j));
                list.set(j, temp);
            }
        }
        Double temp = list.get(i + 1);
        list.set(i + 1, list.get(high));
        list.set(high, temp);
        return i + 1;
    }
}
