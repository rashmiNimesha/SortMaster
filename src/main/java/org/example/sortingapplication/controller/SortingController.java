package org.example.sortingapplication.controller;

import org.example.sortingapplication.model.*;

import java.util.List;

public class SortingController {
    public long runQuickSort(List<Double> data) {
        long startTime = System.nanoTime(); // Get start time
        QuickSort quickSort = new QuickSort();
        quickSort.sort(data);
        long endTime = System.nanoTime(); // Get end time
        return (endTime - startTime) ; // Calculate the time taken
    }

    public long runMergeSort(List<Double> data) {
        long startTime = System.nanoTime(); // Get start time
        MergeSort mergeSort = new MergeSort();
        mergeSort.sort(data);
        long endTime = System.nanoTime(); // Get end time
        return (endTime - startTime); // Calculate the time taken
    }

    public long runShellSort(List<Double> dataItem) {
        // Calculating the execution time by calling shellSort instance
        long startTime = System.nanoTime();
        ShellSort shellSort = new ShellSort();
        shellSort.sort(dataItem);
        long endTime = System.nanoTime();
        return (endTime - startTime);
    }

    public long runHeapSort(List<Double> data) {
        // Measure the execution time of Heap Sort in nanoseconds
        long startTime = System.nanoTime();
        HeapSort heapSort = new HeapSort();
        heapSort.sort(data);
        long endTime = System.nanoTime();
        return (endTime - startTime) ;
    }

    public long runInsertionSort(List<Double> data) {
        // Measure the execution time of Insertion Sort in nanoseconds.
        long startTime = System.nanoTime();
        InsertionSort insertionSort = new InsertionSort();
        insertionSort.sort(data);
        long endTime = System.nanoTime();
        return (endTime - startTime) ;
    }

}
