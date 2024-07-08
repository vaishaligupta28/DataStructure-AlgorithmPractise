package org.example.sorting;

import java.util.Arrays;

public class Sorting {

    public static void main(String[] args) {
        Sorting sorting = new Sorting();


        int[] array = {9, 7, 8, 6, 4, 5, 2, 3};
        int[] sortedArray = sorting.selectionSort(array);
        System.out.println("SortedArray = " + Arrays.toString(sortedArray));

        int[] sortedArray1 = sorting.bubbleSort(array);
        System.out.println("SortedArray = " + Arrays.toString(sortedArray1));

        int[] sortedArray2 = sorting.insertionSort(array);
        System.out.println("SortedArray = " + Arrays.toString(sortedArray2));

    }

    private int[] selectionSort(int[] array) {
        System.out.println("Sort via Selection Sort="+ Arrays.toString(array));
        return null;
    }

    private int[] bubbleSort(int[] array) {
        System.out.println("Sort via Bubble Sort="+ Arrays.toString(array));
        return null;
    }

    private int[] insertionSort(int[] array) {
        System.out.println("Sort via Insertion Sort="+ Arrays.toString(array));
        return null;
    }
}
