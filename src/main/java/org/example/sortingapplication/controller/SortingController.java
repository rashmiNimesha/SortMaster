package org.example.sortingapplication.controller;

import org.example.sortingapplication.model.HeapSort;
import org.example.sortingapplication.model.MergeSort;
import org.example.sortingapplication.model.QuickSort;
import org.example.sortingapplication.model.ShellSort;

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
        long startTime = System.nanoTime();
        HeapSort heapSort = new HeapSort();
        heapSort.sort(data);
        long endTime = System.nanoTime();
        return (endTime - startTime) ;
    }

}
