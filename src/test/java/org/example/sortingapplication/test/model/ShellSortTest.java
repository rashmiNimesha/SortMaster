package org.example.sortingapplication.test.model;

import org.example.sortingapplication.model.ShellSort;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ShellSortTest {

    @Test
    public void testSortWithUnsortedList() {
        ShellSort shellSort = new ShellSort();
        List<Double> data = new ArrayList<>(Arrays.asList(64.5, 34.2, 25.0, 12.22, 22.1, 11.0, 90.3));
        List<Double> expected = new ArrayList<>(Arrays.asList(11.0, 12.22, 22.1, 25.0, 34.2, 64.5, 90.3));

        shellSort.sort(data);
        assertEquals(expected, data, "The list should be sorted in ascending order.");
    }

    @Test
    public void testSortWithAlreadySortedList() {
        ShellSort shellSort = new ShellSort();
        List<Double> data = new ArrayList<>(Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0));
        List<Double> expected = new ArrayList<>(Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0));

        shellSort.sort(data);
        assertEquals(expected, data, "The list should remain unchanged if already sorted.");
    }

    @Test
    public void testSortWithDuplicateElements() {
        ShellSort shellSort = new ShellSort();
        List<Double> data = new ArrayList<>(Arrays.asList(5.0, 3.0, 5.0, 3.0, 5.0));
        List<Double> expected = new ArrayList<>(Arrays.asList(3.0, 3.0, 5.0, 5.0, 5.0));

        shellSort.sort(data);
        assertEquals(expected, data, "The list should be sorted and duplicates retained.");
    }
}