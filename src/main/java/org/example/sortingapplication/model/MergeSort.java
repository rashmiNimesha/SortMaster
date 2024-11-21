package org.example.sortingapplication.model;

import java.util.List;

public class MergeSort {
    public void sort(List<Double> list) {
        if (list == null || list.size() < 2) {
            return;
        }
        mergeSort(list, 0, list.size() - 1);
    }

    private void mergeSort(List<Double> list, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(list, left, mid);
            mergeSort(list, mid + 1, right);
            merge(list, left, mid, right);
        }
    }

    private void merge(List<Double> list, int left, int mid, int right) {
        int leftSize = mid - left + 1;
        int rightSize = right - mid;

        Double[] leftArray = new Double[leftSize];
        Double[] rightArray = new Double[rightSize];

        for (int i = 0; i < leftSize; i++) {
            leftArray[i] = list.get(left + i);
        }
        for (int i = 0; i < rightSize; i++) {
            rightArray[i] = list.get(mid + 1 + i);
        }

        int i = 0, j = 0, k = left;
        while (i < leftSize && j < rightSize) {
            if (leftArray[i] <= rightArray[j]) {
                list.set(k++, leftArray[i++]);
            } else {
                list.set(k++, rightArray[j++]);
            }
        }

        while (i < leftSize) {
            list.set(k++, leftArray[i++]);
        }
        while (j < rightSize) {
            list.set(k++, rightArray[j++]);
        }
    }
}
