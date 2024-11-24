package org.example.sortingapplication.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SortingControllerTest {

    private SortingController sortingController;

    @BeforeEach
    void setUp() {
        sortingController = new SortingController();
    }

    @Test
    void runQuickSort_ValidData() {
        List<Double> data = Arrays.asList(5.0, 3.0, 8.0, 1.0);
        long elapsedTime = sortingController.runQuickSort(data);

        assertSorted(data);
        assertTrue(elapsedTime >= 0, "Elapsed time should be non-negative.");
    }

    @Test
    void runMergeSort_ValidData() {
        List<Double> data = Arrays.asList(10.5, -3.4, 7.2, 0.0, 4.3);
        long elapsedTime = sortingController.runMergeSort(data);

        assertSorted(data);
        assertTrue(elapsedTime >= 0, "Elapsed time should be non-negative.");
    }

    @Test
    void runShellSort_ValidData() {
        List<Double> data = Arrays.asList(2.2, 3.3, 1.1, 5.5, 4.4);
        long elapsedTime = sortingController.runShellSort(data);

        assertSorted(data);
        assertTrue(elapsedTime >= 0, "Elapsed time should be non-negative.");
    }

    @Test
    void runHeapSort_ValidData() {
        List<Double> data = Arrays.asList(9.0, 7.0, 5.0, 3.0, 1.0);
        long elapsedTime = sortingController.runHeapSort(data);

        assertSorted(data);
        assertTrue(elapsedTime >= 0, "Elapsed time should be non-negative.");
    }

    @Test
    void runInsertionSort_ValidData() {
        List<Double> data = Arrays.asList(4.4, 2.2, 8.8, 6.6, 1.1);
        long elapsedTime = sortingController.runInsertionSort(data);

        assertSorted(data);
        assertTrue(elapsedTime >= 0, "Elapsed time should be non-negative.");
    }


    private void assertSorted(List<Double> data) {
        for (int i = 1; i < data.size(); i++) {
            assertTrue(data.get(i - 1) <= data.get(i), "List is not sorted at index " + i);
        }
    }

}