package org.example.sortingapplication.model;

import org.example.sortingapplication.model.QuickSort;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuickSortTest {

    @Test
    void testSortAlreadySortedList() {
        QuickSort quickSort = new QuickSort();
        List<Double> data = new ArrayList<>(List.of(1.0, 2.0, 3.0, 4.0, 5.0));
        quickSort.sort(data);
        assertEquals(List.of(1.0, 2.0, 3.0, 4.0, 5.0), data);
    }

    @Test
    void testSortUnsortedList() {
        QuickSort quickSort = new QuickSort();
        List<Double> data = new ArrayList<>(List.of(3.0, 1.0, 4.0, 1.5, 2.0));
        quickSort.sort(data);
        assertEquals(List.of(1.0, 1.5, 2.0, 3.0, 4.0), data);
    }

    @Test
    void testSortLargeList() {
        QuickSort quickSort = new QuickSort();
        List<Double> data = new ArrayList<>();
        for (int i = 100; i >= 1; i--) {
            data.add((double) i);
        }
        quickSort.sort(data);
        for (int i = 0; i < data.size(); i++) {
            assertEquals((double) (i + 1), data.get(i));
        }
    }



}
