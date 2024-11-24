package org.example.sortingapplication.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InsertionSortTest {

    @Test
    public void testSortWithUnsortedList() {
        // Test case to verify that an unsorted list gets sorted in ascending order
        InsertionSort insertionSort = new InsertionSort();
        List<Double> data = new ArrayList<>(Arrays.asList(64.5, 34.2, 25.0, 12.22, 22.1, 11.0, 90.3));
        List<Double> expected = new ArrayList<>(Arrays.asList(11.0, 12.22, 22.1, 25.0, 34.2, 64.5, 90.3));

        insertionSort.sort(data);
        // Assert that the list is sorted in ascending order
        assertEquals(expected, data, "The list should be sorted in ascending order.");
    }

    // Test case to check if an already sorted list stays unchanged after sorting
    @Test
    public void testSortWithAlreadySortedList() {
        InsertionSort insertionSort = new InsertionSort();
        List<Double> data = new ArrayList<>(Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0));
        List<Double> expected = new ArrayList<>(Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0));

        insertionSort.sort(data);
        // Assert that the list remains unchanged if it was already sorted
        assertEquals(expected, data, "The list should remain unchanged if already sorted.");
    }

    // Test case to ensure that duplicates are handled correctly while sorting
    @Test
    public void testSortWithDuplicateElements() {
        InsertionSort insertionSort = new InsertionSort();
        List<Double> data = new ArrayList<>(Arrays.asList(5.0, 3.0, 5.0, 3.0, 5.0));
        List<Double> expected = new ArrayList<>(Arrays.asList(3.0, 3.0, 5.0, 5.0, 5.0));

        insertionSort.sort(data);
        // Assert that duplicates are retained and the list is sorted correctly
        assertEquals(expected, data, "The list should be sorted and duplicates retained.");
    }

    // Test case to check if a list with a single element is unaffected by sorting
    @Test
    public void testSortWithSingleElement() {
        InsertionSort insertionSort = new InsertionSort();
        List<Double> data = new ArrayList<>(Arrays.asList(42.0));
        List<Double> expected = new ArrayList<>(Arrays.asList(42.0));

        insertionSort.sort(data);
        // Assert that a single-element list stays the same after sorting
        assertEquals(expected, data, "A single-element list should remain unchanged.");
    }

    // Test case to check that an empty list remains unchanged after sorting
    @Test
    public void testSortWithEmptyList() {
        InsertionSort insertionSort = new InsertionSort();
        List<Double> data = new ArrayList<>();
        List<Double> expected = new ArrayList<>();

        insertionSort.sort(data);
        // Assert that an empty list remains unchanged
        assertEquals(expected, data, "An empty list should remain unchanged.");
    }
}
