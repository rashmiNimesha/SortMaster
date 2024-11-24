package org.example.sortingapplication.model;

import java.util.List;

// Implements QuickSort to sort lists of numbers
// Picks the last number as the reference point for sorting
public class QuickSort {
    // Main method to start the sorting process
    public void sort(List<Double> list) {
        quickSort(list, 0, list.size() - 1);
    }

    // Sorts the list by breaking it into smaller parts (sub-lists)
    // and sorting each part separately
    private void quickSort(List<Double> list, int low, int high) {
        if (low < high) {
            // Split the list and get the split position
            int pivotIndex = partition(list, low, high);

            // Sort both halves of the split list
            quickSort(list, low, pivotIndex - 1);   // Sort left half
            quickSort(list, pivotIndex + 1, high);  // Sort right half
        }
    }

    // Splits the list into two parts:
    // - Numbers smaller than chosen number (pivot) go to the left
    // - Numbers larger than chosen number go to the right
    private int partition(List<Double> list, int low, int high) {
        // Pick the last number as reference point
        Double pivot = list.get(high);

        // Keep track of next small number position
        int i = low - 1;

        // Look at each number in range
        for (int j = low; j < high; j++) {
            // If number is smaller than pivot
            if (list.get(j) <= pivot) {
                i++;  // Move marker up

                // Swap the small number to the left side
                Double temp = list.get(i);
                list.set(i, list.get(j));
                list.set(j, temp);
            }
        }

        // Put reference number in its final spot
        Double temp = list.get(i + 1);
        list.set(i + 1, list.get(high));
        list.set(high, temp);

        // Tell the caller where to put the reference number
        return i + 1;
    }
}