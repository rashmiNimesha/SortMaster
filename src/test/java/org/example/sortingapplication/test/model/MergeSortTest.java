package org.example.sortingapplication.test.model;

import org.example.sortingapplication.model.MergeSort;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MergeSortTest {

    @Test
    void testSortEmptyList() {
        MergeSort mergeSort = new MergeSort();
        List<Double> data = new ArrayList<>();
        mergeSort.sort(data);
        assertEquals(0, data.size());
    }

    @Test
    void testSortSingleElementList() {
        MergeSort mergeSort = new MergeSort();
        List<Double> data = new ArrayList<>(List.of(5.0));
        mergeSort.sort(data);
        assertEquals(List.of(5.0), data);
    }

    @Test
    void testSortAlreadySortedList() {
        MergeSort mergeSort = new MergeSort();
        List<Double> data = new ArrayList<>(List.of(1.0, 2.0, 3.0, 4.0, 5.0));
        mergeSort.sort(data);
        assertEquals(List.of(1.0, 2.0, 3.0, 4.0, 5.0), data);
    }

    @Test
    void testSortUnsortedList() {
        MergeSort mergeSort = new MergeSort();
        List<Double> data = new ArrayList<>(List.of(3.0, 1.0, 4.0, 1.5, 2.0));
        mergeSort.sort(data);
        assertEquals(List.of(1.0, 1.5, 2.0, 3.0, 4.0), data);
    }

    @Test
    void testSortLargeList() {
        MergeSort mergeSort = new MergeSort();
        List<Double> data = new ArrayList<>();
        for (int i = 100; i >= 1; i--) {
            data.add((double) i);
        }
        mergeSort.sort(data);
        for (int i = 0; i < data.size(); i++) {
            assertEquals((double) (i + 1), data.get(i));
        }
    }
}