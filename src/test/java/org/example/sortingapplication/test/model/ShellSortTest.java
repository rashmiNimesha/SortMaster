package org.example.sortingapplication.test.model;

import org.example.sortingapplication.model.ShellSort;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class ShellSortTest {


    @Test
    void testSortEmptyList() {
        ShellSort shellSort = new ShellSort();
        List<Double> data = new ArrayList<>();
        long executionTime = shellSort.sort(data);
        assertTrue(executionTime >= 0);
        assertEquals(0, data.size());
    }

    @Test
    void testSortSingleElementList() {
        ShellSort shellSort = new ShellSort();
        List<Double> data = new ArrayList<>(List.of(5.0));
        long executionTime = shellSort.sort(data);
        assertTrue(executionTime >= 0);
        assertEquals(List.of(5.0), data);
    }

    @Test
    void testSortAlreadySortedList() {
        ShellSort shellSort = new ShellSort();
        List<Double> data = new ArrayList<>(List.of(1.0, 2.0, 3.0, 4.0, 5.0));
        long executionTime = shellSort.sort(data);
        assertTrue(executionTime >= 0);
        assertEquals(List.of(1.0, 2.0, 3.0, 4.0, 5.0), data);
    }

    @Test
    void testSortUnsortedList() {
        ShellSort shellSort = new ShellSort();
        List<Double> data = new ArrayList<>(List.of(3.0, 1.0, 4.0, 1.5, 2.0));
        long executionTime = shellSort.sort(data);
        assertTrue(executionTime >= 0);
        assertEquals(List.of(1.0, 1.5, 2.0, 3.0, 4.0), data);
    }

    @Test
    void testSortLargeList() {
        ShellSort shellSort = new ShellSort();
        List<Double> data = new ArrayList<>();
        for (int i = 100; i >= 1; i--) {
            data.add((double) i);
        }
        long executionTime = shellSort.sort(data);
        assertTrue(executionTime >= 0);
        for (int i = 0; i < data.size(); i++) {
            assertEquals((double) (i + 1), data.get(i));
        }
    }
}