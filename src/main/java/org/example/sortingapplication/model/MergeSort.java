package org.example.sortingapplication.model;

import java.util.List;

// Implements MergeSort to sort lists of numbers
// Works by splitting the list into smaller parts, sorting them, and combining them back together
public class MergeSort {
    // Main method that starts the sorting process
    public void sort(List<Double> list) {
        // If the list is empty or has just one number, it's already sorted
        if (list == null || list.size() < 2) {
            return;
        }
        // Begin sorting the whole list
        mergeSort(list, 0, list.size() - 1);
    }

    // Splits the list into smaller parts and sorts them
    private void mergeSort(List<Double> list, int left, int right) {
        if (left < right) {
            // Find the middle point to split the list
            int mid = (left + right) / 2;

            // Sort the left half
            mergeSort(list, left, mid);
            // Sort the right half
            mergeSort(list, mid + 1, right);

            // Combine the sorted halves
            merge(list, left, mid, right);
        }
    }

    // Combines two sorted parts of the list into one sorted part
    private void merge(List<Double> list, int left, int mid, int right) {
        // Calculate how big each half is
        int leftSize = mid - left + 1;
        int rightSize = right - mid;

        // Create temporary arrays to hold each half
        Double[] leftArray = new Double[leftSize];
        Double[] rightArray = new Double[rightSize];

        // Copy numbers to the temporary arrays
        for (int i = 0; i < leftSize; i++) {
            leftArray[i] = list.get(left + i);
        }
        for (int i = 0; i < rightSize; i++) {
            rightArray[i] = list.get(mid + 1 + i);
        }

        // Set up positions to track
        int i = 0;      // Position in left array
        int j = 0;      // Position in right array
        int k = left;   // Position in main list

        // Pick the smaller number from each half and put it in the main list
        while (i < leftSize && j < rightSize) {
            if (leftArray[i] <= rightArray[j]) {
                list.set(k++, leftArray[i++]);
            } else {
                list.set(k++, rightArray[j++]);
            }
        }

        // If any numbers are left in the left array, add them
        while (i < leftSize) {
            list.set(k++, leftArray[i++]);
        }
        // If any numbers are left in the right array, add them
        while (j < rightSize) {
            list.set(k++, rightArray[j++]);
        }
    }
}