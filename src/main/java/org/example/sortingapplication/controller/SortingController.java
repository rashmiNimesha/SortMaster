package org.example.sortingapplication.controller;

import org.example.sortingapplication.model.*;

import java.util.List;

public class SortingController {
    public long runQuickSort(List<Double> data) {
        long startTime = System.nanoTime();
        QuickSort quickSort = new QuickSort();
        quickSort.sort(data);
        long endTime = System.nanoTime();
        return (endTime - startTime) ;
    }

    public long runMergeSort(List<Double> data) {
        long startTime = System.nanoTime();
        MergeSort mergeSort = new MergeSort();
        mergeSort.sort(data);
        long endTime = System.nanoTime();
        return (endTime - startTime);
    }

    public long runShellSort(List<Double> dataItem) {
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
